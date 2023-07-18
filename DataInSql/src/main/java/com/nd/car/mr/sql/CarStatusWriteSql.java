package com.nd.car.mr.sql;

import com.nd.car.mr.entity.CarStatus;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

public class CarStatusWriteSql {
    public static class StatusWriteMapper extends Mapper<LongWritable, Text, IntWritable, CarStatus>{
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntWritable, CarStatus>.Context context) throws IOException, InterruptedException {
            String line = new String(value.getBytes(),0, value.getLength(),"UTF-8");
            String[] split = line.split("\t");
            int year = Integer.parseInt(split[0]);
            String[] nums = split[1].split("_");
            CarStatus v = new CarStatus(year,Integer.parseInt(nums[0]),Integer.parseInt(nums[1]),
                    Integer.parseInt(nums[2]),Integer.parseInt(nums[3]));
            context.write(new IntWritable(year),v);
        }
    }
    public static class StatusWriteReducer extends Reducer<IntWritable,CarStatus,CarStatus, NullWritable> {
        @Override
        protected void reduce(IntWritable key, Iterable<CarStatus> values, Reducer<IntWritable, CarStatus, CarStatus, NullWritable>.Context context) throws IOException, InterruptedException {
            CarStatus next = values.iterator().next();
            context.write(next,NullWritable.get());
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        args = new String[]{"data/output/statusCount/part-r-00000"};
        //获取配置对象
        Configuration conf = new Configuration();
        //连接mysql数据库
        DBConfiguration.configureDB(
                conf,
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/car?characterEncoding=utf-8",
                "root",
                "cmh123456"
        );
        //获取job对象
        Job job = Job.getInstance(conf);
        //设置jar位置
        job.setJarByClass(CarStatusWriteSql.class);
        //关联mapper和reducer
        job.setMapperClass(StatusWriteMapper.class);
        job.setReducerClass(StatusWriteReducer.class);
        //设置mapper的输出
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(CarStatus.class);
        //设置reducer的输出
        job.setOutputKeyClass(CarStatus.class);
        job.setOutputValueClass(NullWritable.class);
        //设置路径
        FileInputFormat.addInputPath(job, new Path(args[0])); //输入路径：本地
        String[] fields = {"cyear","newCount","usedCount","certifiedCount","otherCount"};
        DBOutputFormat.setOutput( //输出路径：数据库
                job,
                "status",
                fields
        );
        //提交
        boolean res = job.waitForCompletion(true);
        System.exit(res ? 0 : 1);
    }
}

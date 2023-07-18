package com.nd.car.mr.sql;

import com.nd.car.mr.entity.CarColor;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
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

/**
 * 外观和内饰颜色数据写入MySQL
 * @author 曹珉浩
 * @date 2023/07/16
 */
public class CarColorWriteSql {
    public static class ColorMapper extends Mapper<LongWritable,Text, Text, CarColor>{
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, CarColor>.Context context) throws IOException, InterruptedException {
            String line = new String(value.getBytes(),0, value.getLength(),"UTF-8");
            String[] split = line.split("\t");
            CarColor v = new CarColor(split[0],Integer.parseInt(split[1]));
            context.write(new Text(v.getColor()),v);
        }
    }
    public static class ColorReducer extends Reducer<Text,CarColor,CarColor, NullWritable>{
        @Override
        protected void reduce(Text key, Iterable<CarColor> values, Reducer<Text, CarColor, CarColor, NullWritable>.Context context) throws IOException, InterruptedException {
            CarColor v = values.iterator().next();
            context.write(v,NullWritable.get());
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        args = new String[]{"data/output/color/interior/part-r-00000"};
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
        job.setJarByClass(CarColorWriteSql.class);
        //关联mapper和reducer
        job.setMapperClass(ColorMapper.class);
        job.setReducerClass(ColorReducer.class);
        //设置mapper的输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(CarColor.class);
        //设置reducer的输出
        job.setOutputKeyClass(CarColor.class);
        job.setOutputValueClass(NullWritable.class);
        //设置路径
        FileInputFormat.addInputPath(job, new Path(args[0])); //输入路径：本地
        String[] fields = {"interiorColor","count"};
        DBOutputFormat.setOutput( //输出路径：数据库
                job,
                "interior",
                fields
        );
        //提交
        boolean res = job.waitForCompletion(true);
        System.exit(res ? 0 : 1);
    }
}


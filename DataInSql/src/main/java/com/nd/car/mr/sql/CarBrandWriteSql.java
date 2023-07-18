package com.nd.car.mr.sql;

import com.nd.car.mr.entity.CarBrand;
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

public class CarBrandWriteSql {
    public static class BrandWriteMapper extends Mapper<LongWritable, Text,Text, CarBrand>{
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, CarBrand>.Context context) throws IOException, InterruptedException {
            String line = new String(value.getBytes(),0,value.getLength(),"UTF-8");
            String[] split = line.split("\t");
            CarBrand v = new CarBrand(split[0],Integer.parseInt(split[1]));
            context.write(new Text(v.getName()),v);
        }
    }
    public static class BrandWriteReducer extends Reducer<Text,CarBrand,CarBrand, NullWritable>{
        @Override
        protected void reduce(Text key, Iterable<CarBrand> values, Reducer<Text, CarBrand, CarBrand, NullWritable>.Context context) throws IOException, InterruptedException {
            CarBrand v = values.iterator().next();
            context.write(v,NullWritable.get());
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        args = new String[]{"data/output/brandCount/part-r-00000"};
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
        job.setJarByClass(CarBrandWriteSql.class);
        //关联mapper和reducer
        job.setMapperClass(BrandWriteMapper.class);
        job.setReducerClass(BrandWriteReducer.class);
        //设置mapper的输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(CarBrand.class);
        //设置reducer的输出
        job.setOutputKeyClass(CarBrand.class);
        job.setOutputValueClass(NullWritable.class);
        //设置路径
        FileInputFormat.addInputPath(job, new Path(args[0])); //输入路径：本地
        String[] fields = {"brand_name","count"};
        DBOutputFormat.setOutput( //输出路径：数据库
                job,
                "brand_count",
                fields
        );
        //提交
        boolean res = job.waitForCompletion(true);
        System.exit(res ? 0 : 1);
    }
}

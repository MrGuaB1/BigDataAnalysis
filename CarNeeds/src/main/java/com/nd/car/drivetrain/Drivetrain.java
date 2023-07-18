package com.nd.car.drivetrain;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class Drivetrain {
    public static final String YEAR = "2023";
    public static class DrivetrainMapper extends Mapper<LongWritable, Text,Text, IntWritable>{
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            String line = new String(value.getBytes(),0,value.getLength(),"UTF-8");
            String[] split = line.split(",", -1);
            String year = split[18];
            String drivetrain = split[6];
            if(drivetrain.length()==0){
                drivetrain="Other";
            }
            if(year.equals(YEAR)){
                context.write(new Text(drivetrain),new IntWritable(1));
            }
        }
    }
    public static class DrivetrainReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            int count = 0;
            for (IntWritable value : values) {
                count+=value.get();
            }
            if(count>10){
                context.write(key,new IntWritable(count));
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        args=new String[]{"hdfs://hadoop101:8020/car/input/cars.csv","data/output/drivetrain/"+YEAR};
        //配置对象
        Configuration conf=new Configuration();
        //获取Job对象
        Job job = Job.getInstance(conf);
        //设置jar位置
        job.setJarByClass(Drivetrain.class);
        //关联和Mapper和Reducer
        job.setMapperClass(DrivetrainMapper.class);
        job.setReducerClass(DrivetrainReducer.class);
        //设置Mapper输出的键值对
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //设置Reducer最终输出的键值对类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //设置路径
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //提交
        boolean res = job.waitForCompletion(true);
        System.exit(res?0:1);
    }
}

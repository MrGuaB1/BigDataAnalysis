package com.nd.car.color;

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

/**
 * 统计外观颜色
 * @author 曹珉浩
 * @date 2023/07/16
 */
public class exteriorColor {
    public static class exteriorMapper extends Mapper<LongWritable, Text,Text, IntWritable>{
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            String line = new String(value.getBytes(),0,value.getLength(),"UTF-8");
            String[] split = line.split(",", -1);
            String exColor = split[4];
            if(exColor.length()==0){
                exColor="Unknown";
            }
            context.write(new Text(exColor),new IntWritable(1));
        }
    }
    public static class exteriorReducer extends Reducer<Text,IntWritable,Text,IntWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            int count = 0;
            for (IntWritable value : values) {
                count+=value.get();
            }
            if(count>=50){ //大于50的颜色有统计意义
                context.write(key,new IntWritable(count));
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        args=new String[]{"hdfs://hadoop101:8020/car/input/cars.csv","data/output/color/exterior"};
        //配置对象
        Configuration conf=new Configuration();
        //获取Job对象
        Job job = Job.getInstance(conf);
        //设置jar位置
        job.setJarByClass(exteriorColor.class);
        //关联和Mapper和Reducer
        job.setMapperClass(exteriorMapper.class);
        job.setReducerClass(exteriorReducer.class);
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

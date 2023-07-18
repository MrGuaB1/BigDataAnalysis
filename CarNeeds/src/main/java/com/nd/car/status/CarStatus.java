package com.nd.car.status;

import com.nd.car.brand.CarBrand;
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

public class CarStatus {
    public static class StatusMapper extends Mapper<LongWritable, Text, IntWritable,Text>{
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntWritable, Text>.Context context) throws IOException, InterruptedException {
            String line = new String(value.getBytes(),0, value.getLength(),"UTF-8");
            String[] split = line.split(",", -1);
            String year = split[18];
            String status = split[1];
            if(status.length()==0){
                status = "Unknown";
            }
            if(year.length()==4&&year.charAt(0)=='2'){
                context.write(new IntWritable(Integer.parseInt(year)),new Text(status));
            }
        }
    }
    public static class StatusReducer extends Reducer<IntWritable,Text,IntWritable,Text>{
        @Override
        protected void reduce(IntWritable key, Iterable<Text> values, Reducer<IntWritable, Text, IntWritable, Text>.Context context) throws IOException, InterruptedException {
            int newCount=0;
            int usedCount=0;
            int certCount=0;
            int otherCount=0;
            for (Text value : values) {
                if(value.toString().equals("New")){
                    newCount++;
                }else if(value.toString().equals("Used")){
                    usedCount++;
                }else if(value.toString().equals("Acura Certified")){
                    certCount++;
                }else {
                    otherCount++;
                }
            }
            String v = String.valueOf(newCount)+"_"+String.valueOf(usedCount)+
                    "_"+String.valueOf(certCount)+"_"+String.valueOf(otherCount);
            context.write(key,new Text(v));
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        args=new String[]{"hdfs://hadoop101:8020/car/input/cars.csv","data/output/statusCount"};
        //配置对象
        Configuration conf=new Configuration();
        //获取Job对象
        Job job = Job.getInstance(conf);
        //设置jar位置
        job.setJarByClass(CarStatus.class);
        //关联和Mapper和Reducer
        job.setMapperClass(StatusMapper.class);
        job.setReducerClass(StatusReducer.class);
        //设置Mapper输出的键值对
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);
        //设置Reducer最终输出的键值对类型
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        //设置路径
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //提交
        boolean res = job.waitForCompletion(true);
        System.exit(res?0:1);
    }
}

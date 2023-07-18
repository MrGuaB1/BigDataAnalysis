package com.nd.car.score;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class CarScore {
    public static class ScoreMapper extends Mapper<LongWritable, Text,Text,Text> {
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
            String line = new String(value.getBytes(),0,value.getLength(),"UTF-8");
            String[] split = line.split(",", -1);
            String brand = split[17];
            if(brand.length()==0){

                brand="Other";
            }
            String generalRateScore = split[20];
            String comfortScore = split[21];
            String designScore = split[22];
            String valueForMoney = split[23];
            String styleScore = split[24];
            String reliabilityScore = split[25];
            if (generalRateScore.length() != 3 || comfortScore.length() != 3||designScore.length()!=3
                ||valueForMoney.length()!=3||styleScore.length()!=3||reliabilityScore.length()!=3) {
                return;
            }
            String v = generalRateScore+"_"+comfortScore+"_"+designScore+"_"+valueForMoney+"_"
                    +styleScore+"_"+reliabilityScore;
            context.write(new Text(brand),new Text(v));
            System.out.println(new Text(v).toString());
        }
    }
    public static class ScoreReducer extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
            double generalRateSumScore=0;
            double comfortSumScore=0;
            double designSumScore=0;
            double valueForMoneySum=0;
            double styleSumScore=0;
            double reliabilitySumScore=0;
            double count=0.0;
            for (Text value : values) {
                String[] split = value.toString().split("_");
                generalRateSumScore+=Double.parseDouble(split[0]);
                comfortSumScore+=Double.parseDouble(split[1]);
                designSumScore+=Double.parseDouble(split[2]);
                valueForMoneySum+=Double.parseDouble(split[3]);
                styleSumScore+=Double.parseDouble(split[4]);
                reliabilitySumScore+=Double.parseDouble(split[5]);
                count=count+1.0;
            }
            String avg1 = String.format("%.2f",generalRateSumScore/count);
            String avg2 = String.format("%.2f",comfortSumScore/count);
            String avg3 = String.format("%.2f",designSumScore/count);
            String avg4 = String.format("%.2f",valueForMoneySum/count);
            String avg5 = String.format("%.2f",styleSumScore/count);
            String avg6 = String.format("%.2f",reliabilitySumScore/count);
            String v = avg1+"_"+avg2+"_"+avg3+"_"+avg4+"_"+avg5+"_"+avg6;
            context.write(new Text(key),new Text(v));
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        args=new String[]{"hdfs://hadoop101:8020/car/input/cars.csv","data/output/score"};
        //配置对象
        Configuration conf=new Configuration();
        //获取Job对象
        Job job = Job.getInstance(conf);
        //设置jar位置
        job.setJarByClass(CarScore.class);
        //关联和Mapper和Reducer
        job.setMapperClass(ScoreMapper.class);
        job.setReducerClass(ScoreReducer.class);
        //设置Mapper输出的键值对
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        //设置Reducer最终输出的键值对类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //设置路径
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //提交
        boolean res = job.waitForCompletion(true);
        System.exit(res?0:1);
    }
}

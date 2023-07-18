package com.nd.car.mr.sql;

import com.nd.car.mr.entity.CarScore;
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

public class CarScoreWriteSql {
    public static class CarScoreMapper extends Mapper<LongWritable, Text, Text, CarScore> {
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, CarScore>.Context context) throws IOException, InterruptedException {
            String line = new String(value.getBytes(), 0, value.getLength(), "UTF-8");
            String[] split = line.split("\t");
            String[] scores = split[1].split("_");
            CarScore v = new CarScore(split[0],Double.parseDouble(scores[0]),Double.parseDouble(scores[1]),
                    Double.parseDouble(scores[2]),Double.parseDouble(scores[3]),Double.parseDouble(scores[4]),
                    Double.parseDouble(scores[5]));
            context.write(new Text(v.getBrand()),v);
        }

        public static class CarScoreReducer extends Reducer<Text, CarScore, CarScore, NullWritable> {
            @Override
            protected void reduce(Text key, Iterable<CarScore> values, Reducer<Text, CarScore, CarScore, NullWritable>.Context context) throws IOException, InterruptedException {
                CarScore v = values.iterator().next();
                context.write(v, NullWritable.get());
            }
        }

        public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
            args = new String[]{"data/output/score/part-r-00000"};
            //获取配置对象
            Configuration conf = new Configuration();
            //连接mysql数据库
            DBConfiguration.configureDB(
                    conf,
                    "com.mysql.cj.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/car?characterEncoding=utf-8&&useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8",
                    "root",
                    "cmh123456"
            );
            //获取job对象
            Job job = Job.getInstance(conf);
            //设置jar位置
            job.setJarByClass(CarScoreWriteSql.class);
            //关联mapper和reducer
            job.setMapperClass(CarScoreMapper.class);
            job.setReducerClass(CarScoreReducer.class);
            //设置mapper的输出
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(CarScore.class);
            //设置reducer的输出
            job.setOutputKeyClass(CarScore.class);
            job.setOutputValueClass(NullWritable.class);
            //设置路径
            FileInputFormat.addInputPath(job, new Path(args[0])); //输入路径：本地
            String[] fields = {"brand", "generalRateScore", "comfortScore",
                    "designScore","valueForMoney", "styleScore", "reliabilityScore"};
            DBOutputFormat.setOutput( //输出路径：数据库
                    job,
                    "score",
                    fields
            );
            //提交
            boolean res = job.waitForCompletion(true);
            System.exit(res ? 0 : 1);
        }
    }
}

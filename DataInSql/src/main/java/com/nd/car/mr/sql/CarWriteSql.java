package com.nd.car.mr.sql;

import com.nd.car.mr.entity.Car;
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

public class CarWriteSql {
    public static class CarMapper extends Mapper<LongWritable, Text, Text, Car> {
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Car>.Context context) throws IOException, InterruptedException {
            String line = new String(value.getBytes(),0, value.getLength(),"UTF-8");
            String[] split = line.split(",",-1); //-1防止分隔符后面为空
            Car car =new Car(Integer.parseInt(split[0]),split[1],split[2],Integer.parseInt(split[3]),split[4],split[5],split[6],split[7],split[8],
                    split[9],split[10],Double.parseDouble(split[11]),split[12],split[13],split[14],split[15],split[16],split[17],
                    Integer.parseInt(split[18]),split[19],Double.parseDouble(split[20]),Double.parseDouble(split[21]),Double.parseDouble(split[22]),
                    Double.parseDouble(split[23]),Double.parseDouble(split[24]),Double.parseDouble(split[25]));
            String str = new String(String.valueOf(car.getCid()));
            context.write(new Text(str),car);
        }
    }
    public static class CarReducer extends Reducer<Text,Car,Car, NullWritable>{
        @Override
        protected void reduce(Text key, Iterable<Car> values, Reducer<Text, Car, Car, NullWritable>.Context context) throws IOException, InterruptedException {
            Car car =values.iterator().next();
            context.write(car,NullWritable.get());
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        args = new String[]{"data/carScore.csv"};
        //获取配置对象
        Configuration conf = new Configuration();
        //连接mysql数据库
        DBConfiguration.configureDB(
                conf,
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/car?characterEncoding=utf-8&&useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8",
                "root",
                "cmh123456"
        );
        //获取job对象
        Job job = Job.getInstance(conf);
        //设置jar位置
        job.setJarByClass(CarWriteSql.class);
        //关联mapper和reducer
        job.setMapperClass(CarMapper.class);
        job.setReducerClass(CarReducer.class);
        //设置mapper的输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Car.class);
        //设置reducer的输出
        job.setOutputKeyClass(Car.class);
        job.setOutputValueClass(NullWritable.class);
        //设置路径
        FileInputFormat.addInputPath(job,new Path(args[0])); //输入路径：本地
        //属性：index,status,name,money,exteriorColor,interiorColor,drivetrain,MPG,fuelType,transmission,
        // engine,mileage,convenience,entertainment,exterior,safety,seating,brand,year,model,
        // generalRateScore,comfortScore,designScore,valueForMoney,styleScore,reliabilityScore
        DBOutputFormat.setOutput( //输出路径：数据库
                job,
                "db_car",
                new String[]{"cid","cstatus","cname","money","exteriorColor","interiorColor","drivetrain",
                        "MPG","fuelType","transmission","cengine","mileage","convenience","entertainment",
                        "exterior","safety","seating","brand","cyear","model","generalRateScore",
                        "comfortScore","designScore","valueForMoney","styleScore","reliabilityScore"}
        );
        //提交
        boolean res = job.waitForCompletion(true);
        System.exit(res?0:1);
    }
}

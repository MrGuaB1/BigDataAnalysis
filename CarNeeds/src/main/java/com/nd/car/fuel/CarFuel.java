package com.nd.car.fuel;

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
 * 统计各年份的汽油占比
 * @author 曹珉浩
 * @date 2023/07/15
 */
public class CarFuel {
    public static class FuelMapper extends Mapper<LongWritable, Text, Text,IntWritable>{
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            String line = new String(value.getBytes(),0, value.getLength(),"UTF-8");
            String[] split = line.split(",", -1);
            //第9列为汽油类型，第19列为年份
            //2,New,2023 Acura Integra A-Spec Technology,38095,Majestic Black Pearl,Ebony,Fwd,26–36,
            //Gasoline,6-Speed Manual,1.5L I4 16V Gdi Dohc Turbo,5.0,Adaptive Cruise ControlHeated Seats,
            //Apple CarPlay/Android AutoBluetoothPremium Sound System,Alloy WheelsSunroof/Moonroof,
            // Backup CameraBrake AssistLane Departure WarningStability Control,Leather SeatsMemory Seat,
            // Acura,2023,Integra A-Spec Technology,3.5,3.3,3.6,3.4,3.9,3.6
            String year = split[18];
            String type = split[8];
            if(type.length()==0){
                type="Unknown";
            }
            //逐年统计，由2012到2023，year为空的数据忽略不计
            if(year.equals("2023")){
                context.write(new Text(type),new IntWritable(1));
            }
        }
    }
    public static class FuelReducer extends Reducer<Text,IntWritable,Text,IntWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            int count = 0;
            for (IntWritable value : values) {
                count+=value.get();
            }
            context.write(new Text(key),new IntWritable(count));
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        args=new String[]{"hdfs://hadoop101:8020/car/input/cars.csv","data/output/fuelCount/2023"};
        //配置对象
        Configuration conf=new Configuration();
        //获取Job对象
        Job job = Job.getInstance(conf);
        //设置jar位置
        job.setJarByClass(CarFuel.class);
        //关联和Mapper和Reducer
        job.setMapperClass(FuelMapper.class);
        job.setReducerClass(FuelReducer.class);
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

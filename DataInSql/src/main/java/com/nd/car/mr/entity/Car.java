package com.nd.car.mr.entity;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * NewYork汽车实体类
 * @author 曹珉浩
 * @date 2023/07/13
 */
public class Car implements DBWritable, Writable {
    //属性：index,status,name,money,exteriorColor,interiorColor,drivetrain,MPG,fuelType,transmission,
    // engine,mileage,convenience,entertainment,exterior,safety,seating,brand,year,model,
    // generalRateScore,comfortScore,designScore,valueForMoney,styleScore,reliabilityScore

    //1,New,2023 Acura Integra Base,33095,Liquid Carbon Metallic,Ebony,Fwd,30–37,Gasoline,Automatic Cvt,
    // 1.5L I4 16V Gdi Dohc Turbo,5.0,Adaptive Cruise ControlHeated SeatsKeyless Start,
    // Apple CarPlay/Android AutoBluetoothPremium Sound SystemUSB Port,Alloy WheelsSunroof/Moonroof,
    // Automatic Emergency BrakingBackup CameraBrake AssistLane Departure WarningRear Cross Traffic AlertStability Control,
    // Leather Seats,Acura,2023,Integra Base,3.6,3.9,3.8,3.8,4.2,3.3
    private int cid; //主键
    private String cstatus; //情况，new&used
    private String cname; //carName：2023 Acura Integra Base
    private int money;
    private String exteriorColor; //外观颜色：Liquid Carbon Metallic
    private String interiorColor; //内饰颜色：Ebony
    private String drivetrain; //传动系统：Fwd
    private String MPG; //每加仑行驶距离：30-37
    private String fuelType; //汽油类型：Gasoline
    private String transmission; //变速箱：Automatic Cvt
    private String cengine; //发动机：1.5L I4 16V Gdi Dohc Turbo
    private double mileage; //里程
    private String convenience; //便利性：Adaptive Cruise ControlHeated SeatsKeyless Start，自适应巡航控制加热座椅无钥匙启动
    private String entertainment; //娱乐性：Apple CarPlay/Android AutoBluetoothPremium Sound SystemUSB Port
    private String exterior; //外观：Alloy WheelsSunroof/Moonroof，合金轮天窗/天窗
    private String safety; //Automatic Emergency BrakingBackup CameraBrake AssistLane Departure WarningRear Cross Traffic AlertStability Control
    private String seating; //Leather Seats
    private String brand; //Acura
    private int cyear;
    private String model; //Integra Base
    private double generalRateScore; //普通费率的得分
    private double comfortScore;
    private double designScore;
    private double valueForMoney; //性价比
    private double styleScore;
    private double reliabilityScore;


    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(cid);
        out.writeUTF(cstatus);
        out.writeUTF(cname);
        out.writeInt(money);
        out.writeUTF(exteriorColor);
        out.writeUTF(interiorColor);
        out.writeUTF(drivetrain);
        out.writeUTF(MPG);
        out.writeUTF(fuelType);
        out.writeUTF(transmission);
        out.writeUTF(cengine);
        out.writeDouble(this.mileage);
        out.writeUTF(convenience);
        out.writeUTF(entertainment);
        out.writeUTF(this.exterior);
        out.writeUTF(safety);
        out.writeUTF(seating);
        out.writeUTF(brand);
        out.writeInt(cyear);
        out.writeUTF(model);
        out.writeDouble(this.generalRateScore);
        out.writeDouble(this.comfortScore);
        out.writeDouble(this.designScore);
        out.writeDouble(this.valueForMoney);
        out.writeDouble(this.styleScore);
        out.writeDouble(this.reliabilityScore);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.cid=in.readInt();
        this.cstatus=in.readUTF();
        this.cname=in.readUTF();
        this.money=in.readInt();
        this.exteriorColor=in.readUTF();
        this.interiorColor=in.readUTF();
        this.drivetrain=in.readUTF();
        this.MPG=in.readUTF();
        this.fuelType=in.readUTF();
        this.transmission=in.readUTF();
        this.cengine=in.readUTF();
        this.mileage=in.readDouble();
        this.convenience=in.readUTF();
        this.entertainment=in.readUTF();
        this.exterior=in.readUTF();
        this.safety=in.readUTF();
        this.seating=in.readUTF();
        this.brand=in.readUTF();
        this.cyear=in.readInt();
        this.model=in.readUTF();
        this.generalRateScore=in.readDouble();
        this.comfortScore=in.readDouble();
        this.designScore=in.readDouble();
        this.valueForMoney=in.readDouble();
        this.styleScore=in.readDouble();
        this.reliabilityScore=in.readDouble();
    }

    @Override
    public void write(PreparedStatement ps) throws SQLException {
        ps.setInt(1,cid);
        ps.setString(2,cstatus);
        ps.setString(3,cname);
        ps.setInt(4,money);
        ps.setString(5,this.exteriorColor);
        ps.setString(6,interiorColor);
        ps.setString(7,drivetrain);
        ps.setString(8,MPG);
        ps.setString(9,fuelType);
        ps.setString(10,this.transmission);
        ps.setString(11,cengine);
        ps.setDouble(12,this.mileage);
        ps.setString(13,convenience);
        ps.setString(14,entertainment);
        ps.setString(15,this.exterior);
        ps.setString(16,safety);
        ps.setString(17,seating);
        ps.setString(18,brand);
        ps.setInt(19,cyear);
        ps.setString(20,model);
        ps.setDouble(21,this.generalRateScore);
        ps.setDouble(22,this.comfortScore);
        ps.setDouble(23,this.designScore);
        ps.setDouble(24,valueForMoney);
        ps.setDouble(25,styleScore);
        ps.setDouble(26,this.reliabilityScore);
    }

    @Override
    public void readFields(ResultSet rs) throws SQLException {
        this.cid=rs.getInt("cid");
        this.cstatus=rs.getString("cstatus");
        this.cname=rs.getString("cname");
        this.money=rs.getInt("money");
        this.exteriorColor=rs.getString("exteriorColor");
        this.interiorColor=rs.getString("interiorColor");
        this.drivetrain=rs.getString("drivetrain");
        this.MPG=rs.getString("MPG");
        this.fuelType=rs.getString("fuelType");
        this.transmission=rs.getString("transmission");
        this.cengine=rs.getString("cengine");
        this.mileage=rs.getDouble("mileage");
        this.convenience=rs.getString("convenience");
        this.entertainment=rs.getString("entertainment");
        this.exterior=rs.getString("exterior");
        this.safety=rs.getString("safety");
        this.seating=rs.getString("seating");
        this.brand=rs.getString("brand");
        this.cyear=rs.getInt("cyear");
        this.model=rs.getString("model");
        this.generalRateScore=rs.getDouble("generalRateScore");
        this.comfortScore=rs.getDouble("comfortScore");
        this.designScore=rs.getDouble("designScore");
        this.valueForMoney=rs.getDouble("valueForMoney");
        this.styleScore=rs.getDouble("styleScore");
        this.reliabilityScore=rs.getDouble("reliabilityScore");
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public String getDrivetrain() {
        return drivetrain;
    }

    public void setDrivetrain(String drivetrain) {
        this.drivetrain = drivetrain;
    }

    public String getMPG() {
        return MPG;
    }

    public void setMPG(String MPG) {
        this.MPG = MPG;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getConvenience() {
        return convenience;
    }

    public void setConvenience(String convenience) {
        this.convenience = convenience;
    }

    public String getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(String entertainment) {
        this.entertainment = entertainment;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public String getSafety() {
        return safety;
    }

    public void setSafety(String safety) {
        this.safety = safety;
    }

    public String getSeating() {
        return seating;
    }

    public void setSeating(String seating) {
        this.seating = seating;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getGeneralRateScore() {
        return generalRateScore;
    }

    public void setGeneralRateScore(double generalRateScore) {
        this.generalRateScore = generalRateScore;
    }

    public double getComfortScore() {
        return comfortScore;
    }

    public void setComfortScore(double comfortScore) {
        this.comfortScore = comfortScore;
    }

    public double getDesignScore() {
        return designScore;
    }

    public void setDesignScore(double designScore) {
        this.designScore = designScore;
    }

    public double getValueForMoney() {
        return valueForMoney;
    }

    public void setValueForMoney(double valueForMoney) {
        this.valueForMoney = valueForMoney;
    }

    public double getStyleScore() {
        return styleScore;
    }

    public void setStyleScore(double styleScore) {
        this.styleScore = styleScore;
    }

    public double getReliabilityScore() {
        return reliabilityScore;
    }

    public void setReliabilityScore(double reliabilityScore) {
        this.reliabilityScore = reliabilityScore;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCengine() {
        return cengine;
    }

    public void setCengine(String cengine) {
        this.cengine = cengine;
    }

    public int getCyear() {
        return cyear;
    }

    public void setCyear(int cyear) {
        this.cyear = cyear;
    }

    public Car(int index, String status, String name, int money, String exteriorColor, String interiorColor, String drivetrain, String MPG, String fuelType, String transmission, String engine, double mileage, String convenience, String entertainment, String exterior, String safety, String seating, String brand, int year, String model, double generalRateScore, double comfortScore, double designScore, double valueForMoney, double styleScore, double reliabilityScore) {
        this.cid = index;
        this.cstatus = status;
        this.cname = name;
        this.money = money;
        this.exteriorColor = exteriorColor;
        this.interiorColor = interiorColor;
        this.drivetrain = drivetrain;
        this.MPG = MPG;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.cengine = engine;
        this.mileage = mileage;
        this.convenience = convenience;
        this.entertainment = entertainment;
        this.exterior = exterior;
        this.safety = safety;
        this.seating = seating;
        this.brand = brand;
        this.cyear = year;
        this.model = model;
        this.generalRateScore = generalRateScore;
        this.comfortScore = comfortScore;
        this.designScore = designScore;
        this.valueForMoney = valueForMoney;
        this.styleScore = styleScore;
        this.reliabilityScore = reliabilityScore;
    }

    public Car() {}

    @Override
    public String toString() {
        return "Car{" +
                "index=" + cid +
                ", status='" + cstatus + '\'' +
                ", name='" + cname + '\'' +
                ", money=" + money +
                ", exteriorColor='" + exteriorColor + '\'' +
                ", interiorColor='" + interiorColor + '\'' +
                ", drivetrain='" + drivetrain + '\'' +
                ", MPG='" + MPG + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", transmission='" + transmission + '\'' +
                ", engine='" + cengine + '\'' +
                ", mileage=" + mileage +
                ", convenience='" + convenience + '\'' +
                ", entertainment='" + entertainment + '\'' +
                ", exterior='" + exterior + '\'' +
                ", safety='" + safety + '\'' +
                ", seating='" + seating + '\'' +
                ", brand='" + brand + '\'' +
                ", year=" + cyear +
                ", model='" + model + '\'' +
                ", generalRateScore=" + generalRateScore +
                ", comfortScore=" + comfortScore +
                ", designScore=" + designScore +
                ", valueForMoney=" + valueForMoney +
                ", styleScore=" + styleScore +
                ", reliabilityScore=" + reliabilityScore +
                '}';
    }
}



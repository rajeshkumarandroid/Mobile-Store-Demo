package deeplinking.com.mvvmsample.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Rajesh Kumar on 07-06-2018.
 */
public class Mobiles extends RealmObject {

    @PrimaryKey
    private long id;

    private String name;
    private String model;
    private String color;
    private String  price;
    private String  battery;
    private String primary_camera;
    private String secondry_camera;
    private String memory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getPrimary_camera() {
        return primary_camera;
    }

    public void setPrimary_camera(String primary_camera) {
        this.primary_camera = primary_camera;
    }

    public String getSecondry_camera() {
        return secondry_camera;
    }

    public void setSecondry_camera(String secondry_camera) {
        this.secondry_camera = secondry_camera;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }


}

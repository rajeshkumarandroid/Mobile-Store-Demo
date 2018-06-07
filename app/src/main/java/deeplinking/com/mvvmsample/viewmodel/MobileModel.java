package deeplinking.com.mvvmsample.viewmodel;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import deeplinking.com.mvvmsample.app.Prefs;
import deeplinking.com.mvvmsample.model.Mobiles;
import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Rajesh Kumar on 07-06-2018.
 */
public class MobileModel {


    private long id;
    private String name;
    private String model;
    private String color;
    private String  price;
    private String  battery;
    private String primary_camera;
    private String secondry_camera;
    private String memory;
    Mobiles mobiles1= new Mobiles();

  public   Mobiles mobiles;


    public MobileModel(Mobiles mobiles){
        this.id = mobiles.getId();
        this.name = mobiles.getName();
        this.model = mobiles.getModel();
        this.color = mobiles.getColor();
        this.price = mobiles.getPrice();
        this.battery = mobiles.getBattery();
        this.primary_camera = mobiles.getPrimary_camera();
        this.secondry_camera = mobiles.getSecondry_camera();
        this.memory = mobiles.getMemory();
        this.mobiles = mobiles;
    }

    public MobileModel(){

    }

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

    public void setRealmDataModel(Realm realm, Context context) {

        ArrayList<MobileModel> al_mobiles = new ArrayList<>();

        mobiles1.setId(1 + System.currentTimeMillis());
        mobiles1.setName("Redmi");
        mobiles1.setModel("Note 5");
        mobiles1.setColor("Black");
        mobiles1.setBattery("4500");
        mobiles1.setMemory("32");
        mobiles1.setPrice("12999");
        mobiles1.setPrimary_camera("16");
        mobiles1.setSecondry_camera("8");
        MobileModel newsModel = new MobileModel(mobiles1);
        al_mobiles.add(newsModel);

        mobiles1= new Mobiles();
        mobiles1.setId(2 + System.currentTimeMillis());
        mobiles1.setName("Lenevo");
        mobiles1.setModel("K3 Note");
        mobiles1.setColor("Silver");
        mobiles1.setBattery("43500");
        mobiles1.setMemory("32");
        mobiles1.setPrice("9999");
        mobiles1.setPrimary_camera("12");
        mobiles1.setSecondry_camera("8");
        MobileModel newsMode2 = new MobileModel(mobiles1);
        al_mobiles.add(newsMode2);

        mobiles1= new Mobiles();
        mobiles1.setId(3 + System.currentTimeMillis());
        mobiles1.setName("Moto G6");
        mobiles1.setModel("Play");
        mobiles1.setColor("Indigo Black");
        mobiles1.setBattery("4000");
        mobiles1.setMemory("32");
        mobiles1.setPrice("11999");
        mobiles1.setPrimary_camera("13");
        mobiles1.setSecondry_camera("8");
        MobileModel newsMode3 = new MobileModel(mobiles1);
        al_mobiles.add(newsMode3);

        Log.e("al_mobiles size is ","<><><"+al_mobiles.size());

        for(int i=0;i<al_mobiles.size();i++){
            realm.beginTransaction();
            realm.copyToRealm(al_mobiles.get(i).mobiles);
            realm.commitTransaction();
        }

        Prefs.with(context).setPreLoad(true);

    }


}

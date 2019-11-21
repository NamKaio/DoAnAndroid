package com.example.doanandroid.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.doanandroid.data.Data;

public class HangHoa implements Parcelable {
    private int id;
    private String photo;
    private String name;
    private String country;
    private String manufacturer;
    private float concentration;
    private String fulldesc;
    private String kind;
    private int cost;
    private int sold;
    private float rate;
    private int quantity;

    public HangHoa(int id, String photo, String name, String country, String manufacturer,
                   float concentration, String fulldesc, String kind, int cost, int sold,
                   float rate, int quantity) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.country = country;
        this.manufacturer = manufacturer;
        this.concentration = concentration;
        this.fulldesc = fulldesc;
        this.kind = kind;
        this.cost = cost;
        this.sold = sold;
        this.rate = rate;
        this.quantity = quantity;
    }

    protected HangHoa(Parcel in) {
        id = in.readInt();
        photo = in.readString();
        name = in.readString();
        country = in.readString();
        manufacturer = in.readString();
        concentration = in.readFloat();
        fulldesc = in.readString();
        kind = in.readString();
        cost = in.readInt();
        sold = in.readInt();
        rate = in.readFloat();
        quantity = in.readInt();
    }

    public static final Creator<HangHoa> CREATOR = new Creator<HangHoa>() {
        @Override
        public HangHoa createFromParcel(Parcel in) {
            return new HangHoa(in);
        }

        @Override
        public HangHoa[] newArray(int size) {
            return new HangHoa[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public float getConcentration() {
        return concentration;
    }

    public void setConcentration(float concentration) {
        this.concentration = concentration;
    }

    public String getFulldesc() {
        return fulldesc;
    }

    public void setFulldesc(String fulldesc) {
        this.fulldesc = fulldesc;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static HangHoa create(Data data, int index){
        return new HangHoa(data.ids()[index],data.urls()[index], data.names()[index], data.countries()[index],
                data.manufacturers()[index], data.concentrations()[index], data.fullDescs()[index], data.kind()[index],
                data.costs()[index], data.solds()[index], data.rates()[index], data.quantities()[index]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(photo);
        dest.writeString(name);
        dest.writeString(country);
        dest.writeString(manufacturer);
        dest.writeFloat(concentration);
        dest.writeString(fulldesc);
        dest.writeString(kind);
        dest.writeInt(cost);
        dest.writeInt(sold);
        dest.writeFloat(rate);
        dest.writeInt(quantity);
    }
}

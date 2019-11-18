package com.example.doanandroid.models;

import com.example.doanandroid.data.DataCart;

public class GioHang {
    private int id;
    private String source_photo;
    private String name;
    private String kind;
    private int cost;
    private int quantity;

    public GioHang(int id, String source_photo, String name, String kind, int cost, int quantity) {
        this.id = id;
        this.source_photo = source_photo;
        this.name = name;
        this.kind = kind;
        this.cost = cost;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource_photo() {
        return source_photo;
    }

    public void setSource_photo(String source_photo) {
        this.source_photo = source_photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static GioHang create(DataCart dataCart, int index) {
        return new GioHang(dataCart.ids()[index],dataCart.urls()[index], dataCart.names()[index], dataCart.kind()[index],
                dataCart.costs()[index], 1);
    }
}

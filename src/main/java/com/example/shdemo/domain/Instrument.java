package com.example.shdemo.domain;


import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "instrument.all", query = "Select i from Instrument i"),
        @NamedQuery(name = "instrument.byName", query = "Select i from Instrument i where i.name = :name")
})
public class Instrument {

    private Long id;
    private String brand;
    private String name;
    private double price;

    public Instrument(long id, String brand, String name, double price){
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.price = price;
    }

    public Instrument(String brand, String name, double price){
        this.brand = brand;
        this.name = name;
        this.price = price;
    }

    public Instrument(String brand, double price){
        this.brand = brand;
        this.price = price;
    }

    public Instrument () { }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}

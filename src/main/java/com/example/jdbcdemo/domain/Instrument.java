package com.example.jdbcdemo.domain;

public class Instrument {
	
	private long id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

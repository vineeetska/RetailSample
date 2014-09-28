package com.example.retailsample.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable{
	
	private int id = 0;
	private String name = "";
	private int image = 0;
	private float price = 0;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(Parcel in) {
		// TODO Auto-generated constructor stub
		id = in.readInt();
		name = in.readString();
		image = in.readInt();
		price = in.readFloat();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeInt(image);
		dest.writeFloat(price);
	}
	
	public static Parcelable.Creator<Product> CREATOR = new Creator<Product>(){

		@Override
		public Product createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Product(source);
		}

		@Override
		public Product[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Product[size];
		}
	};
	
}

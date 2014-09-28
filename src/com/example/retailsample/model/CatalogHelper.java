package com.example.retailsample.model;

import java.util.ArrayList;

import com.example.retailsample.R;

public class CatalogHelper {

	public static final int CAT_ELECTRONICS = 1;
	public static final int CAT_FURNITURE = 2;
	
	private static ArrayList<String> sCategoryList = null;
	
	static{
		sCategoryList = new ArrayList<String>();
		sCategoryList.add("Electronics");
		sCategoryList.add("Furniture");
	}
	
	private static int sCurrentCategory = CAT_ELECTRONICS;
	
	public CatalogHelper() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static ArrayList<String> getCategoryList() {
		return sCategoryList;
	}
	
	public static int getCurrentCategory() {
		return sCurrentCategory;
	}
	
	public static void setCurrentCategory(int currentCategory) {
		CatalogHelper.sCurrentCategory = currentCategory;
	}
	
	public static ArrayList<Product> getProductListByCategory(int category){
		ArrayList<Product> list = new ArrayList<Product>();
		
		switch(category){
			case CAT_ELECTRONICS:
				Product product1 = new Product();
				product1.setName("Kenstar M/O KM20SSLN 17 L Solo Microwave Oven");
				product1.setPrice(4390);
				product1.setImage(R.drawable.kenstar_m_o_km20ssln);
				list.add(product1);
				
				Product product2 = new Product();
				product2.setName("LG MC2143CB 21 L Convection Microwave Oven");
				product2.setPrice(10295);
				product2.setImage(R.drawable.lg_mo);
				list.add(product2);
				
				Product product3 = new Product();
				product3.setName("Samsung 32EH4003 32 inches LED TV");
				product3.setPrice(22760);
				product3.setImage(R.drawable.samsung_tv);
				list.add(product3);
				
				Product product4 = new Product();
				product4.setName("Sony BRAVIA KLV-22P402B 22 inches LED TV");
				product4.setPrice(13490);
				product4.setImage(R.drawable.sony_bravia_tv);
				list.add(product4);
				
				Product product5 = new Product();
				product5.setName("Eureka Forbes Easy Clean Plus Hand-held Vacuum Cleaner");
				product5.setPrice(2483);
				product5.setImage(R.drawable.eureka_forbes_easy_clean);
				list.add(product5);
				
				Product product6 = new Product();
				product6.setName("Eureka Forbes Trendy Nano Vacuum Cleaner");
				product6.setPrice(3065);
				product6.setImage(R.drawable.eureka_forbes_trendy_nano);
				list.add(product6);
				
				break;
			case CAT_FURNITURE:
				Product productF1 = new Product();
				productF1.setName("Amigo Laptop Table LD09");
				productF1.setPrice(999);
				productF1.setImage(R.drawable.amigo_laptop_table_white);
				list.add(productF1);
				
				Product productF2 = new Product();
				productF2.setName("Mothertouch Wonder Table");
				productF2.setPrice(1700);
				productF2.setImage(R.drawable.mothertouch_wonder_table);
				list.add(productF2);
				
				Product productF3 = new Product();
				productF3.setName("Chicco Pocket Lunch Highchair Wave");
				productF3.setPrice(7990);
				productF3.setImage(R.drawable.chair_1);
				list.add(productF3);
				
				Product productF4 = new Product();
				productF4.setName("Coleman Woodsman Chair");
				productF4.setPrice(2019);
				productF4.setImage(R.drawable.chair_2);
				list.add(productF4);
				
				Product productF5 = new Product();
				productF5.setName("HomeTown Magna 4 Door Wardrobe in Walnut_600404074001");
				productF5.setPrice(34900);
				productF5.setImage(R.drawable.almirah_1);
				list.add(productF5);
				
				Product productF6 = new Product();
				productF6.setName("HomeTown Premier 3 Door Wardrobe_600371076003");
				productF6.setPrice(13900);
				productF6.setImage(R.drawable.almirah_2);
				list.add(productF6);
				break;
		}
		return list;
	}
	
}

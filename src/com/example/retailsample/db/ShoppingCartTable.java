package com.example.retailsample.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.BaseColumns;

import com.example.retailsample.model.Product;

public class ShoppingCartTable {

	private final String TAG = "ShoppingCartTable";
	
	private static ShoppingCartTable sInstance = null;
	private static DbHelper sDbHelper;

	private static final String TB_NAME = "ShoppingCartTable";
	private static final String NAME = "name";
	private static final String IMAGE = "image";
	private static final String PRICE = "price";

	public static final String CREATE_TABLE = "create table " + TB_NAME + " ( " + BaseColumns._ID + " integer primary key autoincrement,"
			+ NAME + " text, " + IMAGE + " integer, " 
			+ PRICE + " float )";

	public static final String DELETE_ALL_RECORDS = "DROP TABLE " + TB_NAME;

	private ShoppingCartTable() { 	}
	
	public static ShoppingCartTable getInstance(Context context){
		if (sInstance == null){
			sInstance = new ShoppingCartTable();
			sDbHelper = new DbHelper(context);
		}	
		return sInstance;
	}

	public boolean insert(Product product){
		SQLiteDatabase db = null;
		boolean success = false;
		try{
			db = sDbHelper.getWritableDatabase();
			success = db.insert(TB_NAME, null, getContentValues(product)) > 0;
		}catch(SQLiteException e){
			e.printStackTrace();
		}

		return success;
	}

	public ArrayList<Product> getCartList(){
		ArrayList<Product> list = new ArrayList<Product>();
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try{
			db = sDbHelper.getReadableDatabase();
			cursor = db.query(TB_NAME, null, null, null, null, null, BaseColumns._ID + " ASC");
			if (cursor != null && cursor.moveToFirst()) {
				do {
					list.add(getModel(cursor));
				}while(cursor.moveToNext());
			}
		}catch(SQLiteException e){
			e.printStackTrace();
		}
		finally{
			if (cursor != null && !cursor.isClosed())
				cursor.close();
		}
		
		return list;
	}
	
	public boolean deleteItem (String id){
		SQLiteDatabase db = null;
		boolean success = false;
		try{
			db = sDbHelper.getWritableDatabase();
			success = db.delete(TB_NAME, BaseColumns._ID + "=?", new String[]{id}) > 0;
			
		}catch(SQLiteException e){
			e.printStackTrace();
		}
		
		return success;
	}
	
	public void deleteCartList (){
		SQLiteDatabase db = null;
		try{
			db = sDbHelper.getWritableDatabase();
			db.delete(TB_NAME, "1", null);
			
		}catch(SQLiteException e){
			e.printStackTrace();
		}
	}
	private ContentValues getContentValues( Product product ) {
		ContentValues values = new ContentValues();
		values.put(NAME, product.getName());
		values.put(IMAGE, product.getImage());
		values.put(PRICE, product.getPrice());
		return values;
	}
	
	private Product getModel( Cursor cursor ) {
		Product model = new Product();
		model.setId(cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)));
		model.setName(cursor.getString(cursor.getColumnIndex(NAME)));
		model.setImage(cursor.getInt(cursor.getColumnIndex(IMAGE)));
		model.setPrice(cursor.getFloat(cursor.getColumnIndex(PRICE)));
		return model;
	}

}

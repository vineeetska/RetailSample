package com.example.retailsample.ui.adapter;

import java.util.ArrayList;

import com.example.retailsample.R;
import com.example.retailsample.model.Product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductListAdapter extends BaseAdapter{

	private LayoutInflater mInflator;
	private ArrayList<Product> mListData = null;
	
	public ProductListAdapter(Context contxt, ArrayList<Product> data) {
		// TODO Auto-generated constructor stub
		mInflator = LayoutInflater.from(contxt);
		mListData = data;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (mListData == null)
			return 0;
		return mListData.size();
	}

	@Override
	public Product getItem(int position) {
		// TODO Auto-generated method stub
		return mListData.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null){
			holder = new ViewHolder();
			convertView = mInflator.inflate(R.layout.list_item_layout, null);
			holder.image = (ImageView)convertView.findViewById(R.id.ProductImage);
			holder.name = (TextView)convertView.findViewById(R.id.productName);
			holder.price = (TextView)convertView.findViewById(R.id.productPrice);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.image.setImageResource(getItem(position).getImage());
		holder.name.setText(getItem(position).getName());
		holder.price.setText(getItem(position).getPrice() + "");
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView image;
		TextView name;
		TextView price;
	}

}

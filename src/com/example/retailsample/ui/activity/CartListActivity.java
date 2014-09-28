package com.example.retailsample.ui.activity;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.retailsample.R;
import com.example.retailsample.db.ShoppingCartTable;
import com.example.retailsample.model.Product;

public class CartListActivity extends ListActivity{

	private TextView memptyView, mTotalTextView;
	private CartListAdapter mAdapter;
	private ArrayList<Product> mCartListData = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayUseLogoEnabled(true);
		
		memptyView = new TextView(this);
		memptyView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		memptyView.setText("No Item Found.");
		memptyView.setTextSize(20);
		memptyView.setTextColor(getResources().getColor( android.R.color.white ));
		memptyView.setVisibility(View.GONE);
		memptyView.setGravity(Gravity.CENTER_HORIZONTAL);
		((ViewGroup) getListView().getParent()).addView(memptyView);
		
		mCartListData = ShoppingCartTable.getInstance(getApplicationContext()).getCartList();
		
		View footerView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.cart_list_footer_view, null);
		mTotalTextView = ((TextView)footerView.findViewById(R.id.totalPrice));
		mTotalTextView.setText(getTotalSum() + "");
		
		((Button)footerView.findViewById(R.id.placeOrder)).setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ShoppingCartTable.getInstance(getApplicationContext()).deleteCartList();
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		
		getListView().setBackgroundColor(getResources().getColor(android.R.color.white));
		getListView().addFooterView(footerView);
		
		mAdapter = new CartListAdapter();
		setListAdapter(mAdapter);
		getListView().setEmptyView(memptyView);
	}
	
	private float getTotalSum(){
		float totalSum = 0;
		for (Product product : mCartListData)
			totalSum = totalSum + product.getPrice();
		return totalSum;
	}
	
	@Override
	public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
		// TODO Auto-generated method stub
		super.onCreateNavigateUpTaskStack(builder);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		finish();
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
		intent.putExtra("product", mAdapter.getItem(position));
		intent.putExtra("fromCartList", true);
		startActivity(intent);
	}
	
	class CartListAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if (mCartListData == null)
				return 0;
			return mCartListData.size();
		}

		@Override
		public Product getItem(int position) {
			// TODO Auto-generated method stub
			return mCartListData.get(position);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup arg2) {
			ViewHolder holder = null;
			if (convertView == null){
				holder = new ViewHolder();
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.cart_list_item_row_layout, null);
				holder.image = (ImageView)convertView.findViewById(R.id.image);
				holder.name = (TextView)convertView.findViewById(R.id.name);
				holder.price = (TextView)convertView.findViewById(R.id.price);
				holder.delete = (ImageView)convertView.findViewById(R.id.delete);
				convertView.setTag(holder);
			}
			else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.image.setImageResource(getItem(position).getImage());
			holder.name.setText(getItem(position).getName());
			holder.price.setText(getItem(position).getPrice() + "");
			
			holder.delete.setOnClickListener(new ImageView.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ShoppingCartTable.getInstance(getApplicationContext()).deleteItem(getItem(position).getId() + "");
					mCartListData.remove(position);
					mTotalTextView.setText(getTotalSum() + "");
					mAdapter.notifyDataSetChanged();
				}
			});
			
			return convertView;
		}
		
		class ViewHolder{
			ImageView image;
			TextView name;
			TextView price;
			ImageView delete;
		}
		
	}
}

package com.example.retailsample.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.retailsample.R;
import com.example.retailsample.db.ShoppingCartTable;
import com.example.retailsample.model.Product;

public class ProductDetailActivity extends Activity{

	private ImageView mImage;
	private TextView mName, mPrice;
	private Button mAddToCart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_detail);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayUseLogoEnabled(true);

		mImage = (ImageView)findViewById(R.id.ProductImage);
		mName = (TextView)findViewById(R.id.productName);
		mPrice = (TextView)findViewById(R.id.productPrice);
		mAddToCart = (Button)findViewById(R.id.addToCart);

		if (getIntent().getBooleanExtra("fromCartList", false))
			mAddToCart.setVisibility(View.GONE);
		
		final Product product = getIntent().getExtras().getParcelable("product");
		mImage.setImageResource(product.getImage());
		mName.setText(product.getName());
		mPrice.setText(product.getPrice() + "");

		mAddToCart.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShoppingCartTable.getInstance(getApplicationContext()).insert(product);
				finish();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.cart_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()){
		case android.R.id.home:
			finish();
			break;
		case R.id.showCart:
			startActivity(new Intent(getApplicationContext(), CartListActivity.class));
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}

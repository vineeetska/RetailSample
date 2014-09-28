package com.example.retailsample.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.retailsample.model.CatalogHelper;
import com.example.retailsample.ui.activity.ProductDetailActivity;
import com.example.retailsample.ui.adapter.ProductListAdapter;

public class ProductListFragment extends ListFragment{

	public static final String FRAGMENT_SELECTION_TAG = "catSelection";
	private ProductListAdapter mAdapter;
	
	public static Fragment getNewInstance(int category){
		Fragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_SELECTION_TAG, category);
        fragment.setArguments(args);
        return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		setAdapter(getArguments().getInt(FRAGMENT_SELECTION_TAG, 1));
	}
	
	private void setAdapter(int category){
		mAdapter = new ProductListAdapter(getActivity().getApplicationContext(), CatalogHelper.getProductListByCategory(category));
		setListAdapter(mAdapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(getActivity().getApplicationContext(), ProductDetailActivity.class);
		intent.putExtra("product", mAdapter.getItem(position));
		startActivity(intent);
	}
}

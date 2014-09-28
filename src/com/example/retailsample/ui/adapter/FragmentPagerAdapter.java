package com.example.retailsample.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import com.example.retailsample.model.CatalogHelper;
import com.example.retailsample.ui.fragment.ProductListFragment;

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter{

	public FragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int pos) {
		// TODO Auto-generated method stub
		return ProductListFragment.getNewInstance(pos + 1);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return CatalogHelper.getCategoryList().size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return super.getPageTitle(position);
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		return super.instantiateItem(container, position);
	}

}

package com.app.qbik.qbik.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.qbik.qbik.home.CommentFragment;
import com.app.qbik.qbik.home.DetailsFragment;
import com.app.qbik.qbik.home.HomePubsFragment;

public class HomeTabsPagerAdapter extends FragmentPagerAdapter {

	public HomeTabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new HomePubsFragment();
		case 1:
			// Games fragment activity
			return new DetailsFragment();
		case 2:
			// Movies fragment activity
			return new CommentFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}

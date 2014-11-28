package com.app.qbik.qbik.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.qbik.qbik.login.LoginFragment;
import com.app.qbik.qbik.login.SignUpFragment;

public class LoginTabsPagerAdapter extends FragmentPagerAdapter {

    public LoginTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new LoginFragment();
            case 1:
                // Games fragment activity
                return new SignUpFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}

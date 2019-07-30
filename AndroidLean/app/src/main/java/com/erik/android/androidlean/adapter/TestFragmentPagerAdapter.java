package com.erik.android.androidlean.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.erik.android.androidlean.activity.MainActivity;
import com.erik.android.androidlean.fragment.BuyFragment;
import com.erik.android.androidlean.fragment.HomeFragment;
import com.erik.android.androidlean.fragment.HotFragment;
import com.erik.android.androidlean.fragment.MineFragment;


public class TestFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private HomeFragment homeFragment = null;
    private HotFragment hotFragment = null;
    private BuyFragment buyFragment = null;
    private MineFragment mineFragment = null;

    public TestFragmentPagerAdapter(FragmentManager manager) {
        super(manager);
        homeFragment = new HomeFragment();
        hotFragment = new HotFragment();
        hotFragment.setManager(manager);
        buyFragment = new BuyFragment();
        mineFragment = new MineFragment();
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup, int position) {
        return super.instantiateItem(viewGroup, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = homeFragment;
                bundle.putString("content", "这是首页");
                break;
            case MainActivity.PAGE_TWO:
                fragment = hotFragment;
                bundle.putString("content", "这是热门");
                break;
            case MainActivity.PAGE_THREE:
                fragment = buyFragment;
                bundle.putString("content", "这是已购");
                break;
            case MainActivity.PAGE_FOUR:
                fragment = mineFragment;
                bundle.putString("content", "这是我的");
                break;
        }
        fragment.setArguments(bundle);
        return fragment;
    }

}

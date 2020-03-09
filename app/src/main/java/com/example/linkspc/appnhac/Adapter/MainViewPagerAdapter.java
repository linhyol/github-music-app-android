package com.example.linkspc.appnhac.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> arrayFragment = new ArrayList<>();
    private ArrayList<String> arrayTitle=new ArrayList<>();
    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    //Khi nó trả về cho viewpager thì mình muốn nó trả về thằng nào
    @Override
    public Fragment getItem(int i) {
        return arrayFragment.get(i);//Click tới thằng nào thì trả về vị trí của thằng đó
    }
    //Có bao nhiêu cái fragment cần hiển thị
    @Override
    public int getCount() {
        return arrayFragment.size();//Lấy cái kích thước của fragment
    }
    //Phương thức addFragment
    public void addFragment(Fragment fragment, String title)
    {
        arrayFragment.add(fragment);
        arrayTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle.get(position);
    }
}

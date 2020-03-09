package com.example.linkspc.appnhac.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.linkspc.appnhac.Adapter.MainViewPagerAdapter;
import com.example.linkspc.appnhac.Fragment.Fragment_Tim_Kiem;
import com.example.linkspc.appnhac.Fragment.Fragment_Trang_Chu;
import com.example.linkspc.appnhac.R;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();

    }
    //Phương thức khởi tạo đưa cái Fragment vào trong ViewPager và set lên cho cái tabLayout
    public void init()
    {
        MainViewPagerAdapter mainViewPagerAdapter=new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(), "Trang chủ");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"Tìm kiếm");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }
    //Phương thức ánh xạ
    public void anhxa()
    {
        tabLayout=findViewById(R.id.myTabLayout);
        viewPager=findViewById(R.id.myViewPager);
    }
}

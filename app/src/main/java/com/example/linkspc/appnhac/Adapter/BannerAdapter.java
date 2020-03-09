package com.example.linkspc.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linkspc.appnhac.Activity.DanhsachbaihatActivity;
import com.example.linkspc.appnhac.Model.Quangcao;
import com.example.linkspc.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Quangcao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner, null);

        ImageView imagebackgroundbanner=view.findViewById(R.id.imageviewbackgroundbanner);
        ImageView imgsongbanner= view.findViewById(R.id.imageviewbanner);
        TextView txttitlesongbanner=view.findViewById(R.id.textviewTitlebannerBaihat);
        TextView txtnoidung=view.findViewById(R.id.textviewnoidung);

        Picasso.with(context).load(arrayListBanner.get(position).getHinhAnh()).into(imagebackgroundbanner);
        Picasso.with(context).load(arrayListBanner.get(position).getHinhBaiHat()).into(imgsongbanner);
        txttitlesongbanner.setText(arrayListBanner.get(position).getTenBaiHat());
        txtnoidung.setText(arrayListBanner.get(position).getNoiDung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,DanhsachbaihatActivity.class);
                intent.putExtra("banner",arrayListBanner.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

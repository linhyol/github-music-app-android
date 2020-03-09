package com.example.linkspc.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.linkspc.appnhac.Activity.DanhsachbaihatActivity;
import com.example.linkspc.appnhac.Activity.DanhsachtatcachudeActivity;
import com.example.linkspc.appnhac.Activity.DanhsachtheloaitheochudeActivity;
import com.example.linkspc.appnhac.Model.ChuDe;
import com.example.linkspc.appnhac.Model.TheLoai;
import com.example.linkspc.appnhac.Model.Theloaitrongngay;
import com.example.linkspc.appnhac.R;
import com.example.linkspc.appnhac.Service.APIService;
import com.example.linkspc.appnhac.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai_ToDay extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchudetheloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today,container, false);
        horizontalScrollView=view.findViewById(R.id.horizontalScrollview);
        txtxemthemchudetheloai=view.findViewById(R.id.textviewxemthem);
        txtxemthemchudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DanhsachtatcachudeActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice= APIService.getseservice();
        Call<Theloaitrongngay> callback= dataservice.GetCategoryMusic();



        callback.enqueue(new Callback<Theloaitrongngay>() {
            @Override
            public void onResponse(Call<Theloaitrongngay> call, Response<Theloaitrongngay> response) {
                Theloaitrongngay theloaitrongngay= response.body();
                final ArrayList<ChuDe>chuDeArrayList= new ArrayList<>();
                chuDeArrayList.addAll(theloaitrongngay.getChuDe());

                final ArrayList<TheLoai>TheloaiArrayList= new ArrayList<>();
                TheloaiArrayList.addAll(theloaitrongngay.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(linearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(580,250);
                layoutParams.setMargins(10,20,10,30);
                for(int i=0;i<(chuDeArrayList.size());i++)
                {
                    CardView cardView= new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView= new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY) ;
                    if(chuDeArrayList.get(i).getHinhChuDe()!=null)
                    {
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);

                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(),DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("chude",chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });

                }

                for(int j=0;j<(chuDeArrayList.size());j++)
                {
                    CardView cardView= new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView= new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY) ;
                    if(TheloaiArrayList.get(j).getHinhTheLoai()!=null)
                    {
                        Picasso.with(getActivity()).load(TheloaiArrayList.get(j).getHinhTheLoai()).into(imageView);

                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(),DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai",TheloaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<Theloaitrongngay> call, Throwable t) {

            }
        });
    }
}

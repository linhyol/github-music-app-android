package com.example.linkspc.appnhac.Activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.linkspc.appnhac.Adapter.DanhsachbaihatAdapter2;
import com.example.linkspc.appnhac.Model.Album;
import com.example.linkspc.appnhac.Model.Baihat;
import com.example.linkspc.appnhac.Model.Playlist;
import com.example.linkspc.appnhac.Model.Quangcao;
import com.example.linkspc.appnhac.Model.TheLoai;
import com.example.linkspc.appnhac.R;
import com.example.linkspc.appnhac.Service.APIService;
import com.example.linkspc.appnhac.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    Quangcao quangcao;
    ImageView imgdanhsachcakhuc;
    ArrayList<Baihat> mangbaihat;
    DanhsachbaihatAdapter2 danhsachbaihatAdapter2;
    Playlist playlist;
    TheLoai theLoai;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        Anhxa();
        init();
        if (quangcao!=null && !quangcao.getTenBaiHat().equals("")){
            setValueInView(quangcao.getTenBaiHat(),quangcao.getHinhBaiHat());
            GetDataQuangcao(quangcao.getIdQuangCao());
        }
        if (playlist!=null && !playlist.getTen().equals("")){
            setValueInView(playlist.getTen(),playlist.getHinhPlayList());
            getDataPlaylist(playlist.getIdPlayList());
        }
        if (theLoai!=null &&!theLoai.getTenTheLoai().equals("")){
            setValueInView(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
            GetDataTheloai(theLoai.getIdTheLoai());
        }
        if (album!=null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(),album.getHinhanhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
        
    }

    private void GetDataAlbum(String idalbum) {
        Dataservice dataservice = APIService.getseservice();
        Call<List<Baihat>> callback = dataservice.GetDanhsachbaihattheoalbum(idalbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter2 =  new DanhsachbaihatAdapter2(DanhsachbaihatActivity.this,mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter2);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void GetDataTheloai(String idtheloai){
        Dataservice dataservice = APIService.getseservice();
        Call<List<Baihat>> callback = dataservice.GetDanhsachbaihattheotheloai(idtheloai);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter2 =  new DanhsachbaihatAdapter2(DanhsachbaihatActivity.this,mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter2);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
    private void getDataPlaylist(String idplaylist) {
        Dataservice dataservice = APIService.getseservice();
        Call<List<Baihat>> callback = dataservice.GetDanhsachbaihattheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter2 =  new DanhsachbaihatAdapter2(DanhsachbaihatActivity.this,mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter2);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });

    }



    private void setValueInView(String ten,String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url= new URL(hinh);
            Bitmap bitmap= BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable= new BitmapDrawable(getResources(),bitmap);

                collapsingToolbarLayout.setBackground(bitmapDrawable);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgdanhsachcakhuc);
    }
    private void GetDataQuangcao(String idquangcao) {
        Dataservice dataservice=APIService.getseservice();
        Call<List<Baihat>> callback = dataservice.GetDanhsachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat=(ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter2 =  new DanhsachbaihatAdapter2(DanhsachbaihatActivity.this,mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter2);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void Anhxa() {
        coordinatorLayout=findViewById(R.id.coordiatorlayout);
        collapsingToolbarLayout=findViewById(R.id.collapsingtoolbar);
        toolbar=findViewById(R.id.toolbardanhsach);
        recyclerView=findViewById(R.id.recycleviewDanhsachbaihat);
        floatingActionButton=findViewById(R.id.floatingactionbutton);
        imgdanhsachcakhuc=findViewById(R.id.imageviewdanhsachcakhuc);
    }

    private void DataIntent() {
        Intent intent= getIntent();
        if(intent!=null){
            if(intent.hasExtra("banner")){
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
            }
            if (intent.hasExtra("itemplaylist")){
                playlist= (Playlist) intent.getSerializableExtra("itemplaylist");
            }
            if (intent.hasExtra("idtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if (intent.hasExtra("album")){
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }
    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhsachbaihatActivity.this,PlayNhacActivity.class);
                intent.putExtra("cacbaihat",mangbaihat);
                startActivity(intent);
            }
        });

    }
}

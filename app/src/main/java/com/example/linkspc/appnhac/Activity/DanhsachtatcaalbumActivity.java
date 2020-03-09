package com.example.linkspc.appnhac.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.linkspc.appnhac.Adapter.AllalbumAdapter;
import com.example.linkspc.appnhac.Model.Album;
import com.example.linkspc.appnhac.R;
import com.example.linkspc.appnhac.Service.APIService;
import com.example.linkspc.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcaalbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewalbum;
    Toolbar toolbaralbum;
    AllalbumAdapter allalbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcaalbum);
        intit();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getseservice();
        Call<List<Album>>callback = dataservice.GetAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album>mangalbum = (ArrayList<Album>) response.body();
                allalbumAdapter = new AllalbumAdapter(DanhsachtatcaalbumActivity.this,mangalbum);
                recyclerViewalbum.setLayoutManager(new GridLayoutManager(DanhsachtatcaalbumActivity.this,2));
                recyclerViewalbum.setAdapter(allalbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void intit() {
        recyclerViewalbum = findViewById(R.id.recycleviewAllalbum);
        toolbaralbum = findViewById(R.id.toolbaralbum);
        setSupportActionBar(toolbaralbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Album");
        toolbaralbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}

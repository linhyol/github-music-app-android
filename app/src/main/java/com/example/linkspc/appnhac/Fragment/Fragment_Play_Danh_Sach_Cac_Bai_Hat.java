package com.example.linkspc.appnhac.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.linkspc.appnhac.Activity.PlayNhacActivity;
import com.example.linkspc.appnhac.Adapter.PlaynhacAdapter;
import com.example.linkspc.appnhac.R;

public class Fragment_Play_Danh_Sach_Cac_Bai_Hat extends Fragment {
    View view;
    RecyclerView recyclerViewplaynhac;
    PlaynhacAdapter playnhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_danh_sach_cac_bai_hat,container,false);
        recyclerViewplaynhac = view.findViewById(R.id.recycleviewPlaybaihat);
        if (PlayNhacActivity.mangbaihat.size()>0){
            playnhacAdapter = new PlaynhacAdapter(getActivity(), PlayNhacActivity.mangbaihat);
            recyclerViewplaynhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewplaynhac.setAdapter(playnhacAdapter);
        }

        return view;
    }
}

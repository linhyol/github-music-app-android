package com.example.linkspc.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.linkspc.appnhac.Activity.DanhsachbaihatActivity;
import com.example.linkspc.appnhac.Activity.DanhsachcacplaylistActivity;
import com.example.linkspc.appnhac.Adapter.DanhsachbaihatAdapter2;
import com.example.linkspc.appnhac.Adapter.PlaylistAdapter;
import com.example.linkspc.appnhac.Model.Playlist;
import com.example.linkspc.appnhac.R;
import com.example.linkspc.appnhac.Service.APIService;
import com.example.linkspc.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {
    View view;
    ListView lvPlaylist;
    TextView txtTitlePlaylist, txtViewxemthemplaylist;
    PlaylistAdapter playlistAdapter;
    ArrayList<Playlist> mangplaylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        lvPlaylist = view.findViewById(R.id.listviewplaylist);
        txtTitlePlaylist = view.findViewById(R.id.textviewTitlebannerBaihat);
        txtViewxemthemplaylist = view.findViewById(R.id.textviewmoreplaylist);
        GetData();
        txtViewxemthemplaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DanhsachcacplaylistActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getseservice();
        Call<List<Playlist>> callback = dataservice.GetPlayListCurrentDay();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                mangplaylist = (ArrayList<Playlist>) response.body();
                playlistAdapter = new PlaylistAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, mangplaylist);
                lvPlaylist.setAdapter(playlistAdapter);
                setListViewHeightBasedOnChildren(lvPlaylist);
                lvPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent= new Intent(getActivity(),DanhsachbaihatActivity.class);
                        intent.putExtra("itemplaylist",mangplaylist.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    //Code lụm trên stackOverLow vì nếu không có thì cái list view sẽ rất xấu nó không show hết được cái list cho chúng ta
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if(listAdapter == null) return;
        if(listAdapter.getCount() <= 1) return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        int totalHeight = 0;
        View view = null;
        for(int i = 0; i < listAdapter.getCount(); i++)
        {
            view = listAdapter.getView(i, view, listView);
            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}

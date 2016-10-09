package com.lol.lolgame.fragment;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.lol.lolgame.Bean.CardBean;
import com.lol.lolgame.R;
import com.lol.lolgame.adapter.CommRecyclerViewAdapter;
import com.lol.lolgame.interf.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommFragment extends Fragment {
    public static String tag = CommFragment.class.getName();

    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<CardBean> cardList;
    private CommRecyclerViewAdapter myRecyclerViewAdapter;


    public CommFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comm, container, false);
        getData();
        initView(view);
        return view;
    }

    private void initView(View view) {
        myRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_commFragment);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerViewAdapter = new CommRecyclerViewAdapter(cardList, getContext());
        myRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Toast.makeText(getContext(), cardList.get(position).getTitle() + "被点击了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View itemView, int position) {
                Toast.makeText(getContext(), cardList.get(position).getTitle() + "长点。。。。。", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        myRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    public void getData() {
        Resources res = getResources();
        cardList = new ArrayList<>();
        cardList.add(new CardBean(1, "真人秀", res.getDrawable(R.mipmap.community_usershow_default), res.getDrawable(R.mipmap.menu_icon)));
        cardList.add(new CardBean(2, "晒玩法", res.getDrawable(R.mipmap.community_equipment_default), res.getDrawable(R.mipmap.menu_icon)));
        cardList.add(new CardBean(3, "找战友", res.getDrawable(R.mipmap.community_find_friend_default), res.getDrawable(R.mipmap.menu_icon)));
    }
}

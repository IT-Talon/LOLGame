package com.lol.lolgame.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lol.lolgame.Bean.MoreBean;
import com.lol.lolgame.R;
import com.lol.lolgame.adapter.MoreRecyclerViewAdapter;
import com.lol.lolgame.interf.OnItemClickListener;
import com.lol.lolgame.view.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {
    public static String tag = MoreFragment.class.getName();

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MoreRecyclerViewAdapter moreRecyclerViewAdapter;
    private List<MoreBean> moreList;


    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_moreFragment);
        layoutManager = new GridLayoutManager(getContext(), 4, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        // 添加分割线，貌似没效果
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        initData();
        moreRecyclerViewAdapter = new MoreRecyclerViewAdapter(getContext(), moreList);
        moreRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Toast.makeText(getContext(), "position：" + position + "被点击了", Toast.LENGTH_SHORT).show();
                moreRecyclerViewAdapter.removeItem(position);
            }

            @Override
            public boolean onItemLongClick(View itemView, int position) {
                Toast.makeText(getContext(), "position:" + position + "长点了", Toast.LENGTH_SHORT).show();
                moreRecyclerViewAdapter.addItem(position);
                return true;
            }
        });
        mRecyclerView.setAdapter(moreRecyclerViewAdapter);
    }

    private void initData() {
        moreList = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            MoreBean more = new MoreBean(i, getResources().getDrawable(R.mipmap.more_fight_record_icon), "Talon" + i);
            moreList.add(more);
        }
    }

}

package com.lol.lolgame.fragment.videofragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lol.lolgame.Bean.VideoBean;
import com.lol.lolgame.Config.UrlConfig;
import com.lol.lolgame.R;
import com.lol.lolgame.adapter.MyGridViewAdapter;
import com.lol.lolgame.utils.HttpUtil;
import com.lol.lolgame.view.MyGridView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {
    private MyGridView mGridView;
    private List<VideoBean> beanList;


    public CommentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fun, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mGridView = (MyGridView) view.findViewById(R.id.gridView_FunFragment);
        getData();

    }

    public void getData() {
        beanList = new ArrayList<>();
        HttpUtil.getStringAsync(UrlConfig.VIDEO_FUN, new HttpUtil.RequestCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject res = new JSONObject(result);
                    JSONArray data = res.optJSONArray("data");
                    JSONArray videoArray = data.optJSONObject(1).optJSONArray("catword_id");
                    for (int i = 0; i < videoArray.length(); i++) {
                        JSONObject videoObj = videoArray.optJSONObject(i);
                        VideoBean videoBean = new VideoBean();
                        videoBean.setJson(videoObj);
                        beanList.add(videoBean);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFinish() {
                mGridView.setAdapter(new MyGridViewAdapter(getContext(), beanList));
            }
        });
    }

}

package com.lol.lolgame.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.lol.lolgame.Bean.VideoBean;
import com.lol.lolgame.Config.UrlConfig;
import com.lol.lolgame.R;
import com.lol.lolgame.fragment.videofragment.CommentFragment;
import com.lol.lolgame.fragment.videofragment.FunFragment;
import com.lol.lolgame.fragment.videofragment.MatchFragment;
import com.lol.lolgame.utils.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {
    public static String tag = VideoFragment.class.getName();

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private List<List<VideoBean>> videoDataList;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_video, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout_videoFragment);
        mTabLayout.setBackgroundColor(Color.argb(255, 47, 50, 58));
        mTabLayout.setTabTextColors(Color.WHITE, Color.BLUE);
        for (int i = 0; i < 3; i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }
        mViewPager = (ViewPager) view.findViewById(R.id.vp_videoFragment);
        titleList = new ArrayList<>();
        titleList.add("娱乐");
        titleList.add("解说");
        titleList.add("赛事");
        getJsonDataArray();
        mTabLayout.setupWithViewPager(mViewPager);

    }


    public void getJsonDataArray() {
        videoDataList = new ArrayList<>();
        HttpUtil.getStringAsync(UrlConfig.VIDEO_FUN, new HttpUtil.RequestCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    JSONArray dataArray = obj.optJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONArray videoArray = dataArray.optJSONObject(i).optJSONArray("catword_id");
                        List<VideoBean> videoBeanList = new ArrayList<>();
                        for (int j = 0; j < videoArray.length(); j++) {
                            VideoBean videoBean = new VideoBean();
                            videoBean.setJson(videoArray.optJSONObject(j));
                            videoBeanList.add(videoBean);
                        }
                        videoDataList.add(videoBeanList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFinish() {
                fragmentList = new ArrayList<>();
                for (int i = 0; i < videoDataList.size(); i++) {
                    fragmentList.add(new FunFragment().setBeanList(videoDataList.get(i)));
                }


                mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

                    @Override
                    public CharSequence getPageTitle(int position) {
                        return titleList.get(position);
                    }

                    @Override
                    public Fragment getItem(int position) {
                        return fragmentList.get(position);
                    }

                    @Override
                    public int getCount() {
                        return fragmentList.size();
                    }
                });

            }
        });

    }

}

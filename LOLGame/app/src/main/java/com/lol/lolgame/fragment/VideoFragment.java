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

import com.lol.lolgame.R;
import com.lol.lolgame.fragment.videofragment.CommentFragment;
import com.lol.lolgame.fragment.videofragment.FunFragment;
import com.lol.lolgame.fragment.videofragment.MatchFragment;

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
        mTabLayout.setBackgroundColor(Color.argb(255,47,50,58));
        mTabLayout.setTabTextColors(Color.WHITE,Color.BLUE);
        for (int i = 0; i < 3; i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }
        mViewPager = (ViewPager) view.findViewById(R.id.vp_videoFragment);
        fragmentList = new ArrayList<>();
        fragmentList.add(new FunFragment());
        fragmentList.add(new CommentFragment());
        fragmentList.add(new MatchFragment());
        titleList = new ArrayList<>();
        titleList.add("娱乐");
        titleList.add("解说");
        titleList.add("赛事");
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
        mTabLayout.setupWithViewPager(mViewPager);
    }

}

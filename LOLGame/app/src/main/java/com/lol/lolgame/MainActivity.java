package com.lol.lolgame;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.widget.RadioGroup;

import com.lol.lolgame.fragment.CommFragment;
import com.lol.lolgame.fragment.HeroFragment;
import com.lol.lolgame.fragment.InfoFragment;
import com.lol.lolgame.fragment.MoreFragment;
import com.lol.lolgame.fragment.VideoFragment;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup homeMenu;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private CommFragment commFragment;
    private MoreFragment moreFragment;
    private InfoFragment infoFragment;
    private VideoFragment videoFragment;
    private HeroFragment heroFragment;
    private Fragment mShowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        homeMenu = (RadioGroup) findViewById(R.id.rg_bottomMenu);
        homeMenu.setOnCheckedChangeListener(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (mShowFragment!=null){
            fragmentTransaction.show(mShowFragment);
        }else{
            mShowFragment = new InfoFragment();
            fragmentTransaction.add(R.id.container_main, mShowFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_information:
                showFragment(InfoFragment.tag, InfoFragment.class);
                break;
            case R.id.rb_video:
                showFragment(VideoFragment.tag, VideoFragment.class);
                break;
            case R.id.rb_hero:
                showFragment(HeroFragment.tag, HeroFragment.class);
                break;
            case R.id.rb_community:
                showFragment(CommFragment.tag, CommFragment.class);
                break;
            case R.id.rb_more:
                showFragment(MoreFragment.tag, MoreFragment.class);
                break;
            default:
                break;
        }
    }

    private void showFragment(String tag, Class<? extends Fragment> className) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(mShowFragment);
        mShowFragment = fragmentManager.findFragmentByTag(tag);
        if (mShowFragment != null) {
            fragmentTransaction.show(mShowFragment);
        } else {
            try {
                mShowFragment = className.getConstructor().newInstance();
                fragmentTransaction.add(R.id.container_main, mShowFragment, tag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        fragmentTransaction.commit();
    }

}

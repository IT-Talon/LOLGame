package com.lol.lolgame;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lol.lolgame.fragment.CommFragment;
import com.lol.lolgame.fragment.HeroFragment;
import com.lol.lolgame.fragment.InfoFragment;
import com.lol.lolgame.fragment.MoreFragment;
import com.lol.lolgame.fragment.VideoFragment;


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

    private ActionBar actionBar;
    private ImageView imgIcon;
    private TextView tvTitle;
    private ImageView imgRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initActionBar();
    }

    private void initActionBar() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            View view = LayoutInflater.from(this).inflate(R.layout.actionbar, null);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(view);
            ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT);
            imgIcon = (ImageView) findViewById(R.id.img_icon);
            tvTitle = (TextView) findViewById(R.id.tv_title);
            imgRight = (ImageView) findViewById(R.id.img_right);
            actionBar.setCustomView(view, layoutParams);
            Toolbar parent = (Toolbar) view.getParent();
            parent.setContentInsetsAbsolute(0, 0);

            imgIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "侧滑菜单", Toast.LENGTH_SHORT).show();
                }
            });

            imgRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "搜索Activity", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void initView() {
        homeMenu = (RadioGroup) findViewById(R.id.rg_bottomMenu);
        homeMenu.setOnCheckedChangeListener(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (mShowFragment != null) {
            fragmentTransaction.show(mShowFragment);
        } else {
            mShowFragment = new InfoFragment();
            fragmentTransaction.add(R.id.container_main, mShowFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Resources resources = getResources();
        Drawable drawable = null;
        switch (checkedId) {
            case R.id.rb_information:
                drawable = resources.getDrawable(R.mipmap.abc_ic_search_api_mtrl_alpha);
                tvTitle.setText("新闻资讯");
                showFragment(InfoFragment.tag, InfoFragment.class);
                break;
            case R.id.rb_video:
                drawable = resources.getDrawable(R.mipmap.download_icon);
                tvTitle.setText("游戏视频");
                showFragment(VideoFragment.tag, VideoFragment.class);
                break;
            case R.id.rb_hero:
                tvTitle.setText("英雄列表");
                showFragment(HeroFragment.tag, HeroFragment.class);
                break;
            case R.id.rb_community:
                tvTitle.setText("社区");
                showFragment(CommFragment.tag, CommFragment.class);
                break;
            case R.id.rb_more:
                tvTitle.setText("更多");
                showFragment(MoreFragment.tag, MoreFragment.class);
                break;
            default:
                break;
        }
        imgRight.setImageDrawable(drawable);
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

package com.lol.lolgame.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lol.lolgame.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeroFragment extends Fragment {
    public static String tag = HeroFragment.class.getName();


    public HeroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hero, container, false);
    }

}

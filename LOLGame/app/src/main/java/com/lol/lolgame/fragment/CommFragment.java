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
public class CommFragment extends Fragment {
    public static String tag = CommFragment.class.getName();


    public CommFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comm, container, false);
    }

}

package com.example.thebestone.sismaalghozali.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thebestone.sismaalghozali.R;
import com.example.thebestone.sismaalghozali.adapter.RecyclerAdapter;

/**
 * Created by thebestone on 04/02/18.
 */

public class FragmentBeranda extends Fragment {

    RecyclerView mRecycler;
    RecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_beranda, null);

        initView(v);

        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setHasFixedSize(true);
        mRecycler.setAdapter(adapter);

        return v;
    }

    private void initView(View v) {
        mRecycler = v.findViewById(R.id.recyclerBeranda);
        adapter = new RecyclerAdapter();
    }
}

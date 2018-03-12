package com.example.thebestone.sismaalghozali.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thebestone.sismaalghozali.R;
import com.example.thebestone.sismaalghozali.model.Post;

import java.util.List;

/**
 * Created by thebestone on 04/02/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<Post> posts;
    Activity activity;

    int dummy = 10;

//    public RecyclerAdapter(List<Post> posts, Activity activity) {
//        this.posts = posts;
//        this.activity = activity;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_bereanda, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dummy;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

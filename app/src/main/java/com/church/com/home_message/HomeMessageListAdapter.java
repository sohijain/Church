package com.church.com.home_message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.church.com.R;

import java.util.List;

public class HomeMessageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<HomeMessageBean> movies;


    public HomeMessageListAdapter(Context context, List<HomeMessageBean> movies) {
        mContext = context;
        this.movies = movies;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new MessageHolder(inflater.inflate(R.layout.item_home_message, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        //No else part needed as load holder doesn't bind any data
    }


    @Override
    public int getItemCount() {
        return 4;
    }


    /* VIEW HOLDERS */

    public class MessageHolder extends RecyclerView.ViewHolder {

        public MessageHolder(View itemView) {
            super(itemView);

        }
    }

}

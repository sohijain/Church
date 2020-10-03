package com.church.com.home_message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.church.com.R;

import java.util.List;

public class HomeMessageListAdapter extends RecyclerView.Adapter<HomeMessageListAdapter.MessageHomeHolder> {


    private Context mContext;
    private List<HomeMessageBean> movies;
    private IClickHomeMessageDetailed mIClickHomeMessageDetailed;


    public HomeMessageListAdapter(Context context, List<HomeMessageBean> movies, IClickHomeMessageDetailed aIClickHomeMessageDetailed) {
        mContext = context;
        this.movies = movies;
        this.mIClickHomeMessageDetailed = aIClickHomeMessageDetailed;
    }


    @Override
    public MessageHomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new MessageHomeHolder(inflater.inflate(R.layout.item_home_message, parent, false));

    }

    @Override
    public void onBindViewHolder(MessageHomeHolder holder, final int position) {

        holder.mCvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickHomeMessageDetailed.IClickHomeMessageDetailed();
            }
        });
        //No else part needed as load holder doesn't bind any data
    }


    @Override
    public int getItemCount() {
        return 4;
    }


    /* VIEW HOLDERS */

    public static class MessageHomeHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout mCvClick;

        public MessageHomeHolder(View itemView) {
            super(itemView);

            mCvClick = itemView.findViewById(R.id.cvClick);

        }
    }

}

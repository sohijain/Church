package com.church.com.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.church.com.R;

import java.util.List;

public class WatchListAdapter extends RecyclerView.Adapter<WatchListAdapter.WatchHolder> {


    private Context mContext;
    private List<WatchBean> mWatchList;
    private IClickHomeDetailed mIClickHomeDetailed;


    public WatchListAdapter(Context context, List<WatchBean> aWatchList, IClickHomeDetailed aIClickHomeDetailed) {
        mContext = context;
        this.mWatchList = aWatchList;
        this.mIClickHomeDetailed = aIClickHomeDetailed;
    }


    @Override
    public WatchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new WatchHolder(inflater.inflate(R.layout.item_watch_list, parent, false));

    }

    @Override
    public void onBindViewHolder(WatchHolder holder, final int position) {


        holder.mTvTitle.setText(mWatchList.get(position).getStrText());

        holder.mCLSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickHomeDetailed.IClickHomeDetailed(mWatchList.get(position));
            }
        });
        //No else part needed as load holder doesn't bind any data
    }


    @Override
    public int getItemCount() {
        return mWatchList.size();
    }


    /* VIEW HOLDERS */

    public static class WatchHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout mCLSmall;

        private TextView mTvTitle;

        public WatchHolder(View itemView) {
            super(itemView);

            mCLSmall = itemView.findViewById(R.id.mClSmallBg);
            mTvTitle = itemView.findViewById(R.id.tvWatchTitle);

        }
    }

}

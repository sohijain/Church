package com.church.com.bible;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.church.com.R;

import java.util.List;

public class BibleListAdapter extends RecyclerView.Adapter<BibleListAdapter.BibleHolder> {


    private Context mContext;
    private List<BibleBean> mWatchList;
    private IClickBibleDetailed mIClickBibleDetailed;


    public BibleListAdapter(Context context, List<BibleBean> aWatchList, IClickBibleDetailed aIClickBibleDetailed) {
        mContext = context;
        this.mWatchList = aWatchList;
        this.mIClickBibleDetailed = aIClickBibleDetailed;
    }


    @Override
    public BibleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new BibleHolder(inflater.inflate(R.layout.item_bible_list, parent, false));

    }

    @Override
    public void onBindViewHolder(BibleHolder holder, final int position) {


        holder.mCLMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickBibleDetailed.IClickBibleDetailed();
            }
        });

        //No else part needed as load holder doesn't bind any data
    }


    @Override
    public int getItemCount() {
        //return mWatchList.size();
        return 7;
    }


    /* VIEW HOLDERS */

    public static class BibleHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout mCLMain;

        public BibleHolder(View itemView) {
            super(itemView);
            mCLMain = itemView.findViewById(R.id.clMain);
        }
    }

}

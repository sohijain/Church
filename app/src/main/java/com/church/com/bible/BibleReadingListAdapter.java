package com.church.com.bible;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.church.com.R;

import java.util.List;

public class BibleReadingListAdapter extends RecyclerView.Adapter<BibleReadingListAdapter.BibleReadingHolder> {


    private Context mContext;
    private List<BibleBean> mWatchList;
    private IClickBibleDetailed mIClickBibleDetailed;


    public BibleReadingListAdapter(Context context, List<BibleBean> aWatchList,IClickBibleDetailed aIClickBibleDetailed) {
        mContext = context;
        this.mWatchList = aWatchList;
        this.mIClickBibleDetailed = aIClickBibleDetailed;
    }


    @Override
    public BibleReadingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new BibleReadingHolder(inflater.inflate(R.layout.reading_plan_list_item, parent, false));

    }

    @Override
    public void onBindViewHolder(BibleReadingHolder holder, final int position) {

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
        return 7;
    }


    /* VIEW HOLDERS */

    public static class BibleReadingHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout mCLMain;

        public BibleReadingHolder(View itemView) {
            super(itemView);
            mCLMain = itemView.findViewById(R.id.clHeader);
        }
    }

}

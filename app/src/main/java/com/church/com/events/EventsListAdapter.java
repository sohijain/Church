package com.church.com.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.church.com.R;

import java.util.List;

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.EventsHolder> {


    private Context mContext;
    private List<EventsBean> mWatchList;
    private IEventsClick mIEventsClick;


    public EventsListAdapter(Context context, List<EventsBean> aWatchList, IEventsClick aIEventsClick) {
        mContext = context;
        this.mWatchList = aWatchList;
        this.mIEventsClick = aIEventsClick;
    }


    @Override
    public EventsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new EventsHolder(inflater.inflate(R.layout.item_events_list_item, parent, false));

    }

    @Override
    public void onBindViewHolder(EventsHolder holder, final int position) {


        holder.clHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIEventsClick.IEventsClick();
            }
        });
    }


    @Override
    public int getItemCount() {
        //return mWatchList.size();
        return 7;
    }


    /* VIEW HOLDERS */

    public static class EventsHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout clHeader;

        public EventsHolder(View itemView) {
            super(itemView);

            clHeader = itemView.findViewById(R.id.clHeader);

        }
    }

}

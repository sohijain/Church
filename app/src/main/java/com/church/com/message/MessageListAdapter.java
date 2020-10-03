package com.church.com.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.church.com.R;
import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    static Context mContext;
    List<MessageBean> movies;


    public MessageListAdapter(Context context, List<MessageBean> movies) {
        mContext = context;
        this.movies = movies;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new MessageHolder(inflater.inflate(R.layout.message_list_item, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        //No else part needed as load holder doesn't bind any data
    }


    @Override
    public int getItemCount() {
       // return movies.size();
        return 3;
    }


    /* VIEW HOLDERS */

    public class MessageHolder extends RecyclerView.ViewHolder {
        private TextView mClearConnectiontext;
        private TextView tvMessageTimestamp;
        private TextView tvMessageName;
        private TextView tvMessageText;

        public MessageHolder(View itemView) {
            super(itemView);
            mClearConnectiontext = itemView.findViewById(R.id.mClearConnectiontext);
            tvMessageTimestamp = itemView.findViewById(R.id.tvMessageTimestamp);
            tvMessageName = itemView.findViewById(R.id.tvMessageName);
            tvMessageText = itemView.findViewById(R.id.tvMessageText);

        }
    }

}

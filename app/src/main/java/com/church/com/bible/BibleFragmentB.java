package com.church.com.bible;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.church.com.R;
import com.church.com.screens.ActivityBibleDetailed;

import java.util.ArrayList;
import java.util.List;

public class BibleFragmentB extends Fragment implements IClickBibleDetailed{

    private Context mContext;
    private View view;
    private RecyclerView recyclerView;
    private BibleReadingListAdapter mBibleReadingListAdapter;
    private List<BibleBean> mWatchList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bible_b, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setList();
    }

    private void setList() {

        recyclerView = view.findViewById(R.id.mRvbibleBList);

        mWatchList = new ArrayList<>();
        mBibleReadingListAdapter = new BibleReadingListAdapter(mContext, mWatchList,this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mBibleReadingListAdapter);

    }

    @Override
    public void IClickBibleDetailed() {
        Intent intent = new Intent(mContext, ActivityBibleDetailed.class);
        startActivity(intent);
    }
}

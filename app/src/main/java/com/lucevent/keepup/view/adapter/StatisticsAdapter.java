package com.lucevent.keepup.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.keepup.R;
import com.lucevent.keepup.kernel.stats.Statistics;
import com.lucevent.keepup.view.adapter.viewholder.StatisticsViewHolder;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsViewHolder> {

    private Statistics dataSet;

    public StatisticsAdapter()
    {
    }

    @Override
    public StatisticsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_statistics, parent, false);
        return new StatisticsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StatisticsViewHolder holder, int position)
    {
        //     StatisticsViewHolder.populateViewHolder(holder, dataSet.siteStats.get(position));
    }

    @Override
    public int getItemCount()
    {
        return dataSet == null ? 0 : /*dataSet.siteStats.size()*/0;
    }

    public void setNewDataSet(Statistics dataSet)
    {
        //     notifyItemRangeRemoved(0, getItemCount());
        //      this.dataSet = dataSet;
        //     notifyItemRangeInserted(0, this.dataSet.siteStats.size());
    }

}

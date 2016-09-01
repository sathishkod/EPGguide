package in.sathishkod.epgguide.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.sathishkod.epgguide.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sathishkumar.P07 on 8/8/2016.
 */
public class ChannelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> timeList;

    public ChannelAdapter(List<String> timeList) {
        this.timeList = new ArrayList<>();
        this.timeList.addAll(timeList);
    }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tile_time,parent,false);
        return new ChannelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChannelViewHolder vh = (ChannelViewHolder) holder;
        vh.tvTime.setText(timeList.get(position));
        ViewGroup.LayoutParams layoutParams = vh.containerLayout.getLayoutParams();
        layoutParams.height = 150;
        layoutParams.width = 270;
        vh.containerLayout.setLayoutParams(layoutParams);
        vh.containerLayout.setBackgroundColor(getRandomColor());
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public static class ChannelViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout containerLayout;
        private TextView tvTime;

        public ChannelViewHolder(View itemView) {
            super(itemView);
            containerLayout = (RelativeLayout) itemView.findViewById(R.id.ll_time_container);
            tvTime = (TextView) itemView.findViewById(R.id.tv_tile_time);
        }
    }

    private int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}

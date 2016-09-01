package in.sathishkod.epgguide;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.sathishkod.epgguide.R;

/**
 * Created by Sathishkumar.P07 on 7/25/2016.
 */
public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.TimeViewHolder> {

    private List<String> timeList;

    public TimeAdapter(List<String> timeList) {
        this.timeList = new ArrayList<>();
        this.timeList.addAll(timeList);
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tile_time,parent,false);
        return new TimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimeViewHolder holder, int position) {
        holder.tvTime.setText(timeList.get(position));
        ViewGroup.LayoutParams layoutParams = holder.containerLayout.getLayoutParams();
        layoutParams.height = 100;
        layoutParams.width = 500;
        holder.containerLayout.setLayoutParams(layoutParams);

    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public static class TimeViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout containerLayout;
        private TextView tvTime;

        public TimeViewHolder(View itemView) {
            super(itemView);
            containerLayout = (LinearLayout) itemView.findViewById(R.id.ll_time_container);
            tvTime = (TextView) itemView.findViewById(R.id.tv_tile_time);
        }
    }
}

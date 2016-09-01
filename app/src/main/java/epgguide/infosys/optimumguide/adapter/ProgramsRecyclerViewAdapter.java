package epgguide.infosys.optimumguide.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import epgguide.infosys.optimumguide.Program;
import in.sathishkod.epgguide.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sathishkumar.P07 on 7/20/2016.
 */
public class ProgramsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String LOG_TAG = "ProgramsAdapter";
    private List<Program> programList;
    private OnItemClickListener mItemClickListener;

    public ProgramsRecyclerViewAdapter(List<Program> programList) {
        this.programList = new ArrayList<>();
        this.programList.addAll(programList);
    }

    public ProgramsRecyclerViewAdapter() {
        super();
    }

    @Override
    public ProgramViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.tile_program, parent, false);
        return new ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ProgramViewHolder vh = (ProgramViewHolder) holder;
        ViewGroup.LayoutParams layoutParams = vh.rlContainer.getLayoutParams();
        Program program = programList.get(position);
        vh.tvProgramTitle.setText(program.getTitle());
        vh.tvProgramDesc.setText(program.getDescription());
        layoutParams.height = 150;

        if (program.isNew()) {
            vh.tvNew.setText("New");
            layoutParams.width = 500;

        } else vh.tvNew.setText("");
        vh.rlContainer.setLayoutParams(layoutParams);
        vh.rlContainer.setBackgroundColor(getRandomColor());
    }

    @Override
    public int getItemCount() {
        return programList.size();
    }

    private class ProgramViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvProgramTitle, tvProgramDesc, tvNew;
        private RelativeLayout rlContainer;

        public ProgramViewHolder(View itemView) {
            super(itemView);
            rlContainer = (RelativeLayout) itemView.findViewById(R.id.rl_program_container);
            tvProgramTitle = (TextView) itemView.findViewById(R.id.tv_program_title);
            tvProgramDesc = (TextView) itemView.findViewById(R.id.tv_program_desc);
            tvNew = (TextView) itemView.findViewById(R.id.tv_new);
            rlContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, getAdapterPosition(), programList.get(getAdapterPosition()));
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Program program);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}

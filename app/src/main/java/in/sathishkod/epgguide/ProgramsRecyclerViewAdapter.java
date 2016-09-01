package in.sathishkod.epgguide;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import in.sathishkod.epgguide.R;

/**
 * Created by Sathishkumar.P07 on 7/20/2016.
 */
public class ProgramsRecyclerViewAdapter extends RecyclerView.Adapter<ProgramsRecyclerViewAdapter.ProgramViewHolder> {

    private List<Program> programList;

    public ProgramsRecyclerViewAdapter(List<Program> programList) {
        this.programList = new ArrayList<>();
        this.programList.addAll(programList);
    }

    @Override
    public ProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.tile_program, parent, false);
        return new ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProgramViewHolder holder, int position) {

        ViewGroup.LayoutParams layoutParams = holder.rlContainer.getLayoutParams();

            Program program = programList.get(position);
            holder.tvProgramTitle.setText(program.getTitle());
            holder.tvProgramDesc.setText(program.getDescription());
        layoutParams.height = 150;

            if (program.isNew()) {
                holder.tvNew.setText("New");
                layoutParams.width = 500;

            } else holder.tvNew.setText("");
        holder.rlContainer.setLayoutParams(layoutParams);
        holder.rlContainer.setBackgroundColor(getRandomColor());
    }

    @Override
    public int getItemCount() {
        return programList.size();
    }

    public static class ProgramViewHolder extends RecyclerView.ViewHolder{
        private TextView tvProgramTitle, tvProgramDesc, tvNew;
        private RelativeLayout rlContainer;

        public ProgramViewHolder(View itemView) {
            super(itemView);
            rlContainer = (RelativeLayout) itemView.findViewById(R.id.rl_program_container);
            tvProgramTitle = (TextView) itemView.findViewById(R.id.tv_program_title);
            tvProgramDesc = (TextView) itemView.findViewById(R.id.tv_program_desc);
            tvNew = (TextView) itemView.findViewById(R.id.tv_new);
        }
    }

    private int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}

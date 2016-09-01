package in.sathishkod.epgguide.adapter;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import in.sathishkod.epgguide.Program;
import in.sathishkod.epgguide.ui.customRecyclerView.DividerItemDecoration;
import in.sathishkod.epgguide.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sathishkumar.P07 on 7/21/2016.
 */
public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String LOG_TAG = "VerticalRecyViewAdapter";
    private static final int PROGRAM_COUNT = 20;
    private List<String> channelList;
    private ProgramsRecyclerViewAdapter.OnItemClickListener mItemClickListener;

    public VerticalRecyclerViewAdapter(List<String> channelList) {
        this.channelList = new ArrayList<String>();
        this.channelList.addAll(channelList);
    }

    @Override
    public VerticalRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.verticle_list_item, parent, false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder vh = (ViewHolder) holder;
//        if (position == 0){
//            List<String> timeList = new ArrayList<>();
//            timeList.addAll(getTimeList());
//            TimeAdapter mAdapter = new TimeAdapter(timeList);
//            vh.rcvHorizontal.setAdapter(mAdapter);
//        }else {
            List<Program> programList = new ArrayList<>();
            programList.addAll(getTestData(channelList.get(position)));
            ProgramsRecyclerViewAdapter mAdapter = new ProgramsRecyclerViewAdapter(programList);
            vh.rcvHorizontal.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(mItemClickListener);
//        }
    }

    @Override
    public int getItemCount() {
        return channelList.size();
    }

    private List<Program> getTestData(String channel){
        List<Program> testDataList = new ArrayList<>();
        for (int i=0; i < PROGRAM_COUNT; i++){
            Program program = new Program();
            Random random = new Random();
            int randNumb = random.nextInt();
            program.setTitle("T"+randNumb);
            program.setDescription("Desc-"+randNumb);
            program.setNew(random.nextBoolean());
            program.setChannel(channel);
            testDataList.add(program);
        }
        return testDataList;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView rcvHorizontal;
        private LinearLayout rcvContainer;
        public ViewHolder(View itemView) {
            super(itemView);
            rcvContainer = (LinearLayout) itemView.findViewById(R.id.ll_rcv_container);
            rcvHorizontal = (RecyclerView) itemView.findViewById(R.id.rcv_horizontal);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                    itemView.getContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false);
            Drawable dividerDrawable = ContextCompat.getDrawable(itemView.getContext(),
                    R.drawable.divider_sample);
            rcvHorizontal.addItemDecoration(new DividerItemDecoration(dividerDrawable));
            rcvHorizontal.setHasFixedSize(false);
            rcvHorizontal.setLayoutManager(layoutManager);
        }
    }

    public void setOnItemClickListener(ProgramsRecyclerViewAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}

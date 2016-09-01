package in.sathishkod.epgguide;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import in.sathishkod.epgguide.R;

/**
 * Created by Sathishkumar.P07 on 7/21/2016.
 */
public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.ViewHolder> {

    private static final int PROGRAM_COUNT = 20;
    private List<String> channelList;

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
    public void onBindViewHolder(VerticalRecyclerViewAdapter.ViewHolder holder, int position) {

        RecyclerView.Adapter mAdapter;
        if (position == 0){
            List<String> timeList = new ArrayList<>();
            timeList.addAll(getTimeList());
            mAdapter = new TimeAdapter(timeList);
        }else {
            List<Program> programList = new ArrayList<>();
            programList.addAll(getTestData());
            mAdapter = new ProgramsRecyclerViewAdapter(programList);
        }
        holder.rcvHorizontal.setAdapter(mAdapter);

    }

    @Override
    public int getItemCount() {
        return channelList.size();
    }

    private List<Program> getTestData(){
        List<Program> testDataList = new ArrayList<>();
        for (int i=0; i < PROGRAM_COUNT; i++){
            Program program = new Program();
            Random random = new Random();
            int randNumb = random.nextInt();
            program.setTitle("T"+randNumb);
            program.setDescription("Desc-"+randNumb);
            program.setNew(random.nextBoolean());
            testDataList.add(program);
        }
        return testDataList;
    }

    private List<String> getTimeList(){
        List<String> timeList = new ArrayList<>();
        String[] time = {"12:00","12:30","1:00","1:30","2:00","2:30","3:00","3:30", "4:00","4:30",
                "5:00","5:30","6:00","6:30","7:00","7:30","8:00","8:30", "9:00","9:30","10:00",
                "10:30","11:00","11:30"};
        for (String t:time) {
            timeList.add(t + "A");
        }
        for (String t:time) {
            timeList.add(t + "P");
        }
        return timeList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView rcvHorizontal;
        public ViewHolder(View itemView) {
            super(itemView);
            rcvHorizontal = (RecyclerView) itemView.findViewById(R.id.rcv_horizontal);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                    itemView.getContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false);
            rcvHorizontal.addItemDecoration(new ListItemDividerDecorator(itemView.getContext(),
                    LinearLayoutManager.VERTICAL));
            rcvHorizontal.setHasFixedSize(false);
            rcvHorizontal.setLayoutManager(layoutManager);
        }
    }
}

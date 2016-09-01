package epgguide.infosys.optimumguide;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import epgguide.infosys.optimumguide.adapter.ChannelAdapter;
import epgguide.infosys.optimumguide.adapter.ProgramsRecyclerViewAdapter;
import epgguide.infosys.optimumguide.adapter.TimeAdapter;
import epgguide.infosys.optimumguide.adapter.VerticalRecyclerViewAdapter;
import epgguide.infosys.optimumguide.ui.customRecyclerView.DividerItemDecoration;
import epgguide.infosys.optimumguide.ui.customRecyclerView.SelfRemovingOnScrollListener;

import java.util.ArrayList;
import java.util.List;

import in.sathishkod.epgguide.R;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "MainActivity";
    private final int CHANNEL_SIZE = 100;

    private RecyclerView channelsRecyclerView, programsRecyclerView, headerRecyclerView;
    private VerticalRecyclerViewAdapter mAdapter;
    private RecyclerView.Adapter timeAdapter, channelAdapter;
    private List<String> channelList, timeList;

    private boolean channelScrolling = false;
    private boolean programScrolling = false;

    private final RecyclerView.OnScrollListener channelScrollListener = new SelfRemovingOnScrollListener() {
        @Override
        public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
            super.onScrolled(recyclerView, dx, dy);
            Log.d("onScrolled Channels -","X = "+dx+" Y = "+dy);
            programsRecyclerView.scrollBy(dx, dy);
        }
    }, programScrollListener = new SelfRemovingOnScrollListener() {

        @Override
        public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
            super.onScrolled(recyclerView, dx, dy);
            Log.d("onScrolled Programs -","X = "+dx+" Y = "+dy);
            channelsRecyclerView.scrollBy(dx, dy);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        programsRecyclerView = (RecyclerView) findViewById(R.id.rcv_vertical);
        headerRecyclerView = (RecyclerView) findViewById(R.id.rcv_vertical_header);
        channelsRecyclerView = (RecyclerView) findViewById(R.id.rcv_channel_name);
        //header recyclerview preparation
        timeList = new ArrayList<>();
        timeList.addAll(getTimeList());
        timeAdapter = new TimeAdapter(timeList);
        RecyclerView.LayoutManager headerLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,
                false);
        headerRecyclerView.setLayoutManager(headerLayoutManager);
        Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_sample);
        headerRecyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
        headerRecyclerView.setAdapter(timeAdapter);

        //Channel recyclerview preparation
        channelAdapter = new ChannelAdapter(getChannelList());
        final RecyclerView.LayoutManager channelLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false);
        channelsRecyclerView.setLayoutManager(channelLayoutManager);
        channelsRecyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
        channelsRecyclerView.setAdapter(channelAdapter);

        //program recyclerview preparation
        mAdapter = new VerticalRecyclerViewAdapter(getChannelList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false);

        programsRecyclerView.setLayoutManager(layoutManager);
        programsRecyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
        programsRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ProgramsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Program program) {
                Toast.makeText(getApplicationContext(),
                        "Program-" + position +" Channel-"+program.getChannel(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Sync channel name RCV and Programs RCV scrolling
        channelsRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            private int mLastY;

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                Log.d("debug", "LEFT: onInterceptTouchEvent");

                final Boolean ret = rv.getScrollState() != RecyclerView.SCROLL_STATE_IDLE;
                if (!ret) {
                    onTouchEvent(rv, e);
                }
                return Boolean.FALSE;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                Log.d("debug", "LEFT: onTouchEvent");
                final int action;
                if ((action = e.getAction()) == MotionEvent.ACTION_DOWN && programsRecyclerView
                        .getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastY = rv.getScrollY();
                    Log.d("scroll","channelsRecyclerView Y: "+mLastY);
                    rv.addOnScrollListener(channelScrollListener);
                }
                else {
                    if (action == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
                        rv.removeOnScrollListener(channelScrollListener);
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                Log.d("debug", "LEFT: onRequestDisallowInterceptTouchEvent");
            }
        });

        programsRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            private int mLastY;

            @Override
            public boolean onInterceptTouchEvent(@NonNull final RecyclerView rv, @NonNull final
            MotionEvent e) {
                Log.d("debug", "RIGHT: onInterceptTouchEvent");

                final Boolean ret = rv.getScrollState() != RecyclerView.SCROLL_STATE_IDLE;
                if (!ret) {
                    onTouchEvent(rv, e);
                }
                return Boolean.FALSE;
            }

            @Override
            public void onTouchEvent(@NonNull final RecyclerView rv, @NonNull final MotionEvent e) {
                Log.d("debug", "RIGHT: onTouchEvent");

                final int action;
                if ((action = e.getAction()) == MotionEvent.ACTION_DOWN && channelsRecyclerView
                        .getScrollState
                                () == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastY = rv.getScrollY();
                    rv.addOnScrollListener(programScrollListener);
                    Log.d("scroll","programsRecyclerView Y: "+mLastY);
                }
                else {
                    if (action == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
                        rv.removeOnScrollListener(programScrollListener);
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(final boolean disallowIntercept) {
                Log.d("debug", "RIGHT: onRequestDisallowInterceptTouchEvent");
            }
        });

    }

    private List<String> getChannelList(){
        List<String> channelList = new ArrayList<>();
        for (int i=0; i<CHANNEL_SIZE; i++){
            channelList.add("C-"+i);
        }
        return channelList;
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
}

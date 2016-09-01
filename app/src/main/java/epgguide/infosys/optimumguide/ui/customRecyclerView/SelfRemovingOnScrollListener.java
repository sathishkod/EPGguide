package epgguide.infosys.optimumguide.ui.customRecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by Sathishkumar.P07 on 8/8/2016.
 */
public class SelfRemovingOnScrollListener extends RecyclerView.OnScrollListener {

    @Override
    public final void onScrollStateChanged(@NonNull final RecyclerView recyclerView, final int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            recyclerView.removeOnScrollListener(this);
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        Log.d("SRSL OnScrolled","X = "+dx+" Y = "+dy);
    }
}

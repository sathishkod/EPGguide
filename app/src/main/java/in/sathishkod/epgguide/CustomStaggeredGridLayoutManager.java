package in.sathishkod.epgguide;

import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by Sathishkumar.P07 on 7/21/2016.
 */
public class CustomStaggeredGridLayoutManager extends StaggeredGridLayoutManager {

    public CustomStaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }
}

package epgguide.infosys.optimumguide.epg;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by Sathishkumar.P07 on 8/22/2016.
 */
public class EpgView extends ViewGroup {

    private static final String TAG = "EpgView";

    public EpgView(Context context) {
        this(context, null, 0);
    }

    public EpgView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EpgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EpgView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

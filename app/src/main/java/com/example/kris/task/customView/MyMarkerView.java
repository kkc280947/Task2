package com.example.kris.task.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.TextView;

import com.example.kris.task.R;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

/**
 * Created by kris on 02/03/17.
 */

public class MyMarkerView extends com.github.mikephil.charting.components.MarkerView {

    private TextView tvpop;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public MyMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        tvpop=(TextView) findViewById(R.id.tvPopUp);
    }
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        tvpop.setText("" + e.getY());

        // this will perform necessary layouting
        super.refreshContent(e, highlight);
    }

    private MPPointF mOffset;

    @Override
    public MPPointF getOffset() {

        if(mOffset == null) {
            // center the marker horizontally and vertically
            mOffset = new MPPointF(-(getWidth() / 3), -getHeight());
        }

        return mOffset;
    }
}

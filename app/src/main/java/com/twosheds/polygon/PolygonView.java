package com.twosheds.polygon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by izzat on 11/15/15.
 */
public class PolygonView extends View {
    private int mCenterX;
    private int mCenterY;
    private float mRadius;

    private int mNumSides;

    private Paint mPaint;

    public PolygonView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mNumSides = 4;
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(3.0f);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        mCenterX = w/2;
        mCenterY = h/2;
        mRadius = Math.min(w, h) / 3;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i=1; i<mNumSides; i++) {
            double angle = (2 * Math.PI) / (mNumSides);

            float x1 = (float) (mCenterX + mRadius * Math.cos((i-1) * angle));
            float y1 = (float) (mCenterY + mRadius * Math.sin((i - 1) * angle));
            float x2 = (float) (mCenterX + mRadius * Math.cos(i * angle));
            float y2 = (float) (mCenterY + mRadius * Math.sin(i * angle));

            canvas.drawLine(x1, y1, x2, y2, mPaint);
        }
    }
}

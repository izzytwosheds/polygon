package com.twosheds.polygon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by izzat on 11/15/15.
 */
public class PolygonView extends View {
    private int mCenterX;
    private int mCenterY;
    private float mRadius;
    private Paint mPaint;

    private int mNumSides;

    private double mAngle;

    public PolygonView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mNumSides = 4;
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(6.0f);

        mAngle = 0;
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
            double delta = (2 * Math.PI) / (mNumSides);

            float x1 = (float) (mCenterX + mRadius * Math.cos(mAngle + (i-1) * delta));
            float y1 = (float) (mCenterY + mRadius * Math.sin(mAngle + (i-1) * delta));
            float x2 = (float) (mCenterX + mRadius * Math.cos(mAngle + i * delta));
            float y2 = (float) (mCenterY + mRadius * Math.sin(mAngle + i * delta));

            canvas.drawLine(x1, y1, x2, y2, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                if (event.getHistorySize() > 1) {
                    float deltaX = event.getX() - event.getHistoricalX(1);
                    double alpha = deltaX * 2 * Math.PI / mCenterX;

                    mAngle += alpha;

                    postInvalidate();
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    void setNumSides(int numSides) {
        if (mNumSides != numSides) {
            mNumSides = numSides;
            postInvalidate();
        }
    }
}

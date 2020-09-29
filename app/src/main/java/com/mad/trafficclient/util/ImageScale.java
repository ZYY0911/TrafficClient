package com.mad.trafficclient.util;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ImageScale implements View.OnTouchListener {
    private ImageView imageView;
    private Matrix matrix = new Matrix();
    private Matrix current = new Matrix();
    private PointF start = new PointF();
    private int NONE = 9;
    private int mode = NONE, DROP = 1, ZOOM = 5;
    private float dis;
    private PointF mPointF;
    private GestureDetector detector;

    public ImageScale(final ImageView imageView) {
        this.imageView = imageView;
        detector = new GestureDetector(new GestureDetector.SimpleOnGestureListener());
        detector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (imageView.getScaleX() != 1) {
                    imageView.setScaleY(1);
                    imageView.setScaleX(1);
                } else {
                    imageView.setScaleY(3);
                    imageView.setScaleX(3);
                }
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        });
    }

    public float tance(MotionEvent event) {
        float dx = event.getX(1) - event.getX(0);
        float dy = event.getY(1) - event.getY(0);
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public static PointF tance1(MotionEvent event) {
        float dx = (event.getX(0) + event.getX(1)) / 2;
        float dy = (event.getY(0) + event.getY(1)) / 2;
        return new PointF(dx, dy);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        detector.onTouchEvent(event);
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mode = DROP;
                matrix.set(imageView.getImageMatrix());
                current.set(imageView.getImageMatrix());
                start.set(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DROP) {
                    float dx = event.getX() - start.x;
                    float dy = event.getY() - start.y;
                    matrix.set(current);
                    matrix.postTranslate(dx, dy);
                } else if (mode == ZOOM) {
                    float end = tance(event);
                    if (end > 10f) {
                        float sca = end / dis;
                        matrix.set(current);
                        matrix.postScale(sca, sca, mPointF.x, mPointF.y);
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mode = 0;
                break;
            case MotionEvent.ACTION_UP:
                mode = NONE;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mode = ZOOM;
                dis = tance(event);
                if (dis > 10) {
                    mPointF = tance1(event);
                    current.set(imageView.getImageMatrix());
                }
        }
        imageView.setImageMatrix(matrix);
        return true;
    }
}

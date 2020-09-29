package com.mad.trafficclient.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-09-29 at 15:45
 */
public class MyHorizontalScrollView extends HorizontalScrollView {
    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public interface OnMyScrollChanged {
        void scrollChanged(int scrollY);
    }

    private OnMyScrollChanged myScrollChanged;

    public void setMyScrollChanged(OnMyScrollChanged myScrollChanged) {
        this.myScrollChanged = myScrollChanged;
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (myScrollChanged != null) {
            myScrollChanged.scrollChanged(l);
        }
    }
}

package com.coderpig.workfirst.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

public class NestedScrollLayout extends NestedScrollView {
    public NestedScrollLayout(@NonNull Context context) {
        super(context);
    }

    public NestedScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private ViewGroup mcontentView;
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mcontentView = (ViewGroup) getChildAt(0);
        mcontentView = (ViewGroup) mcontentView.getChildAt(1);
        topView = ((ViewGroup) getChildAt(0)).getChildAt(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams layoutParams = mcontentView.getLayoutParams();
        layoutParams.height = getMeasuredHeight();
        mcontentView.setLayoutParams(layoutParams);
    }

    private View topView;

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(target, dx, dy, consumed, type);
        boolean hideTop = dy > 0 && getScrollY() < topView.getMeasuredHeight();
        if (hideTop) {
            scrollBy(0, dy) ;
            consumed[1] = dy;
        }
    }
}


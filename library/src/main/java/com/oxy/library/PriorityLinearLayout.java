package com.oxy.library;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PriorityLinearLayout extends LinearLayout {
    private List<IndexInfo> indexs = new ArrayList<>();
    private boolean isMeasureing = false;
    private Comparator<IndexInfo> comparator = new Comparator<IndexInfo>() {
        @Override
        public int compare(IndexInfo o1, IndexInfo o2) {
            return o2.priority - o1.priority;
        }
    };
    public PriorityLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public PriorityLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new PriorityLinearLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        refreshIndexs();
        isMeasureing = true;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        isMeasureing = false;
    }

    private void refreshIndexs() {
        indexs.clear();
        for (int i = 0; i < getChildCount(); i++) {
            ViewGroup.LayoutParams layoutParams = super.getChildAt(i).getLayoutParams();
            int priority = 0;
            if (layoutParams != null && layoutParams instanceof PriorityLinearLayout.LayoutParams) {
                priority = ((LayoutParams) layoutParams).priority;
            }
            if (priority <= 0) {
                priority = 0;
            }
            indexs.add(new IndexInfo(i, priority));
        }

        Collections.sort(indexs, comparator);
    }

    /**
     * 根据priority改变measure的顺序
     * @param index
     * @return
     */
    @Override
    public View getChildAt(int index) {
        if (isMeasureing) {
            return getChildWithPriority(index);
        } else {
            return super.getChildAt(index);
        }
    }

    private View getChildWithPriority(int index) {
        try {
            return super.getChildAt(indexs.get(index).index);
        }catch (Exception e){
            return super.getChildAt(index);
        }
    }

    public static class IndexInfo {
        public int index;
        public int priority;

        public IndexInfo(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public int priority = 0;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            initPriority(c, attrs);
        }

        private void initPriority(Context context, AttributeSet attrs) {
            @SuppressLint("CustomViewStyleable")
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PriorityLinearLayout);
            if (typedArray != null) {
                priority = typedArray.getInt(R.styleable.PriorityLinearLayout_measure_priority, 0);
                typedArray.recycle();
            }
        }
    }
}

package com.eehack.visualink.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.eehack.visualink.R;


public class TreeView extends View {

    private Drawable mTreeDrawable;
    private Bitmap mLeafBitmap;

    private int leafCount = 15;
    private int mTreeSize = 0;

    private LeafPosition[] leafPositions = {
            new LeafPosition(0.25f, 0.25f, 45, 1f),
            new LeafPosition(0.5f, 0.5f, 90, 1f)};

    public TreeView(Context context) {
        super(context);
        init(null, 0);
    }

    public TreeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TreeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TreeView, defStyle, 0);


        mTreeDrawable = getResources().getDrawable(R.drawable.tree);
        mTreeDrawable.setCallback(this);

        mLeafBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.leaf);

        a.recycle();

    }


    @Override
    protected void onDraw(Canvas canvas) {

        mTreeDrawable.draw(canvas);

        LeafPosition lp;
        for (int i = 0; i < leafCount && i < leafPositions.length; i++) {
            lp = leafPositions[i];
            canvas.drawBitmap(mLeafBitmap, lp.matrix, null);
        }


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w / h < 1) {
            mTreeDrawable.setBounds(10, 10, w - 10, w - 10);
            mTreeSize = w-20;
        } else {
            mTreeDrawable.setBounds(10, 10, h - 10, h - 10);
            mTreeSize = h-20;
        }
    }

    public void setLeafCount(int count) {
        this.leafCount = (Math.min(Math.max(0, count), 100));
    }

    private class LeafPosition {
        public float xPos;
        public float yPos;
        public Matrix matrix;

        public LeafPosition(float xPos, float yPos, float rot, float scale) {
            this.xPos = xPos;
            this.yPos = yPos;
            this.matrix = new Matrix();
            matrix.setTranslate(mTreeSize*xPos + 10, mTreeSize * yPos + 10);

            matrix.setRotate(rot);

            float sf = mTreeSize/1000;

            matrix.setScale( scale, scale);
        }


    }


}
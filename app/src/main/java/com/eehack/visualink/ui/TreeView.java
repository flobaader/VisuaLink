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
    private float mTreeSize = 0;

    private LeafPosition[] leafPositions = {
            new LeafPosition(0.23f, 0.23f, 55f, 0.9f),
            new LeafPosition(0.0f, 0.26f, 280f, 0.8f),
            new LeafPosition(0.03f, 0.41f, 20f, 1f),
            new LeafPosition(0.1f, 0.52f, 270f, 1.1f),
            new LeafPosition(0.1f, 0.7f, 200f, 0.8f),
            new LeafPosition(0.22f, 0f, 0f, 1.1f),
            new LeafPosition(0.14f, 0.16f, 210f, 0.7f),
            new LeafPosition(0.4f, 0.04f, 15f, 1.3f),
            new LeafPosition(0.4f, 0.23f, 0f, 0.9f),
            new LeafPosition(0.45f, 0.14f, 330f, 0.85f),
            new LeafPosition(0.6f, -0.05f, 45f, 0.7f),
            new LeafPosition(0.74f, 0.04f, 30f, 1f),
            new LeafPosition(0.73f, 0.08f, 55f, 1.15f),
            new LeafPosition(0.89f, 0.12f, 5f, 0.93f),
            new LeafPosition(0.9f, 0.27f, 90f, 1.06f),
            new LeafPosition(0.9f, 0.04f, 30f, 1f)
    };

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
            mTreeSize = w - 20;
        } else {
            mTreeDrawable.setBounds(10, 10, h - 10, h - 10);
            mTreeSize = h - 20;
        }

        float sf = mTreeSize / 1000;

        for (LeafPosition lp : leafPositions) {
            lp.matrix = new Matrix();
            lp.matrix.reset();

            lp.matrix.postScale(sf * lp.scale, sf * lp.scale);
            lp.matrix.postRotate(lp.rot);
            lp.matrix.postTranslate(mTreeSize * lp.xPos + 10f, mTreeSize * lp.yPos + 10f);


        }

        invalidate();

    }

    public void setLeafCount(int branch, int count) {
        this.leafCount = (Math.min(Math.max(0, count), 100));
    }

    private class LeafPosition {
        private final float rot;
        private final float scale;
        public float xPos;
        public float yPos;
        public Matrix matrix;

        public LeafPosition(float xPos, float yPos, float rot, float scale) {
            this.xPos = xPos;
            this.yPos = yPos;
            this.rot = rot;
            this.scale = scale;
        }


    }

    private class Branch {
        float xPos, yPos;
        public String name;
        public LeafPosition[] positions;

        public Branch(String name, LeafPosition[] positions) {
            this.name = name;
            this.positions = positions;
        }
    }


}
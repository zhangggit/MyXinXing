package com.example.zhanggang.myxinxing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhanggang on 2017/10/10.
 */

public class MyView extends View {

    private Path path;
    private Paint paint;
    private Bitmap bitmap;

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        bitmap = bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    }

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.RED);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            path.addArc(100, 100, 500, 500, -225, 225);
//            path.arcTo(200, 100, 800, 500, -180, 225, false);
            path.addArc(200, 200, 400, 400, -225, 225);//画弧
            path.arcTo(400, 200, 600, 400, -180, 225, false);//三阶贝塞尔曲线
            path.lineTo(400, 550);//连接
        }

        int saveLayer = canvas.saveLayer(0, 0, bitmap.getWidth(), bitmap.getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saveLayer);
    }

}

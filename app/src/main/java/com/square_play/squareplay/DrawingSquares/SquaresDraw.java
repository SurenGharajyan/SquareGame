package com.square_play.squareplay.DrawingSquares;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.square_play.squareplay.FragmentPlay;
import com.square_play.squareplay.PlaySettingActivity;
import com.square_play.squareplay.R;

import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class SquaresDraw extends View {
    Paint paint,painter;
    float X,Y;
    Rect rect;
    static int in;
    float XDown,YDown;
    Context context;
    boolean t0=false,t1=false,t2=false,t3=false,t4=false,t5=false;
    static Bitmap cherry_bm;


    public SquaresDraw(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);


        paint = new Paint();
        paint.setColor(Color.RED);
        painter = new Paint();
        rect = new Rect(0,0,0,0);
    }

    public SquaresDraw(Context context,int intentInt) {
        super(context);
        in = intentInt;
        this.context = context;

        Toast.makeText(context, "in="+in, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                XDown = motionEvent.getX();
                YDown = motionEvent.getY();

//              Animation animScale = AnimationUtils.loadAnimation(getContext(), R.anim.anim_scale_change);
//              imageButtonRefresh.startAnimation(animScale);

                Log.d("MyTag", "Down");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("MyTag", "Up");
                t2 = true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("MyTag", "Move");
                Y = motionEvent.getY();
                X = motionEvent.getX();
                invalidate();
                break;
            case MotionEvent.ACTION_BUTTON_PRESS:
                Log.d("MyTag","Press Button");
                break;
            case MotionEvent.ACTION_CANCEL:
                Toast.makeText(getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void RectPositionChangeLevel1(Canvas canvas) {
//        Log.i("XY", "Y = " + (int) Y + " X = " + (int) X);
//        Log.i("canvas", "canvas.getWidth() = " + canvas.getWidth() + " canvas.getHeight() = " + canvas.getHeight());
//        Log.i("rect", "rect.top=" + rect.top + " rect.left=" + rect.left + "\n rect.right=" + rect.right + " rect.bottom=" + rect.bottom);

//        if (Y <= rect.bottom && Y >= rect.top && X <= rect.right && X >= rect.left) {all adding in her but this is unuseless}
        if (rect.top < canvas.getHeight() / 3 && rect.top >= 0) {
            //begin create
                rect.set(0, 0, canvas.getWidth() / 5, canvas.getHeight() / 3);
                t0 = true;
            }

            if(Y>X) {
            if (t1) {
                return;
            }
                //move first down
                rect.set(0, canvas.getHeight()/3, canvas.getWidth() / 5, 2 * canvas.getHeight() / 3);

                if(rect.top==canvas.getHeight()/3) {
                    if (XDown - X < 0 && Y>=canvas.getHeight()/3 && Y<=2*canvas.getHeight()/3 && t2) {
                        //move right
//                        Log.i("XDown-X=", (XDown - X) + "");
                        t2=false;
                        if (rect.right <= canvas.getWidth() && (X <= 4 * canvas.getWidth() / 5 || rect.left >= 0)) {
                            rect.set(4 * canvas.getWidth() / 5, canvas.getHeight() / 3, canvas.getWidth(), 2 * canvas.getHeight() / 3);
                            t1=true;
                        }
                    }
                }
            }
//            Log.i("YDown-Y=", (YDown - Y) + "");
            if(t1 && t2 && YDown-Y < 0 && X >= 4*canvas.getWidth()/5 && X <= canvas.getWidth()) {
                //move second down
                painter.setAlpha(0);
                rect.set(4*canvas.getWidth()/5,2*canvas.getHeight()/3,canvas.getWidth(),canvas.getHeight());
                in++;
                t1=false;
                t2=false;
                rect.set(0, 0, 0, 0);
            }
//        This is Code for touching and moving square
//
//        if (Y <= rect.bottom && Y >= rect.top && X <= rect.right && X >= rect.left) {
//            if (rect.top <= canvas.getHeight() / 3 && rect.top >= 0) {
//                rect.set(0, (int) Y, canvas.getWidth() / 5, (int) (canvas.getHeight() / 3 + Y));
//            }
//
//            if (Y > canvas.getHeight() / 3) {
//                rect.set(0, canvas.getHeight() / 3, canvas.getWidth() / 5, 2 * canvas.getHeight() / 3);
//                //!
//                    if (rect.right <= canvas.getWidth() && (X <= 4 * canvas.getWidth() / 5 || rect.left >= 0)) {
//                        rect.set((int) (X), canvas.getHeight() / 3, (int) (canvas.getWidth() / 5 + X), 2 * canvas.getHeight() / 3);
//                    }
//                Log.i("X+rect.right=",X+rect.right+" ");
//                if(rect.right>=canvas.getWidth()) {
//                    //!
//                   rect.set(4*canvas.getWidth()/5,canvas.getHeight()/3,canvas.getWidth(),2*canvas.getHeight()/3);
//                   if (Y>canvas.getHeight()/3) {
//                       rect.set(4*canvas.getWidth()/5,(int) Y,canvas.getWidth(),(int) (Y+canvas.getHeight()/3));
//                       if (rect.bottom>=canvas.getHeight()) {
//                           rect.set(4*canvas.getWidth()/5,2*canvas.getHeight()/3,canvas.getWidth(),(canvas.getHeight()));
//                       }
//                   }
//                }
//            }
//        }
 }
    private void RectPositionChangeLevel2(Canvas canvas) {
//        if (Y <= rect.bottom && Y >= rect.top && X <= rect.right && X >= rect.left) {
                if (rect.top < canvas.getHeight() / 3 && rect.top >= 0 && !t1) {
                    //begin create
                    rect.set(4 * canvas.getWidth() / 5, 0, canvas.getWidth(), canvas.getHeight() / 5);
                    t0 = true;
                    painter.setAlpha(100);
                }
            if (t0 && YDown - Y < 0 && Y >= 0 && Y <= canvas.getHeight() / 5) {

                rect.set(4 * canvas.getWidth() / 5, canvas.getHeight() / 5, canvas.getWidth(), 2 * canvas.getHeight() / 5);
                t1 = true;
                t2=false;
                Log.i("values1", "YDown-Y = " + (YDown - Y) + " YDown=" + YDown + " X = " + X + "> 4*canvas.getWidth()/5 = " + 4 * canvas.getWidth() / 5 + "\n"
                        + " X<canvas.getWidth() = " + canvas.getWidth());
            }
            if (t1 && t2 && X - XDown < 0 && X >= 4 * canvas.getWidth() / 5 && X <=  canvas.getWidth()) {
                rect.set(0, canvas.getHeight() / 5, canvas.getWidth() / 5, 2*canvas.getHeight() / 5);
                t2=false;
                t3=true;
            }
            if (t2 && t3 && YDown-Y<0 && Y>=canvas.getHeight()/5 && Y<=2*canvas.getHeight()/5) {
                 rect.set(0,4*canvas.getHeight()/5,canvas.getWidth()/5,canvas.getHeight());
                 t2 = false;
                 t4 = true;
            }
            if (t2 && t4 && XDown-X<0 && X>=0 && X<=canvas.getWidth()/5) {
                rect.set(2*canvas.getWidth()/5,4*canvas.getHeight()/5, 3*canvas.getWidth()/5,canvas.getHeight());
                t2=false;
                t5=true;
            }
            if (t2 && t5 && Y-YDown<0 && Y>=4*canvas.getHeight()/5 && Y<=canvas.getHeight()) {
                painter.setAlpha(0);
                    rect.set(2*canvas.getWidth()/5,canvas.getHeight()/5,3*canvas.getWidth()/5,2*canvas.getHeight()/5);


                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        painter.setAlpha(0);
                    t1=t2=t3=t4=t5=false;
                        Intent intent = new Intent(getContext(), PlaySettingActivity.class);
                        getContext().startActivity(intent);
                    }
                },100);
            }

        }




    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //TODO create switch and what position you have take this level number and create your level
        //TODO For example in down you are created Level 1
        switch (in) {
            case 1:
                paint.setColor(Color.BLUE);
                paint.setStyle(Paint.Style.FILL);
                RectPositionChangeLevel1(canvas);
                canvas.drawRect(rect, paint);
                paint.setColor(Color.MAGENTA);
                canvas.drawRect(canvas.getWidth()/5, 0, (canvas.getWidth()),canvas.getHeight()/3,paint);
                paint.setColor(Color.YELLOW);
                canvas.drawRect(0, 2*canvas.getHeight()/3, 4*canvas.getWidth()/5,canvas.getHeight(),paint);

                cherry_bm = BitmapFactory.decodeResource(getResources(),R.drawable.finish_flag);
                canvas.drawBitmap(cherry_bm,4*canvas.getWidth()/5+canvas.getWidth()/15,2*canvas.getHeight()/3+canvas.getHeight()/15,painter);
                break;
            case 2:
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.FILL);
                RectPositionChangeLevel2(canvas);
                canvas.drawRect(rect, paint);
                paint.setColor(Color.CYAN);
                canvas.drawRect(0, 0, 4*canvas.getWidth()/5,canvas.getHeight()/5,paint);
                paint.setColor(Color.GREEN);
                canvas.drawRect(canvas.getWidth()/5, 2*canvas.getHeight()/5, 2*canvas.getWidth()/5,4*canvas.getHeight()/5,paint);
                paint.setColor(Color.BLUE);
                canvas.drawRect(3*canvas.getWidth()/5, 2*canvas.getHeight()/5, canvas.getWidth(),canvas.getHeight(),paint);


                cherry_bm = BitmapFactory.decodeResource(getResources(),R.drawable.finish_flag);
                canvas.drawBitmap(cherry_bm,2*canvas.getWidth()/5,2*canvas.getHeight()/5,painter);
                break;
        }
        Log.i("XY", "Y = " + (int) Y + " X = " + (int) X);


    }
}

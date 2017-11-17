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


public class SquaresDraw extends View  {
    Paint paint,painter;
    float X,Y;
    Rect rect,rectShowHide,rectShowHide2;
    static int in;
    float XDown,YDown;
    Context context;
    boolean t0,t1,touchingScreen,t3,t4,t5,t6,t7,t8,t9,t10;

    static Bitmap cherry_bm;
    int i=0;
    android.os.Handler handler;
    public SquaresDraw(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        t0=t1=touchingScreen=t3=t4=t5=t6=t7=t8=t9=t10=false;

        paint = new Paint();
        paint.setColor(Color.RED);
        painter = new Paint();
        rect = new Rect(0,0,0,0);
        rectShowHide = new Rect(0,0,0,0);
        rectShowHide2 = new Rect(0,0,0,0);
        handler = new android.os.Handler();
    }

    public SquaresDraw(Context context,int intentInt) {
        super(context);
        in = 4;//intentInt;
        this.context = context;

        Toast.makeText(context, "in="+in, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                XDown = motionEvent.getX();
                YDown = motionEvent.getY();

                //Log.d("MyTag", "Down");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("MyTag", "Up");
                touchingScreen = true;
                break;
            case MotionEvent.ACTION_MOVE:
                //Log.d("MyTag", "Move");
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
        if (rect.top < canvas.getHeight() / 3 && rect.top >= 0 && !t0) {
            //begin create
                rect.set(0, 0, canvas.getWidth() / 5, canvas.getHeight() / 3);
                t0 = true;

            }
        if (YDown>rect.top && YDown<rect.bottom && XDown>rect.left && XDown<rect.right) {
                if (t0 && YDown - Y < -50 && YDown > rect.top && YDown < rect.bottom ) {
                    //move first down
                    rect.set(0, canvas.getHeight() / 3, canvas.getWidth() / 5, 2 * canvas.getHeight() / 3);
                    t1 = true;
                }


                if (touchingScreen && t1 && XDown - X < -50 && X > rect.top && X < rect.bottom ) {
                    //move right
                    touchingScreen = false;
                    rect.set(4 * canvas.getWidth() / 5, canvas.getHeight() / 3, canvas.getWidth(), 2 * canvas.getHeight() / 3);
                    t3 = true;
                }

                if (t3 && touchingScreen && YDown - Y < -50 && Y > rect.top && Y < rect.bottom) {
                    //move second down
                    painter.setAlpha(0);
                    rect.set(4 * canvas.getWidth() / 5, 2 * canvas.getHeight() / 3, canvas.getWidth(), canvas.getHeight());
                    in++;
                    t0 = t1 = touchingScreen = t3 = false;
                    rect.set(0, 0, 0, 0);
                }
            }
 }

    private void RectPositionChangeLevel2(Canvas canvas) {
                if (rect.top < canvas.getHeight() / 3 && rect.top >= 0 && !t1) {
                    //begin create
                    rect.set(4 * canvas.getWidth() / 5, 0, canvas.getWidth(), canvas.getHeight() / 5);
                    t0 = true;
                    painter.setAlpha(100);
                }
        if (YDown>rect.top && YDown<rect.bottom && XDown>rect.left && XDown<rect.right) {
            if (t0 && YDown - Y < 0 && Y >= 0 && Y <= canvas.getHeight() / 5) {

                rect.set(4 * canvas.getWidth() / 5, canvas.getHeight() / 5, canvas.getWidth(), 2 * canvas.getHeight() / 5);
                t1 = true;
                touchingScreen = false;
                Log.i("values1", "YDown-Y = " + (YDown - Y) + " YDown=" + YDown + " X = " + X + "> 4*canvas.getWidth()/5 = " + 4 * canvas.getWidth() / 5 + "\n"
                        + " X<canvas.getWidth() = " + canvas.getWidth());
            }
            if (t1 && touchingScreen && X - XDown < 0 && X >= 4 * canvas.getWidth() / 5 && X <= canvas.getWidth()) {
                rect.set(0, canvas.getHeight() / 5, canvas.getWidth() / 5, 2 * canvas.getHeight() / 5);
                touchingScreen = false;
                t3 = true;
            }
            if (touchingScreen && t3 && YDown - Y < 0 && Y >= canvas.getHeight() / 5 && Y <= 2 * canvas.getHeight() / 5) {
                rect.set(0, 4 * canvas.getHeight() / 5, canvas.getWidth() / 5, canvas.getHeight());
                touchingScreen = false;
                t4 = true;
            }
            if (touchingScreen && t4 && XDown - X < 0 && X >= 0 && X <= canvas.getWidth() / 5) {
                rect.set(2 * canvas.getWidth() / 5, 4 * canvas.getHeight() / 5, 3 * canvas.getWidth() / 5, canvas.getHeight());
                touchingScreen = false;
                t5 = true;
            }
            if (touchingScreen && t5 && Y - YDown < 0 && Y >= 4 * canvas.getHeight() / 5 && Y <= canvas.getHeight()) {
                painter.setAlpha(0);
                rect.set(2 * canvas.getWidth() / 5, canvas.getHeight() / 5, 3 * canvas.getWidth() / 5, 2 * canvas.getHeight() / 5);
                painter.setAlpha(0);
                in++;
                t0 = t1 = touchingScreen = t3 = t4 = t5 = false;
                rect.set(0, 0, 0, 0);

            }
        }
    }
    private void RectPositionChangeLevel3(final Canvas canvas) {
        if (!t0) {
            rect.set(4*canvas.getWidth()/5,canvas.getHeight()/5,canvas.getWidth(),2*canvas.getHeight()/5);
            painter.setAlpha(100);
            t0=true;
            t3=true;
            touchingScreen=true;
            t4=true;
        }
        if (YDown>rect.top && YDown<rect.bottom && XDown>rect.left && XDown<rect.right) {
        if(touchingScreen && YDown-Y>50 && YDown>rect.top && YDown<rect.bottom && rect.right==canvas.getWidth()) {
            rect.set(4*canvas.getWidth()/5,0,canvas.getWidth(),canvas.getHeight()/5);
            touchingScreen=false;
            t1=true;
        } else if (touchingScreen && YDown-Y<-50 && YDown>rect.top && YDown<rect.bottom && rect.right==canvas.getWidth() ) {
            rect.set(4*canvas.getWidth()/5,4*canvas.getHeight()/5,canvas.getWidth(),canvas.getHeight());
            t1=true;
            touchingScreen=false;
        }
        if (touchingScreen && XDown-X>50 && XDown>rect.left && XDown<rect.right && rect.top==0 && t1) {
            rect.set(0,0,canvas.getWidth()/5,canvas.getHeight()/5);
            touchingScreen=false;
        } else if (touchingScreen && XDown-X>50 && XDown>rect.left && XDown<rect.right && rect.bottom==canvas.getHeight() && t1) {
            rect.set(0,4*canvas.getHeight()/5,canvas.getWidth()/5,canvas.getHeight());
            touchingScreen=false;
        }
        if (touchingScreen && XDown-X<-50 && XDown>rect.left && XDown<rect.right && rect.top==0 ) {
            rect.set(4*canvas.getWidth()/5,0,canvas.getWidth(),canvas.getHeight()/5);
            touchingScreen=false;
        } else if (touchingScreen && XDown-X<-50 && XDown>rect.left && XDown<rect.right && rect.bottom==canvas.getHeight()) {
            rect.set(4*canvas.getWidth()/5,4*canvas.getHeight()/5,canvas.getWidth(),canvas.getHeight());
            touchingScreen=false;
        }
        if (touchingScreen && YDown-Y<-50 && YDown>rect.top && YDown<rect.bottom && rect.top==0 && rect.left==0) {
            rect.set(0,4*canvas.getHeight()/5,canvas.getWidth()/5,canvas.getHeight());
            if (rectShowHide.right==canvas.getWidth()/5) {
                rect.set(0,0,canvas.getWidth()/5,canvas.getHeight()/5);

            }
            touchingScreen=false;
        } else if (touchingScreen && YDown-Y>50 && YDown>rect.top && YDown<rect.bottom && rect.bottom==canvas.getHeight() && rect.left==0) {
            rect.set(0,0,canvas.getWidth()/5,canvas.getHeight()/5);
            if (rectShowHide.right==canvas.getWidth()/5) {
                rect.set(0,2*canvas.getHeight()/5,canvas.getWidth()/5,3*canvas.getHeight()/5);
                t5=true;
            }
            touchingScreen=false;
        }
        if (t5 && touchingScreen) {
            //move to up or down when you are touch rectShowHide
            if (YDown > rect.top && YDown < rect.bottom) {

                if (YDown - Y < -50) {
                    rect.set(0, 4 * canvas.getHeight() / 5, canvas.getWidth() / 5, canvas.getHeight());
                } else if (YDown - Y > 50) {
                    rect.set(0, 0, canvas.getWidth() / 5, canvas.getHeight() / 5);
                }

            }
            if (XDown > rect.left && XDown < rect.right && XDown - X < -50) {
                rect.set(4 * canvas.getWidth() / 5, 2 * canvas.getHeight() / 5, canvas.getWidth(), 3 * canvas.getHeight() / 5);
                painter.setAlpha(0);
                in++;
                t1 = touchingScreen = t3 = t4 = t5 = false;
                painter.setAlpha(0);
                rect.set(0,0,0,0);
            }
        }
    }
        rectHideAndShowLevel3(canvas);
}
    private void rectHideAndShowLevel3(final Canvas canvas) {
        if (t4) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    rectShowHide.set(0,canvas.getHeight()/5,canvas.getWidth()/5,2*canvas.getHeight()/5);
                    t4=false;
                        handler.removeCallbacksAndMessages(null);
                        invalidate();
                }
            },1000);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    rectShowHide.set(0,0,0,0);
                    t4=true;
                    handler.removeCallbacksAndMessages(null);
                    invalidate();
                }
            },1000);
        }

    }

    private void RectPositionChangeLevel4(Canvas canvas) {
        if (!t0) {
            rect.set(3*canvas.getWidth()/5,4*canvas.getHeight()/5,4*canvas.getWidth()/5,canvas.getHeight());
            t0 = true;
            t4 = true;
        }

        if (YDown>rect.top && YDown<rect.bottom && XDown>rect.left && XDown<rect.right) {

            if (t0 && touchingScreen && rectShowHide2.top != 2 * canvas.getHeight() / 5 && (rect.top == 4 * canvas.getHeight() / 5 || rect.top == 3 * canvas.getHeight() / 5)) {
                //up
                if (YDown - Y > 50 && rectShowHide.bottom!=3*canvas.getHeight()/5) {
                    rect.set(3 * canvas.getWidth() / 5, 2 * canvas.getHeight() / 5, 4 * canvas.getWidth() / 5, 3 * canvas.getHeight() / 5);
                    touchingScreen = false;
                //down
                } else if (YDown - Y < -50 && rect.left == 3 * canvas.getHeight() / 5) {
                    rect.set(3 * canvas.getWidth() / 5, 4 * canvas.getHeight() / 5, 4 * canvas.getWidth() / 5, canvas.getHeight());
                }
                t1 = true;
            }

            if (t1 && touchingScreen && XDown - X > 50 && rect.top!=0 && (rect.left == 2 * canvas.getWidth() / 5 || (rect.left == 3 * canvas.getWidth() / 5 && rect.top == 2 * canvas.getHeight() / 5))) {
                rect.set(0, 2 * canvas.getHeight() / 5, canvas.getWidth() / 5, 3 * canvas.getHeight() / 5);
                t3 = true;
                //move left
            } else if (t1 && touchingScreen && XDown - X < -50 && rect.left == 0 && rect.top == 2 * canvas.getHeight() / 5) {
                //move right
                if (rectShowHide2.top == canvas.getHeight() / 5) {
                    //rect touch the moved rectangle
                    rect.set(2 * canvas.getWidth() / 5, 2 * canvas.getHeight() / 5, 3 * canvas.getWidth() / 5, 3 * canvas.getHeight() / 5);
                    t5 = true;
                } else {
                    //rect.left=3*canvas.getWidth()/5
                    rect.set(3 * canvas.getWidth() / 5, 2 * canvas.getHeight() / 5, 4 * canvas.getWidth() / 5, 3 * canvas.getHeight() / 5);
                    t1 = false;
                }
                touchingScreen = false;
            }
            if (YDown - Y > 50 && touchingScreen && t5 && rect.left == 2 * canvas.getWidth() / 5) {
                //rect.left=2*canvas.getWidth()/3,,,, top=0
                rect.set(2 * canvas.getWidth() / 5, 0, 3 * canvas.getWidth() / 5, canvas.getHeight() / 5);
                t6 = true;
                touchingScreen = false;
            }else if (YDown - Y < -50 && touchingScreen && t5 && rect.top==0 && rect.left==2*canvas.getWidth()/5) {
                rect.set(2*canvas.getWidth()/5,2*canvas.getHeight()/5,3*canvas.getWidth()/5,3*canvas.getHeight()/5);
                t6 = false;
                touchingScreen = false;
            }
            Log.i("rectShowHide=",rectShowHide.top+"");
            if (t6 && XDown - X > 50 && touchingScreen && rect.top==0) {
                rect.set(canvas.getWidth()/5,0,2*canvas.getWidth()/5,canvas.getHeight()/5);
                t7=true;
                touchingScreen=false;
            }else if (XDown - X < -50 && touchingScreen && t6 && rect.top==0) {
                if (rectShowHide2.top!=canvas.getHeight()/5) {
                    rect.set(2 * canvas.getWidth() / 5, 0, 3 * canvas.getWidth() / 5, canvas.getHeight() / 5);
                }else {
                    rect.set(3 * canvas.getWidth() / 5, 0, 4 * canvas.getWidth() / 5, canvas.getHeight() / 5);


                }
                t7 = false;
                t8 = true;
                touchingScreen=false;
            }



            if (touchingScreen && t7 && YDown - Y < -50 && rect.left==canvas.getWidth()/5) {
                rect.set(canvas.getWidth()/5,4*canvas.getHeight()/5,2*canvas.getWidth()/5,canvas.getHeight());
                painter.setAlpha(0);
                in++;
                t1=touchingScreen=t3=t4=t5=t6=t7=t8=t9=false;
                rect.set(0,0,0,0);
            }else if (XDown - X < -50 && t7 && touchingScreen  && rect.left==canvas.getWidth()/5){
                rect.set(3*canvas.getWidth()/5,0,4*canvas.getWidth()/5,canvas.getHeight()/5);
                touchingScreen = false;
                t8=true;
            }


        }
        rectHideAndShowLevel4(canvas);
    }
    private void rectHideAndShowLevel4(final Canvas canvas){
        if (t4) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //UP
                    rectShowHide2.set(3*canvas.getWidth()/5,0,4*canvas.getWidth()/5,2*canvas.getHeight()/5);

                    if (rect.top==0 && rect.left==3*canvas.getWidth()/5) {
                        rect.set(2*canvas.getWidth()/5,0,3*canvas.getWidth()/5,canvas.getHeight()/5);
                    }
                    t4=false;

                    handler.removeCallbacksAndMessages(null);
                    invalidate();
                }
            },2000);
        }else  {
           handler.postDelayed(new Runnable() {
               @Override
               public void run() {
                   //DOWN
                   rectShowHide2.set(3*canvas.getWidth()/5,canvas.getHeight()/5,4*canvas.getWidth()/5,3*canvas.getHeight()/5);

                   if (rect.top==2*canvas.getHeight()/5 && rect.left==3*canvas.getWidth()/5) {
                       rect.set(3*canvas.getWidth()/5,3*canvas.getHeight()/5,4*canvas.getWidth()/5,4*canvas.getHeight()/5);
                   }
                   t4 = true;

                   handler.removeCallbacksAndMessages(null);
                   invalidate();

               }
           },3000);
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
            case 3:
                paint.setColor(Color.GREEN);
                paint.setStyle(Paint.Style.FILL);
                RectPositionChangeLevel3(canvas);
                canvas.drawRect(rect, paint);
                paint.setColor(Color.RED);
                canvas.drawRect(canvas.getWidth()/5, canvas.getHeight()/5, 2*canvas.getWidth()/5,2*canvas.getHeight()/5,paint);
                paint.setColor(Color.YELLOW);
                canvas.drawRect(3*canvas.getWidth()/5, canvas.getHeight()/5, 4*canvas.getWidth()/5,2*canvas.getHeight()/5,paint);
                paint.setColor(Color.MAGENTA);
                canvas.drawRect(canvas.getWidth()/5, 3*canvas.getHeight()/5, 2*canvas.getWidth()/5,4*canvas.getHeight()/5,paint);
                paint.setColor(Color.argb(100,20,154,189));
                canvas.drawRect(3*canvas.getWidth()/5,3*canvas.getHeight()/5,4*canvas.getWidth()/5,4*canvas.getHeight()/5,paint);

                canvas.drawRect(rectShowHide,paint);

                cherry_bm = BitmapFactory.decodeResource(getResources(),R.drawable.finish_flag);
                canvas.drawBitmap(cherry_bm,2*canvas.getWidth()/5,2*canvas.getHeight()/5,painter);
                break;
            case 4:
                paint.setColor(Color.BLUE);
                paint.setStyle(Paint.Style.FILL);
                RectPositionChangeLevel4(canvas);
                canvas.drawRect(rect, paint);
                paint.setColor(Color.argb(100,12,160,211));
                canvas.drawRect(2*canvas.getWidth()/5, 3*canvas.getHeight()/5, 3*canvas.getWidth()/5,canvas.getHeight(),paint);
                paint.setColor(Color.argb(100,180,180,180));
                canvas.drawRect(0, 3*canvas.getHeight()/5, canvas.getWidth()/5,canvas.getHeight(),paint);
                paint.setColor(Color.CYAN);
                canvas.drawRect(4*canvas.getWidth()/5,0,canvas.getWidth(),canvas.getHeight(),paint);

                paint.setColor(Color.argb(100,220,154,189));
                rectShowHide.set(0,0,canvas.getWidth()/5,2*canvas.getHeight()/5);
                canvas.drawRect(rectShowHide,paint);
                paint.setColor(Color.argb(100,200,150,80));
                canvas.drawRect(rectShowHide2,paint);


                cherry_bm = BitmapFactory.decodeResource(getResources(),R.drawable.finish_flag);
                canvas.drawBitmap(cherry_bm,canvas.getWidth()/5,4*canvas.getHeight()/5,painter);
                break;
        }
//        Log.i("XY", "Y = " + (int) Y + " X = " + (int) X);


    }
}

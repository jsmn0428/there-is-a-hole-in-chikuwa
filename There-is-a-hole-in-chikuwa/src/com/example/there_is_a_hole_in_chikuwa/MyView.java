package com.example.there_is_a_hole_in_chikuwa;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.SensorEventListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MyView
  extends View
{
  public static final int INTERVAL = 40;
  public static final float rate = 4/3F;
  Bitmap imgTikuwakatto;
  LawsOfPhysics law;
  List<Drawable> balls = new ArrayList<Drawable>();
  List<Drawable> holes = new ArrayList<Drawable>();
  Paint paint = new Paint();
  int score = 0;
  TextView scoreView;
  TextView axView;
  TextView ayView;
  Timer timer;
  
  public MyView(Context paramContext){
    super(paramContext);
  }
  
  public MyView(Context paramContext, AttributeSet paramAttributeSet){
    super(paramContext, paramAttributeSet);
  }
  
  public MyView(Context paramContext, AttributeSet paramAttributeSet, int paramInt){
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void deleteBall(Ball ball){
    balls.remove(ball);
    law.ballList.remove(ball);
    score++;
    newBall();
  }
  
  public SensorEventListener getlaw(){
    return law;
  }
  
  public void init(final Activity activity){
    scoreView = ((TextView)activity.findViewById(R.id.TextView1));
    axView = ((TextView)activity.findViewById(R.id.TextViewAX));
    ayView = ((TextView)activity.findViewById(R.id.TextViewAY));
    imgTikuwakatto = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.tikuwakatto);
    float[][] f1 = { { 138.0F, 52.0F }, { 158.0F, 49.0F }, { 171.0F, 62.0F }, { 184.0F, 114.0F }, { 168.0F, 128.0F }, { 134.0F, 92.0F }, { 129.0F, 66.0F } };
    float[][] f2 = { { 295.0F, 52.0F }, { 309.0F, 58.0F }, { 319.0F, 92.0F }, { 316.0F, 106.0F }, { 300.0F, 111.0F }, { 284.0F, 95.0F }, { 281.0F, 67.0F } };
    float[][] f3 = { { 151.0F, 313.0F }, { 164.0F, 320.0F }, { 166.0F, 358.0F }, { 154.0F, 381.0F }, { 133.0F, 392.0F }, { 123.0F, 381.0F }, { 122.0F, 359.0F }, { 131.0F, 332.0F } };
    float[][] f4 = { { 338.0F, 360.0F }, { 361.0F, 377.0F }, { 363.0F, 413.0F }, { 342.0F, 437.0F }, { 321.0F, 439.0F }, { 307.0F, 424.0F }, { 305.0F, 391.0F }, { 319.0F, 371.0F } };
    Hole hole1 = new Hole(Point.asList(f1));
    Hole hole2 = new Hole(Point.asList(f2));
    Hole hole3 = new Hole(Point.asList(f3));
    Hole hole4 = new Hole(Point.asList(f4));
    law = new LawsOfPhysics(this);
    law.add(hole1);
    law.add(hole2);
    law.add(hole3);
    law.add(hole4);
    holes.add(hole1);
    holes.add(hole2);
    holes.add(hole3);
    holes.add(hole4);
    newBall();
    newBall();
    newBall();
    newBall();
    timer = new Timer();
    timer.schedule(new TimerTask(){
      Runnable thread = new Runnable(){
        public void run(){
          scoreView.setText("score = " + score);
          axView.setText("ax = " + law.ax);
          ayView.setText("ay = " + law.ay);
        }
      };
      public void run(){
        law.update(INTERVAL / 1000F);
        postInvalidate();
        activity.runOnUiThread(thread);
      }
    }, 100L, 40L);
  }
  
  public void newBall(){
    Ball newBall = new Ball();
    balls.add(newBall);
    law.add(newBall);
  }
  
  @Override
  protected void onDraw(Canvas canvas){
    canvas.drawColor(0xff000000);
    canvas.drawBitmap(imgTikuwakatto, null, canvas.getClipBounds(), paint);
    for(Drawable drawable:balls){
    	drawable.draw(canvas, paint);
    }
    //for(Drawable drawable:holes) drawable.draw(canvas, paint);
  }
}
package com.example.there_is_a_hole_in_chikuwa;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.ArrayList;
import java.util.List;

public class Ball implements Drawable{
	
  static final float r2 = 1.414F;
  int color;
  float m = 100.0F;
  float r = 10.0F;
  float time = 0.0F;
  float vx = 0.0F;
  float vy = 0.0F;
  float x;
  float y;
  
  public Ball(){
	  boolean check = true;
	  while(check){
		  x = ((float)(10.0D + (500.0F - 2.0F * r) * Math.random()));
	      y = ((float)(10.0D + (500.0F - 2.0F * r) * Math.random()));
	      check = checkArea(x, y);
	  }
	  color = (0xFF000000
			  | (int)(127 + 128 * Math.random()) << 16
			  | (int)(64 * Math.random()) << 8
			  | (int)(255 * Math.random()) << 0);
  }
  
  private boolean checkArea(float x, float y){
    if ((x > 130.0F) && (x < 190.0F) && (y > 50.0F) && (y < 130.0F)) return true;
    if ((x > 280.0F) && (x < 320.0F) && (y > 50.0F) && (y < 110.0F)) return true;
    if ((x > 120.0F) && (x < 170.0F) && (y > 310.0F) && (y < 390.0F)) return true;
    if ((x > 300.0F) && (x < 370.0F) && (y > 360.0F) && (y < 440.0F)) return true;
    return false;
  }
  
  public void draw(Canvas paramCanvas, Paint paint){
    paint.setColor(color);
    paramCanvas.drawCircle(MyView.rate * x, MyView.rate * y, MyView.rate * r, paint);
  }
  
  public List<Point> judgePoints(){
    List<Point> list = new ArrayList<Point>();
    list.add(new Point(x - r, y));
    list.add(new Point(x - r / r2, y - r / r2));
    list.add(new Point(x, y - r));
    list.add(new Point(x + r / r2, y - r / r2));
    list.add(new Point(x + r, y));
    list.add(new Point(x + r / r2, y + r / r2));
    list.add(new Point(x, y + r));
    list.add(new Point(x - r / r2, y + r / r2));
    return list;
  }
}
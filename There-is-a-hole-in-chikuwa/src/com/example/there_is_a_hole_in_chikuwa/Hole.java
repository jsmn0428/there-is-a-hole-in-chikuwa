package com.example.there_is_a_hole_in_chikuwa;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import java.util.List;

public class Hole implements Drawable{
  int color;
  Path pathForDraw;
  Polygon polygon;
  
  public Hole(List<Point> list){
    polygon = new Polygon(list);
    initPathForDraw(list);
  }
  
  private void initPathForDraw(List<Point> list){
    pathForDraw = new Path();
    Point start = list.get(0);
    pathForDraw.moveTo(MyView.rate* start.x, MyView.rate * start.y);
    for(int i=1;i<list.size();i++){
    	pathForDraw.lineTo(MyView.rate * list.get(i).x, MyView.rate * list.get(i).y);
    }
    pathForDraw.lineTo(MyView.rate * start.x, MyView.rate * start.y);
    pathForDraw.setFillType(FillType.EVEN_ODD);
    }

  
  public void boundary(boolean paramBoolean){
	  color = paramBoolean? 0xff00ff00 : 0xff0000ff;
  }
  
  public boolean contains(Ball ball){
	for(Point points:ball.judgePoints()){
		if(!polygon.contains(points)) return false;
	}
	return true;
  }

  @Override
  public void draw(Canvas canvas, Paint paint)
  {
    paint.setColor(color);
    canvas.drawPath(pathForDraw, paint);
  }
}

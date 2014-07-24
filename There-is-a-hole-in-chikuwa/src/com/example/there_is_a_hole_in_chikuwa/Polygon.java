package com.example.there_is_a_hole_in_chikuwa;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import java.util.List;

public class Polygon
{

  private static final int IN = 0xffffffff;
  private static final int OUT = 0xff000000;
  int height;
  private final Bitmap img;
  private final Path path = new Path();
  final List<Point> points;
  int width;
  
  public Polygon(List<Point> list){
	points = list;
    Point start = list.get(0);
    path.moveTo(start.x, start.y);
    float maxX = start.x;
    float maxY = start.y;
    for(int i = 1;i<list.size();i++){
    	if(maxX < list.get(i).x) maxX = list.get(i).x;
    	if(maxY < list.get(i).y) maxY = list.get(i).y;
    	path.lineTo(list.get(i).x, list.get(i).y);
    }
    path.lineTo(start.x, start.y);
    path.setFillType(FillType.EVEN_ODD);
    width = (int)maxX + 1;
    height = (int)maxY + 1;
    img = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(img);
    canvas.drawColor(OUT);
    Paint paint = new Paint();
    paint.setColor(IN);
    canvas.drawPath(path, paint);
  }
  
  public boolean contains(float x, float y){
    return contains(new Point(x, y));
  }
  
  public boolean contains(Point point){
    if ((point.x > width) || (point.y > height)) {
      return false;
    }
    if(img.getPixel((int)point.x, (int)point.y) == IN) return true;
    else return false;
  }
  
  public boolean contains(Polygon polygon){
	  for(Point point:polygon.points){
		  if(!contains(point)) return false;
	  }
	  return true;
  }
  
  public Path getPath(){
    return path;
  }
}
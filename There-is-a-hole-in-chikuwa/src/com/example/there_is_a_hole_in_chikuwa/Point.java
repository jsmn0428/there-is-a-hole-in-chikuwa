package com.example.there_is_a_hole_in_chikuwa;

import java.util.ArrayList;
import java.util.List;

public class Point
{
  final float x;
  final float y;
  
  Point(float paramFloat1, float paramFloat2)
  {
    this.x = paramFloat1;
    this.y = paramFloat2;
  }
  
  public static List<Point> asList(float[][] f)
  {
    List<Point> list = new ArrayList<Point>();
    for(int i=0;i<f.length;i++){
    	list.add(new Point(f[i][0], f[i][1]));
    }
    return list;
  }
}
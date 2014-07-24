package com.example.there_is_a_hole_in_chikuwa;

public class Vector2d
{
  final float x;
  final float y;
  
  public Vector2d(float x, float y)
  {
    this.x = x;
    this.y = y;
  }
  
  public Vector2d(Ball ball1, Ball ball2)
  {
    this(ball2.x - ball1.x, ball2.y - ball1.y);
  }
  
  public Vector2d(Point point1, Point point2)
  {
    this(point2.x - point1.x, point2.y - point1.y);
  }
  
  public static Vector2d add(Vector2d v1, Vector2d v2)
  {
    return new Vector2d(v1.x + v2.x, v1.y + v2.y);
  }
  
  public static float innerProduct(Vector2d v1, Vector2d v2)
  {
    return v1.x * v2.x + v1.y * v2.y;
  }
  
  public static Vector2d inverse(Vector2d v)
  {
    return new Vector2d(-v.x, -v.y);
  }
  
  public static float length(Vector2d v)
  {
    return (float)Math.sqrt(v.x * v.x + v.y * v.y);
  }
  
  public static Vector2d normalize(Vector2d v)
  {
    return new Vector2d(v.x / length(v), v.y / length(v));
  }
  
  public static Vector2d scalarProduct(Vector2d v, float c)
  {
    return new Vector2d(c * v.x, c * v.y);
  }
}
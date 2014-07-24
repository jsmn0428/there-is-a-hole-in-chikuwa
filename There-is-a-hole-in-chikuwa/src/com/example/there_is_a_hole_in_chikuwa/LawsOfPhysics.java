package com.example.there_is_a_hole_in_chikuwa;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import java.util.ArrayList;
import java.util.List;

public class LawsOfPhysics implements SensorEventListener{
  float ax;
  float ay;
  List<Ball> ballList = new ArrayList<Ball>();
  float e = 0.8F;
  List<Hole> holeList = new ArrayList<Hole>();
  MyView myView;
  
  public LawsOfPhysics(MyView myView){
    this.myView = myView;
  }
  
  private float distance(Ball paramBall1, Ball paramBall2){
    return (float)Math.sqrt((paramBall1.x - paramBall2.x) * (paramBall1.x - paramBall2.x) + (paramBall1.y - paramBall2.y) * (paramBall1.y - paramBall2.y));
  }
  
  private void wallchecker(Ball ball){
	  
    if(ball.x < ball.r){
      ball.x = (2 * ball.r - ball.x);
      ball.vx *= -e;
    }
    if(ball.x > 500 - ball.r){
    	ball.x = 2 * (500 - ball.r) - ball.x;
    	ball.vx *= -e;
    }
    if(ball.y < ball.r){
    	ball.y = (2 * ball.r - ball.y);
    	ball.vy *= -e;
    }
    if(ball.y > 500 - ball.r){
    	ball.y = 2 * (500 - ball.r) - ball.y;
    	ball.vy *= -e;
    }
  }
  
  public void add(Ball paramBall)
  {
    this.ballList.add(paramBall);
  }
  
  public void add(Hole paramHole)
  {
    this.holeList.add(paramHole);
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {
	  
  }
  
  //@Override
  public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
			ax = -0.5f * event.values[0];
			ay = 0.5f * event.values[1];
		}
  }
  
  public void update(float interval){
    Ball[] balls = (Ball[])ballList.toArray(new Ball[0]); 
    for(int i = 0;i < balls.length-1; i++){
    	for(int j = i+1; j < balls.length;j++){
          if (distance(balls[i], balls[j]) < balls[i].r + balls[j].r){
            Vector2d v1 = new Vector2d(balls[i].vx, balls[i].vy);
            Vector2d v2 = new Vector2d(balls[j].vx, balls[j].vy);
            Vector2d v3 = new Vector2d(balls[j].x - balls[i].x, balls[j].y - balls[i].y);
            Vector2d v4 = Vector2d.normalize(v3);
            float f1 = Vector2d.innerProduct(v1, v4);
            float f2 = Vector2d.innerProduct(v2, v4);
            Vector2d v5 = Vector2d.scalarProduct(v4, f1);
            Vector2d v6 = Vector2d.scalarProduct(v4, f2);
            if (Vector2d.innerProduct(v3, Vector2d.add(v1, Vector2d.inverse(v2))) > 0.0F){
              Vector2d v7 = Vector2d.add(v6, Vector2d.inverse(v5));
              Vector2d v8 = Vector2d.add(v1, v7);
              Vector2d v9 = Vector2d.add(v2, Vector2d.inverse(v7));
              balls[i].vx = v8.x;
              balls[i].vy = v8.y;
              balls[j].vx = v9.x;
              balls[j].vy = v9.y;
            }
          }
        }
    for(Ball ball:ballList){
    	ball.vx += interval * ax;
        ball.vy += interval * ay;
        ball.x += ball.vx;
        ball.y += ball.vy;
        wallchecker(ball);
    }
    for(Hole hole:holeList){
    	List<Ball> deleteList = new ArrayList<Ball>();
    	boolean check = false;
    	for(Ball ball:ballList){
    		check = check | hole.contains(ball);
    		if(hole.contains(ball)) deleteList.add(ball);
    	}
    	hole.boundary(check);
    	for(Ball ball:deleteList){
    		myView.deleteBall(ball);
    	}
    }
    }
  }
}

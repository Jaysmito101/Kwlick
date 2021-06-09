package com.jaysmito.Kwlick.primitives;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.geom.*;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.*;

public class SimplePolygon extends Entity{

	private ArrayList<Point> points;
	public boolean fill;

	public SimplePolygon(){
		points = new ArrayList<Point>();
		this.fill = true;
	}

	public void AddVertex(float x, float y){
		synchronized(points){
			points.add(new Point(x, y));
		}
	}

	public void EmptyVertices(){
		points.clear();
	}

	private int[][] PreparePoints(Coordinate cameraCoord){
		synchronized(points){
			int[][] pointsArr = new int[2][points.size()];
			for(int i=0;i<points.size();i++){
				// Kind of what you would do in vertex shader can be done here(Kind of ... )
				pointsArr[0][i] = (int)points.get(i).x + cameraCoord.x; 
				pointsArr[1][i] = (int)points.get(i).y + cameraCoord.y; 
			}
			return pointsArr;
		}
	}

	@Override
	public void Render(Graphics2D g){
		Resolve();
		if(!(points.size()>0))
			return;
		g.setColor(color);
		Coordinate coord = transform.position.toCameraCoordinates();
		int[][] vertices = PreparePoints(coord);
		AffineTransform old = g.getTransform();
		g.rotate(Math.toRadians(transform.rotation));
		if(fill)
			g.fillPolygon(vertices[0], vertices[1], vertices[0].length);
		else
			g.drawPolygon(vertices[0], vertices[1], vertices[0].length);
		g.setTransform(old);
	}
}

class Point{
	public float x, y;
	public String id;
	public Point(float x, float y){
		this.x = x;
		this.y = y;
		this.id = "" + Math.random();
	}

	public boolean equals(Point point){
		return (point.x == x && point.y == y);
	}

	public boolean isSameAs(String id){
		return (id.equals(id));
	}

	public boolean isSameAs(Point point){
		return (id.equals(point.id));
	}

	@Override
	public String toString(){
		return " (" + x + ", "+ y + ") ";
	}
}
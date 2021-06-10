package com.jaysmito.Kwlick.primitives;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.*;

public class Circle extends Entity{

	public float radius; 
	public boolean fill;

	public Circle(float radius){
		super();
		this.fill = true;
		this.radius = radius;
	}

	@Override
	public boolean contains(int x, int y){
		Coordinate cameraCoordinate = transform.position.toCameraCoordinates();
		int r = (int)(radius * transform.scale);
		int x1 = cameraCoordinate.x- (int)radius;
		int y1 = cameraCoordinate.y- (int)radius;
		double dist = Math.sqrt( Math.pow((x1-x), 2) + Math.pow((y1-y), 2) );
		if(dist <= radius){
			return true;
		}
		return false;
	}

	@Override
	public void Render(Graphics2D g){
		Resolve();
		g.setColor(color);
		Coordinate coord = transform.position.toCameraCoordinates();
		int scaledRadius = (int)(radius*transform.scale);
		if(fill)
			g.fillOval(coord.x - scaledRadius, coord.y - scaledRadius, scaledRadius*2, scaledRadius*2);
		else
			g.drawOval(coord.x - scaledRadius, coord.y - scaledRadius, scaledRadius*2, scaledRadius*2);
	}
}
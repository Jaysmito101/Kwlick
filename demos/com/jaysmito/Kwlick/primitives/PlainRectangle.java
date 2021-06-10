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

public class PlainRectangle extends Entity{

	public float width, height; 
	public boolean fill;

	public PlainRectangle(float width, float height){
		this.width = width;
		this.height = height;
		this.fill = true;
	}

	@Override
	public boolean contains(int x, int y){
		Coordinate cameraCoordinate = transform.position.toCameraCoordinates();
		int w = (int)(width * transform.scale);
		int h = (int)(height * transform.scale);
		int x1 = cameraCoordinate.x- w/2;
		int y1 = cameraCoordinate.y-h/2;
		int x2 = x1 + width;
		int y2 = y1 + height;
		if(x > x1 && x < x2 && y > y1 && y < y2){
			return true;
		}
		return false;	
	}

	@Override
	public void Render(Graphics2D g){
		Resolve();
		g.setColor(color);
		Coordinate coord = transform.position.toCameraCoordinates();
		int w = (int)(width * transform.scale);
		int h = (int)(height * transform.scale);
		AffineTransform old = g.getTransform();
		g.rotate(Math.toRadians(transform.rotation));
		if(fill)
			g.fillRect(coord.x - w/2, coord.y - h/2, w, h);
		else
			g.drawRect(coord.x - w/2, coord.y - h/2, w, h);
		g.setTransform(old);
	}
}
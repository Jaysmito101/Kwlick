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
	public void Render(Graphics2D g){
		Resolve();
		g.setColor(color);
		Coordinate coord = transform.position.toCameraCoordinates();
		int scaledRadius = (int)(radius*transform.scale);
		if(fill)
			g.fillOval(coord.x - scaledRadius/2, coord.y - scaledRadius/2, scaledRadius, scaledRadius);
		else
			g.drawOval(coord.x - scaledRadius/2, coord.y - scaledRadius/2, scaledRadius, scaledRadius);
	}
}
package com.jaysmito.Kwlick.uiprimitives;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.font.*;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.*;

public class Text extends UIEntity{

	public String text;
	public String font;
	public float fontSize;
	public Color backgroundColor;
	public int padding;
	public boolean showBackground;

	private int w, h, clones;

	public Text(String text){
		super();
		this.clones = 0;
		this.padding = 0;
		this.font = "Arial";
		this.showBackground = true;
		this.fontSize = 36.0f;
		this.text = text;
		this.backgroundColor = Color.WHITE;
		this.color = Color.BLACK;
	}

	public Text(){
		super();
		this.clones = 0;
		this.padding = 0;
		this.showBackground = true;
		this.font = "Arial";
		this.fontSize = 36.0f;
		this.text = "Text [" + name + "]";
		this.backgroundColor = Color.WHITE;
		this.color = Color.BLACK;
	}

	public Text clone(){
		Text t = new Text(text);
		t.font = font;
		t.fontSize = fontSize;
		t.backgroundColor =backgroundColor;
		t.padding = padding;
		t.name = this.name + " (Clone " + this.clones + ")";
		t.showBackground = showBackground;
		this.clones++;
		t.transform.position.x = transform.position.x ;
		t.transform.position.y = transform.position.y ;
		return t;
	}

	public Text clone(String stringText){
		Text t = new Text(stringText);
		t.font = font;
		t.fontSize = fontSize;
		t.backgroundColor =backgroundColor;
		t.padding = padding;
		t.name = this.name + " (Clone " + this.clones + ")";
		this.clones++;
		t.showBackground = showBackground;
		t.transform.position.x = transform.position.x ;
		t.transform.position.y = transform.position.y ;
		return t;
	}

	@Override
	public boolean contains(int x, int y){
		Coordinate cameraCoordinate = transform.position.toCameraCoordinates();
		int x1 = cameraCoordinate.x-padding;
		int y1 = cameraCoordinate.y-h+padding;
		int x2 = x1 + w;
		int y2 = y1 + h;
		if(x > x1 && x < x2 && y > y1 && y < y2){
			return true;
		}
		return false;
	}

	@Override
	public void Render(Graphics2D g){
		Coordinate coord = transform.position.toCameraCoordinates();
		int scaledRadius = (int)(fontSize*transform.scale);
		Font uiFont = new Font(font, Font.PLAIN, (int)fontSize);
    	g.setFont(uiFont);
    	int width = g.getFontMetrics().stringWidth(text) + padding*2;
    	this.w = width;
    	FontRenderContext frc = g.getFontRenderContext();
        GlyphVector gv = g.getFont().createGlyphVector(frc, text);
    	int height = gv.getPixelBounds(null, 0, 0).height + padding*2;
    	this.h = height;
    	Coordinate cameraCoordinate = transform.position.toCameraCoordinates();
    	g.setColor(backgroundColor);
    	if(showBackground)
    		g.fill3DRect(cameraCoordinate.x-padding, cameraCoordinate.y-height+padding, width, height, true);
    	g.setColor(color);
    	g.drawString(text, cameraCoordinate.x, cameraCoordinate.y); 
	}

	@Override
	public String toString(){
		return " {" + name + " : " + text+ "} ";
	}
}
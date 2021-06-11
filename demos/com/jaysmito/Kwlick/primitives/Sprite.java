package com.jaysmito.Kwlick.primitives;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.*;

public class Sprite extends Entity{

	public Texture texture;

	public Sprite(){
		super();
		this.texture = null;
	}

	public Sprite(Texture texture){
		super();
		this.texture = texture;
	}

	public void setTexture(Texture texture){
		this.texture = texture;
	}

	@Override
	public boolean contains(int x, int y){
		Coordinate cameraCoordinate = transform.position.toCameraCoordinates();
		
		return false;
	}

	@Override
	public void Render(Graphics2D g){
		Resolve();
		if(this.texture == null){
			//System.out.println("Texture not set!");
			return;
		}
		g.setColor(color);
		Coordinate coord = transform.position.toCameraCoordinates();
		int width = texture.getWidth();
		int height = texture.getHeight();
    	g.drawImage(texture.getImage(), coord.x - width/2, coord.y - height/2, null);
	}
}
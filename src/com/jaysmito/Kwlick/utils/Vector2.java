package com.jaysmito.Kwlick.utils;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;

import com.jaysmito.Kwlick.*;
import com.jaysmito.Kwlick.primitives.*;

public class Vector2{

	public static Vector2 up = new Vector2(0, 1);
	public static Vector2 down = new Vector2(0, -1);
	public static Vector2 right = new Vector2(1, 0);
	public static Vector2 left = new Vector2(-1, 0);

	public float x, y;

	public Vector2(){
		this.x = 0;
		this.y = 0;
	}

	public Vector2(float x, float y){
		this.x = x;
		this.y = y;
	}

	public Vector2 zero(){
		this.x = 0;
		this.y = 0;
		return this;
	}

	public Vector2 add(Vector2 vector){
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}

	public Vector2 subtract(Vector2 vector){
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
	}

	public Vector2 multiply(float scalar){
		this.x *= scalar;
		this.y *= scalar;
		return this;
	}

	public float dot(Vector2 vector){
		return (this.x*vector.x + this.y*vector.y);
	}

	public float magnitude(){
		return (float)Math.sqrt(x*x + y*y);
	}

	public Vector2 normalized(){
		float magnitude = magnitude();
		return new Vector2(x/magnitude, y/magnitude);
	}

	public void normalize(){
		float magnitude = magnitude();
		this.x = this.x/magnitude;
		this.y = this.y/magnitude;
	}

	public void randomize(int rangeX, int rangeY){
		this.x = (float)Math.random()*rangeX - rangeX/2;
		this.y = (float)Math.random()*rangeY - rangeY/2;
	}

	public float angle(){
		return (float)this.y/(float)this.x;
	}

	public Vector2 copy(){
		return new Vector2(this.x, this.y);
	}

	public Coordinate toCameraCoordinates(){
		Coordinate screenCoord = toScreenCoordinates();
		Coordinate cameraOffset = Kwlick.Camera.transform.position.toCoordinates();
		return new Coordinate(screenCoord.x - cameraOffset.x, screenCoord.y + cameraOffset.y);
	}

	public Coordinate toScreenCoordinates(){
		float w = (float)GraphicsContext.Instance.getWidth();
		float h = (float)GraphicsContext.Instance.getHeight();
		return new Coordinate((int)(this.x+w/2), (int)(-this.y+h/2));
	}

	public Coordinate toCoordinates(){
		return new Coordinate((int)this.x, (int)this.y);
	}

	@Override
	public String toString(){
		return "{" + x + ", " + y + "}";
	}

	public static Coordinate toWorldCoordinates(int x, int y){
		float w = (float)Kwlick.Width;
		float h = (float)Kwlick.Height;
		return new Coordinate((x - w/2), (-1 * (y - h/2));
	}
} 

		
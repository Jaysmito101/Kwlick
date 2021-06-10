package com.jaysmito.Kwlick;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.resolvers.*;
import com.jaysmito.Kwlick.primitives.*;

public abstract class Entity{

	private ArrayList<MouseInputListener> listeners;

	public Transform transform;
	public Color color;
	public String name;
	public String tag;
	public int layer;
	public boolean isClickable;

	public boolean isUI;

	private PhysicsResolver physicsResolver;

	public Entity(){
		this.color = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
		this.transform = new Transform();
		this.listeners = new ArrayList<MouseInputListener>();
		this.layer = 1;
		this.tag = "Entity";
		this.isUI = false;
		this.name = "Entity (" + (GraphicsContext.Instance.numEntities()+1) + ")";
	}
	public void SetColor(Color color){
		this.color = color;
	}
	public void SetColor(int r, int g, int b){
		this.color = new Color(r, g, b);
	}
	public boolean IsOutOfScreen(){
		return false;
	}
	public void setPhysicsResolver(PhysicsResolver resolver){
		this.physicsResolver = resolver;
	}



	public void addMouseListener(MouseInputListener listener){
		listeners.add(listener);
	}

	public void onMouseInputEvent(MouseInputEvent e){
		boolean flag = false;
		for(MouseInputListener listener : listeners){
			switch(e.type){
				case CLICK:{
					flag = listener.OnClick(e);
					break;
				}
				case BT_RELEASE:{
					flag = listener.OnMouseButtonReleased(e);
					break;
				}
				case BT_PRESS:{
					flag = listener.OnMouseButtonPressed(e);
					break;
				}
				case ALL:{
					flag = listener.OnMouseButtonPressed(e);
					flag = listener.OnMouseButtonReleased(e);
					flag = listener.OnClick(e);
					break;
				}
				default:{
					flag = true;
					break;
				}
			}
			if(flag)
				break;
		}
	}

	public boolean contains(int x, int y){
		// This is not abstract here so incase not requied i will not have to implement this method in every entity like for the particle system
		return false;
	}

	public void Resolve(){
		if(physicsResolver != null)
			physicsResolver.Resolve(this);
	}

	public void Render(Graphics2D g){}
	public void AddProperty(){}

	public void destroy(){
		GraphicsContext.Instance.destroy(name, 0);
	}

	public void destroy(float delay){
		 GraphicsContext.Instance.destroy(name, delay);
	}
}
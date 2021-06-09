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
	public Transform transform;
	public Color color;
	public String name;
	public String tag;
	public int layer;

	public boolean isUI;

	private PhysicsResolver physicsResolver;

	public Entity(){
		this.color = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
		this.transform = new Transform();
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
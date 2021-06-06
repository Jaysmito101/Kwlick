package com.jaysmito.Kwlick;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.primitives.*;

abstract class Layer{

	protected BufferedImage layer;

	protected boolean isUI;

	public int layerID;

	public BufferedImage GetRenderedLayer(){
		return layer;
	}

	public void Reset(){
		Graphics2D g  = (Graphics2D)layer.getGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, layer.getWidth(), layer.getWidth());
		g.setComposite(AlphaComposite.SrcOver);
	}

	public void AutoResize(){
		if(GraphicsContext.Instance == null)
			layer = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
		else
			layer = new BufferedImage(GraphicsContext.Instance.getWidth(), GraphicsContext.Instance.getHeight(), BufferedImage.TYPE_INT_ARGB);
	}

	public Layer(int layerID){
		AutoResize();
		this.isUI = false;
		this.layerID = layerID;
	}

	public void Render(Entity entity){}
}
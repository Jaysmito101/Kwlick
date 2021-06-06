package com.jaysmito.Kwlick;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.primitives.*;

public abstract class UIEntity extends Entity{

	private ArrayList<ClickListener> listeners;

	public UIEntity(){
		super();
		this.listeners = new ArrayList<ClickListener>();
		this.isUI = true;
		this.layer = 0;
		this.tag = "UIEntity";
		this.name = "UIEntity (" + (GraphicsContext.Instance.numEntities()+1) + ")";
	}

	public void addClickListener(ClickListener listener){
		listeners.add(listener);
	}

	public void onClick(ClickEvent e){
		for(ClickListener listener : listeners){
			listener.OnClick(e);
		}
	}

	public abstract boolean contains(int x, int y);

	@Override
	public void Render(Graphics2D g){
	}
}
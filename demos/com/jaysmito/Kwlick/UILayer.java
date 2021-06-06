package com.jaysmito.Kwlick;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.primitives.*;

public class UILayer extends Layer{

	public UILayer(int layerID){
		super(layerID);
		this.isUI = true;
	}

	@Override
	public void Render(Entity rawEntity){
		UIEntity entity = (UIEntity)rawEntity;
		if(!entity.isUI)
			return;
		Graphics2D g = (Graphics2D)layer.getGraphics();
		if(Kwlick.ANTI_ALIASING)
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		else
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		entity.Render(g);
	}
}
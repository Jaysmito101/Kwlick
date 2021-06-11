package com.jaysmito.Kwlick;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.primitives.*;

public abstract class Application implements Runnable{
	public String title;
	public float FPS;
	private boolean isAlive, showConrols;
	public int delay;


	public Application(){
		this.showConrols = true;
		this.title = "Kwlick";
		this.isAlive = true;
		this.delay = 30;
	}


	public final void setTitle(String title){
		this.title = title;
		MainWindow.Instance.setTitle(title);
	}

	public final void Render(){
		//GraphicsContext.Instance.revalidate();
		GraphicsContext.Instance.repaint();
	}

	public final void Kill(){
		this.isAlive = false;
		OnBeforeClose();
		MainWindow.Instance.Dispose();
		OnClose();
	}


	public void Start(){}
	public void Update(){}
	public void Update(double deltaTime){}


	public void OnBeforeClose(){}
	public void OnClose(){}

	public void OnMouseMove(int x, int y){}
	public void OnMouseButtonPressed(int button, int x, int y){}
	public void OnMouseButtonPressed(int button){}
	public void OnMouseButtonReleased(int button, int x, int y){}
	public void OnMouseButtonReleased(int button){}
	public void OnMouseButtonClicked(int button){}
	public void OnMouseButtonClicked(int button, int x, int y){}
	public void OnMouseEnter(int x, int y){}
	public void OnMouseExit(int x, int y){}
	public void OnMouseDragged(int x, int y){}
	public void OnMouseScrolled(int amount){}
	public void OnMouseScrolled(double amount){}

	@Override
	public void run(){
		long prevTime = System.nanoTime();
		long currTime = prevTime+1;
		while(isAlive){
			while(!MainWindow.isLaunched){
				try{
					Thread.sleep(delay);
				}
				catch(Exception ex){}
			}
			currTime = System.nanoTime();
			double deltaTime = (currTime - prevTime)/1000000000.0;
			FPS = (float)(1/deltaTime);
			prevTime = currTime;
			Kwlick.FPS = FPS;
			Kwlick.DeltaTime = deltaTime;
			GraphicsContext.Instance.DestroyEntities((float)deltaTime);
			Update();
			Update(deltaTime);
			Render();
			ControlPanel.Instance.SetFPS(FPS);
			try{
				Thread.sleep(delay);
			}catch(Exception ex){}
		}
	}
}
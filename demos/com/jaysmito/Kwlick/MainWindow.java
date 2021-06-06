package com.jaysmito.Kwlick;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.primitives.*;

public class MainWindow extends JFrame{

	public static MainWindow Instance;

	public static Application App;

	private static Thread mainApplication;

	public static MainWindow LoadScene(Application app){
		if(Instance == null){
			return CreateInstance(app);
		}else{
			App.Kill();
			try{
				mainApplication.interrupt();
			}catch(Exception ex){}
			App = null;
			mainApplication = null;
			return CreateInstance(app);
		}
	}

	private static MainWindow CreateInstance(Application app){ 
		if(Instance == null){
			if(app == null){
				ControlPanel.Log("Failed to create Application");
				return null;
			}
			App = app;
			Instance = new MainWindow(app.title);
			MainWindow.App.Start();
			setUpApplication();
		}
		return Instance;
	}

	private static void setUpApplication(){
		mainApplication = new Thread(App);
		mainApplication.start();
		ControlPanel.Log("Started Application thread");
	}

	GraphicsContext context;
	ControlPanel controlPanel;

	private MainWindow(String title){
		setLayout(new BorderLayout());
		controlPanel = ControlPanel.CreateInstance(this);
		add(controlPanel, BorderLayout.EAST);
		context = GraphicsContext.CreateInstance();
		add(context, BorderLayout.CENTER);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
	}

	public void Dispose(){
		//mainApplication.interrupt(); // For future use
		this.dispose();
	}

	private void setKeyBinding(int keyCode, AbstractAction action) {
    	int modifier = 0;
    	switch (keyCode) {
	        case KeyEvent.VK_CONTROL:
        	    modifier = InputEvent.CTRL_DOWN_MASK;
    	        break;
	        case KeyEvent.VK_SHIFT:
            	modifier = InputEvent.SHIFT_DOWN_MASK;
        	    break;
    	    case KeyEvent.VK_ALT:
	            modifier = InputEvent.ALT_DOWN_MASK;
            	break;

    	}
    	context.getInputMap().put(KeyStroke.getKeyStroke(keyCode, modifier), keyCode);
    	context.getActionMap().put(keyCode, action);
	}

	public void HideControlPanel(){
		this.remove(controlPanel);
		this.revalidate();
		this.repaint();
	}

	public void ShowControlPanel(){
		this.add(controlPanel, BorderLayout.EAST);
		this.revalidate();
		this.repaint();
	}

	public void launch(){
		setVisible(true);
		context.requestFocus();
		ControlPanel.Log("Launched Window.");
	}
}
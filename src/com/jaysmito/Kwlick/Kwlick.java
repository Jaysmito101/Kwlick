package com.jaysmito.Kwlick;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.primitives.*;
import com.jaysmito.Kwlick.particles.*;

public class Kwlick{
	private Kwlick(){}

	public static Camera Camera = null;

	public static ParticleSystem ParticleSystem = new ParticleSystem();

	public static boolean ANTI_ALIASING = false;

	public static boolean REFRESH_EVERY_FRAME = true;

	public static Color BackgoundColor = Color.BLACK;

	public static InputManager Input = null;

	public static float FPS;

	public static double DeltaTime;

	public static int Height = 0;

	public static int Width = 0;

	public static void Popup(String message, String title){
		JOptionPane.showMessageDialog(GraphicsContext.Instance, message, title, JOptionPane.PLAIN_MESSAGE);
	}

	public static void Popup(String message){
		JOptionPane.showMessageDialog(GraphicsContext.Instance, message, "Kwlick - Info", JOptionPane.PLAIN_MESSAGE);
	}

	public static void LoadScene(Application app){
		Input = new InputManager();
		MainWindow.LoadScene(app);
	}

	public static void RefreshLayers(){
		GraphicsContext.Instance.refreshAllLayers();
	}

	public static void RefreshUI(){
		GraphicsContext.Instance.refreshUI();
	}

	public static void Log(String log){
		ControlPanel.Log(log);
	}

	public static void AddEntity(Entity entity){
		GraphicsContext.Instance.addEntity(entity);
	}

	public static void DestroyEntity(Entity entity){
		DestroyEntity(entity.name);
	}

	public static void DestroyEntity(String entity){
		GraphicsContext.Instance.destroy(entity, 0);
	}

	public static void DestroyEntity(Entity entity, float delay){
		GraphicsContext.Instance.destroy(entity.name, delay);
	}

	public static void DestroyEntity(String entity, float delay){
		GraphicsContext.Instance.destroy(entity, delay);
	}

	public static Entity FindEntity(String name){
		return GraphicsContext.Instance.find(name);
	}

	public static Entity FindEntity(int name){
		return GraphicsContext.Instance.find(name);
	}

	public static void HideControlPanel(){
		MainWindow.Instance.HideControlPanel();
		CenterWindow();
	}

	public static void ShowControlPanel(){
		MainWindow.Instance.ShowControlPanel();
		CenterWindow();
	}

	public static void CenterWindow(){
		MainWindow.Instance.setLocationRelativeTo(null);
	}

	public static void LaunchApp(){
		MainWindow.Instance.launch();
	}

	public static void Quit(){
		Input = null;
		MainWindow.Instance.Dispose();
		System.exit(0);
	}
}
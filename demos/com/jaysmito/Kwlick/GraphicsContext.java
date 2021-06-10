package com.jaysmito.Kwlick;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.primitives.*;


@SuppressWarnings("unchecked")
public  class GraphicsContext extends JPanel{
	public static GraphicsContext Instance;

	public static GraphicsContext CreateInstance(){
		if(Instance == null){
			Instance = new GraphicsContext();
		}
		return Instance;
	}

	private Camera camera;

	private ConcurrentHashMap<String, Entity> entities;

	private ConcurrentHashMap<String, Float> entitiesToDestroy;

	private ArrayList<Layer> layers;

	private GraphicsContext(){
		this.layers = new ArrayList<Layer>();
		this.layers.add(new UILayer(0));
		this.layers.add(new StandardEntityLayer(1));
		this.camera = new Camera();
		Kwlick.Camera = camera;
		this.entities = new ConcurrentHashMap<String, Entity>();
		this.entitiesToDestroy = new ConcurrentHashMap<String, Float>();
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				for(Layer layer: layers){
					Kwlick.Width = getWidth();
					Kwlick.Height = getHeight();
					layer.AutoResize();
				}
			}
		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				requestFocus();
				checkForMouseEvent(e.getX(), e.getY(), e.getButton(), MouseInputEvent.MouseInputEventType.BT_PRESS);
				MainWindow.App.OnMouseButtonPressed(e.getButton(), e.getX(), e.getY());
				MainWindow.App.OnMouseButtonPressed(e.getButton());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				checkForMouseEvent(e.getX(), e.getY(), e.getButton(), MouseInputEvent.MouseInputEventType.BT_RELEASE);
				MainWindow.App.OnMouseButtonReleased(e.getButton(), e.getX(), e.getY());
				MainWindow.App.OnMouseButtonReleased(e.getButton());
			}

			@Override
			public void mouseClicked(MouseEvent e){
				checkForMouseEvent(e.getX(), e.getY(), e.getButton(), MouseInputEvent.MouseInputEventType.CLICK);
				MainWindow.App.OnMouseButtonClicked(e.getButton(), e.getX(), e.getY());
				MainWindow.App.OnMouseButtonClicked(e.getButton());
			}

			@Override
			public void mouseEntered(MouseEvent e){
				requestFocus();
				MainWindow.App.OnMouseEnter(e.getX(), e.getY());
			}

			@Override
			public void mouseExited(MouseEvent e){
				MainWindow.App.OnMouseExit(e.getX(), e.getY());
			}

			@Override
			public void mouseWheelMoved(MouseWheelEvent e){
				MainWindow.App.OnMouseScrolled(e.getPreciseWheelRotation());
				MainWindow.App.OnMouseScrolled(e.getWheelRotation());
			}
		});
		this.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseMoved(MouseEvent e){
				MainWindow.App.OnMouseMove(e.getX(), e.getY());
			}

			@Override
			public void mouseDragged(MouseEvent e){
				MainWindow.App.OnMouseDragged(e.getX(), e.getY());
			}
		});
		this.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) {  
				Kwlick.Input.setKey(e.getKeyCode(), true);
			}  
			@Override
			public void keyReleased(KeyEvent e) {  
				Kwlick.Input.setKey(e.getKeyCode(), false);
			}  
			@Override
			public void keyTyped(KeyEvent e){}
		});
	}

	protected int numEntities(){
		return entities.size();
	}

	protected synchronized void destroy(String entity, float delay){
		entitiesToDestroy.put(entity, delay);
	}

	protected synchronized void addEntity(Entity entity){		
		entities.put(entity.name+"", entity);
	}

	protected Entity find(int name){
		return entities.get(""+name);
	}

	protected Entity find(String name){
		return entities.get(name);
	}

	private void checkForMouseEvent(int x, int y, int button, MouseInputEvent.MouseInputEventType type){
		ConcurrentHashMap<String, Entity> entitiesCopy = null;
		synchronized(entities){
			//entitiesCopy = (ConcurrentHashMap<String, Entity>)entities.clone();
			entitiesCopy = entities;
		}
		try{
			for(Entity entity : entitiesCopy.values()){
				if(entity.isClickable){
					if( (entity).contains(x, y) ){
						(entity).onMouseInputEvent(new MouseInputEvent(x, y, button, type));
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	protected synchronized  void DestroyEntities(float deltaTime){
		deltaTime *= 1000;
		Iterator it = entitiesToDestroy.entrySet().iterator();
		ArrayList<String> tmp = new ArrayList<>();
		while (it.hasNext()) {
			try{
				Map.Entry pair = (Map.Entry)it.next();
				entitiesToDestroy.put(pair.getKey().toString(), entitiesToDestroy.get(pair.getKey().toString()) - deltaTime);
				if(entitiesToDestroy.get(pair.getKey().toString()) < 0){
					tmp.add(pair.getKey().toString());
				}
			}catch(Exception ex){}
		}
		synchronized(entities){
			synchronized(entitiesToDestroy){
				for(String entity:tmp){
					entities.remove(entity);
					entitiesToDestroy.remove(entity);
				}
			}
		}
	}

	protected void refreshAllLayers(){
		for(Layer layer : layers){
			layer.Reset();
		}
	}

	protected void refreshUI(){
		for(Layer layer : layers){
			if(layer.isUI){
				layer.Reset();
			}
		}
	}

	@Override
	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D)graphics;
		if(Kwlick.ANTI_ALIASING)
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		else
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		g.setColor(Kwlick.BackgoundColor);
		g.fillRect(0, 0, getWidth(), getHeight());		
		synchronized(entities){
			synchronized(layers){
				if(Kwlick.REFRESH_EVERY_FRAME){
					refreshAllLayers();
				}
				for(Entity entity: entities.values()){
					try{
						layers.get(entity.layer).Render(entity);
					}catch(Exception ex){
				// Invalid Layer Id set in Intity will not be rendered
					}
				}
				for(int i=layers.size()-1 ; i >= 0 ; i--){
					camera.Render(g, layers.get(i));
				}
			}
		}
		g.setColor(Color.BLACK);
	}
}
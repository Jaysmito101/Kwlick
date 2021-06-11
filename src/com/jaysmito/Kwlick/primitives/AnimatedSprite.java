package com.jaysmito.Kwlick.primitives;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.*;

public class AnimatedSprite extends Entity{

	public Texture[] textures;
	public int animationState;
	private boolean isPlaying;
	public boolean repeat;
	public long animDelay;

	private Thread animation;

	public AnimatedSprite(){
		super();
		this.isPlaying = true;
		this.repeat = true;
		this.animationState = 0;
		this.textures = null;
		this.animDelay = 60;
		startAnimation();
	}

	public AnimatedSprite(Texture[] textures){
		super();
		this.isPlaying = true;
		this.repeat = true;
		this.animationState = 0;
		this.animDelay = 60;
		this.textures = textures;
		startAnimation();
	}

	public void setTextures(Texture[] textures){
		this.isPlaying = true;
		this.repeat = true;
		this.animationState = 0;
		this.animDelay = 60;
		this.textures = textures;
		startAnimation();
	}


	public boolean isPlaying(){
		return isPlaying;
	}

	public void pause(){
		isPlaying = false;
		animation.interrupt();
		animation = null;
	}

	public void stop(){
		pause();
		animationState = 0;
	}

	public void startAnimation(){
		if(textures == null)
			return;
		if(animation == null){
			animation = new Thread(new Runnable(){
				@Override
				public void run(){
					while(isPlaying){
						try{
							animationState++;
							if(animationState == textures.length){
								if(repeat){
									animationState = 0;
								}else{
									animationState--;
								}
							}
							Thread.sleep(animDelay);
						}catch(InterruptedException ie){
							// Nothing needed
						}catch(Exception ex){
							ex.printStackTrace();
						}
					}
				}
			});
			animation.start();
		}else{
			try{
				animation.interrupt();
				animation = null;
				startAnimation();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	@Override
	public boolean contains(int x, int y){
		Coordinate cameraCoordinate = transform.position.toCameraCoordinates();
		// To be done in future
		return false;
	}

	@Override
	public void Render(Graphics2D g){
		Resolve();
		if(this.textures == null){
			//System.out.println("Textures not set!");
			return;
		}
		g.setColor(color);
		Coordinate coord = transform.position.toCameraCoordinates();
		int width = textures[animationState].getWidth();
		int height = textures[animationState].getHeight();
		g.drawImage(textures[animationState].getImage(), coord.x - width/2, coord.y - height/2, null);
	}
}
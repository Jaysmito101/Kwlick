package com.jaysmito.Kwlick.particles;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.*;
import com.jaysmito.Kwlick.primitives.*;

public class ParticleCluster extends Entity{
	private LinkedList<Particle> particles;

	protected ParticleCluster(int approxNumParticles, float maxSize, float minSize, float spread, float approxSpeed, boolean isOnePointStart, float randomness, float life, float x, float y){
		super();
		this.transform.position.x = x;
		this.transform.position.y = y;
		this.particles = new LinkedList<Particle>();
		generate(approxNumParticles, maxSize, minSize, spread, approxSpeed, isOnePointStart, randomness, life);
	}

	private void generate(int approxNumParticles, float maxSize, float minSize, float spread, float approxSpeed, boolean isOnePointStart, float randomness, float life){
		int numPart = (int)(Math.random()*200*randomness) +approxNumParticles;
		for(int i=0;i<numPart;i++){
			Particle p = new Particle(maxSize, minSize, approxSpeed, randomness, this.transform.position, spread);
			p.randomizeDir();
			p.name = "Particle (" + (int)(Math.random()*numPart*5) + ")";
			if(!isOnePointStart){
				p.transform.position.x = (float)Math.random() * spread - spread/2;
				p.transform.position.y = (float)Math.random() * spread - spread/2;
			}
			Kwlick.AddEntity(p);
			p.destroy( (float)(Math.random()*5000) + life );
			particles.add(p);
		}
	}

	// Actually this should never be used!
	@Override
	public void destroy(){
		synchronized(particles){
			for(Particle p:particles){
				p.destroy();
			}
		}
	}

	@Override
	public void Render(Graphics2D g){

	}
}

class Particle extends Entity{
	public float dirX, dirY, speed, spread;

	public float radius; 

	Vector2 parent;

	public Particle(float maxSize, float minSize, float approxSpeed, float randomnes, Vector2 parent, float spread){
		this.radius = ((float)((Math.random()*(maxSize-minSize)) + minSize));
		this.spread = spread;
		this.parent = parent;
		this.speed = (float)( (Math.random()* (50*randomnes) ) + approxSpeed - (50*randomnes/2) );
	}

	public void randomizeDir(){
		dirX = (float)(Math.random()*2 -1);
		dirY = (float)(Math.random()*2 -1);
	}

	@Override
	public void Render(Graphics2D g){
		this.transform.position.x += dirX * Kwlick.DeltaTime * speed;
		this.transform.position.y += dirY * Kwlick.DeltaTime * speed;
		if(this.transform.position.magnitude()  > spread){
			this.destroy();
		}
		else{
			g.setColor(color);
			Coordinate coord = new Vector2(transform.position.x + parent.x, transform.position.y + parent.y).toCameraCoordinates();
			int scaledRadius = (int)(radius*transform.scale);
			g.fillOval(coord.x - scaledRadius/2, coord.y - scaledRadius/2, scaledRadius, scaledRadius);
		}
	}
}
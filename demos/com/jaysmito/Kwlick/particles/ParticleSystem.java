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

public class ParticleSystem{

	public int approxNumParticles;
	public float maxSize;
	public float minSize;
	public float spread;
	public float approxSpeed;
	public boolean isOnePointStart;
	public float randomness;
	public float life;
	public float x, y;

	public ParticleSystem(){
		this.approxNumParticles = 5000;
		this.maxSize = 20;
		this.minSize = 5;
		this.spread = 100;
		this.approxSpeed = 30;
		this.isOnePointStart = false;
		this.randomness = 1;
		this.x = 0;
		this.y = 0;
		this.life = 3000; // 3 Seconds
	}

	public ParticleCluster generate(){
		return new ParticleCluster(approxNumParticles, maxSize, minSize, spread, approxSpeed, isOnePointStart, randomness, life, x, y);
	}
}

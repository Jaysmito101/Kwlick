import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.primitives.*;
import com.jaysmito.Kwlick.resolvers.*;
import com.jaysmito.Kwlick.uiprimitives.*;
import com.jaysmito.Kwlick.particles.*;
import com.jaysmito.Kwlick.*;

public class App{
	public static void main(String[] args) {
		Kwlick.LoadScene(new Fireworks());
		Kwlick.LaunchApp();
	}
}

class Fireworks extends Application{
	public void Start(){
		setTitle("Advanced Particle System Demo [Jaysmito Mukherjee]");
		Kwlick.ParticleSystem.spread= 200;
		Kwlick.ParticleSystem.maxSize= 10;
		Kwlick.ParticleSystem.approxNumParticles = 500;
		Kwlick.ParticleSystem.isOnePointStart = true;
		try{
			new Thread(new Runnable(){
				@Override
				public void run(){
					try{
						Thread.sleep(1000);
					}catch(Exception ex){}
					while(true){ // better to have a flag variable
						ParticleCluster pc = Kwlick.ParticleSystem.generate();
						pc.transform.position.x = (float)Math.random()*700 - 350;
						pc.transform.position.y = (float)Math.random()*700 - 350;
						try{
							Thread.sleep(1000);
						}catch(Exception ex){}
					}
				}
			}).start();
		}catch(Exception ex){}
		Kwlick.ANTI_ALIASING = true;
	}
}

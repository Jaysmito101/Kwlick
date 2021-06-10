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
		Kwlick.LoadScene(new FlappyScene());
		Kwlick.LaunchApp();
	}
}

class FlappyScene extends Application{
	Circle player;
	public void Start(){
		setTitle("Flappy Bird -Made with Kwlick [Jaysmito Mukherjee]");
		// Setup particle system for effects
		Kwlick.ParticleSystem.spread= 200;
		Kwlick.ParticleSystem.maxSize= 10;
		Kwlick.ParticleSystem.approxNumParticles = 500;
		Kwlick.ParticleSystem.isOnePointStart = true;
		Kwlick.ANTI_ALIASING = true;
	}

	public void ResetGame(){
		
	}
}

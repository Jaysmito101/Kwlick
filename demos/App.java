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
		if(args.length != 1){
			System.out.println("Usage : java App [EXAMPLE NAME]");
			return;
		}
		switch(args[0].toLowerCase()){
			case "particles":{
				Kwlick.LoadScene(new FiftyThousandParticlesDemo());
				Kwlick.LaunchApp();
				break;
			}
			case "basic-control":{
				Kwlick.LoadScene(new BasicPlayerControlDemo());
				Kwlick.LaunchApp();
				break;
			}
			case "basic-ui":{
				Kwlick.LoadScene(new BasicUIDemo());
				Kwlick.LaunchApp();
				break;
			}
			case "basic-gravity":{
				Kwlick.LoadScene(new BasicGravityDemo());
				Kwlick.LaunchApp();
				break;
			}
			case "basic-force":{
				Kwlick.LoadScene(new BasicForceDemo());
				Kwlick.LaunchApp();
				break;
			}
			case "polygon":{
				Kwlick.LoadScene(new PolygonDemo());
				Kwlick.LaunchApp();
				break;
			}
			case "adv-particles":{
				Kwlick.LoadScene(new AdvancsdParticleSystemDemo());
				Kwlick.LaunchApp();
				break;
			}
		}
	}
}

class AdvancsdParticleSystemDemo extends Application{
	Text t1;
	public void Start(){
		setTitle("Advanced Particle System Demo [Jaysmito Mukherjee]");
		Kwlick.ParticleSystem.spread= 100;
		Kwlick.ParticleSystem.maxSize= 15;
		Kwlick.ParticleSystem.approxSpeed= 200;
		Kwlick.ParticleSystem.approxNumParticles = 50;
		Kwlick.ParticleSystem.isOnePointStart = true;
		t1 = new Text("Click and drag your mouse!");
		t1.fontSize = 16;
		t1.padding = 10;
		t1.backgroundColor = new Color(225, 255, 0);
		Kwlick.AddEntity(t1);
		Kwlick.ANTI_ALIASING = true;
	}

	public void Update(){
		t1.transform.position.x = -(Kwlick.Width/2) + 30;
		t1.transform.position.y = (Kwlick.Height/2) - 30;
	}

	@Override
	public void OnMouseDragged(int x, int y){
		int xx = Vector2.toWorldCoordinates(x, y).x;
		int yy = Vector2.toWorldCoordinates(x, y).y;
		ParticleCluster pc = Kwlick.ParticleSystem.generate();
		pc.transform.position.x = xx;
		pc.transform.position.y = yy;
		//Kwlick.Log("Created new Particle System in World Coordinates("+ xx +", "+ yy +")");
	}
}

class PolygonDemo extends Application{
	SimplePolygon p;
	Text t1;
	public void Start(){
		setTitle("Basic Polygon Demo [Jaysmito Mukherjee]");
		p = new SimplePolygon();
		p.name = "MyPolygon";
		p.fill = false;
		t1 = new Text("Geneerate new Polygon");
		t1.fontSize = 16;
		t1.padding = 10;
		t1.backgroundColor = new Color(225, 255, 0);
		t1.addMouseListener(new MouseInputListener(){
			@Override
			public boolean OnClick(MouseInputEvent e){
				Kwlick.DestroyEntity(p.name); // No need to worry as if this is not added this does nothing!
				int numVert = (int)(Math.random()*20)+3;
				p.EmptyVertices();
				for(int i=0;i<numVert;i++){
					int x = (int)(Math.random()*600)-300;
					int y = (int)(Math.random()*600)-300;
					p.AddVertex(x, y);
				}
				p.name = "" + Math.random();
				Kwlick.AddEntity(p);
				return true;
			}
		});
		Kwlick.ANTI_ALIASING = true;
		Kwlick.AddEntity(t1);
	}

	public void Update(double deltaTime){
		t1.transform.position.x = -(Kwlick.Width/2) + 30;
		t1.transform.position.y = (Kwlick.Height/2) - 30;
	}
}


class BasicForceDemo extends Application{
	Circle c;
	Text t1, t2, t3, t4, t5, t6, t7;
	ForceResolver res ;
	public void Start(){
		setTitle("Basic Force Demo [Jaysmito Mukherjee]");
		Kwlick.ANTI_ALIASING = true;
		c = new Circle(100);
		res = new ForceResolver();
		c.setPhysicsResolver(res);
		c.transform.position.y = 300;
		t1=new Text("Reset Position");
		t1.addMouseListener(new MouseInputListener(){
			@Override
			public boolean OnClick(MouseInputEvent e){
				c.transform.position.x = 0;
				c.transform.position.y = 0;
				res.Halt();
				res.ClearForces();
				return true;

			}
		});
		t1.fontSize = 16;
		t1.padding = 10;
		t1.backgroundColor = new Color(225, 255, 0);
		t2 = t1.clone("Add Force Towards Left");
		t2.addMouseListener(new MouseInputListener(){
			@Override
			public boolean OnClick(MouseInputEvent e){
				res.AddForce(new Vector2(-100, 0));
				return true;
			}
		});
		t3 = t1.clone("Add Force Towards Right");
		t3.addMouseListener(new MouseInputListener(){
			@Override
			public boolean OnClick(MouseInputEvent e){
				res.AddForce(new Vector2(100, 0));
				return true;
			}
		});
		t4 = t1.clone("Add Force Towards Up");
		t4.addMouseListener(new MouseInputListener(){
			@Override
			public boolean OnClick(MouseInputEvent e){
				res.AddForce(new Vector2(0, 100));
				return true;
			}
		});
		t5 = t1.clone("Add Force Towards Down");
		t5.addMouseListener(new MouseInputListener(){
			@Override
			public boolean OnClick(MouseInputEvent e){
				res.AddForce(new Vector2(0, -100));
				return true;
			}
		});
		t6 = t1.clone("Activate Gravity");
		t6.addMouseListener(new MouseInputListener(){
			@Override
			public boolean OnClick(MouseInputEvent e){
				if(res.isGravityActive){
					res.isGravityActive = false;					
				}else{
					res.isGravityActive = true;					
				}
				return true;
			}
		});
		t7 = t1.clone("Activate Drag");
		t7.addMouseListener(new MouseInputListener(){
			@Override
			public boolean OnClick(MouseInputEvent e){
				if(res.drag == 0){
					res.drag = 2;					
				}else{
					res.drag = 0;					
				}
				return true;
			}
		});
		Kwlick.AddEntity(t1);
		Kwlick.AddEntity(t2);
		Kwlick.AddEntity(t3);
		Kwlick.AddEntity(t4);
		Kwlick.AddEntity(t5);
		Kwlick.AddEntity(t6);
		Kwlick.AddEntity(t7);
		Kwlick.AddEntity(c);
	}

	public void Update(double deltaTime){
		t1.transform.position.x = -(Kwlick.Width/2) + 30;
		t1.transform.position.y = (Kwlick.Height/2) - 30;

		t2.transform.position.x = -(Kwlick.Width/2) + 30;
		t2.transform.position.y = (Kwlick.Height/2) - 30 - 60;

		t3.transform.position.x = -(Kwlick.Width/2) + 30;
		t3.transform.position.y = (Kwlick.Height/2) - 30 - 120;

		t4.transform.position.x = -(Kwlick.Width/2) + 30;
		t4.transform.position.y = (Kwlick.Height/2) - 30 - 180;

		t5.transform.position.x = -(Kwlick.Width/2) + 30;
		t5.transform.position.y = (Kwlick.Height/2) - 30 - 240;

		t6.transform.position.x = -(Kwlick.Width/2) + 30;
		t6.transform.position.y = (Kwlick.Height/2) - 30 - 300;

		t7.transform.position.x = -(Kwlick.Width/2) + 30;
		t7.transform.position.y = (Kwlick.Height/2) - 30 - 360;

		if(res.isGravityActive){
			t6.text = "Deactivate Gravity";
		}else{
			t6.text = "Activate Gravity";
		}

		if(res.drag != 0){
			t7.text = "Deactivate Drag";
		}else{
			t7.text = "Activate Drag";
		}
	}
}

class BasicGravityDemo extends Application{
	Circle c;
	Text t;
	public void Start(){
		setTitle("Basic Gravity Demo [Jaysmito Mukherjee]");
		Kwlick.ANTI_ALIASING = true;
		c = new Circle(100);
		c.transform.position.y = 300;
		t=new Text("Simulate Gravity");
		t.fontSize = 16;
		t.padding = 10;
		t.backgroundColor = new Color(225, 255, 0);
		t.addMouseListener(new MouseInputListener(){
			@Override
			public boolean OnClick(MouseInputEvent e){
				c.transform.position.y = 300;
				c.setPhysicsResolver(new GravityResolver());
				return true;
			}
		});
		Kwlick.AddEntity(t);
		Kwlick.AddEntity(c);
	}

	public void Update(double deltaTime){
		t.transform.position.x = -(Kwlick.Width/2) + 30;
		t.transform.position.y = (Kwlick.Height/2) - 30;
	}
}

class BasicUIDemo extends Application{
	Text text;
	public void Start(){
		setTitle("Basic UI Demo [Jaysmito Mukherjee]");
		text = new Text("Click Me!");
		Kwlick.ANTI_ALIASING = true;
		text.transform.position.x = -100;
		text.color = Color.RED;
		text.fontSize= 50;
		text.padding = 20;
		text.addMouseListener(new MouseInputListener(){
			@Override
			public boolean OnClick(MouseInputEvent e){
				Kwlick.Popup("You Clicked Me!");
				return true;
			}
		});
		Kwlick.AddEntity(text);
	}

	public void Update(double deltaTime){
	}
}

class BasicPlayerControlDemo extends Application{
	float speed;
	Circle c;
	boolean isAc;
	public void Start(){
		Kwlick.ANTI_ALIASING = true;
		isAc = false;
		Kwlick.ParticleSystem.spread= 200;
		Kwlick.ParticleSystem.maxSize= 10;
		Kwlick.ParticleSystem.approxNumParticles = 20;
		Kwlick.ParticleSystem.isOnePointStart = true;
		//Kwlick.HideControlPanel();
		setTitle("Basic Player Control Demo [Jaysmito Mukherjee]");
		speed = 100;
		c = new Circle(100);
		c.color = Color.RED;
		c.name = "Player";
		Kwlick.AddEntity(c);
	}

	public void Update(double deltaTime){
		float speedX = Kwlick.Input.GetAxis("Horizontal");
		float speedY = Kwlick.Input.GetAxis("Vertical");
		Entity e = Kwlick.FindEntity("Player");
		e.transform.position.x += deltaTime*speedX*speed;
		e.transform.position.y += deltaTime*speedY*speed;
		if(!isAc && c!=null){
			isAc = true;
			ParticleCluster pc = Kwlick.ParticleSystem.generate();
			pc.transform.position.x = c.transform.position.x;
			pc.transform.position.y = c.transform.position.y;
			// This is just for the demo in a proper game delay with theads like this is a bad practice
			new Thread(new Runnable(){
				@Override
				public void run(){
					try{
						Thread.sleep(500);
					}catch(Exception ex){}
					isAc = false;
				}
			}).start();
		}
	}
}

class FiftyThousandParticlesDemo extends Application{
	float[][] speed;
	public void Start(){
		Kwlick.ANTI_ALIASING = true;
		setTitle("Fifty Thousand Particles Demo [Jaysmito Mukherjee]");
		speed = new float[2][50000];
		for(int i=0;i<50000;i++){
			Circle c = new Circle((float)Math.random()*10);
			c.transform.position.randomize(-500, 500);
			c.name = "" +i;
			Kwlick.AddEntity(c);
			//Kwlick.DestroyEntity(c, (float)Math.random()*12000);
			speed[0][i] = (float)Math.random()*200-100;
			speed[1][i] = (float)Math.random()*200-100;
		}
	}

	public void Update(double deltaTime){
		for(int i=0;i<50000;i++){
			try{			
				Circle c = (Circle)Kwlick.FindEntity(""+i);
				c.transform.position.x += deltaTime*speed[0][i];
				c.transform.position.y += deltaTime*speed[1][i];
				if(Math.abs(c.transform.position.x) > 200 || Math.abs(c.transform.position.y) > 200){
				}
			}catch(Exception ex){
			}
		}
	}
}
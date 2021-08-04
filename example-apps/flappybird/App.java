import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.geom.*;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.primitives.*;
import com.jaysmito.Kwlick.resolvers.*;
import com.jaysmito.Kwlick.uiprimitives.*;
import com.jaysmito.Kwlick.particles.*;
import com.jaysmito.Kwlick.*;

public class App{
	public static void main(String[] args) {
		Kwlick.LoadScene(new Flappy());
		Kwlick.LaunchApp();
	}
}

class Flappy extends Application{
	ForceResolver res;
	float obsTimer;
	float cooldown;
	int sc;
	Text score;
	Sprite sprite;
	public boolean isPlay;
	public void Start(){
		isPlay = true;
		setTitle("SimplyFlappy - Made with Kwlick [Jaysmito Mukherjee]");
		Texture t = Texture.FromFile("res/sample-sprite.png");
		this.sc = 0;
		t.resize(80, 80);
		cooldown = 0;
		obsTimer = 5;
		score = new Text("Score : " + sc);
		score.color = Color.WHITE;
		score.showBackground = false;
		res = new ForceResolver();
		sprite = new Sprite(t);
		sprite.setPhysicsResolver(res);
		res.isGravityActive = true;
		res.drag = 0;		
		Kwlick.AddEntity(sprite);
		Kwlick.AddEntity(score);
		Kwlick.ANTI_ALIASING = true;
	}

	public void Update(double deltaTime){
		score.transform.position.x = Kwlick.Width/2 -170;
		score.transform.position.y = Kwlick.Height/2 - 50;
		if(!isPlay)
			return;
		if(obsTimer <= 0){
			obsTimer = (float)Math.random()*3 + 5;
			addRect();
		}
		obsTimer -= deltaTime;
		float activity = Kwlick.Input.GetAxis("Jump");
		if(cooldown <= 0 && activity == 1){
			cooldown = 0.5f;
			res.ClearForces();
			res.Halt();
			res.AddForce(new Vector2(0, 250));		
		}else{
			cooldown -= (float)deltaTime;
		}
		if(Math.abs(sprite.transform.position.y) > Kwlick.Height/2){
			Kwlick.DestroyEntity(sprite);
			Text go = new Text("Game Over!");
			go.name = "GO";
			go.showBackground = false;
			go.color = Color.WHITE;
			Kwlick.AddEntity(go);
			go = new Text("Click to restart");
			go.name = "RS";
			go.showBackground = false;
			go.color = Color.WHITE;
			go.transform.position.y -= 50;
			go.transform.position.x += 5;			
			go.fontSize = 3*go.fontSize/4;
			Kwlick.AddEntity(go);
			isPlay = false;
		}
	}

	@Override
	public void OnMouseButtonPressed(int b){
		if(!isPlay){
			Kwlick.DestroyEntity("GO");
			Kwlick.DestroyEntity("RS");
			isPlay = true;
			Texture t = Texture.FromFile("res/sample-sprite.png");
			sc = 0;
			t.resize(80, 80);
			cooldown = 0;
			obsTimer = 5;
			sprite = new Sprite(t);
			sprite.setPhysicsResolver(res);	
			Kwlick.AddEntity(sprite);
			updateScore();
		}
	}

	public void addRect(){
		float h = (float)Math.random()*(3*Kwlick.Height/4-300)+100;
		PlainRectangle r = new PlainRectangle(100, h, this);
		r.name = "P_TOP" + Math.random();
		r.transform.position.x = Kwlick.Width/2;
		r.transform.position.y -= Kwlick.Height/2 - r.height/2;
		Kwlick.AddEntity(r);
		Kwlick.DestroyEntity(r, 50000);
		r = new PlainRectangle(100, Kwlick.Height-h-250, null);
		r.name = "P_BOT" + Math.random();
		r.transform.position.x = Kwlick.Width/2;
		r.transform.position.y += Kwlick.Height/2 - r.height/2;
		Kwlick.AddEntity(r);
		Kwlick.DestroyEntity(r, 50000);
	}

	private void updateScore(){
		score.text = "Score : " + sc;
	}

	public void addToScore(){
		sc++;
		updateScore();
	}
}


class PlainRectangle extends Entity{
	public Flappy flappy;
	public float width, height, speed; 
	public boolean fill, done;

	public PlainRectangle(float width, float height, Flappy flappy){
		this.width = width;
		this.height = height;
		this.fill = true;
		this.done = false;
		this.speed = 80;
		this.flappy = flappy;
	}



	@Override
	public boolean contains(int x, int y){
		Coordinate cameraCoordinate = transform.position.toCameraCoordinates();
		int w = (int)(width * transform.scale);
		int h = (int)(height * transform.scale);
		int x1 = cameraCoordinate.x- w/2;
		int y1 = cameraCoordinate.y-h/2;
		int x2 = x1 + (int)width;
		int y2 = y1 + (int)height;
		if(x > x1 && x < x2 && y > y1 && y < y2){
			return true;
		}
		return false;	
	}

	@Override
	public void Resolve(){
		if(transform.position.x < -100 && flappy != null && !done && flappy.isPlay){
			flappy.addToScore();
			this.done = true;
		}
	}

	@Override
	public void Render(Graphics2D g){
		transform.position.x -= Kwlick.DeltaTime * speed;
		Resolve();
		g.setColor(color);
		Coordinate coord = transform.position.toCameraCoordinates();
		int w = (int)(width * transform.scale);
		int h = (int)(height * transform.scale);
		AffineTransform old = g.getTransform();
		g.rotate(Math.toRadians(transform.rotation));
		if(fill)
			g.fillRect(coord.x - w/2, coord.y - h/2, w, h);
		else
			g.drawRect(coord.x - w/2, coord.y - h/2, w, h);
		g.setTransform(old);
	}
}
package com.jaysmito.Kwlick.resolvers;

import com.jaysmito.Kwlick.Entity;
import com.jaysmito.Kwlick.Kwlick;
import com.jaysmito.Kwlick.utils.*;

public class ForceResolver implements PhysicsResolver{
	public Vector2 force;
	public Vector2 velocity;
	public float g;
	public float mass;
	public float drag;

	public boolean isGravityActive;

	public ForceResolver(){
		this.mass = 1;
		this.drag = 0.0f;
		this.force = new Vector2();
		this.g = -9.80665f;
		this.velocity = new Vector2();
		this.isGravityActive = false;
	}

	public void AddForce(Vector2 force){
		this.force.add(force);
	}

	public void Halt(){
		this.velocity.zero();
	}

	public void ClearForces(){
		this.force.zero();
	}

	public void ApplyDrag(){
		if(velocity.x < 0){
			velocity.x += drag * (float)Kwlick.DeltaTime;
		}else{
			velocity.x -= drag * (float)Kwlick.DeltaTime;
		}
		if(velocity.y < 0){
			velocity.y += drag * (float)Kwlick.DeltaTime;
		}else{
			velocity.y -= drag * (float)Kwlick.DeltaTime;
		}
		if(force.x < 0){
			force.x += drag * (float)Kwlick.DeltaTime;
		}else{
			force.x -= drag * (float)Kwlick.DeltaTime;
		}
		if(force.x < 0){
			force.x += drag * (float)Kwlick.DeltaTime;
		}else{
			force.x -= drag * (float)Kwlick.DeltaTime;
		}
	}

	@Override
	public void Resolve(Entity entity){
		if(isGravityActive)
			AddForce(new Vector2(0, g).multiply(mass));
		ApplyDrag();
		velocity.add(force.multiply((float)Kwlick.DeltaTime).multiply(1/mass));
		entity.transform.position.add(velocity);
	}
}
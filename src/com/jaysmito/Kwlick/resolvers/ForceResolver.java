package com.jaysmito.Kwlick.resolvers;

import com.jaysmito.Kwlick.Entity;
import com.jaysmito.Kwlick.Kwlick;
import com.jaysmito.Kwlick.utils.*;

public class ForceResolver implements PhysicsResolver{
	
	private Vector2 force;

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

	public void SetForce(Vector2 force){
		this.force.x = force.x;
		this.force.y = force.y;
	}

	public void Halt(){
		this.velocity.zero();
	}

	public void ClearForces(){
		this.force.zero();
	}

	private void ApplyDrag(){
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
	}

	@Override
	public void Resolve(Entity entity){
		velocity.add(force.multiply((float)Kwlick.DeltaTime).multiply(1/mass));
		if(isGravityActive)
			velocity.add(new Vector2(0, g*(float)Kwlick.DeltaTime)); // For gravity
		ApplyDrag();
		ClearForces();
		entity.transform.position.add(velocity);
	}
}
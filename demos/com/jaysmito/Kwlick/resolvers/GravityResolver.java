package com.jaysmito.Kwlick.resolvers;

import com.jaysmito.Kwlick.Entity;
import com.jaysmito.Kwlick.Kwlick;

public class GravityResolver implements PhysicsResolver{
	public float g;

	public float velocity;

	public GravityResolver(float g){
		this.g=g;
		this.velocity = 0;
	}

	public GravityResolver(){
		this.g=-9.80665f;
		this.velocity = 0;
	}

	@Override
	public void Resolve(Entity entity){
		velocity += g* Kwlick.DeltaTime;
		entity.transform.position.y += velocity;
	}
}
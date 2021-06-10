package com.jaysmito.Kwlick;

import com.jaysmito.Kwlick.utils.Vector2;

public class MouseInputEvent{

	public enum MouseInputEventType{
		CLICK,
		BT_PRESS,
		BT_RELEASE,
		ALL
	}

	public int screenX, screenY;
	public float worldX, worldY;
	public int button;
	public MouseInputEventType type;


	// Outer should never create any of these objects
	protected MouseInputEvent(int x, int y, int button, MouseInputEventType type){
		this.screenX = x;
		this.screenY = y;
		this.button = button;
		Vector2 worldCoord = Vector2.toPositionVector(x, y);
		this.worldX = worldCoord.x;
		this.worldY = worldCoord.y;
		this.type = type;
	}
}
package com.jaysmito.Kwlick;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.primitives.*;

public abstract class UIEntity extends Entity{


	public UIEntity(){
		super();
		this.isClickable = true;
		this.isUI = true;
		this.layer = 0;
		this.tag = "UIEntity";
		this.name = "UIEntity (" + (GraphicsContext.Instance.numEntities()+1) + ")";
	}

	@Override
	public void Render(Graphics2D g){
	}
}
package com.jaysmito.Kwlick;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.image.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.primitives.*;

// As of now this class is useless(other than holding the transform which could be shifted to GraphicsContext)
// and this class may be removed but i have some future plans with this so its still present.

public class Camera{
	public Transform transform = null;


	public Camera(){
		transform = new Transform();
	}

	public void Render(Graphics2D g, Layer layer){
		g.drawImage(layer.GetRenderedLayer(), 0, 0, null);
	}
}
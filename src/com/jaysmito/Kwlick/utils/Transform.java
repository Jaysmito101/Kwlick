package com.jaysmito.Kwlick.utils;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;



public class Transform{
	public Vector2 position;
	public float rotation;
	public float scale;

	public Transform(){
		position = new Vector2();
		rotation = 0;
		scale = 1;
	}
}
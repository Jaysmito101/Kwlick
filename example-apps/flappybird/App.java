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
		Kwlick.LoadScene(new Flappy());
		Kwlick.LaunchApp();
	}
}

class Flappy extends Application{
	public void Start(){
		setTitle("SimplyFlappy - Made with Kwlick [Jaysmito Mukherjee]");
		// Yet to be made
		Kwlick.ANTI_ALIASING = true;
	}
}

import java.awt.*;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.primitives.*;
import com.jaysmito.Kwlick.resolvers.*;
import com.jaysmito.Kwlick.uiprimitives.*;
import com.jaysmito.Kwlick.*;



public class DrawApp{
	public static void main(String[] args) {
		Kwlick.LoadScene(new SimpleDrawApp());
		Kwlick.LaunchApp();
	}
}

class SimpleDrawApp extends Application{
	Circle brush;
	Text t1;
	public void Start(){
		Kwlick.HideControlPanel();
		setTitle("Simple Drawing App [ Kwlick Demo - Jaysmito Mukherjee ]");
		Kwlick.ANTI_ALIASING = true;
		Kwlick.REFRESH_EVERY_FRAME = false;
		brush = new Circle(10);
		brush.color = Color.WHITE;
		brush.transform.position.x = -5000;
		t1=new Text("Clear Screen");
		t1.addClickListener(new ClickListener(){
			@Override
			public void OnClick(ClickEvent e){
				Kwlick.RefreshLayers();
				brush.transform.position.x = -5000;
			}
		});

		t1.fontSize = 16;
		t1.padding = 10;
		t1.backgroundColor = new Color(225, 255, 0);
		Kwlick.AddEntity(t1);
		Kwlick.AddEntity(brush);

		MainWindow.App.delay = 20; // Optional (Just for higher frame rate) (Warning :- Very high CPU usage for values less than 10)
	}

	@Override
	public void OnMouseDragged(int x, int y){
		float w = (float)Kwlick.Width;
		float h = (float)Kwlick.Height;
		brush.transform.position.x = (x - w/2);
		brush.transform.position.y = -1 * (y - h/2);
	}

	public void Update(double deltaTime){
		Kwlick.RefreshUI();
		t1.transform.position.x = -(Kwlick.Width/2) + 30;
		t1.transform.position.y = (Kwlick.Height/2) - 30;
	}
}
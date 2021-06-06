package com.jaysmito.Kwlick;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.jaysmito.Kwlick.utils.*;
import com.jaysmito.Kwlick.primitives.*;

public class ControlPanel extends JPanel{

	public static ControlPanel Instance;
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	public static ControlPanel CreateInstance(MainWindow window){
		if(Instance == null)
			Instance = new ControlPanel(window);
		return Instance;
	}

	public static void Log(String log){
		if(Instance == null)
			return;
		log = "[" + dateTimeFormatter.format(LocalDateTime.now()) + "] "+ log + "\n";
		Instance.logPane.setText(Instance.logPane.getText() + log );
	}


	private MainWindow window;
	private JTextPane logPane;
	private JFormattedTextField delay;

	private JLabel fps;

	private ControlPanel(MainWindow window){
		this.window = window;
		setLayout(new FlowLayout());
		Dimension pSize = new Dimension(300, 500);
		setPreferredSize(pSize);
		add(new JLabel("Set Frame Delay:-"));
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(true);
		delay = new JFormattedTextField(formatter);
		delay.setPreferredSize(new Dimension(300, 30));
		delay.setFont(new Font("Arial", Font.BOLD, 16));
		delay.setText("30");
		delay.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				String text = textField.getText().replace(",", "");
				try{
					MainWindow.App.delay = Integer.parseInt(text);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		add(delay);
		fps = new JLabel("FPS :- 60.0");
		fps.setFont(new Font("Arial", Font.BOLD, 20));
		fps.setPreferredSize(new Dimension(300, 50));
		add(fps);
		add(new JLabel("Logs:-"));
		logPane = new JTextPane();
		logPane.setEnabled(false);
		logPane.setFont(new Font("Arial", Font.PLAIN, 20));
		logPane.setForeground(Color.BLACK);
		JScrollPane logPaneC = new JScrollPane(logPane);
		logPaneC.setPreferredSize(new Dimension(300, 300));
		add(logPaneC);
	}

	public void SetFPS(float fps){
		this.fps.setText("FPS :- " + fps);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}
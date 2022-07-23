package finalGraphicsProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class GamePanel extends javax.swing.JPanel implements ActionListener{
// global variables 
	Player player; 
	
	ArrayList<Wall> walls = new ArrayList<>(); 
	
	int cameraX; 
	int offset; 
	Timer gameTimer; 
	
	Rectangle restartRect; 
	Rectangle homeRect; 
	Rectangle enemy;
	int eHeight = 500; 
	int eWidth = 25; 
	int eX = 10;
	int eY = 50; 
	int frames=0; 
	int i =0; 
	int score = 0; 
	int hiScore = 0; 
	Font buttonFont = new Font("Arial", Font.BOLD, 12); 
	Color c = new Color(156, 225, 182);
	Color tan = new Color (239,222,205); 
	public GamePanel() {
		
		restartRect = new Rectangle (640, 25, 50, 50); 
		homeRect = new Rectangle (625, 25, 50, 50); 
		enemy = new Rectangle (eX, eY, eWidth, eHeight); 
		player = new Player (400, 300, this); 
		
		reset(); 
		
		
		gameTimer =new Timer(); 
		gameTimer.schedule (new TimerTask() {
			
			public void run() {
				if (walls.get(walls.size()-1).x < 800){ // if the player is almost on the next screen then generate new one
					offset += 700; 
					makeWalls(offset); 
				}
				//score calculation
				score = frames/10; 
				
				//  enemy moving
					frames++; 
					eX = cameraX-300; 
					i+=6; 
					eX+= i; 
				
				
				player.set(); // updates player position
				for(Wall wall: walls) 
					wall.set(cameraX); // essentially moves the background instead of the player
				
				// remove excess walls that have already been past by
				for(int i = 0; i<walls.size(); i++) {
					if(walls.get(i).x < -800) {
						walls.remove(i);  
					}
						
					
				}
				
				
				
				 repaint(); 
			}

		}, 0, 17); // this will make the game run at 60 fps
	}
	
	public void reset() {
		player.x= 200; 
		player.y = 150; 
		cameraX = 150; 
		player.xspeed = 0; 
		player.yspeed = 0; 
		walls.clear(); // gets rid of all the walls
		 offset = -150; 
		makeWalls(offset);
		frames=0; 
		 i =0;
		 eX =10; 
		 player.isHit = false; 
		}
	
	
	
	public void makeWalls(int offset) {
		int s = 50; // this is the size of the walls
		Random rand = new Random(); 
		int index = rand.nextInt(12); // this will return a whole number between 0 and 11
		// there will only be terrains that are made up of 14 blocks
		
		if(index == 0) {
			for(int i = 0; i < 14; i++) walls.add(new Wall(Color.PINK, offset + i * 50, 600, s, s));
			} else if(index == 1) {
				for(int i = 0; i < 5; i++) walls.add(new Wall(Color.WHITE, offset + i * 50, 600, s, s));
				walls.add(new Wall(Color.WHITE, offset + 500, 600, s, s));
				walls.add(new Wall(Color.WHITE, offset + 550, 600, s, s));
				walls.add(new Wall(Color.WHITE, offset + 600, 600, s, s));
				walls.add(new Wall(Color.WHITE, offset + 650, 600, s, s));
				walls.add(new Wall(Color.WHITE, offset + 700, 600, s, s));
				walls.add(new Wall(Color.WHITE, offset + 750, 600, s, s));
			} else if(index == 2) {
				for(int i = 0; i < 14; i++) walls.add(new Wall(Color.RED, offset + i * 50, 600, s, s));
				for(int i = 0; i < 12; i++) walls.add(new Wall(Color.RED, offset + 50 + i * 50, 550, s, s));
				for(int i = 0; i < 10; i++) walls.add(new Wall(Color.RED, offset + 100 + i * 50, 500, s, s));
				for(int i = 0; i < 8; i++) walls.add(new Wall(Color.RED, offset + 150 + i * 50, 450, s, s));
				for(int i = 0; i < 6; i++) walls.add(new Wall(Color.RED, offset + 200 + i * 50, 400, s, s));
			} else if(index == 3) {
				for(int i = 0; i < 5; i++) walls.add(new Wall(Color.GREEN, offset + i * 50, 600, s, s));
				for(int i = 0; i < 5; i++) walls.add(new Wall(Color.GREEN, offset + 450 + i * 50, 600, s, s));
				walls.add(new Wall(Color.GREEN, offset + 150, 550, s, s));
				walls.add(new Wall(Color.GREEN, offset + 200, 550, s, s));
			
		        	walls.add(new Wall(Color.GREEN, offset + 200, 500, s, s));
				walls.add(new Wall(Color.GREEN, offset + 200, 500, s, s));
				walls.add(new Wall(Color.GREEN, offset + 500, 550, s, s));
				walls.add(new Wall(Color.GREEN, offset + 450, 550, s, s));
				walls.add(new Wall(Color.GREEN, offset + 450, 500, s, s));
				walls.add(new Wall(Color.GREEN, offset + 450, 450, s, s));
			} else if (index == 4) {
				for(int i = 0; i < 5; i++) walls.add(new Wall(Color.BLUE, offset + i * 50, 600, s, s));
				for(int i = 0; i < 4; i++) walls.add(new Wall(Color.BLUE, offset + 50 + i * 50, 550, s, s));
				for(int i = 0; i < 3; i++) walls.add(new Wall(Color.BLUE, offset + 100 + i * 50, 500, s, s));
				for(int i = 0; i < 2; i++) walls.add(new Wall(Color.BLUE, offset + 150 + i * 50, 450, s, s));
				for(int i = 0; i < 4; i++) walls.add(new Wall(Color.BLUE, offset + 500 + i * 50, 600, s, s));
			} else if(index == 5) {
				for(int i = 0; i < 5; i++) walls.add(new Wall(Color.YELLOW, offset + i * 50, 600, s, s));
				for(int i = 0; i < 3; i++) walls.add(new Wall(Color.YELLOW, offset + 100 + i * 50, 550, s, s));
				for(int i = 0; i < 2; i++) walls.add(new Wall(Color.YELLOW, offset + 150 + i * 50, 500, s, s));
				for(int j = 0; j < 4; j++) {
					for(int i = 0; i < 4; i++) walls.add(new Wall(Color.YELLOW, offset + 350 + i * 50, 400 + 50*j, s, s));
				}
				for(int i = 0; i < 7; i++) walls.add(new Wall(Color.YELLOW, offset + 350 + i * 50, 600, s, s));
				for(int i = 0; i < 2; i++) walls.add(new Wall(Color.YELLOW, offset + 200 + i * 50, 450, s, s));
			} else if(index == 6) {
				for(int i = 0; i < 14; i++) walls.add(new Wall(Color.CYAN, offset + i * 50, 600, s, s));
				for(int i = 0; i < 7; i++) walls.add(new Wall(Color.CYAN, offset + 200 + i * 50, 450, s, s));
			} else if(index == 7) {
				walls.add(new Wall(Color.MAGENTA, offset, 600, s,s));
				walls.add(new Wall(Color.MAGENTA, offset + 50, 600, s, s));
				
				walls.add(new Wall(Color.MAGENTA, offset + 150, 500, s, s));
				walls.add(new Wall(Color.MAGENTA, offset + 200, 500, s, s));
				
				walls.add(new Wall(Color.MAGENTA, offset + 300, 400, s, s));
				walls.add(new Wall(Color.MAGENTA, offset + 350, 400, s, s));
				
				walls.add(new Wall(Color.MAGENTA, offset + 450, 500, s, s));
				walls.add(new Wall(Color.MAGENTA, offset + 500, 500, s, s));
				
				walls.add(new Wall(Color.MAGENTA, offset + 600, 600, s, s));
				walls.add(new Wall(Color.MAGENTA, offset + 650, 600, s, s));
			} else if(index == 8) {
				walls.add(new Wall(Color.DARK_GRAY, offset, 500, s,s));
				walls.add(new Wall(Color.DARK_GRAY, offset + 50, 600, s, s));
				
				walls.add(new Wall(Color.DARK_GRAY, offset + 150, 500, s, s));
				walls.add(new Wall(Color.DARK_GRAY, offset + 250, 500, s, s));
				
				walls.add(new Wall(Color.DARK_GRAY, offset + 350, 400, s, s));
				walls.add(new Wall(Color.DARK_GRAY, offset + 250, 400, s, s));
				
				walls.add(new Wall(Color.DARK_GRAY, offset + 450, 500, s, s));
				walls.add(new Wall(Color.DARK_GRAY, offset + 650, 500, s, s));
				
				walls.add(new Wall(Color.DARK_GRAY, offset + 600, 600, s, s));
				walls.add(new Wall(Color.DARK_GRAY, offset + 550, 600, s, s));
			} else if (index ==9) {
				for(int i = 0; i < 12; i++) walls.add(new Wall(Color.ORANGE, offset + i * 50, 600, s, s));
				walls.add(new Wall(Color.ORANGE, offset + 450, 500, s, s));
				walls.add(new Wall(Color.ORANGE, offset + 500, 500, s, s));
				
			} else if (index == 10) {
				for(int i = 0; i < 14; i++) walls.add(new Wall(c, offset + i * 50, 600, s, s));
				//for(int i = 0; i < 10; i++) walls.add(new Wall(c, offset + 50 + i * 50, 550, s, s));
				for(int i = 0; i < 8; i++) walls.add(new Wall(c, offset + 100 + i * 50, 500, s, s));
				//for(int i = 0; i < 6; i++) walls.add(new Wall(c, offset + 150 + i * 50, 450, s, s));
				for(int i = 0; i < 2; i++) walls.add(new Wall(c, offset + 200 + i * 50, 400, s, s));
			} else if (index == 11) {
				for(int i = 0; i < 12; i++) walls.add(new Wall(tan, offset + i * 50, 600, s, s));
				walls.add(new Wall(tan, offset + 7 * 50, 550, s, s));
				walls.add(new Wall(tan, offset + 6 * 50, 500, s, s));
				walls.add(new Wall(tan, offset + 5 * 50, 550, s, s));
				walls.add(new Wall(tan, offset + 6 * 50, 500, s, s));
				walls.add(new Wall(tan, offset + 5 * 50, 550, s, s));
				walls.add(new Wall(tan, offset + 8 * 50, 500, s, s));
				walls.add(new Wall(tan, offset + 10 * 50, 350, s, s));
			}
		
		
		
		
	
	}
	
	
	
	public void paint (Graphics g) {
		
		super.paint(g); // paints over previous frame
		ImageIcon i = new ImageIcon("C:\\Users\\apenu\\Desktop\\Akhil's Folder\\ui for final.png"); 
		i.paintIcon(this, g, 0, 0);
		
		
		Graphics2D gtd = (Graphics2D) g; 
		
		player.draw(gtd);
		
		
		gtd.drawRect(eX, eY, eWidth, eHeight);
		gtd.setColor(Color.RED); 
		gtd.fillRect(eX, eY, eWidth, eHeight);
		
		for (Wall wall: walls) wall.draw(gtd);
		
		Font f = new Font ("Arial", Font.BOLD, 10); 
		gtd.setFont(f);
		gtd.setColor(Color.WHITE);
		gtd.drawString("Walls generated: " + walls.size(), 15, 45);
		gtd.setColor(Color.WHITE);
		gtd.drawString("Frames past by : " + frames, 15, 65);
		gtd.setColor(Color.BLACK);
		Font hi = new Font ("Arial", Font.BOLD, 13); 
		gtd.setFont(hi);
		
		gtd.drawString("Hi-Score: " + hiScore , 310, 67); // was at 89 for y and 315 for x
		Font y = new Font ("Arial", Font.BOLD, 25); 
		gtd.setFont(y);
		gtd.setColor(Color.BLACK);
		gtd.drawString("Score: " + score , 299, 45); // handles score
	}
	
	

	
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'a') player.keyLeft = true; 
		if (e.getKeyChar() == 'w') player.keyUp = true; 
		if (e.getKeyChar() == 's') player.keyDown = true; 
		if (e.getKeyChar() == 'd') player.keyRight = true; 
		if (e.getKeyChar() == 'r') reset(); 
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == 'a') player.keyLeft = false; 
		if (e.getKeyChar() == 'w') player.keyUp = false; 
		if (e.getKeyChar() == 's') player.keyDown = false; 
		if (e.getKeyChar() == 'd') player.keyRight = false; 
		
	}

	@Override
public void actionPerformed(ActionEvent e) {
		
		
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse has been clicked"); 
		
		if (restartRect.contains(e.getPoint().x, e.getPoint().y-27)) {
			reset(); // reset button
		}
		
	}

	
	
	
	
	
	
	
	
	
	
}

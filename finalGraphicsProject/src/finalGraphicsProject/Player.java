package finalGraphicsProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player {

	 GamePanel panel;
	 boolean isHit = false; 
	 int x; 
	 int y; 
	 int width; 
	 int height; 
	 
	 double xspeed; // horizontal velocity
	 double yspeed; // vertical velocity
	 
	 Rectangle hitBox; // super helpful for collsions 
	 
	 boolean keyLeft; 
	 boolean keyRight; 
	 boolean keyDown; 
	 boolean keyUp; 
	 
	public Player(int x, int y, GamePanel panel) { // gives the player x, y location
		
		this.panel = panel; 
		this.x = x; 
		this.y = y; 
	
		width = 50; 
		height = 100; 
		hitBox = new Rectangle(x, y, width, height);
		
	}
	
	public void set() { // runs every frame of the game
		if(keyLeft && keyRight || !keyLeft &&  !keyRight) xspeed *= 0.8; // slow down player
		else if (keyLeft && !keyRight) xspeed --; 
		else if (keyRight && !keyLeft) xspeed ++; 
		
		if (xspeed > 0 && xspeed<0.75) xspeed = 0; 
		if (xspeed < 0 && xspeed>-0.75) xspeed = 0; 
		
		if (xspeed > 7) xspeed = 7; 
		if (xspeed < -7) xspeed = -7; 
		
		if (keyUp) {
			//check if touching ground
			hitBox.y ++; 
			for(Wall wall: panel.walls ) {
				if(wall.hitBox.intersects(hitBox)) yspeed = -11; // only jump once if hit the ground
					
			}
			hitBox.y--; 
		}
		//add gravity
		yspeed += 0.5; 
		// enemy collisions 
		if (x < panel.eX) {
			System.out.println("player hit"); 
			isHit = true; 
			if (isHit == true) {
				
				if(panel.score>panel.hiScore) panel.hiScore = panel.score; 
				panel.reset();
			}
		}
		// horizontal collisions 
		hitBox.x += xspeed; 
		for(Wall wall: panel.walls) {
			if (hitBox.intersects(wall.hitBox)) {
				hitBox.x -= xspeed; // pushes player back
				while(!wall.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xspeed); //kinda like absoulte value
				
				hitBox.x -= Math.signum(xspeed); 
				panel.cameraX += x - hitBox.x; 
				xspeed = 0; 
				hitBox.x = x; 
			}
			
			
			
		}
		
		//Vertical Collisions
		hitBox.y += yspeed; 
		for(Wall wall: panel.walls) {
			if (hitBox.intersects(wall.hitBox)) {
				hitBox.y -= yspeed; // pushes player back
				while(!wall.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed); //kinda like absoulte value
				hitBox.y -= Math.signum(yspeed); 
				yspeed = 0; 
				y = hitBox.y; 
			}
		}
		
		panel.cameraX-= xspeed; 
		y+= yspeed; 
		hitBox.x = x; 
		hitBox.y = y; 
		
		//Death Code
		if (y > 800) {
			if(panel.score>panel.hiScore) panel.hiScore = panel.score; 
			panel.reset();
		}
		
		
		
	}
	
	public void draw (Graphics2D gtd) {
		gtd.setColor(Color.BLACK);
		gtd.fillRect(x, y, width, height);
		Font f = new Font ("Arial", Font.BOLD, 9); 
		gtd.setFont(f); 
		gtd.drawString("X position: " + Integer.toString(panel.cameraX) + " \t Y position: " + Integer.toString(y), 15, 25);
	}
	
	
}

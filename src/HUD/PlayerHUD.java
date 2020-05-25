package HUD;

import java.awt.Color;
import java.awt.Graphics;

import com.liam.game.entities.Player;

public class PlayerHUD {
	
	private int playerHealth;

	public PlayerHUD() {
	}
	

	public void draw(Graphics g) {
		for (int i = 0; i <= playerHealth; i++) {
			g.setColor(Color.RED);
			g.fillRect(i + 10, 10, 4, 8);
		}
	}
	public void tick(int playerHealth) {
		this.playerHealth = playerHealth;
	}
}

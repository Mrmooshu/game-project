package com.liam.game.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.liam.game.entities.Inventory;
import com.liam.game.entities.Item;
import com.liam.game.main.GamePanel;
import com.liam.game.resources.Images;

public class PauseState extends GameState {
	

	private Inventory itemInventory;
	private Inventory weaponInventory;
	private Inventory styleInventory;
	private boolean itemTab = false;
	private boolean weaponTab = false;
	private boolean styleTab = false;
	private boolean statTab = false;
	private int iconDimension = 64;
	private int iconX = 150;
	private int iconY = 200;
	
	private int selectorX, selectorY;
	
	
	public PauseState(GameStateManager gsm, Inventory itemInventory, Inventory weaponInventory, Inventory styleInventory, double xOffset, double yOffset) {
		super(gsm);
		this.itemInventory = itemInventory;
		this.weaponInventory = weaponInventory;
		this.styleInventory = styleInventory;
		GameState.xOffset = xOffset;
		GameState.yOffset = yOffset;
		statTab = true;
	}

	public void init() {

	}

	public void tick() {
		
	}

	public void draw(Graphics g) {
		g.setColor(new Color(50, 150, 200));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		if (selectorY == 0) {
			for (int i = 0; i < 5; i++) {
				if (selectorX == i) {
					g.setColor(Color.BLACK);
					g.fillRect(iconX + iconDimension * i, iconY - iconDimension, iconDimension * 2, iconDimension);
				}
			}
		}
		
		
		if (itemTab) {
			int row;
			for (int i = 0; i < itemInventory.getItems().size(); i++) {
				if (i < 10) {
					row = 0;
					g.drawImage(itemInventory.getItems().get(i).getIcon(), iconX + iconDimension * i, iconY + iconDimension * row, iconDimension, iconDimension, null);
					if (selectorX == i && selectorY == row + 1) {
						g.setColor(Color.BLACK);
						g.fillRect(iconX + iconDimension * i, iconY + iconDimension * row, iconDimension, iconDimension);
					}
				}
				else if (i < 20) {
					row = 1;
					g.drawImage(itemInventory.getItems().get(i).getIcon(), iconX + iconDimension * (i - 10), iconY + iconDimension * row, iconDimension, iconDimension, null);
					if (selectorX == i - 10 && selectorY == row + 1) {
						g.setColor(Color.BLACK);
						g.fillRect(iconX + iconDimension * (i - 10), iconY + iconDimension * row, iconDimension, iconDimension);
					}
				}
				else if (i < 30) {
					row = 2;
					g.drawImage(itemInventory.getItems().get(i).getIcon(), iconX + iconDimension * (i - 20), iconY + iconDimension * row, iconDimension, iconDimension, null);
					if (selectorX == i - 20 && selectorY == row + 1) {
						g.setColor(Color.BLACK);
						g.fillRect(iconX + iconDimension * (i - 20), iconY + iconDimension * row, iconDimension, iconDimension);
					}
				}
				else if (i < 40) {
					row = 3;
					g.drawImage(itemInventory.getItems().get(i).getIcon(), iconX + iconDimension * (i - 30), iconY + iconDimension * row, iconDimension, iconDimension, null);
					if (selectorX == i - 30 && selectorY == row + 1) {
						g.setColor(Color.BLACK);
						g.fillRect(iconX + iconDimension * (i - 30), iconY + iconDimension * row, iconDimension, iconDimension);
					}
				}
			}
		}
		else if (weaponTab) {
			int row;
			for (int i = 0; i < weaponInventory.getItems().size(); i++) {
				if (i < 10) {
					row = 0;
					g.drawImage(weaponInventory.getItems().get(i).getIcon(), iconX + iconDimension * i, iconY + iconDimension * row, iconDimension, iconDimension, null);
					if (selectorX == i && selectorY == row + 1) {
						g.setColor(Color.BLACK);
						g.fillRect(iconX + iconDimension * i, iconY + iconDimension * row, iconDimension, iconDimension);
					}
				}
				else if (i < 20) {
					row = 1;
					g.drawImage(weaponInventory.getItems().get(i).getIcon(), iconX + iconDimension * (i - 10), iconY + iconDimension * row, iconDimension, iconDimension, null);
					if (selectorX == i - 10 && selectorY == row + 1) {
						g.setColor(Color.BLACK);
						g.fillRect(iconX + iconDimension * i, iconY + iconDimension * row, iconDimension, iconDimension);
					}
				}
				else if (i < 30) {
					row = 2;
					g.drawImage(weaponInventory.getItems().get(i).getIcon(), iconX + iconDimension * (i - 20), iconY + iconDimension * row, iconDimension, iconDimension, null);
					if (selectorX == i -20 && selectorY == row + 1) {
						g.setColor(Color.BLACK);
						g.fillRect(iconX + iconDimension * i, iconY + iconDimension * row, iconDimension, iconDimension);
					}
				}
				else if (i < 40) {
					row = 3;
					g.drawImage(weaponInventory.getItems().get(i).getIcon(), iconX + iconDimension * (i - 30), iconY + iconDimension * row, iconDimension, iconDimension, null);
					if (selectorX == i - 30 && selectorY == row + 1) {
						g.setColor(Color.BLACK);
						g.fillRect(iconX + iconDimension * i, iconY + iconDimension * row, iconDimension, iconDimension);
					}
				}
			}
		}
		else if (styleTab) {
			int row;
			for (int i = 0; i < styleInventory.getItems().size(); i++) {
				if (i < 10) {
					row = 0;
					g.drawImage(styleInventory.getItems().get(i).getIcon(), iconX + iconDimension * i, iconY + iconDimension * row, iconDimension, iconDimension, null);
					if (selectorX == i && selectorY == row + 1) {
						g.setColor(Color.BLACK);
						g.fillRect(iconX + iconDimension * i, iconY + iconDimension * row, iconDimension, iconDimension);
					}
				}
				else if (i < 20) {
					row = 1;
					g.drawImage(styleInventory.getItems().get(i).getIcon(), iconX + iconDimension * (i - 10), iconY + iconDimension * row, iconDimension, iconDimension, null);
					if (selectorX == i - 10 && selectorY == row + 1) {
						g.setColor(Color.BLACK);
						g.fillRect(iconX + iconDimension * i, iconY + iconDimension * row, iconDimension, iconDimension);
					}
				}
				else if (i < 30) {
					row = 2;
					g.drawImage(styleInventory.getItems().get(i).getIcon(), iconX + iconDimension * (i - 20), iconY + iconDimension * row, iconDimension, iconDimension, null);
					if (selectorX == i - 20 && selectorY == row + 1) {
						g.setColor(Color.BLACK);
						g.fillRect(iconX + iconDimension * i, iconY + iconDimension * row, iconDimension, iconDimension);
					}
				}
				else if (i < 40) {
					row = 3;
					g.drawImage(styleInventory.getItems().get(i).getIcon(), iconX + iconDimension * (i - 30), iconY + iconDimension * row, iconDimension, iconDimension, null);
					if (selectorX == i - 30 && selectorY == row + 1) {
						g.setColor(Color.BLACK);
						g.fillRect(iconX + iconDimension * i, iconY + iconDimension * row, iconDimension, iconDimension);
					}
				}
			}
		}
		else if (statTab) {
			
		}
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ESCAPE) {
			gsm.states.pop();//unpause
		}
		if (k == KeyEvent.VK_D && selectorY == 0) {
			if (selectorX < 4) {
				selectorX++; 
			}
			else if (selectorX == 4) {
				selectorX = 0;
			}
		}
		else if (k == KeyEvent.VK_D && selectorX != 9) {
			selectorX++;
		}
		if (k == KeyEvent.VK_A && selectorY == 0) {
			if (selectorX > 0) {
				selectorX--; 
			}
			else if (selectorX == 0) {
				selectorX = 4;
			}
		}
		else if (k == KeyEvent.VK_A && selectorX != 0) {
			selectorX--;
		}
		if (k == KeyEvent.VK_W && selectorY != 0) {
			selectorY--;
			if (selectorY == 0 && statTab) {
				selectorX = 0;
			}
			else if (selectorY == 0 && itemTab) {
				selectorX = 1;
			}
			else if (selectorY == 0 && weaponTab) {
				selectorX = 2;
			}
			else if (selectorY == 0 && styleTab) {
				selectorX = 3;
			}
		}
		if (k == KeyEvent.VK_S && selectorY != 4) {
			selectorY++;
		}
		if (k == KeyEvent.VK_SPACE) {
			if (selectorY == 0 && selectorX == 0) {
				statTab = true; itemTab = false; weaponTab = false; styleTab = false;
			}
			else if (selectorY == 0 && selectorX == 1) {
				statTab = false; itemTab = true; weaponTab = false; styleTab = false;
			}
			else if (selectorY == 0 && selectorX == 2) {
				statTab = false; itemTab = false; weaponTab = true; styleTab = false;
			}
			else if (selectorY == 0 && selectorX == 3) {
				statTab = false; itemTab = false; weaponTab = false; styleTab = true;
			}
			else if (selectorY == 0 && selectorX == 4) {
				gsm.states.pop();//unpause
			}
		}

	}

	public void keyReleased(int k) {
		
	}

}

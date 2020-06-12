package com.liam.game.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Images {

	public static BufferedImage[] blocks;
	public static ArrayList<BufferedImage> playerIdleSprites, playerWalkSprites, playerJumpSprites, playerFallSprites, playerCrouchSprites, playerWallslideSprites, playerRunSprites;
	public static BufferedImage[] backgrounds;
	public static BufferedImage[] icons;
	
	public Images() {
		blocks = new BufferedImage[2];
		playerIdleSprites = new ArrayList<BufferedImage>();
		playerWalkSprites = new ArrayList<BufferedImage>();
		playerJumpSprites = new ArrayList<BufferedImage>();
		playerFallSprites = new ArrayList<BufferedImage>();
		playerCrouchSprites = new ArrayList<BufferedImage>();
		playerWallslideSprites = new ArrayList<BufferedImage>();
		playerRunSprites = new ArrayList<BufferedImage>();
		backgrounds = new BufferedImage[1];
		icons = new BufferedImage[1];

		try {
			blocks[0] = ImageIO.read(getClass().getResourceAsStream("/Blocks/block_brick.png"));
			blocks[1] = ImageIO.read(getClass().getResourceAsStream("/Blocks/george.png"));
			for (int i = 0; i * 100 < ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/player_idle-Sheet.png")).getWidth(); i++) {
				playerIdleSprites.add(i, ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/player_idle-Sheet.png")).getSubimage(i * 100, 0, 100, 80));
			}
			for (int i = 0; i * 100 < ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/player_walk-Sheet.png")).getWidth(); i++) {
				playerWalkSprites.add(i, ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/player_walk-Sheet.png")).getSubimage(i * 100, 0, 100, 80));
			}
			for (int i = 0; i * 100 < ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/player_jump-Sheet.png")).getWidth(); i++) {
				playerJumpSprites.add(i, ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/player_jump-Sheet.png")).getSubimage(i * 100, 0, 100, 80));
			}
			for (int i = 0; i * 100 < ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/player_fall-Sheet.png")).getWidth(); i++) {
				playerFallSprites.add(i, ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/player_fall-Sheet.png")).getSubimage(i * 100, 0, 100, 80));
			}
			for (int i = 0; i * 100 < ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/player_crouch-Sheet.png")).getWidth(); i++) {
				playerCrouchSprites.add(i, ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/player_crouch-Sheet.png")).getSubimage(i * 100, 0, 100, 80));
			}
			for (int i = 0; i * 100 < ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/player_wallslide-Sheet.png")).getWidth(); i++) {
				playerWallslideSprites.add(i, ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/player_wallslide-Sheet.png")).getSubimage(i * 100, 0, 100, 80));
			}
			for (int i = 0; i * 100 < ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/player_run-Sheet.png")).getWidth(); i++) {
				playerRunSprites.add(i, ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/player_run-Sheet.png")).getSubimage(i * 100, 0, 100, 80));
			}
			backgrounds[0] = ImageIO.read(getClass().getResourceAsStream("/Blocks/background.png"));
			icons[0] = ImageIO.read(getClass().getResourceAsStream("/Blocks/george.png"));

			} catch (IOException e) {
				e.printStackTrace();
				}
	}
}

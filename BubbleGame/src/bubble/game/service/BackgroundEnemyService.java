package bubble.game.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import bubble.game.component.Enemy;


public class BackgroundEnemyService implements Runnable{

	private BufferedImage image;
	private Enemy enemy;
	private int JUMPCOUNT = 0; // 점프 카운트
	private int FIRST = 0;     // 바닥 도착 여부 0 바닥, 1 꼭대기
	private int BOTTOMCOLOR = -131072; // 바닥 빨강.
	

	public BackgroundEnemyService(Enemy enemy) {
		this.enemy = enemy;
		try {
			image = ImageIO.read(new File("images/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void run() {
		
		

		while(enemy.getState() == 0) {
		
			//벽 충돌 체크
			Color leftColor = new Color(image.getRGB(enemy.getX()-3, enemy.getY()+25));
			Color rightColor = new Color(image.getRGB(enemy.getX() + 50 +15, enemy.getY()+25));
		
			int bottomColor = image.getRGB(enemy.getX() +20, enemy.getY()+50+5)
					+image.getRGB(enemy.getX()+50, enemy.getY()+50+5);
			

			
			// 바닥 도착
			if (bottomColor == BOTTOMCOLOR)	FIRST = 1;
						
			// 꼭대기 도착.
			if(JUMPCOUNT >= 3) {
				JUMPCOUNT = 0;
				FIRST = 0;
			}
			//바닥 충돌 확인
			if(bottomColor != -2) {
			
				enemy.setDown(false);
			}else { 
				if(!enemy.isUp() && !enemy.isDown()) {
														
														
													
					//System.out.println("다운 실행됨");
					enemy.down();
				}
			}
			
			// 오른쪽 구석
						if (JUMPCOUNT < 3 
								&& FIRST == 1 
								&& rightColor.getRed() == 255
								&& rightColor.getGreen() == 0 
								&& rightColor.getBlue() == 0) {
							enemy.setRight(false);
							enemy.setLeft(true);
							if(!enemy.isUp() && !enemy.isDown()) {
								JUMPCOUNT++;
								if(JUMPCOUNT == 3) enemy.left();
								enemy.up();
							}
						// 왼쪽 구석. 	
						}else if(JUMPCOUNT < 3 
								&& FIRST == 1 
								&& leftColor.getRed() == 255
								&& leftColor.getGreen() == 0 
								&& leftColor.getBlue() == 0) {
							enemy.setLeft(false);
							enemy.setRight(true);
							if(!enemy.isUp() && !enemy.isDown()) {
								JUMPCOUNT++;
								if(JUMPCOUNT == 3) enemy.right();
								enemy.up();
							}
						}else if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
							enemy.setLeft(false);
							if(!enemy.isRight()) {
								enemy.right();	
							}
						}else if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
							enemy.setRight(false);
							if(!enemy.isLeft()) {
								enemy.left();	
							}
						}
						
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					
		}
		
	} 
	
}

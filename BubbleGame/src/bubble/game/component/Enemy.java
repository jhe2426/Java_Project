package bubble.game.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bubble.game.BubbleFrame;
import bubble.game.Moveable;
import bubble.game.music.BGM;
import bubble.game.service.BackgroundEnemyService;
import bubble.game.state.EnemyWay;


public class Enemy extends JLabel implements Moveable {
	
	
	private BubbleFrame mContext;
	private Player player; // 플레이어 추가. 
	// 위치 상태
	private int x;
	private int y;
	
	// 적군의 방향
	private EnemyWay enemyWay;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private int state; // 0(살아있는 상태), 1(물방울에 갇힌 상태)
	
	// 적군 속도 상태
	private final int SPEED = 3;
	private final int JUMPSPEED = 1;

	private ImageIcon enemyR, enemyL;

	public Enemy(BubbleFrame mContext, EnemyWay enemyWay) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		initObject();
		initSetting();
		initBackgroundEnemyService();
		initEnemyDirection(enemyWay);
	}

	private void initObject() {
		enemyR = new ImageIcon("images/enemyR.png");
		enemyL = new ImageIcon("images/enemyL.png");
	}

	private void initSetting() {
		x = 480;
		

		left = false;
		right = false;
		up = false;
		down = false;
		
		state = 0;

		setSize(50, 50);
		setLocation(x, y);
	}
	
	private void initEnemyDirection(EnemyWay enemyWay) {
		if(EnemyWay.RIGHT == enemyWay) {
			enemyWay = EnemyWay.RIGHT;
			setIcon(enemyR);
			right();
		}else {
			enemyWay = EnemyWay.LEFT;
			setIcon(enemyL);
			left();
		}
	}
	
	private void initBackgroundEnemyService() {
		new Thread(new BackgroundEnemyService(this)).start();
	}

	@Override
	public void left() {
		//System.out.println("left");
		enemyWay = EnemyWay.LEFT;
		left = true;
		new Thread(()-> {
			while(left) {
				setIcon(enemyL);
				x = x - SPEED;
				setLocation(x, y);
				if (Math.abs(x - player.getX()) < 50 && Math.abs(y - player.getY()) < 50) {
					if (player.getState() == 0 && getState() == 0) {
						player.die();
					}
				}
				try {
					Thread.sleep(10); // 0.01초
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		}).start();

	}

	@Override
	public void right() {
		//System.out.println("right");
		enemyWay = EnemyWay.RIGHT;
		right = true;
		new Thread(()-> {
			while(right) {
				setIcon(enemyR);
				x = x + SPEED;
				setLocation(x, y);
				if (Math.abs(x - player.getX()) < 50 && Math.abs(y - player.getY()) < 50) {
					if (player.getState() == 0 && getState() == 0) 
						player.die();
				}
				try {
					Thread.sleep(10); // 0.01초
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		}).start();
		

	}

	@Override
	public void up() {
		//System.out.println("up");
		up = true;
		new Thread(()->{
			for(int i=0; i<130/JUMPSPEED; i++) {
				y = y - JUMPSPEED;
				setLocation(x, y);
				if (Math.abs(x - player.getX()) < 50 && Math.abs(y - player.getY()) < 50) {
					if (player.getState() == 0 && getState() == 0) 
						player.die();
				}
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			up = false;
			down();
			
		}).start();
	}

	@Override
	public void down() {
		//System.out.println("down");
		down = true;
		new Thread(()->{
			while(down) {
				y = y + JUMPSPEED;
				setLocation(x, y);
				if (Math.abs(x - player.getX()) < 50 && Math.abs(y - player.getY()) < 50) {
					if (player.getState() == 0 && getState() == 0) 
						player.die();
				}
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down = false;
		}).start();
	}
	
	
	
	
	
	// getter, setter 부분

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public EnemyWay getEnemyWay() {
		return enemyWay;
	}

	public void setEnemyWay(EnemyWay enemyWay) {
		this.enemyWay = enemyWay;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public ImageIcon getEnemyR() {
		return enemyR;
	}

	public void setEnemyR(ImageIcon enemyR) {
		this.enemyR = enemyR;
	}

	public ImageIcon getEnemyL() {
		return enemyL;
	}

	public void setEnemyL(ImageIcon enemyL) {
		this.enemyL = enemyL;
	}



	public int getSPEED() {
		return SPEED;
	}

	public int getJUMPSPEED() {
		return JUMPSPEED;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
	
	
	

	

	
}

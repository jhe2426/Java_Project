package bubble.game.component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bubble.game.BubbleFrame;
import bubble.game.Moveable;
import bubble.game.music.BGM;
import bubble.game.service.BackgroundPlayerService;
import bubble.game.state.PlayerWay;


public class Player extends JLabel implements Moveable {
	
	
	
	private BubbleFrame myContext;
	private List<Bubble> bubbleList;
	private BGM bgm;
	// 위치 상태
	private int x;
	private int y;

	
	// 플레이어의 뱡향
	private PlayerWay playerWay;
	
	private int state = 0; // 0 : live , 1 : die
	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	private ImageIcon playerR, playerL, playerRdie, playerLdie;
	
	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	
	// 바닥에 충돌한 상태
	private boolean bottomWallCrash;
	// 플레이어 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2; // up, down의 스피드임
	// 기본 생성자
	public Player(BubbleFrame myContext) {
		this.myContext = myContext;
		initObject();
		initSetting();
		initBackgoundPlayerService();
	}

	private void initObject() {
		playerR = new ImageIcon("images/playerR.png");
		playerL = new ImageIcon("images/playerL.png");
		playerRdie = new ImageIcon("images/playerRdie.png");
		playerLdie = new ImageIcon("images/playerLdie.png");
		bubbleList = new ArrayList<>();
	}

	private void initSetting() {
		x = 80;
		y = 535;
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		leftWallCrash = false;
		rightWallCrash = false;
		

		playerWay = PlayerWay.RIGHT;
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}
	
	private void initBackgoundPlayerService() {
		new Thread(new BackgroundPlayerService(this)).start();
	}
	
	
	
	@Override
	public void attack() {
		new Thread(()->{
			Bubble bubble = new Bubble(myContext);
			myContext.add(bubble);
			bubbleList.add(bubble);
			if(playerWay == PlayerWay.LEFT) {
				bubble.left();
			}else {
				bubble.right();
			}
		}).start();
		
		
	}
	
	
	
	
	
	
	@Override
	public void left() {
		new Thread(()-> {
			//System.out.println("left");
			playerWay = PlayerWay.LEFT;
			left = true; 
			while(left) {
						
				setIcon(playerL); 
				x -= SPEED;
				setLocation(x,y);
				try {
					Thread.sleep(10); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
			
		}).start();
		
	}

	@Override
	public void right() {
		new Thread(()-> { 
			//System.out.println("right");
			
			playerWay = PlayerWay.RIGHT;
			right = true;
			while(right) {
				setIcon(playerR); 
				x += SPEED;
				setLocation(x,y);
				try {
					Thread.sleep(10);
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
			
			for(int i = 0; i<130/JUMPSPEED; i++) {
				
				y -= JUMPSPEED; 

				setLocation(x,y);
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
				
				y += JUMPSPEED; 
								

				setLocation(x,y);
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down = false;
		}).start();
	}

	
	public void die() {
		new Thread(() -> {
			setState(1);
			setIcon(PlayerWay.RIGHT == playerWay ? playerRdie : playerLdie);
			try {				
				if(!isUp() && !isDown()) up();
				myContext.PlayerDie();
				Thread.sleep(2000);
				myContext.remove(this);
				myContext.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("플레이어 사망.");
			
		}).start();
	}
	
	
	
	
	
	// getter, setter 부분
	
	
	public int getX() {
		return x;
	}

	public PlayerWay getPlayerWay() {
		return playerWay;
	}

	public void setPlayerWay(PlayerWay playerWay) {
		this.playerWay = playerWay;
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

	public ImageIcon getPlayerR() {
		return playerR;
	}

	public void setPlayerR(ImageIcon playerR) {
		this.playerR = playerR;
	}

	public ImageIcon getPlayerL() {
		return playerL;
	}

	public void setPlayerL(ImageIcon playerL) {
		this.playerL = playerL;
	}

	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}

	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}

	public boolean isRightWallCrash() {
		return rightWallCrash;
	}

	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}

	public List<Bubble> getBubbleList() {
		return bubbleList;
	}

	public void setBubbleList(List<Bubble> bubbleList) {
		this.bubbleList = bubbleList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	
	
}

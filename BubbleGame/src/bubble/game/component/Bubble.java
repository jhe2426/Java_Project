package bubble.game.component;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bubble.game.BubbleFrame;
import bubble.game.Moveable;
import bubble.game.service.BackgroundBubbleService;

public class Bubble extends JLabel implements Moveable{
	
	// 의존성 콤포지션
	private BubbleFrame mContext;
	private Player player;
	private List<Enemy> enemys;
	private Enemy removeEnemy = null; // 적 제거 변수. 
	private BackgroundBubbleService backgroundBubbleService;
	
	// 위치 상태
	private int x;
	private int y;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	
	// 적군을 맞춘 상태
	private int state; // 0(물방울), 1(적을 가둔 물방울)
	
	private ImageIcon bubble; // 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 물방울이 터진 상태

	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		this.enemys = mContext.getEnemys();
		initObject();
		initSetting();
	}
	
	private void initObject() {
		bubble = new ImageIcon("images/bubble.png");
		bubbled = new ImageIcon("images/bubbled.png");
		bomb = new ImageIcon("images/bomb.png");
		
		backgroundBubbleService = new BackgroundBubbleService(this);
	}
	
	private void initSetting() {
		left = false;
		right = false;
		up = false;
		
		x = player.getX();
		y = player.getY();
		
		setIcon(bubble);
		setSize(50, 50);
		
		state = 0;
	}

	@Override
	public void left() {
		left = true;
		Stop:for(int i=0; i<400; i++) {
			x--;
			setLocation(x, y);
			
			if(backgroundBubbleService.leftWall()) {
				left = false;
				break;
			}
			
			
			
			for (Enemy e : enemys) {
				if (Math.abs(x - e.getX()) < 10 && Math.abs(y - e.getY()) > 0 && Math.abs(y - e.getY()) < 50) {
					if (e.getState() == 0) {
						attack(e);
						break Stop;
					}
				}
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		Stop:for(int i=0; i<400; i++) {
			x++;
			setLocation(x, y);
			
			if(backgroundBubbleService.rightWall()) {
				right = false;
				break;
			}
			
			// 아군과 적군의 거리가 10
			for (Enemy e : enemys) {
				if (Math.abs(x - e.getX()) < 10 && Math.abs(y - e.getY()) > 0 && Math.abs(y - e.getY()) < 50) {
					if (e.getState() == 0) {
						attack(e);
						break Stop;
					}
				}
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void up() {
		up = true;
		while(up) {
			y--;
			setLocation(x, y);
			
			if(backgroundBubbleService.topWall()) {
				up = false;
				break;
			}
			
			try {
				if(state==0) { // 기본 물방울
					Thread.sleep(1);
				}else { // 적을 가둔 물방울
					Thread.sleep(10);
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(state == 0) clearBubble(); 
	}
	
	@Override
	public void attack(Enemy e) {
		state = 1;
		e.setState(1);
		setIcon(bubbled);
		removeEnemy = e;
		mContext.remove(e);
		mContext.repaint(); 
	}
	
	
	
	private void clearBubble() {
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			Thread.sleep(500);
			// 버블 객체 메모리에서 날리기
			mContext.getPlayer().getBubbleList().remove(this);
			mContext.remove(this);
			mContext.repaint(); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clearBubbled() {
		new Thread(()->{
			System.out.println("clearBubbled");
			try {
				up = false;
				setIcon(bomb);
				Thread.sleep(1000);
				// 버블 객체 메모리에서 날리기
				mContext.getPlayer().getBubbleList().remove(this);
				mContext.getEnemys().remove(removeEnemy); 
				mContext.remove(this);
				mContext.repaint();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

// getter setter
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	// getter setter
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public ImageIcon getBubble() {
		return bubble;
	}
	public void setBubble(ImageIcon bubble) {
		this.bubble = bubble;
	}
	public ImageIcon getBubbled() {
		return bubbled;
	}
	public void setBubbled(ImageIcon bubbled) {
		this.bubbled = bubbled;
	}
	public ImageIcon getBomb() {
		return bomb;
	}
	public void setBomb(ImageIcon bomb) {
		this.bomb = bomb;
	}

	
	
}



















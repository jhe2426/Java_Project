package bubble.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bubble.game.component.Enemy;
import bubble.game.component.Player;
import bubble.game.music.BGM;
import bubble.game.state.EnemyWay;

public class BubbleFrame extends JFrame{
	
	private BubbleFrame myContext = this;
	private JLabel backgroundMap;
	private Player player;
	private List<Enemy> enemys;
	private BGM bgm;
	public BubbleFrame() {
		initObject();
		initSetting();
		initListener(); 
		setVisible(true);
		
	}
	
	private void initObject() {
		
		
		backgroundMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
		setContentPane(backgroundMap);
		player = new Player(myContext);
	 	add(player);
	 	enemys = new ArrayList<Enemy>();
		enemys.add(new Enemy(myContext, EnemyWay.RIGHT));
		enemys.add(new Enemy(myContext, EnemyWay.LEFT));
		for(Enemy e : enemys) {
			add(e);
		}
			 bgm = new BGM();
		
	}

	private void initSetting() {
		setSize(1000,640);
		setLayout(null); 
		setResizable(false);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}
	
	private void initListener() {
		addKeyListener(new KeyAdapter() { 
			
			// 키보드 클릭 이벤트 핸들러
			@Override
			public void keyPressed(KeyEvent e) { 
				
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT: 
					if(!player.isLeft() && !player.isLeftWallCrash() && player.getState() == 0) { 
						player.left();
					}
					break; 
				case KeyEvent.VK_RIGHT:
					if(!player.isRight()&& !player.isRightWallCrash() && player.getState() == 0) {
						player.right();
					}
					
					break;
					case KeyEvent.VK_UP:
						if(!player.isUp() && !player.isDown() && player.getState() == 0) {
							player.up();
						}
						
					break;
					
					case KeyEvent.VK_SPACE:
						player.attack();
						break;
				
						
						
					
					
			}
			}
			// 키보드 해체 이벤트 핸들러
			@Override
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT:
					player.setLeft(false); 
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false); 
					break;
				}
			}
		});
			
			
	}
	
	public void PlayerDie() {
		bgm.PlayerDie();
	}
	
	


	public List<Enemy> getEnemys() {
		return enemys;
	}

	public void setEnemys(List<Enemy> enemys) {
		this.enemys = enemys;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	
	
	
	
	public BGM getBgm() {
		return bgm;
	}

	public void setBgm(BGM bgm) {
		this.bgm = bgm;
	}

}

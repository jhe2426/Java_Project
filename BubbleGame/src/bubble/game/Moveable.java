package bubble.game;

import bubble.game.component.Enemy;

public interface Moveable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
	
	default public void down() {}; 
	default public void attack(Enemy e) {};
	default public void attack() {};
}

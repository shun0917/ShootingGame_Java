
public class Enemy extends Character{
	
	int life=1+GameWorld.stage;
	int score=1;
	public Enemy (double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
	}

}

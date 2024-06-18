
public class PlayerBullet extends Character{

	public PlayerBullet(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public void draw (MyFrame f){
		f.setColor(0,128,0);
		f.fillRect(x+10, y-10, 10, 30);
	}
	

}

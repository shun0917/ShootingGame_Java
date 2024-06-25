import java.util.Vector;

public class GameFrame extends MyFrame {
	public void run() {
		GameWorld.player = new Player(100, 300, 0, 0);
		GameWorld.playerBullets = new Vector<PlayerBullet>();

		addKeyListener(GameWorld.player);

		GameWorld.enemies = new Vector<Enemy>();
		GameWorld.enemies.add(new EnemyBase(100, 50, 1, 0));
		GameWorld.enterPressed=false;

		while (true) {
			clear();
			GameWorld.player.draw(this);
			GameWorld.player.move();

			movePlayerBullets();

			moveEnemies();

			checkPlayerAndEnemies();

			checkPlayerBulletsAndEnemies();
			
			if(GameWorld.enemies.size()==0) {
				setColor(0,0,0);
				drawString("クリア！",100,200,40);
				if(GameWorld.enterPressed) {
					break;
				}
			}else if(GameWorld.player.y<0) {
				setColor(0,0,0);
				drawString("ゲームオーバー！",50,200,40);
				if (GameWorld.enterPressed) {
					break;
				}
			}

			sleep(0.03);
		}
	}

	public void movePlayerBullets() {
		int i = 0;
		while (i < GameWorld.playerBullets.size()) {
			PlayerBullet b = GameWorld.playerBullets.get(i);
			b.draw(this);
			b.move();

			if (b.y < 0) {
				GameWorld.playerBullets.remove(i);
			} else {
				i++;
			}
		}
	}

	public void moveEnemies() {
		for (int i = 0; i < GameWorld.enemies.size(); i++) {
			Enemy e = GameWorld.enemies.get(i);
			e.draw(this);
			e.move();
		}
		
		int i=0;
		while(i<GameWorld.enemies.size()) {
			Enemy e=GameWorld.enemies.get(i);
			if((e.y>400)) {
				GameWorld.enemies.remove(i);
			}else {
				i++;
			}
		}
	}

	public void checkPlayerAndEnemies() {
		for (int i = 0; i < GameWorld.enemies.size(); i++) {

			Enemy e = GameWorld.enemies.get(i);

			if(checkHit(GameWorld.player,e)) {
			
				System.out.println("やられた!");
				GameWorld.player.y = -1000;
			
		}
		}
	}

	public void checkPlayerBulletsAndEnemies() {
		int i = 0;
		while (i < GameWorld.playerBullets.size()) {
			//プレイヤー弾１つ１つについて、変数bに入れて繰り返し実行する
			PlayerBullet b = GameWorld.playerBullets.get(i);
			int j = 0;
			int hits = 0;
			while (j < GameWorld.enemies.size()) {
				//敵１つ１つについて、変数eに入れて繰り返し実行する
				Enemy e = GameWorld.enemies.get(j);
				//敵eとプレイヤーの弾bが衝突していたら「あたり」と表示する
				 
				if(checkHit(e,b)) {

					System.out.println("あたり");
					hits++;
					e.life--;
				} if (e.life<=0){
					GameWorld.enemies.remove(j);
				}else {
					j++;
				}
			}
			if (hits > 0) {
				GameWorld.playerBullets.remove(i);
			} else {
				i++;
			}
		}
	}
	
	public boolean checkHit(Character a, Character b) {
		if (Math.abs(a.x-b.x)<=22 && Math.abs(a.y-b.y) <= 22){
			return true;
		}else {
			return false;
		}
	}
}

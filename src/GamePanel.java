import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener{
	private static final Font tileFont = new Font("Arial", Font.BOLD, 30);
	private static String status = "playing";
	public GamePanel() {
		// TODO Auto-generated constructor stub
	}
	public void drawTile(Graphics g, int row, int col, int tileValue){
		int screenWidth = this.getWidth();
		int screenHeight = this.getHeight();
		int tileX = screenWidth/4;
		int tileY = screenHeight/4;
		int cornerX = tileX * col;
		int cornerY = tileY * row;
		//setting tile color based on value
		if(tileValue == 0) {
			g.setColor(new Color(255, 255, 255));
			g.fillRect(cornerX, cornerY, tileX, tileY);
		}else if(tileValue == 2) {
			//grey
			g.setColor(new Color(224, 223, 222));
			g.fillRect(cornerX, cornerY, tileX, tileY);
		}else if(tileValue == 4) {
			//tan
			g.setColor(new Color(249, 225, 202));
			g.fillRect(cornerX, cornerY, tileX, tileY);
		}else if(tileValue == 8) {
			//orange
			g.setColor(new Color(237, 162, 92));
			g.fillRect(cornerX, cornerY, tileX, tileY);
		}else if(tileValue == 16) {
			//darker orange
			g.setColor(new Color(239, 151, 69));
			g.fillRect(cornerX, cornerY, tileX, tileY);
		}else if(tileValue == 32) {
			//even darker
			g.setColor(new Color(239, 135, 38));
			g.fillRect(cornerX, cornerY, tileX, tileY);
		}else if(tileValue == 64) {
			//red
			g.setColor(new Color(239, 134, 105));
			g.fillRect(cornerX, cornerY, tileX, tileY);
		}else if(tileValue == 128) {
			//yellow
			g.setColor(new Color(237, 231, 64));
			g.fillRect(cornerX, cornerY, tileX, tileY);
		}else if(tileValue == 256) {
			//darker yellow
			g.setColor(new Color(247, 210, 74));
			g.fillRect(cornerX, cornerY, tileX, tileY);
		}else if(tileValue == 512) {
			//darkerer yellow
			g.setColor(new Color(234, 190, 30));
			g.fillRect(cornerX, cornerY, tileX, tileY);
		}else if(tileValue == 1024) {
			//darker yellow
			g.setColor(new Color(234, 191, 35));
			g.fillRect(cornerX, cornerY, tileX, tileY);
		}else if(tileValue == 2048) {
			//darkest yellow
			g.setColor(new Color(229, 179, 0));
			g.fillRect(cornerX, cornerY, tileX, tileY);
		}
		g.setColor(Color.black);
		g.setFont(tileFont);
		if(tileValue != 0 ) {
			FontMetrics fm = g.getFontMetrics(tileFont);
			int strWidth = fm.stringWidth("" + tileValue);
			int center = (tileY - strWidth)/2;
			String tileValueStr = "" + tileValue;
			g.drawString(tileValueStr, cornerX + center, cornerY + (tileY/2));
		}
	}
	public void drawBoard(Graphics g) {
		int[][] board = GamePlay.getBoard();
		int screenWidth = this.getWidth();
		int screenHeight = this.getHeight();
		int tileX = screenWidth/4;
		int tileY = screenHeight/4;
		for(int i = 0;i < board.length; i++) {
			for(int j = 0;j<board[i].length; j++) {
				int cur = board[i][j];
				drawTile(g, i, j, cur);
			}
		}
		g.drawLine(tileX, 0, tileX, screenHeight);
		g.drawLine(tileX*2, 0, tileX*2, screenHeight);
		g.drawLine(tileX*3, 0, tileX*3, screenHeight);
		g.drawLine(0, tileY, screenWidth, tileY);
		g.drawLine(0, tileY*2, screenWidth, tileY*2);
		g.drawLine(0, tileY*3, screenWidth, tileY*3);
	}
	public void paintComponent(Graphics g) {
		drawBoard(g);
		if(status.equals("win")) {
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(),this.getHeight());
			g.setFont(tileFont);
			g.setColor(Color.red);
			g.drawString("You Win!!!", this.getWidth()/2, this.getHeight()/2);
			System.exit(0);
		}else if(status.equals("lose")) {
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(),this.getHeight());
			g.setFont(tileFont);
			g.setColor(Color.red);
			g.drawString("You Lose!!!", this.getWidth()/2, this.getHeight()/2);
			System.exit(0);
		}
	}
	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		boolean flag = false;
		if(keyCode == KeyEvent.VK_UP) {
			GamePlay.moveBoard('w');
			flag = true;
		}else if(keyCode == KeyEvent.VK_DOWN) {
			GamePlay.moveBoard('s');
			flag = true;
		}else if(keyCode == KeyEvent.VK_LEFT) {
			GamePlay.moveBoard('a');
			flag = true;
		}else if(keyCode == KeyEvent.VK_RIGHT) {
			GamePlay.moveBoard('d');
			flag = true;
		}
		if(flag == true) {
			repaint();
			if(GamePlay.check2048()) {
				status = "win";
			}
			if(GamePlay.checkAdjacent() == false && GamePlay.isFull()) {
				status = "lose";
			}
			if(status == "playing") {
				GamePlay.addNewNumber();
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

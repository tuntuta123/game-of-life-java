package conway;

public class Cell{
	
	public boolean alive;
	public int coordX, coordY;
	public Cell north, south, east, west, ne, nw, sw, se;
	
	public Cell (boolean alive, int x, int y) {
		this.alive = alive;
		this.coordX = x;
		this.coordY = y;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public void setState(boolean a) {
		this.alive = a;
	}
	
	public int getX() {
		return this.coordX;
	}
	
	public int getY() {
		return this.coordY;
	}
	
	public void setX(int x) {
		this.coordX = x;
	}
	
	public void setY(int y) {
		this.coordY = y;
	}
	
	public void toggleState() {
		if (this.alive) {
			this.alive = false;
		}
		else {
			this.alive = true;
		}
	}
}

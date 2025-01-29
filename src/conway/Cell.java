package conway;

public class Cell{
	
	public boolean alive;
	public int coordX, coordY;
	public Cell north, south, east, west, ne, nw, sw, se;
	
	public Cell (boolean alive, int x, int y) {
		this.alive = alive;
		this.coordX = x;
		this.coordY = y;
		/*this.ne=null;
		this.se=null;
		this.nw=null;
		this.sw=null;
		this.north=null;
		this.west=null;
		this.east=null;
		this.south=null;*/
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
	
	
	/*public Node getSw(Node n){
		return this.sw;
	}
	
	public Node getNw(Node n){
		return this.Nw;
	}
	
	public Node getNe(Node n){
		return this.ne;
	}
	
	public Node getSe(Node n){
		return this.se;
	}
	
	public Node getSouth(Node n){
		return this.south;
	}
	
	public Node getWest(Node n){
		return this.west;
	}
	
	public Node getNorth(Node n){
		return this.north;
	}
	
	public Node getEast(Node n){
		return this.east;
	}*/
	
}

package conway;

public class Node {
	public Node sw;
	public Node ne;
	public Node se;
	public Node nw;
	int level;
	boolean alive;
	
	public Node(Node ne, Node sw, Node se, Node nw, int level, boolean alive){
		this.ne=ne;
		this.se=se;
		this.nw=nw;
		this.sw=sw;
		this.alive=alive;
		this.level=level;
	}
	
	public boolean isAlive(){
		return this.alive;
	}

	
	public int getLevel(){
		return this.level;
	}
	
	public Node getSw(){
		return this.sw;
	}
	
	public Node getNw(){
		return this.nw;
	}
	
	public Node getNe(){
		return this.ne;
	}
	
	public Node getSe(){
		return this.se;
	}
	
	
	public void setSw(Node n){
		this.sw = n;
	}
	
	public void setNw(Node n){
		this.nw = n;
	}
	
	public void setNe(Node n){
		this.ne = n;
	}
	
	public void setSe(Node n){
		this.se = n;
	}

}


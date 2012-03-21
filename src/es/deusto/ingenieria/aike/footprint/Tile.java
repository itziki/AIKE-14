package es.deusto.ingenieria.aike.footprint;

import java.awt.Point;

public class Tile extends Point {
	
	private static final long serialVersionUID = 1L;
	private boolean bottomWall;
	private boolean rightWall;
	private boolean rightFoot;
	private boolean leftFoot;
	
/*	public Tile()
	{
		
	}
	
	public Tile(int row, int column, boolean bottomWall, boolean rightWall, boolean rightFoot, boolean leftFoot)
	{
		super(row, column);
		this.bottomWall = bottomWall;
		this.rightWall = rightWall;
		this.rightFoot = rightFoot;
		this.leftFoot = leftFoot;
	}*/

	public boolean isBottomWall() {
		return bottomWall;
	}

	public void setBottomWall(boolean bottomWall) {
		this.bottomWall = bottomWall;
	}

	public boolean isRightWall() {
		return rightWall;
	}

	public void setRightWall(boolean rightWall) {
		this.rightWall = rightWall;
	}


	public boolean isRightFoot() {
		return rightFoot;
	}

	public void setRightFoot(boolean rightFoot) {
		this.rightFoot = rightFoot;
	}

	public boolean isLeftFoot() {
		return leftFoot;
	}

	public void setLeftFoot(boolean leftFoot) {
		this.leftFoot = leftFoot;
	}

	public void setRow(int row) {
		this.y = row;
	}
	
	public int getRow() {
		return this.y;
	}
	
	public void setColumn(int column) {
		this.x = column;
	}
	
	public int getColumn() {
		return this.x;
	}
	
	public int getFoot() {
		if (rightFoot == true)
			return 0;
		else
			return 1;
	}
	
	public boolean equals(Object obj) {
		if (obj != null	&& obj instanceof Tile) {
			return ((Tile)obj).x == x && ((Tile)obj).y == y;
		} else {
			return false;
		}
	}
	
	public Tile clone() {
		Tile newTile = new Tile();
		
		newTile.rightFoot = this.rightFoot;
		newTile.leftFoot = this.leftFoot;
		newTile.bottomWall = this.bottomWall;
		newTile.rightWall = this.rightWall;
		newTile.x = this.x;
		newTile.y = this.y;
		
		return newTile;
	}
	
	public Point getPosition() {
		return this;
	}
}
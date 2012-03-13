package es.deusto.ingenieria.aike.footprint;

import java.awt.Point;

public class Tile extends Point {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean bottomWall;
	private boolean rightWall;
	private int column;
	private int row;
	private boolean rightFoot;
	private boolean leftFoot;
	
	public Tile()
	{
		
	}
	
	public Tile(int row, int column)
	{
		super(row, column);
	}
	
	public Tile(int row, int column, boolean bottomWall, boolean rightWall, boolean rightFoot, boolean leftFoot)
	{
		super(row, column);
		this.bottomWall = bottomWall;
		this.rightWall = rightWall;
		this.rightFoot = rightFoot;
		this.leftFoot = leftFoot;
	}

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
	
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj != null	&& obj instanceof Tile) 
		{
			Tile tileTemp = (Tile)obj;
			if (tileTemp.bottomWall == this.bottomWall &&
					tileTemp.rightWall == this.rightWall &&
						tileTemp.rightFoot == this.rightFoot &&
							tileTemp.leftFoot == this.leftFoot &&
								tileTemp.column == this.column &&
									tileTemp.row == this.row)
				result = true;
		} 
		return result;
	}
	
	public Point getPosition() {
		return this;
	}
}
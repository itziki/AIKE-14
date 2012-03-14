package es.deusto.ingenieria.aike.footprint;

import es.deusto.ingenieria.aike.footprint.Tile;

public class Board {
	
	private Tile[][] tiles;
	private int[] tam = new int[2];
	
	public Board()
	{
	}
	
	public Board(int[] tam2)
	{
		this.tam = tam2;
		this.tiles = new Tile[tam2[0]][tam2[1]];
		for (int i = 0; i<tam2[0];i++)
		{
			for (int j = 0; j<tam2[1];j++)
			{
				this.tiles[i][j] = new Tile(i,j,false, false, true, false);
			}
		}
	}
	
	public Board(int[] tam, Tile[][] tiles) 
	{
		this.tam = tam;
		this.tiles = new Tile[tam[0]][tam[1]];
		for (int i = 0; i<tam[0];i++)
		{
			for (int j = 0; j<tam[1];j++)
			{
				this.tiles[i][j] = tiles[i][j];
			}
		}
	}

	public void setTam(int[] tam)
	{
		this.tam = tam;
	}
	
	public int[] getTam()
	{
		return tam;
	}
	
	public void setTile(int[] pos, boolean rightFoot, boolean leftFoot)
	{
		this.tiles[pos[0]][pos[1]].setRightFoot(rightFoot);
		this.tiles[pos[0]][pos[1]].setLeftFoot(leftFoot);
	}
	
	public void setTileVerticalWall(int[] pos, boolean wall)
	{
		this.tiles[pos[0]][pos[1]].setRightWall(wall);
	}
	
	public void setTileHorizontalWall(int[] pos, boolean wall)
	{
		this.tiles[pos[0]][pos[1]].setBottomWall(wall);
	}

	public Tile getTile(int row, int column){
		return this.tiles[row][column];		
	}	
	
	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	public boolean equals(Object obj) 
	{
		boolean result = false;
		if (obj != null	&& obj instanceof Board) 
		{
			Tile tile1;
			Tile tile2;			
			
			for (int i = 0; i<tam[0];i++)
			{
				for (int j = 0; j<tam[1];j++)
				{
					tile1 = this.getTile(i, j);
					tile2 = ((Board)obj).getTile(i, j);
					if (tile1.equals(tile2)) 
					{
						//return false;
						result = true;
					}
					else
					{
						result = false;
					}
				}
			}
			//return true;
		} 
		else 
		{
			//return false;
			result = false;
		}
		return result;
	}

	public void boardToString()
	{
		for (int i = 0; i<tam[0];i++)
		{
			for (int j = 0; j<tam[1];j++)
			{
				if (this.getTile(i, j).isRightFoot())
					System.out.print("R");
				else
					System.out.print("L");
			}
			System.out.println();
		}
	}
}

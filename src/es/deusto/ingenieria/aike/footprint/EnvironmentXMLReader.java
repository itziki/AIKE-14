package es.deusto.ingenieria.aike.footprint;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import es.deusto.ingenieria.aike.xml.InformationXMLReader;

public class EnvironmentXMLReader extends InformationXMLReader
{
        //private Board board;
		private Tile[][] tiles;
        private Position pos;
        private Position goal;

        public EnvironmentXMLReader(String xmlFile) {           
                super(xmlFile);
        }
        
        public Object getInformation() {
                return new Environment(this.tiles, this.pos, this.goal);
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                try {        
                        if (qName.equals("aike:maze"))
                        {
                        	this.tiles = new Tile[Integer.parseInt(attributes.getValue("rows"))][Integer.parseInt(attributes.getValue("columns"))];
                            
                        	for (int y=0; y<this.tiles.length; y++) {
            					for (int x=0; x<this.tiles[y].length; x++) {
            						this.tiles[y][x] = new Tile();
            						this.tiles[y][x].y = y;
            						this.tiles[y][x].x = x;
            						this.tiles[y][x].setBottomWall(false);
            						this.tiles[y][x].setRightWall(false);
            						this.tiles[y][x].setRightFoot(true);
            						this.tiles[y][x].setLeftFoot(false);
            					}
            				}
                        		// int[] tam = new int[2];
                               // tam[0]= Integer.parseInt(attributes.getValue("rows"));
                               // tam[1]= Integer.parseInt(attributes.getValue("columns"));
                               // this.board = new Board(tam);
                        } 
                        else if (qName.equals("aike:start"))
                        {
                                int y= Integer.parseInt(attributes.getValue("row"))-1;
                                int x= Integer.parseInt(attributes.getValue("column"))-1;
                                                 		
                                this.pos = new Position(x, y);
                        } 
                        else if (qName.equals("aike:end")) 
                        {
                        		int y= Integer.parseInt(attributes.getValue("row"))-1;
                        		int x= Integer.parseInt(attributes.getValue("column"))-1;
                                             		
                        		this.goal = new Position(x, y);
                        }
                        else if (qName.equals("aike:left"))
                        {
                        	this.tiles[Integer.parseInt(attributes.getValue("row"))-1][Integer.parseInt(attributes.getValue("column"))-1].setLeftFoot(true);
                        	this.tiles[Integer.parseInt(attributes.getValue("row"))-1][Integer.parseInt(attributes.getValue("column"))-1].setRightFoot(false);
                        	/*int[]pos = new int[2];
                            pos[0] = Integer.parseInt(attributes.getValue("row"))-1;
                            pos[1] = Integer.parseInt(attributes.getValue("column"))-1;
                            board.setTile(pos, false, true);*/
                        }
                        else if (qName.equals("aike:right-wall"))
                        {
                        	this.tiles[Integer.parseInt(attributes.getValue("row"))-1][Integer.parseInt(attributes.getValue("column"))-1].setRightWall(true);
                        /*	int[]pos = new int[2];
                            pos[0] = Integer.parseInt(attributes.getValue("row"))-1;
                            pos[1] = Integer.parseInt(attributes.getValue("column"))-1;
                            board.setTileVerticalWall(pos, true);*/
                        }                
                        else if (qName.equals("aike:bottom-wall"))
                        {
                        	this.tiles[Integer.parseInt(attributes.getValue("row"))-1][Integer.parseInt(attributes.getValue("column"))-1].setBottomWall(true);
                        	/*int[]pos = new int[2];
                            pos[0] = Integer.parseInt(attributes.getValue("row"))-1;
                            pos[1] = Integer.parseInt(attributes.getValue("column"))-1;
                            board.setTileHorizontalWall(pos, true);*/
                        }
                } catch (Exception ex) {
                        System.out.println(this.getClass().getName() + ".startElement(): " + ex);
                        ex.printStackTrace();
                }
        }
}

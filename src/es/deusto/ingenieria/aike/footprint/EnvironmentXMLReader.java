package es.deusto.ingenieria.aike.footprint;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import es.deusto.ingenieria.aike.xml.InformationXMLReader;

public class EnvironmentXMLReader extends InformationXMLReader
{
        private Board board;
        private CurrentPosition pos;
        private Goal goal;
        private int cont;

        public EnvironmentXMLReader(String xmlFile) {           
                super(xmlFile);
                cont = 0;
        }
        
        public Object getInformation() {
                return new Environment(this.board, this.pos, this.goal);
        }

        public CurrentPosition getPos() {
                return pos;
        }

        public Goal getGoal() {
                return goal;
        }

        public int getCont() {
                return cont;
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                try {        
                        if (qName.equals("aike:maze"))
                        {
                                int[] tam = new int[2];
                                tam[0]= Integer.parseInt(attributes.getValue("rows"))+ 2;
                                tam[1]= Integer.parseInt(attributes.getValue("columns"))+ 2;
                                this.board = new Board(tam);
                        } 
                        else  if (qName.equals("aike:start"))
                        {
                                int y= Integer.parseInt(attributes.getValue("row"));
                                int x= Integer.parseInt(attributes.getValue("column"));
                                                 		
                                this.pos = new CurrentPosition(x, y);
                        } 
                        else if (qName.equals("aike:end")) 
                        {
                        		int y= Integer.parseInt(attributes.getValue("row"));
                        		int x= Integer.parseInt(attributes.getValue("column"));
                                             		
                        		this.goal = new Goal(x, y);
                        }
                        else  if (qName.equals("aike:left"))
                        {
                        	int[]pos = new int[2];
                            pos[0] = Integer.parseInt(attributes.getValue("row"));
                            pos[1] = Integer.parseInt(attributes.getValue("column"));
                            //he cambiado el enum por dos booleans :S que me resultaba mas sencillo para los operadores
                            //chorry
                            board.setTile(pos, false, true);
                        }
                        else  if (qName.equals("aike:right-walls"))
                        {
                        	int[]pos = new int[2];
                            pos[0] = Integer.parseInt(attributes.getValue("row"));
                            pos[1] = Integer.parseInt(attributes.getValue("column"));
                            board.setTileVerticalWall(pos, true);
                        }
                        else  if (qName.equals("aike:bottom-walls"))
                        {
                        	int[]pos = new int[2];
                            pos[0] = Integer.parseInt(attributes.getValue("row"));
                            pos[1] = Integer.parseInt(attributes.getValue("column"));
                            board.setTileHorizontalWall(pos, true);
                        }
                } catch (Exception ex) {
                        System.out.println(this.getClass().getName() + ".startElement(): " + ex);
                }
        }
        
        public void imprimir()
    	{
    		this.board.toString();
    	}
}

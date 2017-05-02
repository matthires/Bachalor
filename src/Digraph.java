import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Class for displaying the digraph of matrices.
 * The nodes are displayed in a row in a special sequence.
 * @author matty
 *
 */
@SuppressWarnings("serial")
public class Digraph extends JPanel{			
	private Matrix mtx;
	private int[][] matrix;
	private int dim;
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	
    public Digraph(String title, Matrix mtx, int[][] matrix) throws NullPointerException{
    	
		this.mtx = mtx;
		this.matrix = matrix;
		dim = mtx.getDimension();	
		
    	JFrame frame = new JFrame(title);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(dim*100,300);
		frame.setVisible(true);			
			
		
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		
		addAllContents();
    }
    
    /**
	 * Adds a node with a given coordinate
	 * @param name the name of the node
	 * @param x given X coordinate where to add
	 * @param y given Y coordinate where to add
	 */
	public void addNode(String name, int x, int y) { 
		//add a node at pixel (x,y)
		nodes.add(new Node(name,x,y));
		this.repaint();
    }
	
	/**
	 * Adds an edge between nodes n1 and n2.
	 * The node is oriented from n1 to n2
	 * @param n1 
	 * @param n2
	 */
    public void addEdge(int n1, int n2) {
		edges.add(new Edge(n1,n2));
		this.repaint();
    }
	
    /**
     * Adding all the contents 
     */
	public void addAllContents(){		
		ArrayList<Integer> sortedNodes = new ArrayList<>();
		int count = 1;
		int node1, node2;
		
		//creating a list of nodes in a special sorting
		while(count <= dim ){
			sortedNodes.add(count);	
			sortedNodes.add(dim-(count-1));
			count++;
		}
		
		//adding the nodes with its number
		for(int i = 0;i < dim; i++){
			String name = Integer.toString(sortedNodes.get(i));
			this.addNode(name, 100*i+50, 120);			
		}
		
		//adding the edges between nodes
		for(int n1 = 0; n1 < nodes.size(); n1++){
			for(int n2 = 0; n2 < nodes.size(); n2++){
				//saving the indexes of the nodes to search for their value 
				node1 = nodes.get(n1).getNode()-1;
				node2 = nodes.get(n2).getNode()-1;
				if(mtx.getValueOf(matrix, node1 , node2) == 1){
					this.addEdge(n1, n2);
				}
			}
		}
		
	}
	
	/**
	 * Draws the digraph of given matrix.
	 * The nodes are sorted in a special sorting.
	 */
	public void paint(Graphics g) {
		int width = 30;
		int height = 30; 	
		g.setColor(Color.black);
		Node node1, node2;
		
		QuadCurve2D q = new QuadCurve2D.Float();
					
		//drawing the nodes with names
		for (Node node : nodes) {
			g.setColor(Color.white);
			g.fillOval(node.x-width/2, node.y-height/2, width, height);
			g.setColor(Color.black);
			g.drawOval(node.x-width/2, node.y-height/2, width, height);		    
			g.drawString(node.name, node.x-width/2+12, node.y-height/2+20);
		}
			
		for (Edge edge : edges) {

			node1 = nodes.get(edge.node1);
			node2 = nodes.get(edge.node2);
			
			//neighbour nodes
			if(Math.abs(edge.node2 - edge.node1) == 1){
				if(edge.node1 > edge.node2){
					g.drawLine(node1.x-15, node1.y-3, node2.x+15, node2.y-3);
					drawArrow(g, node2.x, node2.y, -1);
				}else{
					g.drawLine(node1.x+15, node1.y+3, node2.x-15, node2.y+3);
					drawArrow(g, node2.x, node2.y, 1);
				}
			}
			
			//checking for loops
			else if(node1.equals(node2)){
				g.drawArc(node1.x, node1.y-35, 8, 40, 0, 180);
				drawArrow(g, node1.x, node1.y, 0);
			}
			//other edges between non-neighbour nodes 
			else{
				int hght = 0, y1 =0, y2 = 0; 
				int wdth = Math.abs(node2.x - node1.x);
				int dist = Math.abs(edge.node2 - edge.node1);
				switch(dist){
					case 2:
						hght = 50;
						y1 = node2.y-10;
						y2 = node1.y-38;
						break;
					case 3:
						hght = 60;
						y1 = node2.y-15;
						y2 = node1.y-43;
						break;
					case 4:
						hght = 70;
						y1 = node2.y-20;
						y2 = node1.y-48;
						break;
					case 5:
						hght = 80;
						y1 = node2.y-25;
						y2 = node1.y-53;
						break;
					case 6:
						hght = 90;
						y1 = node2.y-30;
						y2 = node1.y-58;
						break;
				}
				
				if(edge.node1 > edge.node2){
					g.drawArc(node2.x, y1, wdth, hght, 0, -180);
					//q.setCurve(node2.x, node2.y, wdth/2, hght, node1.x, node1.y);
					drawArrow(g, node2.x-2, node2.y, -2);
				}else{
					g.drawArc(node1.x + 8, y2, wdth-8, hght, 0, 180);
					drawArrow(g, node2.x, node2.y, 2);
				}
			}
							
		}
		

	 }
	    
	
	/**
	 * Draws arrows to a specific place in a graph to show the direction
	 * @param g Graphics to draw with
	 * @param x X coordinate of the node
	 * @param y Y coordinate of the node
	 * @param direction direction of the edge:
	 * 		0 - loop
	 * 	   -1 - edge between neighbour nodes from right to left
	 * 		1 - edge between neighbour nodes from left to right
	 * 	   -2 - edge between non-neighbour nodes from right to left
	 * 		2 - edge between non-neighbour nodes from left to right
	 */
	public void drawArrow(Graphics g, int x, int y,int direction){
		switch(direction){
			case 0:
				g.drawLine(x+8, y-15, x+5, y-25);
				g.drawLine(x+8, y-15, x+11, y-25);
				break;
			case -1:
				g.drawLine(x+15, y-3, x+25, y-6);
				g.drawLine(x+15, y-3, x+25, y);
				break;
			case 1:
				g.drawLine(x-15, y+3, x-25, y);
				g.drawLine(x-15, y+3, x-25, y+6);
				break;
			case -2:
				g.drawLine(x, y+15, x+14, y+20);
				g.drawLine(x, y+15, x+3, y+28);
				break;
			case 2:
				g.drawLine(x, y-15, x-15, y-20);
				g.drawLine(x, y-15, x-5, y-28);
				break;				
		}
	}
	
    
    /**
     * Class representing the nodes of a digraph
     * @author matty
     *
     */
    public class Node {
		int x, y;
		String name;
		
		public Node(String name, int x, int y) {
		    this.x = x;
		    this.y = y;
		    this.name = name;
		}
		
		/**
		 * Returns the number of the node
		 * @return
		 */
		public int getNode(){
			return Integer.valueOf(name);
		}
		
		/**
		 * Checks whether the 2 nodes are equal or not
		 * @param node2
		 */
		public boolean equals(Node node2){
			if(this.getNode() == node2.getNode()){
				return true;
			}
			return false;
		}
    }
	
    /**
     * Class representing the Edges in the digraph
     * @author matty
     *
     */
	public class Edge {
		int node1, node2;
		
		public Edge(int node1, int node2) {		
			this.node1 = node1;
			this.node2 = node2;
		}
    }
	
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;




public class Digraph extends JPanel{			
	private Matrix mtx;
	private int[][] matrix;
	private int dim;
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	
    public Digraph(String title, Matrix mtx, int[][] matrix) throws NullPointerException{
    	JFrame f = new JFrame(title);
		f.add(this);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(600,300);
		f.setVisible(true);			
		
		this.mtx = mtx;
		this.matrix = matrix;
		dim = mtx.getDimension();		
		
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
			this.addNode(name, 80*i+42, 120);			
		}
		
		//adding the edges between nodes
		for(int n1 = 0; n1 < nodes.size(); n1++){
			for(int n2 = 0; n2 < nodes.size(); n2++){
				//saving the indexes of the nodes to search for thei value 
				node1 = nodes.get(n1).getNode()-1;
				node2 = nodes.get(n2).getNode()-1;
				if(mtx.getValueOf(matrix, node1 , node2) == 1){
					this.addEdge(n1, n2);
				}
			}
		}
		
	}
	
	   public void paint(Graphics g) {
		    int width = 30;
		    int height = 30; 	
			g.setColor(Color.black);
			int count = 0;
			
			QuadCurve2D q = new QuadCurve2D.Float();
			
			for (Edge e : edges) {
				//checking for edges
				
			}
		
			for (Node n : nodes) {
			    g.setColor(Color.white);
			    g.fillOval(n.x-width/2, n.y-height/2, width, height);
			    g.setColor(Color.black);
			    g.drawOval(n.x-width/2, n.y-height/2, width, height);		    
			    g.drawString(n.name, n.x-width/2+12, n.y-height/2+20);
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

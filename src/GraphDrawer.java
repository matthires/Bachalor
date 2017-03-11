import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GraphDrawer extends JPanel{			
	Matrix mtx;
	int[][] matrix;
	int dim;
    ArrayList<Node> nodes;
    ArrayList<Edge> edges;
	
	public GraphDrawer(String title, Matrix mtx, int[][] matrix) throws NullPointerException{
		
		JFrame f = new JFrame(title);
		f.add(this);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(600,300);
		f.setVisible(true);		
		
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		
		this.mtx = mtx;
		this.matrix = matrix;
		dim = mtx.getDimension();
		
		addContents();
	}

	/**
	 * 
	 * @param name the name of the node
	 * @param x given X coordinate where to add
	 * @param y given Y coordinate where to add
	 */
	public void addNode(String name, int x, int y) { 
		//add a node at pixel (x,y)
		nodes.add(new Node(name,x,y));
		this.repaint();
    }
	
    public void addEdge(int n1, int n2) {
		//add an edge between nodes n1 and n2
		edges.add(new Edge(n1,n2));
		this.repaint();
    }
	
	public void addContents(){		
		ArrayList<Integer> vtx = new ArrayList<>();
		int count = 1;
		
		//creating a list of vertices
		while(count <= dim ){
			vtx.add(count);	
			vtx.add(dim-(count-1));
			count++;
		}
		
		//adding the edges with its number
		for(int i = 0;i < dim; i++){
			String name = Integer.toString(vtx.get(i));
			this.addNode(name, 80*i+42, 120);
			
		}
		
		for(int n1 = 0; n1 < nodes.size(); n1++){
			for(int n2 = 0; n2 < nodes.size(); n2++){
				if(mtx.getValueOf(matrix, nodes.get(n1).getValue()-1 , nodes.get(n2).getValue()-1) == 1){
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
			if(mtx.getValueOf(matrix, nodes.get(e.n1).getValue()-1, 
					nodes.get(e.n2).getValue()-1) == 1){
				
				//checking for loops
				if(nodes.get(e.n1).getValue()-1 == nodes.get(e.n2).getValue()-1){
					g.drawOval(nodes.get(e.n1).x-15, 85, 20, 25);
				}
				//when the 2 nodes are neighbours
				else if(Math.abs(e.n2-e.n1) == 1){
					g.drawLine(nodes.get(e.n1).x, nodes.get(e.n1).y,
						     nodes.get(e.n2).x, nodes.get(e.n2).y);
				}
				else{
					count ++;
					if(count % 2 == 0){
						if(count % 2 == 0){
							q.setCurve(nodes.get(e.n1).x, 105, 
									Math.abs(nodes.get(e.n1).x + nodes.get(e.n2).x)/2, 50,
									nodes.get(e.n2).x, 105);
							((Graphics2D)g).draw(q);
							drawArrow(g, nodes.get(e.n2).x, nodes.get(e.n2).y, -3);
						}
						else{
							q.setCurve(nodes.get(e.n1).x, 105, 
									Math.abs(nodes.get(e.n1).x + nodes.get(e.n2).x)/2, 200,
									nodes.get(e.n2).x, 105);
							((Graphics2D)g).draw(q);
							drawArrow(g, nodes.get(e.n2).x, nodes.get(e.n2).y, 3);
						}
					}
					else{
						if(count % 2 == 0){
							q.setCurve(nodes.get(e.n1).x, 135, 
									Math.abs(nodes.get(e.n1).x + nodes.get(e.n2).x)/2, 50,
									nodes.get(e.n2).x, 135);
							((Graphics2D)g).draw(q);
							drawArrow(g, nodes.get(e.n2).x, nodes.get(e.n2).y, -2);
						}
						else{
							q.setCurve(nodes.get(e.n1).x, 135, 
									Math.abs(nodes.get(e.n1).x + nodes.get(e.n2).x)/2, 200,
									nodes.get(e.n2).x, 135);
							((Graphics2D)g).draw(q);
							drawArrow(g, nodes.get(e.n2).x, nodes.get(e.n2).y, 2);
						}
					}
				}
			}
		}
	
		for (Node n : nodes) {
		    g.setColor(Color.white);
		    g.fillOval(n.x-width/2, n.y-height/2, width, height);
		    g.setColor(Color.black);
		    g.drawOval(n.x-width/2, n.y-height/2, width, height);		    
		    g.drawString(n.name, n.x-width/2+12, n.y-height/2+20);
		}
    }
    
	
	public class Node {
		int x, y;
		String name;
		
		public Node(String name, int x, int y) {
		    this.x = x;
		    this.y = y;
		    this.name = name;
		}
		public int getValue(){
			return Integer.valueOf(name);
		}
    }
	
    
	public class Edge {
		int n1, n2;
		
		public Edge(int node1, int node2) {		
			n1 = node1;
			n2 = node2;
		}
    }
	
	
	public void drawArrow(Graphics g, int x, int y,int direction){
		switch(direction){
			case 0:
				g.drawLine(x, y-15, x+25, y+10);
				g.drawLine(x, y-15, x+25, y+20);
				break;
			case -1:
				g.drawLine(x+15, y, x+25, y-5);
				g.drawLine(x+15, y, x+25, y+5);
				break;
			case 1:
				g.drawLine(x-15, y, x-25, y-5);
				g.drawLine(x-15, y, x-25, y+5);
				break;
			case -2:
				g.drawLine(x, y+15, x+12, y+18);
				g.drawLine(x, y+15, x+5, y+28);
				break;
			case 2:
				g.drawLine(x, y+15, x-12, y+18);
				g.drawLine(x, y+15, x-5, y+28);
				break;
			case -3:
				g.drawLine(x, y-15, x+17, y-15);
				g.drawLine(x, y-15, x+10, y-28);
				break;
			case 3:
				g.drawLine(x, y-15, x-17, y-15);
				g.drawLine(x, y-15, x-10, y-28);
				break;				
					
		}
	}

}

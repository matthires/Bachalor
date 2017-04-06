import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 * Own class/type to represent the matrix.
 * When the constructor is called, it displays
 * a 2D array (of chosen dimension) of text fields on the main frame.
 * To initialize the matrix or matrices, the class also creates a 
 * new 2D array of integers where it stores the values filled in the text fields.
 * 
 * 
 * @author metti
 *
 */
public class Matrix {	
		private int dim;
		private int[][] lowerMatrix, upperMatrix;
		private JTextField[][] lMatrix, uMatrix;
		private JLabel panel;
		private JFrame mainFrame;
		private Gui gui;
		
		/**
		 * Displays 2 matrices in form of 2D array of text fields on the main frame.
		 * 
		 * @param frame The main frame of the GUI
		 * @param dimension Dimension of the matrices
		 * @param panel Label to display the matrices
		 */
	public  Matrix(Gui gui, JFrame frame, int dimension, JLabel panel){  
		this.gui = gui;
		mainFrame = frame;  //the main frame -for updating the screen 
        this.panel = panel; //panel to display the matrix on
		lMatrix = new JTextField [dimension][dimension]; //array to store the lower value matrix
		uMatrix = new JTextField [dimension][dimension]; //array to store the upper value matrix
		dim = dimension; //dimension of the matrix
		
		//updating the frame - removing the previous matrix, if there was
		panel.removeAll();
		frame.repaint();
		
		
		//display the lower value matrix
        for(int i = 0;i < dim;i++){
        	for(int j = 0;j < dim;j++){
	        	JTextField textField = new JTextField("0", 10);
	            textField.setBounds(90 + j * 35, 240 + i * 35, 30, 30);
	            lMatrix[i][j] = textField;
            	textField.setHorizontalAlignment(SwingConstants.CENTER);
            	textField.setFont(new Font("San-Serif", Font.BOLD, 16));
            	textField.setEditable(false);
            	textField.setToolTipText("Kliknutím zmeníte 0 na 1 a naopak.");
            	textField.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						if(textField.getText().equals("0")){
							textField.setText("1");
						}
						else{
							textField.setText("0");
						}
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
            	panel.add(textField);
        	}

		}
        
      //display the upper value matrix
        for(int i = 0;i < dim;i++){
        	for(int j = 0;j < dim;j++){
	        	JTextField textField = new JTextField("0", 10);
	            textField.setBounds(490 + j * 35, 240 + i * 35, 30, 30);
	            uMatrix[i][j] = textField;
            	textField.setHorizontalAlignment(SwingConstants.CENTER);
            	textField.setFont(new Font("San-Serif", Font.BOLD, 16));
            	textField.setEditable(false);
            	textField.setToolTipText("Kliknutím zmeníte 0 na 1 a naopak.");
            	textField.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						if(textField.getText().equals("0")){
							textField.setText("1");
						}
						else{
							textField.setText("0");
						}
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
            	panel.add(textField);
        	}

		}
	}
	
	
	/**
	 * Returns any needed value of the edge directed from vertex1 to vertex2
	 * @param vertex1 start vertex
	 * @param vertex2 end vertex
	 * @return The weight of the edge from vertex1 to vertex2
	 */
	public int getValueOf(int[][] matrix, int vertex1, int vertex2) {
		return matrix[vertex1][vertex2];
	}
	 
	
	/**
	 * Sets the value of the edge directed from vertex1 to vertex2
	 * @param vertex1 start vertex
	 * @param vertex2 end vertex
	 * @param value - value to be set.
	 */
	protected void setEdge(int[][] matrix, int vertex1, int vertex2, int value) {		
		matrix[vertex1][vertex2] = value;			
	}
	
	
	/**
	 * Initialises the 2 matrices what the user filled to have access to the data.
	 * Creates new 2D arrays to store the values to them. One for the upper values, 
	 * an other one for the lower values.
	 * Also validates the input - it must be binary, must be a number. Whether it isn't, 
	 * an alert-box shows up to note the user to fill in valid, binary data. 
	 * @throws IsNotGreaterException 
	 */
	public boolean initMatrices() throws IsNotGreaterException{
	    int value;
	  
	  	lowerMatrix = new int[dim][dim];
	  	upperMatrix = new int[dim][dim];
	  
	  	try{
	  		gui.showMatrices(dim);
			for (int row = 0; row < dim; row++) {
				for (int col = 0; col < dim; col++) {
					value = Integer.parseInt(lMatrix[row][col].getText());
					setEdge(lowerMatrix, row, col, value);
				}
			}
			
			for (int row = 0; row < dim; row++) {
				for (int col = 0; col < dim; col++) {
					value = Integer.parseInt(uMatrix[row][col].getText());
					if(value < getValueOf(lowerMatrix, row, col)){
						throw new IsNotGreaterException();
					}
					else{
						setEdge(upperMatrix, row, col, value);
					}					
				}
			}	
			return true;
	  	}
		catch(NullPointerException npe){
			JOptionPane.showMessageDialog(mainFrame , 
					"Matice musia byť inicializované!","CHYBA!",JOptionPane.ERROR_MESSAGE);
		}
	  	return false;
	}
	

	
	
	/**
	 * Checks if the matrices are Monge matrices.
	 * The principle is:
	 * The program checks, whether for all i,j,k,l such that:
	 * 	1≤ i < k ≤ m  and  1≤ j < l ≤ n  one obtains:
	 *		A[i,j] + A[k,l] ≤  A[i,l] + A[k,j] .
	 * If it finds the first four, where it is not satisfied,
	 * it stops and returns a string for input,that the matrix is not Monge.
	 * Otherwise the string says that the matrix is Monge.
	 *  
	 * @param lowerMatrix the lower-value matrix 
	 * @param upperMatrix the upper-value matrix
	 * @return "The matrix is Monge" if  true, "The matrix is NOT Monge" if false
	 * @throws IsNotGreaterException 
	 */
	 public String isMonge() throws NullPointerException, IsNotGreaterException{
	    int i,j,k,l;
	    if(initMatrices()){
		   if(lowerMatrix == null || upperMatrix == null){
		 	  throw new NullPointerException();
		   }
		   boolean isMonge = true;
			 for(i=0;i<dim-1;i++){
				 for(j=0;j<dim-1;j++){
					 for(k=i+1;k<dim;k++){
						 for(l=j+1;l<dim;l++){
							 if(Math.min(getValueOf(lowerMatrix, i, j),getValueOf(lowerMatrix, k, l))
								 > Math.min(getValueOf(lowerMatrix, i, l),getValueOf(lowerMatrix, k, j))
								 || Math.min(getValueOf(upperMatrix, i, j),getValueOf(upperMatrix, k, l))
								 > Math.min(getValueOf(upperMatrix, i, l),getValueOf(upperMatrix, k, j))){
									 isMonge = false;
									 break;
							 }
						 }
					 }
				 }
			 }
			 if(isMonge){
				 return " Matica JE mongeovská.";
			 }
			 else return " Matica NIE je mongeovská.";
	    }
	    return "";
	 }
	
	
	
	/**
	 * Getter for the lower value matrix.
	 * @return the lower value matrix
	 */
	public int[][] getLMatrix(){
		return lowerMatrix;
	}
	
	/**
	 * Getter for the upper value matrix.
	 * @return the upper value matrix
	 */
	public int[][] getUMatrix(){
		return upperMatrix;
	}
	
	
	/**
	 * Checks whether the given interval matrix is possibly 
	 * or universally robust or if it has a type of Gamma.
	 * The principle is in the bachelor thesis
	 * There are two *types* of possibilities:
	 *  the top'n'left L - the Gamma' type,
	 *  the bottom'n'right L - the Gamma_ type.
	 * 
	 * @return the state of the robustness:
	 * 		 0 - at least one of the matrices don't have a type of Gamma
	 * 		 1 - one of the matrices has a type of Gamma_ with a loop and both are non-trivial
	 * 		11 - each matrices are non-trivial and have a type of Gamma_ with a loop
	 * 		 2 - one of the matrices has a type of Gamma' with a loop and both are non-trivial
	 * 		22 - each matrices are non-trivial and have a type of Gamma' with a loop
	 * 		13 - none of the matrices are robust but have a type of Gamma_
	 * 		23 - none of the matrices are robust but have a type of Gamma'
	 * 		14 - each matrices are trivial and have a type of Gamma_
	 * 		24 - each matrices are trivial and have a type of Gamma'
	 * 		15 - lower matrix is trivial, the upper non-trivial,
	 * 			  both have a type of Gamma_ and are robust 
	 * 		25 - lower matrix is trivial, the upper non-trivial,
	 * 			  both have a type of Gamma' and are robust 
	 * 		16 - lower matrix is trivial, the upper non-trivial,
	 * 			  both have a type of Gamma_ but,the upper matrix is not robust
	 * 		26 - lower matrix is trivial, the upper non-trivial,
	 * 			  both have a type of Gamma' but,the upper matrix is not robust
	 * 			
	 * 
	 */
	public int isL() throws NullPointerException, IsNotGreaterException{
		int state = -1;
		int edge = -1;
		if (initMatrices()){
			for(int i = 0; i < dim; i++){
				for(int j = 0; j < dim; j++){	
					if(edge < 0){
						//searching for 1s
						if(getValueOf(upperMatrix, i, j) == 1 || getValueOf(upperMatrix, j, i) == 1){
							//checking for the upper L with loop
							if(getValueOf(upperMatrix, i, i) == 1){
								//lower and also the upper matrix have loop
								edge = i;
								if(getValueOf(lowerMatrix, i, i) == 1 ){
									state = 22;
								}
								//one of the matrices has a loop
								else{
									state = 2;
								}	
							}
							//checking for the lower L with loop
							else if(getValueOf(upperMatrix, j, j) == 1){
								//lower and also the upper matrix have loop
								edge = j;
								if(getValueOf(lowerMatrix, j, j) == 1){
									state = 11;
								}
								//one of the matrices has a loop
								else{
									state = 1;
								}
							}
							//there's no loop
							else{
								edge = i;
								state = -2;
							}							
						}
						//there are no 1s
						else{
							state = 24;
						}
					}
					
					//checking for triviality
					if(state == -2 && getValueOf(upperMatrix, i, j)==1){
						if((i==edge && j>=0) || (j==edge && i>=0)){  
							//assuming there's upper L-searching for other 1s
							edge = i;
							state = 20;
						}
						//if it's not the upper L it would be the lower L 
						else{
							edge = j;
							state = 10;
						}
					}
					//checking the lower Gamma type	- the lower matrix is trivial, the upper non-trivial				
					if(state == 1 || state == 11 || state == 13 || state == 10 || state == 15 || state == 16){
						//checks if there are other 1s out of the Gamma
						if((getValueOf(lowerMatrix, i, j) == 1  ||
								getValueOf(upperMatrix, i, j) == 1) && ((j>edge && i>=0) || (i>edge && j>=0))){
							state = 0;
						}
						//the upper matrix has a loop
						if(state == 1 && getValueOf(upperMatrix, i, j) == 1){
							state = 15;
						}
						if(state == 10 && getValueOf(upperMatrix, i, j)==1 && getValueOf(upperMatrix, j, i)==1){
							//when they are both non trivial with no loop
							if(getValueOf(lowerMatrix, i, j) == 1 && getValueOf(lowerMatrix, j, i)==1){
								state = 13;
							}							
							else{
								state = 16;
							}
						}
					}
					//checking the upper Gamma type - the lower matrix is trivial, the upper non-trivial		
					if(state == 2 || state == 22 || state == 23 || state == 20 || state == 25 || state == 26){
						//checks if there are other 1s out of the Gamma
						if((getValueOf(lowerMatrix, i, j) == 1  ||
								getValueOf(upperMatrix, i, j) == 1) && (i > edge &&j > edge) ){
							state = 0;
						}
						//the upper matrix has a loop
						if(state == 2 && getValueOf(upperMatrix, i, j) == 1){
							state = 25;
						}
						if(state == 20 && getValueOf(upperMatrix, i, j)==1 && getValueOf(upperMatrix, j, i)==1){
							//when they are both non trivial and no loop
							if(getValueOf(lowerMatrix, i, j) == 1 && getValueOf(lowerMatrix, j, i) == 1){
								state = 23;
							}
							else{
								state = 26;
							}
						}
					}
					
				}
			}
			return state;
		}
		return 10;
	}
	
	/**
	 * Decides the state of the chosen matrix, whether it is possibly,
	 * universally robust, if it's trivial or if it has a type of Gamma.
	 * @param state the option we want to know: 
	 * 		0 - possibly robustness,
	 * 		1 - universal robustness
	 * 		2 - has type of Gamma or not
	 * 		3 - triviality
	 * @return a given String to print
	 * @throws IsNotGreaterException 
	 * @throws NullPointerException 
	 */
	public String isRobust(int state) throws NullPointerException, IsNotGreaterException{
		String textToPrint = "";
		if(state == 2){
			if(isL() < 20 && isL() > 0){
				return "Matica Aᴹ je typu Γ⏌ !";
			}else if(isL() == 0){
				return "Matica Aᴹ NIE je typu Γ !";
			}else{
				return "Matica Aᴹ je typu ⎾Γ !";
			}
		}
		
		else if(state == 3){
			if(isL() == 0){
				return "Matica Aᴹ NIE je typu Γ !";			
			}else if(isL() == 14 || isL() == 24){
				return "Matica je triviálna.!";
			}else if(isL() % 5 == 0 || isL() == 26 || isL() == 16){
				return "Matica A̲ je triviálna, matica A̅ je netriviálna. ";
			}else{
				return "Matica Aᴹ je netriviálna !";
			}
		}
			
		
		else{
				
			switch (isL()){
			case 0:				
				textToPrint = "Matica Aᴹ nie je typu Γ !";
				break;
			case 24:
				if(state == 1){
					textToPrint =  "Matica Aᴹ je univerzálne robustná.";	
					break;
				}
				else{
					textToPrint =  "Matica Aᴹ je možne robustná.";	
					break;
				}
			case 14:
				if(state == 1){
					textToPrint =  "Matica Aᴹ je univerzálne robustná.";	
					break;
				}
				else{
					textToPrint =  "Matica Aᴹ je možne robustná.";	
					break;
				}
			case 23:
				if(state == 0){
					textToPrint =  "Matica Aᴹ nie je možne robustná.";
					break;
				}
				else{
					textToPrint =  "Matica  Aᴹ nie je univerzálne robustná.";
					break;
				}
			case 13:
				if(state == 0){
					textToPrint =  "Matica Aᴹ nie je možne robustná.";
					break;
				}
				else{
					textToPrint =  "Matica Aᴹ nie je univerzálne robustná.";
					break;
				}
			case 2:
				if(state == 0){
					textToPrint =  "Matica Aᴹ je možne robustná.";	
					break;
				}
				else{
					textToPrint =  "Matica Aᴹ nie je univerzálne robustná.";
					break;
				}
			case 1:
				if(state == 0){
					textToPrint =  "Matica Aᴹ je možne robustna.";	
					break;
				}
				else{
					textToPrint =  "Matica Aᴹ nie je univerzálne robustná.";
					break;
				}
			case 22:
				if(state == 1){
					textToPrint =  "Matica Aᴹ je univerzálne robustná.";	
					break;
				}
				else{
					textToPrint =  "Matica Aᴹ je možne robustná.";	
					break;
				}
			case 11:
				if(state == 1){
					textToPrint =  "Matica Aᴹ je univerzálne robustná.";	
					break;
				}
				else{
					textToPrint =  "Matica Aᴹ je možne robustná.";	
					break;
				}
				case 15:
				if(state == 1){
					textToPrint =  "Matica Aᴹ je univerzálne robustná.";	
					break;
				}
				else{
					textToPrint =  "Matica Aᴹ je možne robustná.";	
					break;
				}
			case 25:
				if(state == 1){
					textToPrint =  "Matica Aᴹ je univerzálne robustná.";	
					break;
				}
				else{
					textToPrint =  "Matica Aᴹ je možne robustná.";	
					break;
				}
			case 16:
				if(state == 1){
					textToPrint =  "Matica Aᴹ nie je univerzálne robustná.";	
					break;
				}
				else{
					textToPrint =  "Matica Aᴹ je možne robustná.";	
					break;
				}
			case 26:
				if(state == 1){
					textToPrint =  "Matica Aᴹ nie je univerzálne robustná.";	
					break;
				}
				else{
					textToPrint =  "Matica Aᴹ je možne robustná.";	
					break;
				}			
			default:
				textToPrint =  "";
				break;
			}
			
		}
		
		return textToPrint;
			
	}
	
	
	/**
	 * Returns the set up dimension of the matrix.
	 * @return dimension of the matrix
	 */
	public int getDimension() {		
		return this.dim;
	}
	

}

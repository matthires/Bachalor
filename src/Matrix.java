import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 * Represents an interval matrix and operations with matrices..
 * When the constructor is called, it displays
 * a 2D array (of chosen dimension) of text fields on the main frame.
 * To initialise the matrix or matrices, the class also creates a 
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
	 * Also checks all the operations needed (type, triviality, robustness) and 
	 * validates the input - if the upper value is greater than the lower.
	 * Whether it isn't, an alert-box shows up to note the user to fill in valid data. 
	 * 
	 * @throws IsNotGreaterException 
	 */
	public boolean initMatrices()  throws NullPointerException, IsNotGreaterException{
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
			checkProperties();
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
	 * @deprecated
	 */
	 public String isMon() throws NullPointerException, IsNotGreaterException{
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
				 return " Matica je mongeovská.";
			 }
			 else return " Matica nie je mongeovská.";
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
	
		
		protected int edge; //the position of the 𝚪, the edge of the L
		//holds a value to represent the triviality of the interval matrix
		//0 if both trivial, 1 if the lower is trivial, 2 if both re non-trivial
		protected int trivial; 
		protected int state; //holds a value for robustness
		//0-both matrices has a type of 𝚪, 1- only the lower matrix, 2- non of the matrices
		protected int gamma; 
		protected int temp; //value to save the original row/column where the first 1 was found
		
	/**
	 * Checks whether the given interval matrix is possibly 
	 * or universally robust or if it has a type of Gamma.
	 * It validates the data via method {@link #validate(int row, int col)}
	 * The principle is in the bachelor thesis.
	 * There are two *types* of possibilities of type:
	 *  the top'n'left L - the ⎾𝚪 type,
	 *  the bottom'n'right L - the 𝚪⏌ type.
	 *  
	 *  Saves the position of 𝜸 to the variable {@link #edge},
	 *  The variable {@link #gamma} is representing the type of matrices:
	 *  - 0: both matrices have a type 𝚪
	 *  - 1: A̅ doesn't have a type 𝚪
	 *  - 2: non of the matrices have a type 𝚪
	 *  The variable {@link #trivial}represents the triviality of matrices:
	 *  - 0: both matrices are trivial
	 *  - 1: A̅ is not trivial
	 *  - 2: non of the matrices are trivial
	 *  The variable {@link #state} represents the state of robustness and type:
	 *  - if the state is divisible by 2, it has a type of 𝚪⏌, else it's ⎾𝚪
	 *  - 1, 2 : A̅ is non-trivial, robust, 
	 *  - 11, 22: both A̲ a A̅ are non-trivial, robust,
	 *  - 41, 31, 32 - must be trivial to be robust
	 */	
	public void checkProperties(){		
		gamma = 0;
		edge = -1;
		trivial = 0;
		state = -1;
		for(int i = 0; i < dim; i++){
			for(int j = i; j < dim; j++){	
				if(gamma == 0){												
					//checking for 1s
					if(edge < 0){			
						if(getValueOf(upperMatrix, i, j) == 1 
						|| getValueOf(upperMatrix, j, i) == 1){
							//found a loop at the position i,i -⎾𝚪
							if(getValueOf(upperMatrix, i, i) == 1){
								edge = i;
								if(getValueOf(lowerMatrix, i, i) == 1){
									state = 11;
								}else{									
									state = 1;	
								}
							}
							//found a loop at the position j,j - 𝚪⏌
							else if(getValueOf(upperMatrix, j, j) == 1){
								edge = j;
								if(getValueOf(lowerMatrix, j, j) == 1){
									state = 22;
								}else{
									state = 2;									
								}
							}else{ //unsure of the type, need to be validated, assumed to type ⎾𝚪
								state = 41;
								edge = i;
								temp = j;							
							}
						}
					}
					if(state > 0){
						validate(i, j);
					}						
				}				
			}
		}		
	}
	
	/**
	 * Checks if there are other 1s out of the 𝚪,
	 * validates the type 𝚪  on a given position.
	 * Also checks for its triviality.
	 * 
	 * @param row row index of the given position
	 * @param col column index of the given position
	 */
	public void validate(int row, int col){
		//checking for triviality
		if(trivial <= 1){
			if(getValueOf(upperMatrix, row, col) == 1 
			&& getValueOf(upperMatrix, col, row) == 1){
				if(getValueOf(lowerMatrix, row, col) == 1 
				&& getValueOf(lowerMatrix, col, row) == 1){
					trivial = 2;
				}else{
					trivial = 1;
				}							
			}
		}
		//validates the type when unsure
		if(state == 41 	&& (getValueOf(upperMatrix, row, col) == 1 
		|| getValueOf(upperMatrix, col, row) == 1)){
			if((row == edge && col > temp) || (col == edge && row > temp)){
				state = 31;
				edge = row;
			}else if((row == temp && col < temp && col > edge)
				|| (col == temp && row < temp && row > edge)){
				state = 32;
				edge = temp;
			}
		}
		//validating the 𝚪 type
		if(state % 2 == 0){
			if((row > edge || col > edge || (row < edge && col < edge)) 
			&& (getValueOf(upperMatrix, row, col) == 1 
			|| getValueOf(upperMatrix, col, row) == 1)){
				gamma = 1;
				if(getValueOf(lowerMatrix, row, col) == 1 
				|| getValueOf(lowerMatrix, col, row) == 1){
					gamma = 2;
				}			
			}
		}else if(state % 2 == 1){
			if((row > edge && col > edge) &&
			(getValueOf(upperMatrix, row, col) == 1 
			|| getValueOf(upperMatrix, col, row) == 1)){
				gamma = 1;
				if(getValueOf(lowerMatrix, row, col) == 1 
				|| getValueOf(lowerMatrix, col, row) == 1){
					gamma = 2;
				}			
			}
		}			
	}
	
	/**
	 * Getter method for gamma to check if the matrix has a type of 𝚪.
	 * @return String representing the actual state :
	 * both matrices has a type of 𝚪 / only the lower matrix / non of the matrices
	 * Also prints the position of the edge.
	 */
	public String isGama(){
		String type = "";
		
		if(state == -1){
			gamma = 3;
		}if(state % 2 == 0){
			type = "𝚪⏌";
		}else{
			type = "⎾𝚪 ";
		}
		switch(gamma){
			case 0:
				return " Matice A̲ a A̅ sú typu " + type + "pre 𝜸 = " + (edge+1) + ".";
			case 1:
				return " Matica A̅ nie je typu 𝚪. ";
			case 2:
				return " Matice A̲ a A̅ nie sú typu 𝚪.";
			default:
				return " Matice A̲ a A̅ sú typu 𝚪.";
		}
	}
	
	/**
	 * Checks whether the given interval matrix is monge matrix.
	 * If it has a type of 𝚪, it is monge, else it isn't.
	 * @return text representing the mongeness of the interval matrix.
	 * It won't print anything if it doesn't have the type of 𝚪.
	 */
	public String isMonge(){
		if(gamma == 0 || gamma == 3){
			return " Aᴹ je intervalovo mongeovská matica.";
		}else{
			return "";
		}
		
	}
	
	protected String isUni = " Matica Aᴹ je univerzálne robustná.",
					 isPos = " Matica Aᴹ je možne robustná.",
					 notUni = " Matica Aᴹ nie je univerzálne robustná.",
					 notPos = " Matica Aᴹ nie je možne robustná.",
					 notGamma = " Matica nie je uvažovaného typu.";	
	
	/**
	 * Checks if the interval matrix of type 𝚪 is possibly robust or not.
	 * An interval matrix of type 𝚪  is possibly robust if:
	 * - at least one of the matrices A̲, A̅  of type 𝚪  are trivial
	 * - at least one of the digraphs of matrices of type 𝚪  has a loop
	 * If at least one of the matrices doesn't have a type of 𝚪,
	 * the algorithm stops, and prints out a text to note the user,
	 * that the matrix doesn't have the type we work with.
	 * @see {@link #checkProperties()}
	 * @return text - the matrix is/ is not possibly robust 
	 */
	public String isPosRobust(){		
		if(gamma > 0){
			return notGamma;
		}else{
			if(trivial < 2 || state == 11 || state == 22 || (trivial == 2 && state <= 22)){
				return isPos;
			}else{
				return notPos;
			}
		}		
	}
	
	
	/**
	 * Checks if the interval matrix of type 𝚪 is universally robust or not.
	 * An interval matrix of type 𝚪  is universally robust if:
	 * - both of the matrices A̲, A̅  of type 𝚪  are trivial
	 * - both of the digraphs of matrices of type 𝚪  has a loop
	 * If at least one of the matrices doesn't have a type of 𝚪,
	 * the algorithm stops, and prints out a text to note the user,
	 * that the matrix doesn't have the type we work with.
	 * @see {@link #checkProperties()}
	 * @return text - the matrix is/ is not universally robust 
	 */
	public String isUniRobust(){		
		if(gamma > 0){
			return notGamma;
		}else{
			if(trivial == 0 || (state == 11 || state == 22)
			|| ((state == 1 || state ==2) && trivial == 1)){
				return isUni;
			}else{
				return notUni;
			}
		}		
	}
	
	/**
	 * Checks whether the matrices are trivial or not.
	 * @see {@link #checkProperties()}
	 * @return text representing which matrix is/is not trivial
	 */
	public String isTrivial(){
		switch(trivial){
			case 0:
				return " Matice A̲ a A̅ sú triviálne typu 𝚪 . ";
			case 1:
				return " Matica A̲ je triviálna, matica A̅ je netriviálna typu 𝚪 . ";
			case 2:
				return " Matice A̲ a A̅ sú netriviálne typu 𝚪 . ";
			default:
				return "";
		}
	}
		
	
	
	/**
	 * Returns the set up dimension of the matrix.
	 * @return dimension of the matrix
	 */
	public int getDimension() {		
		return this.dim;
	}
	

}

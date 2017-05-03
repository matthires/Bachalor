import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Visibility;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;


/**
 * This class stands for the GUI part of the program.
 * 
 * @author matthires
 *
 */
public class Gui {
	 private static JLabel title, author, selectDimension, notes, isGamma, isMon, 
	 	isTrivi, isUni, isPos, upperMatrix, lowerMatrix, lMatrix, uMatrix;
	 private static JFrame frame;
	 private static JLabel panel, matrixLayout;
	 private static JButton mongeButton, uniRobustButton, typeButton, isTriviButton,
	 	possRobustButton, lGraphBtn, uGraphBtn, resetBtn;
	 private String[] dims = {"1", "2", "3", "4", "5", "6", "7"};
	 private JComboBox<?> dimensions;
	 private Matrix matrix = null;
	 private int dim;
	 private Gui gui;
	 /**
	  * The constructor of the GUI.
	  */
	public Gui(){
		gui = this;
	}
	
	/**
	 * Starts the GUI - displays a new window with the given components.
	 * It uses 2 labels, one transparent for the matrices and the 
	 * other one for all the other components like buttons,labels etc.
	 */
	public void startGui(){
		frame = new JFrame("Robustnosť mongeovských matíc");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JLabel(new ImageIcon(
			"/home/matty/Desktop/bachalor.zip_expanded/Bachelor/background.jpg"));
        panel.setOpaque(true);
        panel.setLayout(null);
        
        matrixLayout = new JLabel();
        matrixLayout.setOpaque(false);
        matrixLayout.setLayout(null);
        matrixLayout.setSize(800,800);
                
		title = new JLabel("Možná a univerzálna"
				+ " robustnosť mongeovských fuzzy matíc");
		title.setFont(new Font("San-Serif", Font.BOLD, 22));
		title.setForeground(Color.white);
		title.setSize(800, 30);
		title.setLocation(25, 40);
		
		author = new JLabel("Máté Hireš, 2017");
		author.setForeground(Color.white);
		author.setFont(new Font("San-Serif", Font.BOLD, 16));
		author.setSize(300, 30);
		author.setLocation(640, 730);
		
		
		selectDimension = new JLabel("Veľkosť matice:");
		selectDimension.setForeground(Color.white);
		selectDimension.setFont(new Font("San-Serif", Font.BOLD, 16));
		selectDimension.setSize(200, 30);
		selectDimension.setLocation(50, 100);
		
		lMatrix = new JLabel("A̲=");
		lMatrix.setFont(new Font("San-Serif", Font.BOLD, 20));
		lMatrix.setForeground(Color.WHITE);
		lMatrix.setSize(50,50);
		lMatrix.setVisible(false);
		
		uMatrix = new JLabel("A̅=");
		uMatrix.setFont(new Font("San-Serif", Font.BOLD, 20));
		uMatrix.setForeground(Color.WHITE);
		uMatrix.setSize(50,50);
		uMatrix.setVisible(false);
		
		//some text to note the user 
		notes = new JLabel(" Zvoľte veľkosť matice kliknutím na combobox "
				, SwingConstants.CENTER);
		notes.setOpaque(true);
		notes.setBackground(Color.white);
		notes.setFont(new Font("San-Serif", Font.BOLD, 16));
		notes.setSize(690, 25);
		notes.setLocation(50, 140);
		//enterMatrices.setVisible(false);
		
		lowerMatrix = new JLabel("Matica dolných hodnôt:");
		lowerMatrix.setForeground(Color.white);
		lowerMatrix.setFont(new Font("San-Serif", Font.BOLD, 16));
		lowerMatrix.setSize(300, 30);
		lowerMatrix.setLocation(90, 170);	
		
		upperMatrix = new JLabel("Matica horných hodnôt:");
		upperMatrix.setForeground(Color.white);
		upperMatrix.setFont(new Font("San-Serif", Font.BOLD, 16));
		upperMatrix.setSize(300, 30);
		upperMatrix.setLocation(490, 170);
		
	    resetBtn = new JButton("Vynulovať");
	    resetBtn.setSize(120, 30);
	    resetBtn.setLocation(620, 100);
		
		typeButton = new JButton("Overiť typ matice");
	    typeButton.setSize(270, 45);
	    typeButton.setLocation(10, 505);
				
	    /*mongeButton = new JButton("Overiť mongeovskosť");
	    mongeButton.setSize(270, 30);
	    mongeButton.setLocation(10, 550);*/	
	    
	    isTriviButton = new JButton("<html> <div style='text-align: center;'> "
	    		+ "Overiť triviálnosť typu 𝚪  <br> matíc A̲, A̅ </div> </html>");
	    isTriviButton.setHorizontalAlignment(SwingConstants.CENTER);
	    isTriviButton.setSize(270, 45);
	    isTriviButton.setLocation(10, 575);
	    
	    possRobustButton = new JButton("<html> <div style='text-align: center;'> "
	    		+ "Overiť možnú robsutnosť <br> matice Aᴹ </div> </html> ");
	    possRobustButton.setHorizontalAlignment(SwingConstants.CENTER);
	    possRobustButton.setSize(270, 45);
	    possRobustButton.setLocation(10, 630);
	    
	    uniRobustButton = new JButton("<html> <div style='text-align: center;'> "
	    		+ "Overiť univerzálnu robustnosť <br> matice Aᴹ </div> </html>");
	    uniRobustButton.setHorizontalAlignment(SwingConstants.CENTER);
	    uniRobustButton.setSize(270, 45);
	    uniRobustButton.setLocation(10, 685);
	    
	    lGraphBtn= new JButton("Ukáž digraf matice A̲");
	    lGraphBtn.setSize(180, 30);
	    lGraphBtn.setLocation(100, 200);
	    lGraphBtn.setVisible(false);
	    
	    uGraphBtn= new JButton("Ukáž digraf matice A̅");
	    uGraphBtn.setSize(180, 30);
	    uGraphBtn.setLocation(500, 200);
	    uGraphBtn.setVisible(false);
	    
		isGamma = new JLabel("");
		isGamma.setFont(new Font("San-Serif", Font.BOLD, 14));
		isGamma.setOpaque(true);
		isGamma.setBackground(Color.white);
		isGamma.setSize(470, 45);
		isGamma.setLocation(300, 490);
	    
		isMon = new JLabel("");
		isMon.setFont(new Font("San-Serif", Font.BOLD, 14));
		isMon.setOpaque(true);
		isMon.setBackground(Color.white);
		isMon.setSize(470, 45);
		isMon.setLocation(300, 520);
		
		isTrivi = new JLabel("");
		isTrivi.setFont(new Font("San-Serif", Font.BOLD, 14));
		isTrivi.setOpaque(true);
		isTrivi.setBackground(Color.white);
		isTrivi.setSize(470, 45);
		isTrivi.setLocation(300, 575);
		
		isPos = new JLabel("");
		isPos.setFont(new Font("San-Serif", Font.BOLD, 14));
		isPos.setOpaque(true);
		isPos.setBackground(Color.white);
		isPos.setSize(470, 45);
		isPos.setLocation(300, 630);
	    		
		isUni = new JLabel("");
		isUni.setFont(new Font("San-Serif", Font.BOLD, 14));
		isUni.setOpaque(true);
		isUni.setBackground(Color.white);
		isUni.setSize(470, 45);
		isUni.setLocation(300, 685);
	    
		dimensions  = new JComboBox<Object>(dims);
	    dimensions.setSize(50, 30);
	    dimensions.setLocation(200, 100);	    

	    setPanelContents();
		
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setContentPane(panel);
		
		dimensions.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	String selectedDim = dimensions.getSelectedItem().toString();	
				dim = Integer.parseInt(selectedDim);	
				showMatrices(dim);
				reset();				
				lGraphBtn.setVisible(true);
				uGraphBtn.setVisible(true);
		    }
		});
				
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		
		lGraphBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					matrix.initMatrices();
					new Digraph("Digraf matice A̲",
							matrix, matrix.getLMatrix());			
				}catch(IsNotGreaterException isge){
				JOptionPane.showMessageDialog(frame , 
					"Prvky hornej matice musia byt väčšie nanajvyš rovné ako "
					+ "prvky dolnej matice!", "CHYBA!",JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		
		uGraphBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					matrix.initMatrices();
					new Digraph("Digraf matice A̅",
						matrix, matrix.getUMatrix());	
				}catch(IsNotGreaterException isge){
				JOptionPane.showMessageDialog(frame , 
					"Prvky hornej matice musia byt väčšie nanajvyš rovné ako "
					+ "prvky dolnej matice!", "CHYBA!",JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		
		typeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					matrix.initMatrices();
					isGamma.setText(matrix.isGama());
					isMon.setText(matrix.isMonge());
				}
				catch(NullPointerException npe){
					JOptionPane.showMessageDialog(frame , 
						"Matice musia byť inicializované!","CHYBA!",JOptionPane.ERROR_MESSAGE);
				}catch(IsNotGreaterException isge){
					JOptionPane.showMessageDialog(frame , 
						"Prvky hornej matice musia byt väčšie nanajvyš rovné ako "
						+ "prvky dolnej matice!", "CHYBA!",JOptionPane.ERROR_MESSAGE);
			  	}	
			}		
		});		
		
		/*mongeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					matrix.initMatrices();
					isMon.setText(matrix.isMonge());					
				}
				catch(NullPointerException npe){
					JOptionPane.showMessageDialog(frame , 
						"Matice musia byť inicializované!","CHYBA!",JOptionPane.ERROR_MESSAGE);
				}catch(IsNotGreaterException isge){
					JOptionPane.showMessageDialog(frame , 
						"Prvky hornej matice musia byt väčšie nanajvyš rovné ako "
						+ "prvky dolnej matice!", "CHYBA!",JOptionPane.ERROR_MESSAGE);
			  	}	
			}		
		});	*/
		
		isTriviButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					matrix.initMatrices();
					isTrivi.setText(matrix.isTrivial());
				}
				catch(NullPointerException npe){
					JOptionPane.showMessageDialog(frame , 
						"Matice musia byť inicializované!","CHYBA!",JOptionPane.ERROR_MESSAGE);
				}catch(IsNotGreaterException isge){
					JOptionPane.showMessageDialog(frame , 
						"Prvky hornej matice musia byt väčšie nanajvyš rovné ako "
						+ "prvky dolnej matice!", "CHYBA!",JOptionPane.ERROR_MESSAGE);
			  	}	
			}	
		});
		
		possRobustButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					matrix.initMatrices();
					isPos.setText(matrix.isPosRobust());	
				}
				catch(NullPointerException npe){
					JOptionPane.showMessageDialog(frame , 
						"Matice musia byť inicializované!","CHYBA!",JOptionPane.ERROR_MESSAGE);
				}catch(IsNotGreaterException isge){
					JOptionPane.showMessageDialog(frame , 
						"Prvky hornej matice musia byt väčšie nanajvyš rovné ako "
						+ "prvky dolnej matice!", "CHYBA!",JOptionPane.ERROR_MESSAGE);
			  	}		
			}
		});
		
		uniRobustButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					matrix.initMatrices();
					isUni.setText(matrix.isUniRobust());
				}
				catch(NullPointerException npe){
					JOptionPane.showMessageDialog(frame , 
						"Matice musia byť inicializované!","CHYBA!",JOptionPane.ERROR_MESSAGE);
				}catch(IsNotGreaterException isge){
					JOptionPane.showMessageDialog(frame , 
						"Prvky hornej matice musia byt väčšie nanajvyš rovné ako"
						+ " prvky dolnej matice!", "CHYBA!",JOptionPane.ERROR_MESSAGE);
			  	}	
			}		
		});			
		
	}

	/**
	 * Resets all the fields, deletes all the text.
	 */
	public void reset(){	
		matrix = new Matrix(gui, frame, dim, matrixLayout);	
		isMon.setText("");
		isUni.setText("");
		isPos.setText("");
		isTrivi.setText("");
		isGamma.setText("");
		notes.setText("Kliknutím na danú pozíciu zmeníte prvky "
				+ "matice z 1 na 0 a opačne");
	}
	
	/**
	 * Printing out the "names" of the matrices when they show up.
	 * @param dim dimension of the matrices
	 */
	public void showMatrices(int dim){		
		lMatrix.setLocation(50, 230 + (dim-1)*35/2);
		lMatrix.setVisible(true);
		uMatrix.setLocation(450, 230 + (dim-1)*35/2);
		uMatrix.setVisible(true);
	}
	
	/**
	 * Adds the labels and buttons to the main label.
	 */
	public void setPanelContents(){		
	    panel.add(title);
	    panel.add(author);
	    panel.add(matrixLayout);
	    panel.add(uMatrix);
	    panel.add(lMatrix);
	    panel.add(typeButton);
		//panel.add(mongeButton);
		panel.add(isTriviButton);
		panel.add(possRobustButton);
		panel.add(uniRobustButton);
		panel.add(selectDimension);
		panel.add(lGraphBtn);
		panel.add(uGraphBtn);
		panel.add(notes);
		panel.add(dimensions);
		panel.add(isGamma);
		panel.add(isMon);
		panel.add(isTrivi);
		panel.add(isPos);
		panel.add(isUni);
		panel.add(lowerMatrix);
		panel.add(upperMatrix);		
		panel.add(resetBtn);
	}
		
}

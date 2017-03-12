import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	 private static JLabel title, selectDimension, enterMatrices, isGamma,
	 		isMon, isTrivi, isUni, isPos, upperMatrix, lowerMatrix;
	 private static JFrame frame;
	 private static JLabel panel, matrixLayout;
	 private static JButton mongeButton, uniRobustButton, typeButton, isTriviButton,
	 	possRobustButton, chooseButton, lGraphBtn, uGraphBtn;
	 private String[] dims = {"1", "2", "3", "4", "5", "6", "7"};
	 private JComboBox<?> dimensions;
	 private Matrix matrix = null;
	 private int dim;
	 
	 /**
	  * The constructor of the GUI.
	  */
	public Gui(){
		
	}
	
	/**
	 * Starts the GUI - displays a new window with the given components.
	 * It uses 2 labels, one transparent for the matrices and the 
	 * other one for all the other components like buttons,labels etc.
	 */
	public void startGui(){
		frame = new JFrame("Robustnosť mongeovských matíc");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JLabel(new ImageIcon(
			"/home/matty/Desktop/bachalor.zip_expanded/Bachalor/background.jpg"));
        panel.setOpaque(true);
        panel.setLayout(null);
        
        matrixLayout = new JLabel();
        matrixLayout.setOpaque(false);
        matrixLayout.setLayout(null);
        matrixLayout.setSize(800,800);
                
		title = new JLabel("Možná a univerzálna"
				+ " robustnosť mongeovských fuzzy matíc");
		title.setFont(new Font("San-Serif", Font.BOLD, 20));
		title.setForeground(Color.white);
		title.setSize(800, 30);
		title.setLocation(60, 20);
		
		selectDimension = new JLabel("Veľkosť matice:");
		selectDimension.setForeground(Color.white);
		selectDimension.setFont(new Font("San-Serif", Font.BOLD, 16));
		selectDimension.setSize(200, 30);
		selectDimension.setLocation(50, 100);
		
		//some text to note the user about changing the values of the matrix
		enterMatrices = new JLabel(" ", SwingConstants.CENTER);
		enterMatrices.setOpaque(true);
		enterMatrices.setBackground(Color.white);
		enterMatrices.setFont(new Font("San-Serif", Font.BOLD, 16));
		enterMatrices.setSize(690, 30);
		enterMatrices.setLocation(50, 150);
		enterMatrices.setVisible(false);
		
		lowerMatrix = new JLabel("Matica dolných hodnôt:");
		lowerMatrix.setForeground(Color.white);
		lowerMatrix.setFont(new Font("San-Serif", Font.BOLD, 16));
		lowerMatrix.setSize(300, 30);
		lowerMatrix.setLocation(50, 190);	
		
		upperMatrix = new JLabel("Matica horných hodnôt:");
		upperMatrix.setForeground(Color.white);
		upperMatrix.setFont(new Font("San-Serif", Font.BOLD, 16));
		upperMatrix.setSize(300, 30);
		upperMatrix.setLocation(450, 190);
		
		chooseButton = new JButton("Zvoliť");
		chooseButton.setSize(80, 30);
		chooseButton.setLocation(260, 100);
		
		typeButton = new JButton("Overiť typ matice");
	    typeButton.setSize(240, 30);
	    typeButton.setLocation(30, 500);
				
	    mongeButton = new JButton("Zistiť mongeovskosť");
	    mongeButton.setSize(240, 30);
	    mongeButton.setLocation(30, 550);	
	    
	    isTriviButton = new JButton("Overiť trivialnosť matice");
	    isTriviButton.setSize(240, 30);
	    isTriviButton.setLocation(30, 600);
	    
	    possRobustButton = new JButton("Zistiť možnú robstnosť");
	    possRobustButton.setSize(240, 30);
	    possRobustButton.setLocation(30, 650);
	    
	    uniRobustButton = new JButton("Zistiť univerzálnu robustnosť");
	    uniRobustButton.setSize(240, 30);
	    uniRobustButton.setLocation(30, 700);
	    
	    lGraphBtn= new JButton("Ukáž digraf");
	    lGraphBtn.setSize(120, 30);
	    lGraphBtn.setLocation(270, 190);
	    lGraphBtn.setVisible(false);
	    
	    uGraphBtn= new JButton("Ukáž digraf");
	    uGraphBtn.setSize(120, 30);
	    uGraphBtn.setLocation(670, 190);
	    uGraphBtn.setVisible(false);
	    
		isGamma = new JLabel("");
		isGamma.setFont(new Font("San-Serif", Font.BOLD, 14));
		isGamma.setOpaque(true);
		isGamma.setBackground(Color.white);
		isGamma.setSize(450, 30);
		isGamma.setLocation(280, 500);
	    
		isMon = new JLabel("");
		isMon.setFont(new Font("San-Serif", Font.BOLD, 14));
		isMon.setOpaque(true);
		isMon.setBackground(Color.white);
		isMon.setSize(450, 30);
		isMon.setLocation(280, 550);
		
		isTrivi = new JLabel("");
		isTrivi.setFont(new Font("San-Serif", Font.BOLD, 14));
		isTrivi.setOpaque(true);
		isTrivi.setBackground(Color.white);
		isTrivi.setSize(450, 30);
		isTrivi.setLocation(280, 600);
		
		isPos = new JLabel("");
		isPos.setFont(new Font("San-Serif", Font.BOLD, 14));
		isPos.setOpaque(true);
		isPos.setBackground(Color.white);
		isPos.setSize(450, 30);
		isPos.setLocation(280, 650);
	    		
		isUni = new JLabel("");
		isUni.setFont(new Font("San-Serif", Font.BOLD, 14));
		isUni.setOpaque(true);
		isUni.setBackground(Color.white);
		isUni.setSize(450, 30);
		isUni.setLocation(280, 700);
	    
		dimensions  = new JComboBox<Object>(dims);
	    dimensions.setSize(50, 30);
	    dimensions.setLocation(200, 100);

	    setPanelContents();
		
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setContentPane(panel);
		
		
		
		chooseButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedDim = dimensions.getSelectedItem().toString();	
				dim = Integer.parseInt(selectedDim);				
				matrix = new Matrix(frame, dim, matrixLayout);	
				isMon.setText("");
				isUni.setText("");
				isPos.setText("");
				isTrivi.setText("");
				isGamma.setText("");
				enterMatrices.setVisible(true);
				lGraphBtn.setVisible(true);
				uGraphBtn.setVisible(true);
			}		
		});
		
		lGraphBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				matrix.initMatrices();
				new Digraph("Digraf matice dolných hodnôt",
						matrix, matrix.getLMatrix());			
			}
		});
		
		uGraphBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				matrix.initMatrices();
				new Digraph("Digraf matice horných hodnôt",
						matrix, matrix.getUMatrix());				
			}
		});
		
		typeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					isGamma.setText(matrix.isRobust(2));
				}
				catch(NullPointerException npe){
					JOptionPane.showMessageDialog(frame , 
							"Matice musia byť inicializované!","CHYBA!",JOptionPane.ERROR_MESSAGE);
				}
			}		
		});		
		
		mongeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					isMon.setText(matrix.isMonge());					
				}
				catch(NullPointerException npe){
					JOptionPane.showMessageDialog(frame , 
							"Matice musia byť inicializované!","CHYBA!",JOptionPane.ERROR_MESSAGE);
				}
			}		
		});	
		
		isTriviButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					isTrivi.setText(matrix.isRobust(3));
				}
				catch(NullPointerException npe){
					JOptionPane.showMessageDialog(frame , 
							"Matice musia byť inicializované!","CHYBA!",JOptionPane.ERROR_MESSAGE);
				}
			}	
		});
		
		possRobustButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					isPos.setText(matrix.isPosRobust());	
				}
				catch(NullPointerException npe){
					JOptionPane.showMessageDialog(frame , 
							"Matice musia byť inicializované!","CHYBA!",JOptionPane.ERROR_MESSAGE);
				}		
			}
		});
		
		uniRobustButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					isUni.setText(matrix.isUniRobust());
				}
				catch(NullPointerException npe){
					JOptionPane.showMessageDialog(frame , 
							"Matice musia byť inicializované!","CHYBA!",JOptionPane.ERROR_MESSAGE);
				}
			}		
		});				
	}

	/**
	 * Adds the labels and buttons to the main label.
	 */
	public void setPanelContents(){		
	    panel.add(title);
	    panel.add(matrixLayout);
	    panel.add(typeButton);
		panel.add(mongeButton);
		panel.add(isTriviButton);
		panel.add(chooseButton);
		panel.add(possRobustButton);
		panel.add(uniRobustButton);
		panel.add(selectDimension);
		panel.add(lGraphBtn);
		panel.add(uGraphBtn);
		panel.add(enterMatrices);
		panel.add(dimensions);
		panel.add(isGamma);
		panel.add(isMon);
		panel.add(isTrivi);
		panel.add(isPos);
		panel.add(isUni);
		panel.add(lowerMatrix);
		panel.add(upperMatrix);		
		
	}
		
}

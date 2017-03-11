
/**
 * Possible and universal robustness of interval Monge fuzzy matrices
 * 
 * At first the user has to choose the dimension of the matrix and click the *Choose* button[Zvoliť]
 * Next two matrices of given dimension will show up. One is for the lower-values 
 * the other one for upper-values.
 * When the user fills these 2 matrices they have to click the *Initialise* button [Inicializuj].
 * 
 * NOTE!
 * The input values must be binary!
 * Otherwise the user will be note to fill with binary data.
 * 
 * At the bottom of the window there are 3 buttons.
 * The first button, *Is Monge*[Je mongeovská] checks whether the matrices are Monge or not. 
 * The second and third button, *Is possibly robust*[Je možne robustná?]  and 
 * *Is universally robust*[Je univerzálne robustná?] checks the universal and possible robustness.
 * It is implemented to work only with matrices with max dimension of 7.
 * 
 * 
 * @author matthires
 *
 */


//Starts the GUI
public class Robustness {

	public static void main(String[] args) {
		Gui gui = new Gui();
		gui.startGui();		
	}

}

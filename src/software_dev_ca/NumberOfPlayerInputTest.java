package software_dev_ca;

import static org.junit.Assert.*;
import org.junit.Test;

/*testing all input related to the player
 * when the user is prompted with
 * "Enter number of players:"
 */
public class NumberOfPlayerInputTest {
	
	//testing the ideal valid input for the card game
	@Test
	public void playerValidInput() {
		String validInput = "4";
		assertEquals(4,CardGame.checkInput(validInput));
	}
	
	//testing invalid inputs for system's error catching
	/**
	 * Input must be a:
	 * positive integer
	 * greater than 0
	 * therefore not a string and not an empty input
	 */
	
	//testing with a negative input
	@Test
	public void playerNegativeInput() {
		String negativeInputNotValid = "-4";
		assertEquals(-4, CardGame.checkInput(negativeInputNotValid));
	}
	
	//testing with an input of zero players
	@Test
	public void noPlayersInput() {
		String inputZeroNotValid = "0";
		assertEquals(0,CardGame.checkInput(inputZeroNotValid));
	}	
	
	//test with a string input
	@Test
	public void playerStringInput() {
		String stringInputNotValid = "#This Is A String!";
		assertEquals(0,CardGame.checkInput(stringInputNotValid));
	}
	
	//testing with an empty input
	@Test
	public void playerEmptyInput() {
		String emptyInputNotValid = "";
		assertEquals(0,CardGame.checkInput(emptyInputNotValid));
	}
		
}
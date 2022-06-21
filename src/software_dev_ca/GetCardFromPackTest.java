package software_dev_ca;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class GetCardFromPackTest {

    @Test
    public void validPack() {
        int numberOfPlayer = 4; 
        int numberOfValidCard = numberOfPlayer*8;
        String validPackPathFor4Player = "testpack4player.txt"; 
        
        ArrayList<Card> valid4PlayerPack = CardGame.getCardFromPack(validPackPathFor4Player,numberOfValidCard);
        assertEquals("Return Whole pack",numberOfValidCard,valid4PlayerPack.size());
    }
	
	//Test the valid process of inputting 2 player but playing with a pack valid for 4 players
	@Test
	public void twoPlayersWithPack4() {
		int playerNum = 2;
		int packNum = playerNum*8;
		String validPackFor4p = "testpack4player.txt";
		
		ArrayList<Card> valid4pPackFor2p = CardGame.getCardFromPack(validPackFor4p, packNum);
		assertFalse(packNum == valid4pPackFor2p.size());
	}
	
	//Test the valid process of inputting 4 player but playing with a pack valid for 2 players
	@Test
	public void fourPlayersWithPack2() {
		int playerNum = 4;
		int packNum = playerNum*8;
		String valid2pPackPath = "testpack2player.txt";
		
		ArrayList<Card> valid2pPackFor4p = CardGame.getCardFromPack(valid2pPackPath,packNum);
		assertFalse(packNum == valid2pPackFor4p.size());
	}	
	
	//Test the invalid input of a file that does not exist on the system
	@Test
	public void missingPack() {
		int playerNum = 4;
		int packNum = playerNum*8;
		String missingFile = "this-file-doesnt-exist.txt";
	
		ArrayList<Card> missingPack = CardGame.getCardFromPack(missingFile,packNum);
		//assertTrue - jUnit boolean condition which checks whether the expected value is true or not.
		assertTrue(missingPack.isEmpty());
	}
	
	@Test
	public void packContainsString() {
		int playerNum = 2;
		int packNum = playerNum*8;
		String invalidPackContent = "testpacknotint.txt";
		
		ArrayList<Card> packWithString = CardGame.getCardFromPack(invalidPackContent,packNum);
		//assertTrue - jUnit boolean condition which checks whether the expected value is true or not.
		assertTrue(packWithString.isEmpty());
		
	}

}
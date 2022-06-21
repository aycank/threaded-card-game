package software_dev_ca;
import java.util.ArrayList;
import java.util.Scanner; 

public class CardGame{
    public static void main(String[] args) {

    	//Initialise Variables
    	ArrayList<Player> playerList = new ArrayList<>();
    	ArrayList<CardDeck> deckList = new ArrayList<>();
        boolean winnerFound = false;
    	int count = 0;
    	int playerNumber = 0;
    	String pathOfPack;        
          
    	// Scanner class for getting and handling user input
    	Scanner scanner = new Scanner(System.in);        

        //While number of players hasn't been given or is less than 1, the user will enter the number of players to start the game
        while (playerNumber < 1) {
            System.out.println("Please enter the number of players: ");
            String userInputNumber = scanner.nextLine();
            //Update variable playerNumber
            playerNumber = checkInput(userInputNumber);
        }

        int cardNumMax = playerNumber * 8;
        //Create new ArrayList cardDeck
        ArrayList<Card> cardDeck = new ArrayList<>();
        //While the size of the cardDeck array is not equal to the max number of cards, the user will enter a valid input pack to start the game
        //It will break out the loop as soon as the pack loaded has the amount of cards equal to cardNumMax
        while (cardDeck.size() != cardNumMax) {
            System.out.println("Please enter a location of pack to load: ");
            pathOfPack = scanner.nextLine();
            cardDeck = getCardFromPack(pathOfPack, cardNumMax);
        }

        //Create list of card deck, which are indexed by their position in the array of CardDeck
        for(int i = 0; i < playerNumber; i++){
        	CardDeck cd = new CardDeck(i+1);
        	deckList.add(cd);
        }
        
        //Create List of Player and assign deck to draw and deck to discard
        //Player number is indexed by position in array of Player(s).
        for(int i = 0; i < playerNumber; i++){
            try{
            	Player pl = new Player(deckList.get(i), deckList.get(i+1), i+1);
            	playerList.add(pl);     
            }catch(IndexOutOfBoundsException e){
            	Player pl2 = new Player(deckList.get(i), deckList.get(0),i+1);
            	playerList.add(pl2);
            }
        }
        
        //Player picks up card
        for(Card card : cardDeck){
            if (count > ((cardNumMax - 1 ) / 2)){
            	deckList.get(count%playerNumber).addCardToDeck(card);
            }
            else{
            	playerList.get(count%playerNumber).cardPickUp(card);
            }
            count++;
        }
        
        //For the current player in PlayerList
        for (Player currentPlayer : playerList) {
        	//Start the game
            currentPlayer.gameStartPrint();
            //if the current player is found to be a winner, set the player's current index as the index of the winning player and winnerFound is set to true
            if (currentPlayer.isWinner()) {
            	currentPlayer.setWinnercurrentPlayerIndex(currentPlayer.getcurrentPlayerIndex());
            	winnerFound = true;
            }
        }
        
        System.out.println("The Game has now started!");

        //If a winner is not found...
        if(!winnerFound) {	        
	        ArrayList<Thread> threadsPlayer = new ArrayList<>();
	        for (Player player: playerList) {
	        	Thread thr = new Thread(player);
	        	threadsPlayer.add(thr);
            }            
	        //For all players, start the thread
	        for (Thread thread : threadsPlayer) {
	            thread.start();
	        }	        
	        try {
	            for (Thread thread : threadsPlayer) {
	                thread.join();
	            }
	        //If the threading is interrupted
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
        }
        
        //Write in a text file for all details of the contents of each deck
        for (int i = 0; i <deckList.size(); i++ ) {
            String deckPath = String.format("card_deck%d_output.txt", i+1);
            FileManagement.clearFile(deckPath);
            CardDeck deck = deckList.get(i);
            FileManagement.writeFile(deck.finalDeck(), deckPath);
        }

        for (Player player: playerList) {
            if (player.getcurrentPlayerIndex() == player.getWinnercurrentPlayerIndex()) {
                player.gameEndPrint();
                System.out.println("Player " + player.getWinnercurrentPlayerIndex() + " has won the game!");
            }
            else {
                player.writeLoser();
            }
        }       
        System.out.println("The Game has now finished!");
        scanner.close();
    }

    public static int checkInput(String userInputNumber) {
		int playerNumber = 0;
		try {
			//parseInt used to convert String to Int
			playerNumber = Integer.parseInt(userInputNumber);
			//Check if the user input is greater than 0
			if (playerNumber < 1){
				System.out.println("The number of players can only be a positive integer larger than 0.");
			}
			//Else print the amount of players in the game (for confirmation)
			else {
				System.out.println("Number of players in the game: " + playerNumber);
			}
		//If the user inputs anything other than an integer, print a NumberFormatException
		}catch(NumberFormatException e){
            System.out.println("Please input an integer.");
        }
		return playerNumber;
	}
	
	public static ArrayList<Card> getCardFromPack(String pathOfPack, int cardNumMax){
		ArrayList<Card> cardDeck = FileManagement.readFile(pathOfPack);
		//If the size of the card Deck is less than the max number of cards AND the hand isn't empty, print an error message. Repeat loop: "while (cardDeck.size() != cardNumMax)" until it is satisfied
        if(cardDeck.size() < cardNumMax && cardDeck.size() != 0) {
        	System.out.println("There isn't enough cards to start the game. Please enter a different pack.");
        //If the size of the Card deck is greater than the max number of cards, print an error message. Repeat loop: "while (cardDeck.size() != cardNumMax)" until it is satisfied
        }else if(cardDeck.size() > cardNumMax) {
        	System.out.println("There are too many cards in this pack. Please enter a different pack.");
        }
        return cardDeck;
	}
}
package software_dev_ca;
import java.util.ArrayList;

public class Player implements Runnable {

	//create arrayList hand with Card Class
	private ArrayList <Card> hand = new ArrayList<>();
    private Boolean turnRun = true;
    private CardDeck leftDeck;
    private CardDeck rightDeck;
	private int currentPlayerIndex;
    private int turnNum = 0;
    private static int gameWinner;
    private static volatile Boolean foundWinner = false;
    private String pathOfPlayer;
    private volatile static int turnNumMax = 0;

    public Player(CardDeck rightDeck, CardDeck leftDeck, int currentPlayerIndex){
    	/*
    	 * each player picks a card from the top of the	deck to their left,
    	 * and discards	one to the bottom of the deck to their right.
    	 */
        this.rightDeck = rightDeck; //Player removes from bottom to their right
    	this.leftDeck = leftDeck; //Player picks card from top
        this.currentPlayerIndex = currentPlayerIndex; //Current player's assigned number
        /*
         * This method is to output the current player's game log to a temporary file
         */
        String currentPlayPath = String.format("player%d_output.txt",currentPlayerIndex);
        FileManagement.clearFile(currentPlayPath);
        this.pathOfPlayer = currentPlayPath;
    }
    
    //Player picks card from top of the left deck
    public void cardPickUp(Card card){
        this.hand.add(card);
    }
    
    //Boolean check for player's winning hand
    public synchronized Boolean isWinner(){
        int checkDenomination = this.hand.get(0).getDenomination();
        //the first player declares	that they have four	cards of the same value wins
        for(int i = 1; i < hand.size(); i++){
            if(this.hand.get(i).getDenomination() != checkDenomination){
                return false;
            }
        }
        return true;
    }

    /*
     * player will discard one of their cards to the deck on their right
     * Each	player will	prefer certain card	denominations, 
     * which reflect their index value (currentPlayerIndex)
     */
    public Card cardRemove(){
        for (int i = 0; i < 5; i++){
            Card removeCard = hand.get(i);
            /*
             * prefer denominations reflecting the player's index
             * therefore if this is not the case 
             * choose the card that is not preferential to be removed
             */
            if(removeCard.getDenomination() != currentPlayerIndex){
                hand.remove(i);
                return removeCard;
            }
        }
        //If all cards have the preferred denominations
        Card removeCard = hand.get(0);
        //remove the first card in hand
        hand.remove(0);
        return removeCard;
    }
  
    //Will run the player's turn
    public void run(){
    	while (turnRun) {
    		try {
    			//If the amount of turns has been reached AND a winner has been found, end the turn
    			if (foundWinner && turnNum == turnNumMax) {
                        turnRun = false;
    			}
                Card drawCard = leftDeck.drawTopCard();
                cardPickUp(drawCard);
                Card removeCard = cardRemove();
                rightDeck.addCardToBottomDeck(removeCard);
                turnNum += 1;
                //If the number of turns exceed the max number of turns AND a winner isn't found, the max turns will increase to the number of turns
                if (turnNum > turnNumMax && !foundWinner) {
                	turnNumMax = turnNum;
                }
                //If a winner is found, the winner will update from the current player index and foundWinner will be updated to true
                if (!foundWinner && isWinner()) {
                	gameWinner = currentPlayerIndex;
                	foundWinner = true;
                }
                String turnText = String.format("Player %d draws a %d from deck %d"
                		+ "\nPlayer %d removes a %d to deck %d",
                		currentPlayerIndex,
                		drawCard.getDenomination(),
                		leftDeck.getDeckNum(),
                		currentPlayerIndex,
                		removeCard.getDenomination(),
                		rightDeck.getDeckNum());
                FileManagement.writeFile(turnText, this.pathOfPlayer);
                FileManagement.writeFile(printHand(), this.pathOfPlayer);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    //Function to print current hand of the specific player 
    public synchronized String printHand() {
        return String.format("Player %d's current hand is %d %d %d %d",
        		currentPlayerIndex,
        		hand.get(0).getDenomination(),
        		hand.get(1).getDenomination(),
        		hand.get(2).getDenomination(),
        		hand.get(3).getDenomination());
    }
    
    //return player's index number
    public int getcurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    //return the index number of the winning player
    public int getWinnercurrentPlayerIndex() {
        return gameWinner;
    }

    //Set the winning player's index if they won with the initial hand
    public void setWinnercurrentPlayerIndex(int i) {
    	gameWinner = i;
    }
    
    //Print out and write in a text file of each player's first hand
    public void gameStartPrint(){        
        FileManagement.writeFile(String.format("Player %d's first hand is %d %d %d %d",
        		currentPlayerIndex,
        		hand.get(0).getDenomination(),
        		hand.get(1).getDenomination(),
        		hand.get(2).getDenomination(),
        		hand.get(3).getDenomination()),
        		pathOfPlayer);
    }
    
    //Print out and write the details (in a text file) of the player that won the game
    public void gameEndPrint() {
    	String text = String.format("Player %d wins\nPlayer %d leaves\nTheir final hand: %d %d %d %d",
    			gameWinner,gameWinner,
    			hand.get(0).getDenomination(),
    			hand.get(1).getDenomination(),
    			hand.get(2).getDenomination(),
    			hand.get(3).getDenomination());
    	FileManagement.writeFile(text,pathOfPlayer);
    }
    
    //Print out and write the details (in a text file) of the player/s that did not win the game
    public void writeLoser() {
        String text = String.format("Player %d has let Player %d know that they have won"
        		+ "\nPlayer %d leaves\nTheir final hand: %d %d %d %d",
        		gameWinner, currentPlayerIndex, currentPlayerIndex,
        		hand.get(0).getDenomination(),
        		hand.get(1).getDenomination(),
        		hand.get(2).getDenomination(),
        		hand.get(3).getDenomination());
        FileManagement.writeFile(text,pathOfPlayer);
    }
}
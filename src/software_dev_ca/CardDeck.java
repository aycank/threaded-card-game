package software_dev_ca;
import java.util.ArrayList;

public class CardDeck
{
	//Use ArrayList (index 0 is the top card of the deck, whilst the final index is the bottom of the card deck)
    private ArrayList<Card> cardList;
    private int deckNum;
    private int deckSize;
 
    //Add and return the final index (bottom) of the card deck list
    public synchronized void addCardToBottomDeck(Card card) throws InterruptedException {
    	cardList.add(card);
        this.deckSize += 1;
    }
    
    //Add card to deck.   
    public void addCardToDeck(Card card){
        this.cardList.add(card);
        this.deckSize += 1;
    }
    
    public CardDeck(int deckNum){
        this.cardList = new ArrayList<>();
        this.deckNum = deckNum;
        this.deckSize = 0;
    }

    //Remove and return top card.
    public synchronized Card drawTopCard() throws InterruptedException {
        Card drawCard = cardList.get(0);
        cardList.remove(0);
        this.deckSize -= 1;
        return drawCard;
    }
    //Returns the formatted string of the list of card deck
    public String finalDeck() {
        Card cardOne = cardList.get(0);
        Card cardTwo = cardList.get(1);
        Card cardThree = cardList.get(2);
        Card cardFour = cardList.get(3);
        return String.format("The list of card deck %d contains: %d %d %d %d",
        		deckNum,
        		cardOne.getDenomination(),
        		cardTwo.getDenomination(),
        		cardThree.getDenomination(),
        		cardFour.getDenomination());
    }
   //Get card deck index
    public int getDeckNum() {
        return deckNum;
    }
    //Get card deck size
    public int getSize() {
        return this.deckSize;
    }

}
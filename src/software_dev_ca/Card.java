package software_dev_ca;

//The Card class is represented by the Card object.
public class Card{
	
	private int cardNumber;
	private int cardDenomination;    
    
	//Shows card value is an attribute of Card object
    public Card (int cardDenomination){
        this.cardDenomination = cardDenomination;
    }    
    //Get the value of the card and return it
    public int getDenomination(){ 
        return this.cardDenomination;
    }
    public int getCardNumber(){ 
        return this.cardNumber;
    }

}

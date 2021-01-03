package poker;
/**
 * @author Liv Nguekap
 */
public class Card {
    private char value = 0;
    private char suit = 0;
    private static final String VALUES_WORTH = "23456789TJQKA";
    private static final String SUITS_WORTH = "CDHS";
    /***************************************************************************
     * 
    ***************************************************************************/
    public Card(){
        this.value = 0;
        this.suit = 0;
    }
    public Card(char v, char s){
        this.value = v;
        this.suit = s;
    }
    /***************************************************************************
     * 
    ***************************************************************************/
    public void setValue(char v){
        this.value = v;
    }
    public void setSuit(char s){
        this.suit = s;
    }
    /***************************************************************************
     * 
    ***************************************************************************/
    public char getValue(){
        return this.value;
    }
    public char getSuit(){
        return this.suit;
    }
    /***************************************************************************
     * 
    ***************************************************************************/
    public boolean isSameValueAs(Card c){
        char myValue = this.value;
        char cardValue = c.getValue();
        if(myValue == cardValue){
            return true;
        }else{
            return false;
        }
    }
    public boolean isSameSuitAs(Card c){
        char mySuit = this.suit;
        char cardSuit = c.getSuit();
        if(mySuit == cardSuit){
            return true;
        }else{
            return false;
        }
    }
    public boolean isSameCardAs(Card c){
        if((this.isSameValueAs(c) == true) && (this.isSameSuitAs(c) == true)){
            return true;
        }else{
            return false;
        }
    }
    public boolean isHigherThan(Card c){
        char myValue = this.value;
        char cardValue = c.getValue();
        if(VALUES_WORTH.indexOf(myValue) > VALUES_WORTH.indexOf(cardValue)){
            return true;
        }else{
            return false;
        }
    }
    public boolean isLowerThan(Card c){
        char myValue = this.value;
        char cardValue = c.getValue();
        if(VALUES_WORTH.indexOf(myValue) < VALUES_WORTH.indexOf(cardValue)){
            return true;
        }else{
            return false;
        }
    }
    public boolean follows(Card c){
        char myValue = this.value;
        char cardValue = c.getValue();
        if(VALUES_WORTH.indexOf(myValue) == (VALUES_WORTH.indexOf(cardValue) + 1)){
            return true;
        }else{
            return false;
        }
    }
    public boolean checkIfValidCardValue(){
        char myValue = this.getValue();
        if(VALUES_WORTH.indexOf(myValue) >= 0){
            return true;
        }else{
            return false;
        }
    }
    public boolean checkIfValidCardSuit(){
        char mySuit = this.getSuit();
        if(SUITS_WORTH.indexOf(mySuit) >= 0){
            return true;
        }else{
            return false;
        }
    }
}
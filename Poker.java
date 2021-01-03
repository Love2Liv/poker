package poker;
/**
 * @author Liv Nguekap
 */
public class Poker {
    public static void main(String[] args) {
        Player player1 = new Player();
        Player player2 = new Player();
        try{
            String player1Name = removeColon(args[0]);
            String player2Name = removeColon(args[6]);
            String[] firstPlayerInput = {args[1], args[2], args[3], args[4], args[5]};
            String[] secondPlayerInput = {args[7], args[8], args[9], args[10], args[11]};
            if(!checkDuplicateCards(firstPlayerInput, secondPlayerInput)){
                Card[] firstPlayerHand;
                Card[] secondPlayerHand;
                firstPlayerHand = createPlayersHand(firstPlayerInput);
                if(firstPlayerHand.length == 5){
                    player1 = new Player(player1Name, firstPlayerHand[0], firstPlayerHand[1], firstPlayerHand[2], firstPlayerHand[3], 
                                            firstPlayerHand[4]);
                }else{
                    System.out.println("Wait a minute. "+ player1Name +" did not receive the right amount of cards.");
                    System.exit(-1);
                }
                secondPlayerHand = createPlayersHand(secondPlayerInput);
                if(firstPlayerHand.length == 5){
                    player2 = new Player(player2Name, secondPlayerHand[0], secondPlayerHand[1], secondPlayerHand[2], secondPlayerHand[3], 
                                            secondPlayerHand[4]);
                }else{
                    System.out.println("Wait a minute. "+ player2Name +" did not receive the right amount of cards.");
                    System.exit(-1);
                }
                player1.playsHand();
                player2.playsHand();
                player1.compareHandsWith(player2);
            }else{
                System.exit(-1);
            }
        }catch(ArrayIndexOutOfBoundsException aiobe){
            System.out.println("Hold on, partner!! You didn't pass the necessary arguments to the program.");
            System.exit(-1);
        }
    }
    /***************************************************************************
     * 
    ***************************************************************************/
    public static String removeColon(String str){
        int colonIndex = str.lastIndexOf(':');
        if(colonIndex > 0){
            String newStr = str.substring(0, colonIndex);
            return newStr;
        }else{
            return str;
        }
    }
    public static boolean checkDuplicateCards(String[] hand1, String[] hand2){
        String rawCard1;
        String rawCard2;
        if(checkHandForDuplicates(hand1)){
            return true;
        }
        if(checkHandForDuplicates(hand2)){
            return true;
        }
        for(int i = 0; i < hand1.length; i++){
            rawCard1 = hand1[i];
            for(int j = 0; j < hand2.length; j++){
                rawCard2 = hand2[j];
                if(rawCard1.equalsIgnoreCase(rawCard2)){
                    System.out.println("Whoa!! There seems to be a problem with your deck of cards. The card '"+rawCard2
                            +"' is duplicated.");
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean checkHandForDuplicates(String[] hand){
        String rawCard1;
        String rawCard2;
        for(int i = 0; i < hand.length; i++){
            rawCard1 = hand[i];
            for(int j = i+1; j < hand.length; j++){
                rawCard2 = hand[j];
                if(rawCard1.equalsIgnoreCase(rawCard2)){
                    System.out.println("Whoa!! There seems to be a problem with your deck of cards. The card '"+rawCard2
                            +"' is duplicated.");
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean validateCard(Card c){
        if(c.checkIfValidCardValue()){
            if(c.checkIfValidCardSuit()){
                return true;
            }else{
                System.out.println("Sorry, but this suit of cards '"+ c.getValue()+c.getSuit() +"' does not exist.");
                return false;
            }
        }else{
            System.out.println("Sorry, but the card '"+ c.getValue()+c.getSuit() +"' does not exist.");
            return false;
        }
    }
    public static Card[] createPlayersHand(String[] hand){
        char value = ' ';
        char suit = ' ';
        char[] myCard;
        Card[] playersHand = new Card[5];
        for(int i = 0; i < hand.length; i++){
            myCard = hand[i].toCharArray();
            value = Character.toUpperCase(myCard[0]);
            suit = Character.toUpperCase(myCard[1]);
            playersHand[i] = new Card(value, suit);
            if(!validateCard(playersHand[i])){
                System.exit(-1);
            }
        }
        return playersHand;
    }
}
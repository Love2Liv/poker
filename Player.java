package poker;
/**
 * @author Liv Nguekap
 */
public class Player {
    private String name = "";
    private Card[] myHand = new Card[5];
    private String winningPlay = "";
    /***************************************************************************
     * 
    ***************************************************************************/
    public Player(){
    
    }
    public Player(String name, Card c1, Card c2, Card c3, Card c4, Card c5){
        this.name = name;
        this.myHand[0] = c1;
        this.myHand[1] = c2;
        this.myHand[2] = c3;
        this.myHand[3] = c4;
        this.myHand[4] = c5;
    }
    /***************************************************************************
     * 
    ***************************************************************************/
    public String getName(){
        return this.name;
    }
    public Card getHighestCard(){
        return this.myHand[4];
    }
    public String getWinningPlay(){
        return this.winningPlay;
    }
    public Card[] getHand(){
        return this.myHand;
    }
    public int getWinningPlayValue(){
        String wp = this.winningPlay;
        int wpValue = 0;
        if(wp.equals("NOTHING")){
            wpValue = 1;
        }else if(wp.equals("A Pair")){
            wpValue = 2;
        }else if(wp.equals("Two Pairs")){
            wpValue = 3;
        }else if(wp.equals("Three Of A Kind")){
            wpValue = 4;
        }else if(wp.equals("Straight Hand")){
            wpValue = 5;
        }else if(wp.equals("Flush")){
            wpValue = 6;
        }else if(wp.equals("Full House")){
            wpValue = 7;
        }else if(wp.equals("Four Of A Kind")){
            wpValue = 8;
        }else if(wp.equals("Straight Flush")){
            wpValue = 9;
        }
        return wpValue;
    }
    /***************************************************************************
     * 
    ***************************************************************************/
    public void playsHand(){
        this.sortPlayerHand();
        if(this.isStraightFlush() == true){
            this.winningPlay = "Straight Flush";
        }else if(this.isFourOfAKind() == true){
            this.winningPlay = "Four Of A Kind";
        }else if(this.isFullHouse() == true){
            this.winningPlay = "Full House";
        }else if(this.isFlush() == true){
            this.winningPlay = "Flush";
        }else if(this.isStraightHand() == true){
            this.winningPlay = "Straight Hand";
        }else if(this.isThreeOfAKind() == true){
            this.winningPlay = "Three Of A Kind";
        }else if(this.isTwoPairs() == true){
            this.winningPlay = "Two Pairs";
        }else if(this.isAPair() == true){
            this.winningPlay = "A Pair";
        }else{
            this.winningPlay = "NOTHING";
        }
    }
    public boolean isStraightFlush(){
        if(this.checkIfSameSuit() == true){
            return this.checkIfConsecutive();
        }else{
            return false;
        }
    }
    public boolean isFourOfAKind(){
        String[] sameCards = this.countNumberOfSameValues();
        if(!sameCards[0].equals("0") && !sameCards[1].equals("0")){
            int numCardsOfSameValue = Integer.parseInt(sameCards[0]);
            if(numCardsOfSameValue == 4){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public boolean isFullHouse(){
        String[] sameCards = this.countNumberOfSameValues();
        if(!sameCards[0].equals("0") && !sameCards[1].equals("0")){
            int numCardsOfSameValue = Integer.parseInt(sameCards[0]);
            String valueOfSameCards = sameCards[1];
            if(numCardsOfSameValue == 3){
                String[] cardPairs = this.countNumberOfPairs();
                if(!cardPairs[0].equals("0") && !cardPairs[1].equals("0") && !cardPairs[2].equals("0")){
                    int numPairCards = Integer.parseInt(cardPairs[0]);
                    String valueOfPairCards1 = cardPairs[1];
                    String valueOfPairCards2 = cardPairs[2];
                    if(numPairCards >= 1){
                        if(!valueOfPairCards1.equals(valueOfSameCards)){
                            return true;
                        }else if(!valueOfPairCards2.equals(valueOfSameCards)){
                            return true;
                        }else{
                            return false;
                        }
                    }else{return false;}
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public boolean isFlush(){
        if(this.checkIfSameSuit() == true){
            return true;
        }else{
            return false;
        }
    }
    public boolean isStraightHand(){
        if(this.checkIfConsecutive() == true){
            return true;
        }else{
            return false;
        }
    }
    public boolean isThreeOfAKind(){
        String[] sameCards = this.countNumberOfSameValues();
        if(!sameCards[0].equals("0") && !sameCards[1].equals("0")){
            int numCardsOfSameValue = Integer.parseInt(sameCards[0]);
            if(numCardsOfSameValue == 3){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public boolean isTwoPairs(){
        String[] cardPairs = this.countNumberOfPairs();
        if(!cardPairs[0].equals("0") && !cardPairs[1].equals("0") && !cardPairs[2].equals("0")){
            return true;
        }else{
            return false;
        }
    }
    public boolean isAPair(){
        String[] cardPairs = this.countNumberOfPairs();
        if(!cardPairs[0].equals("0") && !cardPairs[1].equals("0")){
            return true;
        }else{
            return false;
        }
    }
    public String[] countNumberOfSameValues(){
        Card[] h = this.myHand;
        int sameFound = 1;
        int max = 1;
        String cardValue;
        String[] returnArray = {"0", "0", "0", "0", "0", "0", "0"};
        for(int i = 0; i < h.length - 1; i++){
            if(h[i].isSameValueAs(h[i+1])){
                sameFound++;
                if(sameFound > max){
                    max += 1;
                    cardValue = h[i].getValue()+"";
                    returnArray[0] = Integer.toString(max);
                    returnArray[1] = cardValue;
                }
            }else{
                sameFound = 1;
            }
        }
        return returnArray;
    }
    public String[] countNumberOfPairs(){
        Card[] h = this.myHand;
        Card lastCard = new Card();
        int pairFound = 0;
        String cardValue;
        String[] returnArray = {"0", "0", "0", "0", "0", "0", "0", "0"};
        int returnValueIndex = 1;
        for(int i = 0; i < h.length - 1; i++){
            if(h[i].isSameValueAs(h[i + 1])){
                if(!h[i].isSameCardAs(lastCard)){
                    pairFound++;
                    lastCard = h[i + 1];
                    cardValue = h[i].getValue() + "";
                    returnArray[0] = Integer.toString(pairFound);
                    returnArray[returnValueIndex++] = cardValue;
                }
            }
            String pairValueFound1, pairValueFound2;
            String arrayValue;
            int unpairedIndex = 3;
            for(int k = 0; k < h.length; k++){
                pairValueFound1 = returnArray[1];
                pairValueFound2 = returnArray[2];
                arrayValue = h[k].getValue() + "";
                if((!arrayValue.equals(pairValueFound1) && !pairValueFound1.equals("0")) || 
                        (!arrayValue.equals(pairValueFound2) && !pairValueFound2.equals("0"))){
                    returnArray[unpairedIndex++] = arrayValue;
                }
            }
        }
        return returnArray;
    }
    public boolean checkIfSameSuit(){
        Card[] h = this.myHand;
        if(h[0].isSameSuitAs(h[1]) && h[1].isSameSuitAs(h[2]) && h[2].isSameSuitAs(h[3]) && h[3].isSameSuitAs(h[4])){
            return true;
        }else{
            return false;
        }
    }
    public boolean checkIfConsecutive(){
        Card[] h = this.myHand;
        if(h[4].follows(h[3]) && h[3].follows(h[2]) && h[2].follows(h[1]) && h[1].follows(h[0])){
            return true;
        }else{
            return false;
        }
    }
    public void sortPlayerHand(){
        Card[] h = this.myHand;
        for(int x = 0; x < h.length; x++){
            for(int y = 0; y < h.length - x - 1; y++){
                if(h[y].isHigherThan(h[y + 1])){
                    Card temp = h[y];
                    h[y] = h[y + 1];
                    h[y + 1] = temp;
                }
            }
        }
        this.myHand = h;
    }
    public String[] highCardDecision(Card c1, Card c2){
        String[] myReturn = new String[2];
        if(c1.isHigherThan(c2)){
            myReturn[0] = "1";
            myReturn[1] = c1.getValue() + "";
            return myReturn;
        }else if(c1.isLowerThan(c2)){
            myReturn[0] = "2";
            myReturn[1] = c2.getValue() + "";
            return myReturn;
        }else{
            myReturn[0] = "0";
            return myReturn;
        }
    }
    public String[] highCardHandDecision(Player p2){
        Card[] p1h = this.getHand();
        Card[] p2h = p2.getHand();
        Card c1;
        Card c2;
        String[] myReturn = new String[2];
        for(int x = 4; x >= 0; x--){
            c1 = p1h[x];
            c2 = p2h[x];
            String[] result = this.highCardDecision(c1, c2);
            int res = Integer.parseInt(result[0]);
            if(res != 0){
                return result;
            }
        }
        myReturn[0] = "0";
        return myReturn;
    }
    public String[] highPairDecision(Player p2){
        String[] handPairs1 = this.countNumberOfPairs();
        String[] handPairs2 = p2.countNumberOfPairs();
        String[] myReturn = new String[2];
        Card myCard1 = new Card();
        Card myCard2 = new Card();
        if(!handPairs1[0].equals("0") && !handPairs1[1].equals("0") && !handPairs2[0].equals("0")
               && !handPairs2[1].equals("0")){
            myCard1.setValue(handPairs1[2].charAt(0));
            myCard2.setValue(handPairs2[2].charAt(0));
            String[] result = this.highCardDecision(myCard1, myCard2);
            int res = Integer.parseInt(result[0]);
            if(res == 0){
                if(!handPairs1[2].equals("0") && !handPairs2[2].equals("0")){
                    myCard1.setValue(handPairs1[1].charAt(0));
                    myCard2.setValue(handPairs2[1].charAt(0));
                    result = this.highCardDecision(myCard1, myCard2);
                    res = Integer.parseInt(result[0]);
                    if(res == 0){
                        myCard1.setValue(handPairs1[3].charAt(0));
                        myCard2.setValue(handPairs2[3].charAt(0));
                        result = this.highCardDecision(myCard1, myCard2);
                        return result;
                    }else{
                        return result;
                    }
                }else{
                    res = 0;
                    for(int x = 5; x > 2; x--){
                        myCard1.setValue(handPairs1[x].charAt(0));
                        myCard2.setValue(handPairs2[x].charAt(0));
                        result = this.highCardDecision(myCard1, myCard2);
                        res = Integer.parseInt(result[0]);
                        if(res != 0){
                            return result;
                        }
                    }
                    result[0] = "0";
                    return result;
                }
            }else{return result;}
        }
        myReturn[0] = "-1";
        return myReturn;
    }
    public String[] threeOfAKindDecision(Player p2){
        String[] sameCards1 = this.countNumberOfSameValues();
        String[] sameCards2 = p2.countNumberOfSameValues();
        String[] myReturn = new String[2];
        Card myCard1 = new Card();
        Card myCard2 = new Card();
        if(!sameCards1[0].equals("0") && !sameCards1[1].equals("0") && !sameCards2[0].equals("0")
               && !sameCards2[1].equals("0")){
            myCard1.setValue(sameCards1[1].charAt(0));
            myCard2.setValue(sameCards2[1].charAt(0));
            String[] result = this.highCardDecision(myCard1, myCard2);
            return result;
        }
        myReturn[0] = "-1";
        return myReturn;
    }
    public String[] straightHandDecision(Player p2){
        Card sameCards1 = this.getHighestCard();
        Card sameCards2 = p2.getHighestCard();
        String[] result = this.highCardDecision(sameCards1, sameCards2);
        return result;
    }
    public String[] flushDecision(Player p2){
        Card[] p1h = this.getHand();
        Card[] p2h = p2.getHand();
        Card c1;
        Card c2;
        String[] myReturn = new String[2];
        for(int x = 4; x >= 0; x--){
            c1 = p1h[x];
            c2 = p2h[x];
            String[] result = this.highCardDecision(c1, c2);
            int res = Integer.parseInt(result[0]);
            if(res != 0){
                return result;
            }
        }
        myReturn[0] = "0";
        return myReturn;
    }
    public String[] fullHouseDecision(Player p2){
        String[] result = this.threeOfAKindDecision(p2);
        return result;
    }
    public String[] fourOfAKindDecision(Player p2){
        String[] result = this.threeOfAKindDecision(p2);
        return result;
    }
    public String[] straightFlushDecision(Player p2){
        String[] result = this.straightHandDecision(p2);
        return result;
    }
    public String compareHandsWith(Player p2){
        int wpv1 = this.getWinningPlayValue();
        int wpv2 = p2.getWinningPlayValue();
        String[] comparisonResult = new String[2];
        String winningMethod = "";
        if(wpv1 > wpv2){
            winningMethod = this.getWinningPlay();
            this.displayResult(p2, 1, 1, winningMethod);
        }else if(wpv1 < wpv2){
            winningMethod = p2.getWinningPlay();
            this.displayResult(p2, 2, 1, winningMethod);
        }else{
            if(wpv1 == 1){
                comparisonResult = this.highCardHandDecision(p2);
            }else if(wpv1 == 2){
                comparisonResult = this.highPairDecision(p2);
            }else if(wpv1 == 3){
                comparisonResult = this.highPairDecision(p2);
            }else if(wpv1 == 4){
                comparisonResult = this.threeOfAKindDecision(p2);
            }else if(wpv1 == 5){
                comparisonResult = this.straightHandDecision(p2);
            }else if(wpv1 == 6){
                comparisonResult = this.flushDecision(p2);
            }else if(wpv1 == 7){
                comparisonResult = this.fullHouseDecision(p2);
            }else if(wpv1 == 8){
                comparisonResult = this.fourOfAKindDecision(p2);
            }else if(wpv1 == 9){
                comparisonResult = this.straightFlushDecision(p2);
            }
            int cr = Integer.parseInt(comparisonResult[0]);
            winningMethod = comparisonResult[1];
            this.displayResult(p2, cr, 2, winningMethod);
        }
        return "";
    }
    public void displayResult(Player p2, int winner, int winType, String cardValue){
        String p1Name = this.getName();
        String p2Name = p2.getName();
        String p1Plays = this.getWinningPlay();
        String p2Plays = p2.getWinningPlay();
        String p1Cards = this.displayCards();
        String p2Cards = p2.displayCards();
        String resultsTitle = "";
        String resultsAnnouncement = "";
        if(winType == 1){
            if(winner == 1){
                resultsTitle = "WINNER!!";
                resultsAnnouncement = p1Name + " wins, with " + p1Plays;
            }else if(winner == 2){
                resultsTitle = "WINNER!!";
                resultsAnnouncement = p2Name + " wins, with " + cardValue + ".";
            }else{
                resultsTitle = "DRAW";
                resultsAnnouncement = "The game is a tie.";
            }
        }else{
            if(cardValue != null && cardValue.equalsIgnoreCase("A")){
                cardValue = "Ace";
            }
            if(winner == 1){
                resultsTitle = "WINNER!!";
                resultsAnnouncement = p1Name + " wins, with high card " + cardValue + ".";
            }else if(winner == 2){
                resultsTitle = "WINNER!!";
                resultsAnnouncement = p2Name + " wins, with high card " + cardValue + ".";
            }else{
                resultsTitle = "DRAW";
                resultsAnnouncement = "The game is a tie.";
            }
        }
        System.out.println();
        System.out.printf("|==============================================================|%n");
        System.out.printf("|                        POKER GAME                            |%n");
        System.out.printf("|==============================================================|%n");
        System.out.printf("|                                                              |%n");
        System.out.printf("|  %20s plays: %16s                |%n", p1Name, p1Plays);
        System.out.printf("|                      %20s                    |%n", p1Cards);
        System.out.printf("|--------------------------------------------------------------|%n");
        System.out.printf("|                                                              |%n");
        System.out.printf("|  %20s plays: %16s                |%n", p2Name, p2Plays);
        System.out.printf("|                      %20s                    |%n", p2Cards);
        System.out.printf("|--------------------------------------------------------------|%n");
        System.out.printf("|                          RESULTS                             |%n");
        System.out.printf("|--------------------------------------------------------------|%n");
        System.out.printf("|                        %10s                            |%n", resultsTitle);
        System.out.printf("|       %40s               |%n", resultsAnnouncement);
        System.out.printf("|                                                              |%n");
        System.out.printf("|==============================================================|%n");
    }
    public String displayCards(){
        Card[] c = this.getHand();
        String str = "";
        str = "("+c[0].getValue()+c[0].getSuit()+ ", " + c[1].getValue()+c[1].getSuit() + ", " 
                + c[2].getValue()+c[2].getSuit() + ", " + c[3].getValue()+c[3].getSuit() + ", "
                + c[4].getValue()+c[4].getSuit()+ ")";
        return str;
    }
}
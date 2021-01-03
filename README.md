# poker
Java program to compare two poker hands and identify the winning hand.

The program is set to run from the command line.

Sample input:

Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH

In the line above, "Black" identifies the name of the first player and "White" the name of the second player. The letters H, D, S, and C stand for Hearts, Diamonds, Spade, and Clubs, respectively. Therefore, 2H = 2 of Hearts. 3d = 3 of Diamonds. AH = Ace of Hears. And so on...

As you can see from the sample input, each player is given five cards.

And here's a sample output:

White wins. - with flush

Black wins. - with high card: 9

Tie.

This program contains three classes:

Card.java
Player.java
Poker.java
The three classes are part of the 'poker' package.

To run the program, first compile the three classes, then run Poker class, which contains the 'main' method, with the arguments required.

Example: C:>java poker.Poker Black 2H 3D 5S 9C KD White 2D 3H 5C 9S KH

That is all!!

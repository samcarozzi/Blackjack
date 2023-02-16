import java.util.Scanner;
public class Blackjack {
    //helper to convert cards to string
    public static String cardToString(int card){
        if(card == 1)
            return "ACE";
        else if(card == 11)
            return "JACK";
        else if(card == 12)
            return "QUEEN";
        else if(card == 13)
            return "KING";
        else
            return Integer.toString(card);
    }
    //helper to convert card vals>10 for hand use
    public static int cardVal(int card){
        if(card >10 && card<14)
            return card-(card-10);
        else
            return card;
    }

    public static void main(String[] args) {
        //declare init. vars
        Scanner in = new Scanner(System.in);
        int selection = 0;
        int gameCount = 0;
        int gamesWon = 0;
        double gamePercentage = 0;
        int tieCount = 0;
        P1Random num = new P1Random();
        //external loop to end game
        while (selection != 4) {
            boolean w = false;
            int hand = 0;
            int dealerHand = 0;
            int myCardAdd = 0;
            System.out.println("START GAME #" + (gameCount + 1) + "\n");
            myCardAdd = num.nextInt(13) + 1;
            hand += cardVal(myCardAdd);
            int cardVal = 0;
            System.out.println("Your card is a " + cardToString(myCardAdd) + "!\n" +
                    "Your hand is: " + hand + "\n");
            //internal loop to present options
            while (!w) {
                System.out.println(
                        "1. Get another card\n" +
                        "2. Hold hand\n" +
                        "3. Print statistics\n" +
                        "4. Exit");

                System.out.println("\nChoose an option:");
                selection = in.nextInt();
                //begin choices to determine what to do with the loop
                //another card to player
                if (selection == 1) {
                    myCardAdd = num.nextInt(13) + 1;
                    hand += cardVal(myCardAdd);
                    System.out.println("Your card is a " + cardToString(myCardAdd) + "!\n" +
                            "Your hand is: " + hand + "\n");
                    if (hand == 21) {
                        System.out.println("BLACKJACK! You win!\n");
                        gamesWon ++;
                        w=true;
                    } else if (hand > 21) {
                        System.out.println("You exceeded 21! You lose.\n");
                        w=true;
                    }
                }
                //another card to dealer, player holds
                else if (selection == 2) {
                    dealerHand = num.nextInt(11) + 16;
                    System.out.println("Dealer's hand: " + cardToString(dealerHand) +
                            "\nYour hand is: " + hand + "\n");
                    if (hand == dealerHand) {
                        System.out.println("It's a tie! No one wins!\n");
                        tieCount++;
                        w = true;
                    }
                    //determines where user wins
                    else if (hand == 21 || dealerHand > 21) {
                        System.out.println("You win!\n");
                        gamesWon++;
                        w = true;
                    }
                    else if (hand < 21 && dealerHand < 21) {
                        if (21 - hand < 21 - dealerHand) {
                            System.out.println("You win!\n");
                            gamesWon++;
                            w = true;
                        }
                        //determines where dealer wins
                        else {
                            System.out.println("Dealer wins!\n");
                            w = true;
                        }
                    }
                    else {
                        System.out.println("Dealer wins!\n");
                        w = true;
                    }
                }
                //print stats
                else if (selection == 3) {
                    if (gameCount == 0)
                        gamePercentage = 0;
                    else
                        gamePercentage = (double)Math.round(((double)gamesWon / gameCount)*1000)/10;
                    System.out.println("Number of Player wins: " + gamesWon +
                            "\nNumber of Dealer wins: " + (gameCount - gamesWon - tieCount) +
                            "\nNumber of tie games: " + tieCount +
                            "\nTotal # of games played is: " + gameCount +
                            "\nPercentage of Player wins: " + gamePercentage + "%\n");
                    }
                //show errors
                else if (selection != 4) {
                    System.out.println("Invalid input!\n" +
                            "Please enter an integer value between 1 and 4.\n");
                }
                else {
                    w = true;
                }
            }
            gameCount++;
        }
    }
}


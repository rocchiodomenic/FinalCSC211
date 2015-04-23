import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TexasHoldEm {

    public static void main(String[] args) throws Exception {
        // variables
        Deck holdemDeck = new Deck();
        int numPlayers = 2;
        int cardCounter = 0;
        int burnCounter = 0;
        int boardCounter = 0;
        Board board = new Board();

        // initializations      
        numPlayers = 2;
        Player[] player = new Player[numPlayers];

        /* 3 shuffles just like in real life. */
        for(int i=0;i<3;i++){
            holdemDeck.shuffle();
        }

        // Cut Deck
        holdemDeck.cutDeck();

        // Initialize players
        for (int i=0;i<numPlayers;i++){
            player[i] = new Player();
        }

        // Main processing
        // Deal hole cards to players
        for (int i=0;i<2;i++){
            for (int j=0;j<numPlayers;j++){
                player[j].setCard(holdemDeck.getCard(cardCounter++), i);
            }
        }

        // Start dealing board

        // Burn one card before flop
        board.setBurnCard(holdemDeck.getCard(cardCounter++), burnCounter++);

        // deal flop
        for (int i=0; i<3;i++){
            board.setBoardCard(holdemDeck.getCard(cardCounter++), boardCounter++);
        }

        // Burn one card before turn
        board.setBurnCard(holdemDeck.getCard(cardCounter++), burnCounter++);

        // deal turn
        board.setBoardCard(holdemDeck.getCard(cardCounter++), boardCounter++);

        // Burn one card before river
        board.setBurnCard(holdemDeck.getCard(cardCounter++), burnCounter++);

        // deal river
        board.setBoardCard(holdemDeck.getCard(cardCounter++), boardCounter++);

        //------------------------
        // end dealing board
        //------------------------

        System.out.println("The hand is complete...\n");

        // print deck
        holdemDeck.printDeck();

        //print board
        board.printBoard();

        // print player cards
        System.out.println("The player cards are the following:\n");
        for (int i=0;i<numPlayers;i++){
            player[i].printPlayerCards(i);
        }

        // print burn cards
        board.printBurnCards();

        //------------------------
        // Begin hand comparison
        //------------------------
        for (int i=0;i<numPlayers;i++){
            HandEval handToEval = new HandEval();

            // populate with player cards           
            for (int j=0;j<player[i].holeCardsSize();j++){
                handToEval.addCard(player[i].getCard(j),j);
            }

            //populate with board cards
            for (int j=player[i].holeCardsSize();j<(player[i].holeCardsSize()+board.boardSize());j++){
                handToEval.addCard(board.getBoardCard(j-player[i].holeCardsSize()),j);
            }

            System.out.println("Player " + (i+1) + " hand value: " + handToEval.evaluateHand());    
        }
    }
       
}
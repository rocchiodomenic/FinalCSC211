
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



/**
 * Author: Domenic Rocchio
 * Program #4 - A Game of Poker
 */
public class Game extends Frame implements MouseListener {

 /**
  * I am creating my four card objects to be use throughout the program
  * stack1 is the face down stack to the far left, stack2
  * is the face down all the way to the right, myCard
  * is the card face up on the left and theComputerCard
  * is the face up one on the right
  */
 private Card myCard, theComputerCard, stack1, stack2;

 /**
  * The three buttons that I create and 
  * are used in the program
  */
 private Abutton playButton, resetButton, quitButton;

 /**
  * So the System doesn't try to paint the buttons before they are initialized
  */
 private boolean complete = false;

 /**
  * x and y coordinate of the mouse click
  */
 private int xValue, yValue;

 /**
  * lowColor is the color when the button is down, highColor when the button is up
  */
 private Color lowColor, highColor;

 /**
  * a test to see whether the button is 
  * up or down. 
  */
 private boolean up;

 /**
  *The creationg of a score object
  *from the BarGraph class to keep track
  *of the score of the game
  */
 private BarGraph score;

 /**
  * what is thegamescore
  */
 private int gameScore = 0;

 /**
  * if it was a tie the counter
  * that keeps track if there was
  * a tie between myCard and 
  * theComputerCard
  */
 private int tie = 0; 

 public Game(){


  setTitle("Program #4 - Playing War");    //The title of the program frame
  setSize(550, 500);        //Size of the program frame
  setLocation(300, 300);     //Location where the frame will appear
  setBackground(Color.WHITE);//Set the background color to be white


  Window myWindow = new Window();//to allow for window closing
  addWindowListener(myWindow);//A must for AWT to close the window

  setVisible(true);

  addMouseListener(this);//so you can handle the mouse clicks

  //This is where I create the two cards
  //That are always going to be face down
  stack1 = new Card(25, 75, true);
  stack2 = new Card(400, 75, true);
  
  //This is where I set my cards to 
  //be visible
  myCard = new Card(true);
  theComputerCard = new Card(true);

  //This creates a new instance of the
  //BarGraph class at the specified area
  //Of the window
  score = new BarGraph(275, 320);

  //I create my three buttons of different colors
  playButton = new Abutton("Play", Color.green, 125, 400, 100, 50);
  resetButton = new Abutton("Reset", Color.yellow, 250, 400, 100, 50);
  quitButton = new Abutton("Quit", Color.red, 375, 400, 100, 50);

  //Showing that the program is ready to execute
  complete = true;

 }

 /**
  * The mouseClicked method is where you collect any information you
  * need for painting properly.  If the mouse is clicked
  * in either button you'll want to flip that button, and you'll want to
  * get the coordinates so you can mark where the click has taken place.
  */
 public void mouseClicked(MouseEvent event) {

  //The values of where the mouse is clicked
  //and sets them to the xValue and yValue
  xValue = event.getX();
  yValue = event.getY();

  //my test to see if the mouse click is 
  //inside the boundries of the button
  //If it is, I create new cards that will be
  //The cards that are being compared in
  //the game of war.
  if(playButton.isInside(xValue, yValue)){ 
   myCard = new Card(150, 75, false);
   theComputerCard = new Card(275, 75, false);
   
   //Implements my gameRules method that
   //takes the number value of the rank of 
   //the two compared cards.
   gameRules(myCard.getValue(), theComputerCard.getValue());
  }

  else if(resetButton.isInside(xValue, yValue)){
   
   //Makes it so that my cards dissappear 
   //When the reset button is pressed.
   myCard = new Card(true);
   theComputerCard = new Card(true);
   
   //Resets the score and tie to 0 to restart the game
   score.reset();
   tie = 0;
  }

  System.out.println(score.score);
  repaint();
 }


 //mouseEntered is triggered when the mouse enters the frame
 public void mouseEntered(MouseEvent event) {}

 //mouseExited is triggered when the mouse leaves the frame
 public void mouseExited(MouseEvent event) {}

 /**mousePressed is triggered on the downstroke of a mouse click (or if 
  * the user holds the button down)
  */
 public void mousePressed(MouseEvent event) {

  //The values of where the mouse is clicked
  //and sets them to the xValue and yValue
  xValue = event.getX();
  yValue = event.getY();

  //Just like before, tests if the click is inside
  //And if it is it updates the frame
  //by showing that the button is pressed
  //using flip()
  if(playButton.isInside(xValue, yValue)){
   playButton.flip();
   repaint();//updates the frame
  }

  //Same thing, shows the button flipped if inside the boundaries
  else if(resetButton.isInside(xValue, yValue)){
   resetButton.flip();
   repaint();
  }
  
  //Same thing, shows the button flipped if inside the boundaries
  else if(quitButton.isInside(xValue, yValue)){
   quitButton.flip();
   repaint();
  }

 }

 /**mouseReleased is triggered on the release of the mouse button, (the
  * upstroke of a click or when it's released after being held down).
  * 
  */
 public void mouseReleased(MouseEvent event) {

  //The values of where the mouse is clicked
  //and sets them to the xValue and yValue
  xValue = event.getX();
  yValue = event.getY();

  //Same thing, shows the button flipped if inside the boundaries
  if(playButton.isInside(xValue, yValue)){
   playButton.flip();
   repaint();
  }
  
  //Same thing, shows the button flipped if inside the boundaries
  else if(resetButton.isInside(xValue, yValue)){
   resetButton.flip();
   repaint();
  }
  
  //Same thing, shows the button flipped if inside the boundaries
  else if(quitButton.isInside(xValue, yValue)){
   quitButton.flip();
   repaint();
   System.exit(1);
  }
 }

 /**The game rules method which begins the comparison between both cards
  * And takes their rank as a parameter
  */
 public void gameRules(int cardOneNumber, int cardTwoNumber)
 {
  //If their values are equal
  if(cardOneNumber == cardTwoNumber){
   tie++;//increment counter
  }
  
  //Otherwise it takes the score and adds
  //To it the value of tie to the second power
  //Because is the rule when it comes to ties.
  else if(tie > 1){
   gameScore += tie^2;
   tie = 0;
  }
  else{
   if(cardOneNumber > cardTwoNumber) 
   {
    //Still figuring out the score based on
    //The comparison of the cards
    gameScore += 1+tie;
    tie = 0; 

    //Mutator method to change the score
    score.changeScore(gameScore);
   }

   else if(cardOneNumber < cardTwoNumber)
   {
    //Still figuring out the score based on
    //The comparison of the cards
    gameScore -= 1+tie;
    tie = 0;

    //Mutator method to change the score
    score.changeScore(gameScore);
   }
  }
 }

 /**This is my entire paint method that paints everything
  * That is going to be in the frame
  */
 public void paint(Graphics pane){
  if (complete){

   Graphics2D pane2 = (Graphics2D)pane; 
   
   //This is where the color will change showing
   //when a button is pressed
   pane2.setColor(up? highColor: lowColor);

   stack1.paint(pane);
   myCard.paint(pane);
   theComputerCard.paint(pane);
   stack2.paint(pane);
   playButton.paint(pane);
   resetButton.paint(pane);
   quitButton.paint(pane);
   score.paint(pane);
  }
 }
}

  
  
/**
 * AUTHOR: Domenic Rocchio
 * CLASS: CSC 211
 * LAB: THURSDAY
 * 
 *         PROGRAM #4-A Game of War
 *      ===============================================
 * The following method allows
 * the window to be closed after the dice
 * have been drawn
 */

import java.awt.event.*;


public class Window extends WindowAdapter {

 /**
  * So our window closes when the user hits the close button.
  */
 public void windowClosing(WindowEvent event){
  System.exit(0);
 }
}

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
class Main {
  public static void main(String[] args) {
    try{
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    }
    catch(Exception e){} 
    JFrame frame = new JFrame("Tic Tac Toe");
    Board b = new Board(frame);
  }
}
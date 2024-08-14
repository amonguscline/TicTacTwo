import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.Color;
class Square{
  private int squareID;
  private SubBoard container;
  private JButton button;
  public Square(SubBoard container, int index){
    this.container = container;
    button = new JButton("");
    squareID = (index/3+1)*10+index%3+1;
    button.setBackground(Color.white);
    button.setBounds(index*40%120, index/3*40,40,40);
    container.getPanel().add(button);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(container.isValidMove(squareID)){
          container.updateSubBoardState(squareID);
        }
      }
    });
  }
  public JButton getButton(){
    return button;
  }
  public int getID(){
    return squareID;
  }
}
import javax.swing.*;
import java.util.*;
import java.awt.Color;
class SubBoard {
  private Square[] squares = new Square[9];
  private boolean[] squareAccessible = new boolean[9];
  private String winner;
  private boolean completed;
  private JPanel panel;
  private int sBoardID;
  private Board container;
  private boolean allSquaresWon;
  public SubBoard(Board board, int index){
    container = board;
    panel = new JPanel();
    panel.setBounds(index*120%360, index/3*120,120,120);
    panel.setBackground(Color.green);
    panel.setLayout(null);  
    panel.setVisible(true);
    sBoardID = (index/3+1)*10+index%3+1;
    winner = "";
    for(int i=0;i<9;i++){
      squares[i] = new Square(this, i);
      squareAccessible[i] = true;
    }
  }
  public void updateSubBoardState(int id){
    int buttonIndex = (id/10-1)*3+id%10-1;
    squareAccessible[buttonIndex]=false;
    squares[buttonIndex].getButton().setText(container.getCurrentMove());
    if(container.getCurrentMove().equals("x")){squares[buttonIndex].getButton().setBackground(Color.blue);}
    else{squares[buttonIndex].getButton().setBackground(Color.red);}
    this.checkIfWin();
    container.updateBoardState(buttonIndex);
  }
  public JPanel getPanel(){
    return panel;
  }
  public boolean isValidMove(int id){
    int buttonIndex = (id/10-1)*3+id%10-1;
    if(squareAccessible[buttonIndex]&&container.isValidSBoard(sBoardID)){return true;}
    System.out.println("Invalid Move");
    return false;
  }

  public String getButtonContentsById(int id){
    for(Square square:squares){
      if(square.getID()==id){
        if(square.getButton().getText().equals("")){allSquaresWon=false;}
        return square.getButton().getText();
      }
    }
    return null;
  }
  
  public void checkIfWin(){
    allSquaresWon = true;
    if((this.getButtonContentsById(11).equals("x")&&this.getButtonContentsById(12).equals("x")&&this.getButtonContentsById(13).equals("x"))||(this.getButtonContentsById(21).equals("x")&&this.getButtonContentsById(22).equals("x")&&this.getButtonContentsById(23).equals("x"))||(this.getButtonContentsById(31).equals("x")&&this.getButtonContentsById(32).equals("x")&&this.getButtonContentsById(33).equals("x"))||(this.getButtonContentsById(11).equals("x")&&this.getButtonContentsById(21).equals("x")&&this.getButtonContentsById(31).equals("x"))||(this.getButtonContentsById(12).equals("x")&&this.getButtonContentsById(22).equals("x")&&this.getButtonContentsById(32).equals("x"))||(this.getButtonContentsById(13).equals("x")&&this.getButtonContentsById(23).equals("x")&&this.getButtonContentsById(33).equals("x"))||(this.getButtonContentsById(11).equals("x")&&this.getButtonContentsById(22).equals("x")&&this.getButtonContentsById(33).equals("x"))||(this.getButtonContentsById(13).equals("x")&&this.getButtonContentsById(22).equals("x")&&this.getButtonContentsById(31).equals("x"))){
      winner = "x";
      completed =true;
      for(int i=0;i<9;i++){
        JButton button = squares[i].getButton();
        button.setText("");
        button.setBackground(Color.white);
        if(i%2==0){button.setBackground(Color.blue);}
      }
    }  else if((this.getButtonContentsById(11).equals("o")&&this.getButtonContentsById(12).equals("o")&&this.getButtonContentsById(13).equals("o"))||(this.getButtonContentsById(21).equals("o")&&this.getButtonContentsById(22).equals("o")&&this.getButtonContentsById(23).equals("o"))||(this.getButtonContentsById(31).equals("o")&&this.getButtonContentsById(32).equals("o")&&this.getButtonContentsById(33).equals("o"))||(this.getButtonContentsById(11).equals("o")&&this.getButtonContentsById(21).equals("o")&&this.getButtonContentsById(31).equals("o"))||(this.getButtonContentsById(12).equals("o")&&this.getButtonContentsById(22).equals("o")&&this.getButtonContentsById(32).equals("o"))||(this.getButtonContentsById(13).equals("o")&&this.getButtonContentsById(23).equals("o")&&this.getButtonContentsById(33).equals("o"))||(this.getButtonContentsById(11).equals("o")&&this.getButtonContentsById(22).equals("o")&&this.getButtonContentsById(33).equals("o"))||(this.getButtonContentsById(13).equals("o")&&this.getButtonContentsById(22).equals("o")&&this.getButtonContentsById(31).equals("o"))){
      winner = "o";
      completed=true;
      for(int i=0;i<9;i++){
        JButton button = squares[i].getButton();
        button.setText("");
        button.setBackground(Color.white);
        if(i!=4){button.setBackground(Color.red);}
      }
    }
    else if(allSquaresWon){
      winner="na";
      completed=true;
    }
  }

  public String getWinner(){
    return winner;
  }
  public boolean getIfCompleted(){
    return completed;
  }
  public int getID(){
    return sBoardID;
  }
  public void winningSequence(){
    for(int i=0;i<9;i++){
      squares[i].getButton().setText("");
      squares[i].getButton().setBackground(Color.white);
    }
  }
}
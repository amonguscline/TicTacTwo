import javax.swing.*;
import java.awt.Color;
class Board {
  private SubBoard[] sBoards = new SubBoard[9];
  private boolean[] sBoardsAccessible = new boolean[9];
  private JFrame frame;
  private String currentMove;
  public Board(JFrame frame){
    this.frame=frame;
    currentMove = "x";
    for(int i=0;i<9;i++){
      sBoards[i] = new SubBoard(this, i);
      sBoardsAccessible[i] = true;
      sBoards[i].getPanel().setBorder(BorderFactory.createLineBorder(Color.black));
      frame.getContentPane().add(sBoards[i].getPanel());
    }
    frame.setSize(360,380);  
    frame.setLayout(null);  
    frame.setVisible(true);
  }

  public boolean isValidSBoard(int id){
    int sBoardIndex = (id/10-1)*3+id%10-1;
    if(sBoardsAccessible[sBoardIndex]){return true;}
    return false;
  }

  public String getCurrentMove(){
    return currentMove;
  }
  
  public void updateBoardState(int newMove){
    for(int i=0;i<9;i++){
      sBoardsAccessible[i]=false;
      if(sBoards[newMove].getWinner()!=""){
        sBoardsAccessible[i]=true;
        sBoardsAccessible[newMove]=false;
      }
      else{sBoardsAccessible[newMove]=true;}
      if(sBoards[i].getWinner()!=""){sBoardsAccessible[i]=false;}
    }
    for(int i=0;i<9;i++){
      if(sBoardsAccessible[i]){
        sBoards[i].getPanel().setBackground(Color.green);
      }
      else{sBoards[i].getPanel().setBackground(Color.white);}
    }
    if(this.checkIfWin().equals("x")||this.checkIfWin().equals("o")||this.checkIfWin().equals("draw")){
      for(int i=0;i<9;i++){
        sBoardsAccessible[i]=false;
        sBoards[i].winningSequence();
        if(this.checkIfWin().equals("draw")){;}
        else if(currentMove.equals("x")){
          JPanel panel = sBoards[i].getPanel();
          panel.setBackground(Color.white);
          if(i%2==0){panel.setBackground(Color.blue);}
        }
        else if(currentMove.equals("o")){
          JPanel panel = sBoards[i].getPanel();
          panel.setBackground(Color.white);
          if(i!=4){panel.setBackground(Color.red);}
        }
      }
    }
    if(currentMove.equals("x")){currentMove="o";}
    else{currentMove="x";}
  }
  private String checkIfWin(){
    if((this.getSBoardWinState(11).equals("x")&&this.getSBoardWinState(12).equals("x")&&this.getSBoardWinState(13).equals("x"))||(this.getSBoardWinState(21).equals("x")&&this.getSBoardWinState(22).equals("x")&&this.getSBoardWinState(23).equals("x"))||(this.getSBoardWinState(31).equals("x")&&this.getSBoardWinState(32).equals("x")&&this.getSBoardWinState(33).equals("x"))||(this.getSBoardWinState(11).equals("x")&&this.getSBoardWinState(21).equals("x")&&this.getSBoardWinState(31).equals("x"))||(this.getSBoardWinState(12).equals("x")&&this.getSBoardWinState(22).equals("x")&&this.getSBoardWinState(32).equals("x"))||(this.getSBoardWinState(13).equals("x")&&this.getSBoardWinState(23).equals("x")&&this.getSBoardWinState(33).equals("x"))||(this.getSBoardWinState(11).equals("x")&&this.getSBoardWinState(22).equals("x")&&this.getSBoardWinState(33).equals("x"))||(this.getSBoardWinState(13).equals("x")&&this.getSBoardWinState(22).equals("x")&&this.getSBoardWinState(31).equals("x"))) return "x";
    else if((this.getSBoardWinState(11).equals("o")&&this.getSBoardWinState(12).equals("o")&&this.getSBoardWinState(13).equals("o"))||(this.getSBoardWinState(21).equals("o")&&this.getSBoardWinState(22).equals("o")&&this.getSBoardWinState(23).equals("o"))||(this.getSBoardWinState(31).equals("o")&&this.getSBoardWinState(32).equals("o")&&this.getSBoardWinState(33).equals("o"))||(this.getSBoardWinState(11).equals("o")&&this.getSBoardWinState(21).equals("o")&&this.getSBoardWinState(31).equals("o"))||(this.getSBoardWinState(12).equals("o")&&this.getSBoardWinState(22).equals("o")&&this.getSBoardWinState(32).equals("o"))||(this.getSBoardWinState(13).equals("o")&&this.getSBoardWinState(23).equals("o")&&this.getSBoardWinState(33).equals("o"))||(this.getSBoardWinState(11).equals("o")&&this.getSBoardWinState(22).equals("o")&&this.getSBoardWinState(33).equals("o"))||(this.getSBoardWinState(13).equals("o")&&this.getSBoardWinState(22).equals("o")&&this.getSBoardWinState(31).equals("o"))) return "o";
    else if(checkIfTie()){
      return "draw";
    }
    return "na";
  }
  public String getSBoardWinState(int id){
    for(SubBoard s:sBoards){
      if(s.getID()==id){
        return s.getWinner();
      }
    }
    return null;
  }
  public boolean checkIfTie(){
    boolean hasTied = true;
    for(SubBoard s:sBoards){
      if(!s.getIfCompleted()){hasTied=false;}
    }
    return hasTied;
  }
}
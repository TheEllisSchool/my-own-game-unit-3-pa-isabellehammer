import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TicTacTiger extends JFrame {

	private static final int GRIDSIZE = 3;
	
	private String playerX = "X";
	private String playerO = "O";
	
	private int clicks = 0;

	
	private GridButton[][] grid = new GridButton[GRIDSIZE][GRIDSIZE];
	
	public TicTacTiger () {
		initGUI();
		setTitle("Tic Tac Tiger");
		setSize(600,600);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void initGUI() { 
		JLabel titleLabel = new JLabel("Tic Tac Tiger!!!");
		Font titleFont = new Font (Font.SERIF, Font.BOLD + Font.ITALIC, 32);
		titleLabel.setFont(titleFont);
		titleLabel.setForeground(new Color(107, 158, 239));
		titleLabel.setBackground(new Color(241, 169, 242));
		titleLabel.setOpaque(true);
		add(titleLabel, BorderLayout.PAGE_START);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
		add(centerPanel, BorderLayout.CENTER);
		for (int r = 0; r < GRIDSIZE; r++) {
			for (int c = 0; c < GRIDSIZE; c++) {
				grid[r][c] = new GridButton(r, c);
				grid[r][c].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						GridButton button = (GridButton) e.getSource();
						int row = button.getRow();
						int col = button.getCol();
						clickedGrid(row, col);
					}
				});
				centerPanel.add(grid[r][c]);
			}
		}
	
	}
	
	private void clickedGrid(int row, int col) {
		clicks++;
		if(grid[row][col].isRevealed() == false) { 
			if (clicks%2 == 1) {
				grid[row][col].setText(playerX);
				grid[row][col].reveal();
			}
			if (clicks%2 == 0) {
				grid[row][col].setText(playerO);
				grid[row][col].reveal();
			}
		} else {
			clicks--;
			
		}
		if (checkForWin()) {
	 		System.out.println("We have a winner!");
	 		System.exit(0);
	 	} else if (isBoardFull()) {
	 		System.out.println("It's a tie!");
	 		System.exit(0);
	 	}
		
	}
	
	
	private void promptForNewGame(String mess) {
		int option = JOptionPane.showConfirmDialog(this, mess, "Play again?", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			newGame();
		} else {
			System.exit(0);
		}
	}
	
	private void newGame() {
		for (int r = 0; r < GRIDSIZE; r++) {
			for (int c = 0; c < GRIDSIZE; c++) {
		grid[r][c].reset();
	}
		}
	}
	
	public boolean isBoardFull() {
		   boolean isFull = true;
		   for (int r = 0; r < 3; r++) {
			   for (int c = 0; c < 3; c++) {
				 if (grid[r][c].isRevealed() == false) {
					   isFull = false;
	                }
	            }
	        }
		   return isFull;
	    }
	
	public boolean checkForWin() {
    	return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }
	
	private boolean checkRowsForWin() {
    	for (int r = 0; r < 3; r++) {
    		if (grid[r][0].isRevealed() && grid[r][1].isRevealed() && grid[r][2].isRevealed()) {
    			if ((grid[r][0].getText().equals("X") && grid[r][1].getText().equals("X") && grid[r][2].getText().equals("X")) || 
    					(grid[r][0].getText().equals("O") && grid[r][1].getText().equals("O") && grid[r][2].getText().equals("O"))) {
    			return true;
    		}
        }
    	}
        return false;
    }

    private boolean checkColumnsForWin() {
    	//getText(); //where do I insert the getText function?
    	for (int r = 0; r < 3; r++) {
    		if (grid[0][r].isRevealed() && grid[1][r].isRevealed() && grid[2][r].isRevealed()) {
    			if ((grid[0][r].getText().equals("X") && grid[1][r].getText().equals("X") && grid[2][r].getText().equals("X")) || 
    					grid[0][r].getText().equals("O") && grid[1][r].getText().equals("O") && grid[2][r].getText().equals("O"))
    				return true;
    			
            }
        }
    	return false;
    }

     private boolean checkDiagonalsForWin() {
    	 if (grid[0][0].isRevealed() && grid[1][1].isRevealed() && grid[2][2].isRevealed()) {
    		 if ((grid[0][0].getText().equals("X") && grid[1][1].getText().equals("X") && grid[2][2].getText().equals("X")) || 
    				 (grid[0][0].getText().equals("X") && grid[1][1].getText().equals("O") && grid[2][2].getText().equals("O"))) {
    			 return true;
    		 } else {
    			 return false;
    		 }
    	 }
    			 
    	if (grid[0][2].isRevealed() && grid[1][1].isRevealed() && grid[2][0].isRevealed()) {
        	if ((grid[0][2].getText().equals("X") && grid[1][1].getText().equals("X") && grid[2][0].getText().equals("X")) || 
        				 (grid[0][2].getText().equals("X") && grid[1][1].getText().equals("O") && grid[2][0].getText().equals("O"))) {
        		return true;
        	} else {
        		return false;
        	}
    	} else {
    		return false;
    	}
    		 
    }
    
     
     
     public static void main(String[] args) {
 		
 		try {
             String className = UIManager.getCrossPlatformLookAndFeelClassName();
             UIManager.setLookAndFeel(className);
         } catch ( Exception e) {}
         
         EventQueue.invokeLater(new Runnable (){
             @Override
             public void run() {
                 new TicTacTiger();
             }   
         });
 		
 	}
 

 	
	
}

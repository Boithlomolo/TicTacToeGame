import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToeGame extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private boolean gameWon = false;

    public TicTacToeGame() {
        setTitle("Tic-Tac-Toe");
        setSize(350, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        initializeButtons();
        createMenuBar();
        setVisible(true);
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 80));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].setBackground(Color.WHITE);
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");

        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem exitItem = new JMenuItem("Exit");

        newGameItem.addActionListener(e -> startNewGame());
        exitItem.addActionListener(e -> System.exit(0));

        gameMenu.add(newGameItem);
        gameMenu.add(exitItem);
        menuBar.add(gameMenu);

        setJMenuBar(menuBar);
    }

    private void startNewGame() {
        currentPlayer = 'X';
        gameWon = false;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setBackground(Color.WHITE);
                buttons[row][col].setEnabled(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (!gameWon && buttonClicked.getText().equals("")) { 
            buttonClicked.setText(String.valueOf(currentPlayer)); 

            if (currentPlayer == 'X') {
                buttonClicked.setForeground(Color.RED); 
            } else {
                buttonClicked.setForeground(Color.BLUE); 
            }

            buttonClicked.setEnabled(false); 

            if (checkWin()) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                highlightWinningLine(); 
                gameWon = true;
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
            } else {
                switchPlayer();
            }
        }
    }
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean checkWin() {

        for (int row = 0; row < 3; row++) {
            if (checkLine(buttons[row][0], buttons[row][1], buttons[row][2])) {
                return true;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (checkLine(buttons[0][col], buttons[1][col], buttons[2][col])) {
                return true;
            }
        }

        if (checkLine(buttons[0][0], buttons[1][1], buttons[2][2])) {
            return true;
        }
        if (checkLine(buttons[0][2], buttons[1][1], buttons[2][0])) {
            return true;
        }

        return false;
    }

    private boolean checkLine(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().equals("") && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }

    private void highlightWinningLine() {
        for (int row = 0; row < 3; row++) {
            if (checkLine(buttons[row][0], buttons[row][1], buttons[row][2])) {
                buttons[row][0].setBackground(Color.BLUE);
                buttons[row][1].setBackground(Color.BLUE);
                buttons[row][2].setBackground(Color.BLUE);
            }
        }
        for (int col = 0; col < 3; col++) {
            if (checkLine(buttons[0][col], buttons[1][col], buttons[2][col])) {
                buttons[0][col].setBackground(Color.BLUE);
                buttons[1][col].setBackground(Color.BLUE);
                buttons[2][col].setBackground(Color.BLUE);
            }
        }

        // Check diagonals
        if (checkLine(buttons[0][0], buttons[1][1], buttons[2][2])) {
            buttons[0][0].setBackground(Color.BLUE);
            buttons[1][1].setBackground(Color.BLUE);
            buttons[2][2].setBackground(Color.BLUE);
        }
        if (checkLine(buttons[0][2], buttons[1][1], buttons[2][0])) {
            buttons[0][2].setBackground(Color.BLUE);
            buttons[1][1].setBackground(Color.BLUE);
            buttons[2][0].setBackground(Color.BLUE);
        }
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}

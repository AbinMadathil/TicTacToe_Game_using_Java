import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGame {
    private JFrame frame;
    private JPanel board;
    private JButton[][] buttons;
    private char[][] grid;
    private boolean isXPlayerTurn = true;
    private String player1Name;
    private String player2Name;

    public TicTacToeGame() {
        player1Name = JOptionPane.showInputDialog("Enter Player 1's Name:");
        player2Name = JOptionPane.showInputDialog("Enter Player 2's Name:");

        frame = new JFrame("Tic-Tac-Toe");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board = new JPanel();
        board.setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        grid = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                board.add(buttons[i][j]);
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(e -> onCellClick(finalI, finalJ));
            }
        }

        frame.add(board);
        frame.setVisible(true);
    }

    private void onCellClick(int row, int col) {
        if (buttons[row][col].getText().isEmpty()) {
            String currentPlayerName = isXPlayerTurn ? player1Name : player2Name;
            buttons[row][col].setText(isXPlayerTurn ? "X" : "O");
            grid[row][col] = isXPlayerTurn ? 'X' : 'O';
            isXPlayerTurn = !isXPlayerTurn;

            if (haveWon(grid, currentPlayerName)) {
                JOptionPane.showMessageDialog(frame, currentPlayerName + " wins!");
                resetGame();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(frame, "It's a tie!");
                resetGame();
            }
        }
    }

    private boolean haveWon(char[][] board, String playerName) {
        // Check the rows
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == 'X' && board[row][1] == 'X' && board[row][2] == 'X' ||
                    board[row][0] == 'O' && board[row][1] == 'O' && board[row][2] == 'O') {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < board.length; col++) {
            if (board[0][col] == 'X' && board[1][col] == 'X' && board[2][col] == 'X' ||
                    board[0][col] == 'O' && board[1][col] == 'O' && board[2][col] == 'O') {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X' ||
                board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') ||
                (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X' ||
                        board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O')) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        // Clear the grid and buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                grid[i][j] = '\0';
            }
        }
        isXPlayerTurn = true;
    }

}

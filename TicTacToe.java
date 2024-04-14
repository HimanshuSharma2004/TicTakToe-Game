import java.awt.*;
import java.awt.event.*;

class FDemo extends Frame implements ActionListener {
    Button b[] = new Button[9];
    boolean gameOver = false;
    Label winnerLabel;

    FDemo() {
        setLayout(null);
        setTitle("Tic Tac Toe Game");

        Font f = new Font("Helvetica", Font.BOLD, 50);
        setFont(f);

        int i, j, k = 0;
        int x, y, w, h;

        x = y = w = h = 100;

        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                b[k] = new Button();
                b[k].setSize(w, h);
                b[k].setLocation(x, y);
                add(b[k]);

                b[k].addActionListener(this);
                k++;
                x += 100; // x = x+100;
            }
            x = 100;
            y += 100; // y = y+100;
        }

        winnerLabel = new Label();
        winnerLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        winnerLabel.setBounds(100, 350, 300, 50);
        add(winnerLabel);
    }

    int c = 1;

    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            Button b1 = (Button) e.getSource();

            if (c % 2 == 0) {
                b1.setLabel("0");
            } else {
                b1.setLabel("X");
            }
            b1.removeActionListener(this);
            c++;
            
            if (checkWinner()) {
                displayWinner();
                gameOver = true;
            }
        }
    }

    private boolean checkWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (!b[i * 3].getLabel().isEmpty() && b[i * 3].getLabel().equals(b[i * 3 + 1].getLabel())
                    && b[i * 3 + 1].getLabel().equals(b[i * 3 + 2].getLabel())) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (!b[i].getLabel().isEmpty() && b[i].getLabel().equals(b[i + 3].getLabel())
                    && b[i + 3].getLabel().equals(b[i + 6].getLabel())) {
                return true;
            }
        }

        // Check diagonals
        if (!b[0].getLabel().isEmpty() && b[0].getLabel().equals(b[4].getLabel()) && b[4].getLabel().equals(b[8].getLabel())) {
            return true;
        }
        if (!b[2].getLabel().isEmpty() && b[2].getLabel().equals(b[4].getLabel()) && b[4].getLabel().equals(b[6].getLabel())) {
            return true;
        }

        return false;
    }

    private void displayWinner() {
        String winner = c % 2 == 0 ? "Player 1 (X)" : "Player 2 (0)";
        winnerLabel.setText("Winner: " + winner);
        winnerLabel.setLocation(100,450);
        winnerLabel.setVisible(true);
    }

    public void windowClosing(WindowEvent e) {
        dispose();
    }
}

class TicTacToe {
    public static void main(String ar[]) {
        FDemo f = new FDemo();

        f.setVisible(true);
        f.setSize(500, 800);
        f.setLocation(500, 100);
        f.setBackground(Color.yellow);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}

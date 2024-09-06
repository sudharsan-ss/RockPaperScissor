package RockPaperScissor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsGame {
    private JFrame frame;
    private JTextArea textArea;
    private JButton rockButton, paperButton, scissorsButton;
    private int playerScore, computerScore;

    public RockPaperScissorsGame() {
        frame = new JFrame("Rock Paper Scissors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");

        rockButton.addActionListener(new ButtonClickListener());
        paperButton.addActionListener(new ButtonClickListener());
        scissorsButton.addActionListener(new ButtonClickListener());

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        playerScore = 0;
        computerScore = 0;

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String playerChoice = e.getActionCommand();
            String computerChoice = getComputerChoice();
            String result = determineWinner(playerChoice, computerChoice);
            updateScores(result);

            textArea.append("Player chose: " + playerChoice + "\n");
            textArea.append("Computer chose: " + computerChoice + "\n");
            textArea.append(result + "\n");
            textArea.append("Player Score: " + playerScore + " | Computer Score: " + computerScore + "\n\n");
        }

        private String getComputerChoice() {
            String[] choices = {"Rock", "Paper", "Scissors"};
            Random rand = new Random();
            return choices[rand.nextInt(3)];
        }

        private String determineWinner(String playerChoice, String computerChoice) {
            if (playerChoice.equals(computerChoice)) {
                return "It's a tie!";
            }

            switch (playerChoice) {
                case "Rock":
                    if (computerChoice.equals("Scissors")) {
                        return "Player wins!";
                    } else {
                        return "Computer wins!";
                    }
                case "Paper":
                    if (computerChoice.equals("Rock")) {
                        return "Player wins!";
                    } else {
                        return "Computer wins!";
                    }
                case "Scissors":
                    if (computerChoice.equals("Paper")) {
                        return "Player wins!";
                    } else {
                        return "Computer wins!";
                    }
                default:
                    return "Invalid choice!";
            }
        }

        private void updateScores(String result) {
            if (result.equals("Player wins!")) {
                playerScore++;
            } else if (result.equals("Computer wins!")) {
                computerScore++;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RockPaperScissorsGame::new);
    }
}

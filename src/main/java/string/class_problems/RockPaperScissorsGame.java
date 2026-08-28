package string.class_problems;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissorsGame {

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        } else if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
                   (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
                   (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    public static void main(String[] args) {
        String[] possibleMoves = {"Rock", "Paper", "Scissors"};
        Random randomGenerator = new Random();
        Scanner scanner = new Scanner(System.in);

        int wins = 0, losses = 0, draws = 0;
        int totalRounds = 5;

        System.out.println("Round	Player Move	Computer Move	Result");

        for (int round = 1; round <= totalRounds; round++) {
            System.out.print("Enter your move (Rock/Paper/Scissors): ");
            String playerMove = scanner.nextLine();
            String computerMove = possibleMoves[randomGenerator.nextInt(3)];
            String result = playRound(playerMove, computerMove);

            if (result.equals("Player Wins")) wins++;
            else if (result.equals("Computer Wins")) losses++;
            else draws++;

            System.out.println(round + "	" + playerMove + "	" + computerMove + "	" + result);
        }

        double winPercentage = (wins * 100.0) / totalRounds;
        System.out.println("Wins: " + wins + " | Losses: " + losses + " | Draws: " + draws + " | Win % = " + winPercentage + "%");

        scanner.close();
    }
}

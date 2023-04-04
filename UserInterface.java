package bullscows;

import java.util.Scanner;

public class UserInterface {

    private GameLogic game;
    private int turnCount;

    public UserInterface() {
        this.game = new GameLogic();
    }

    public void run(Scanner scanner) {
        initializeGame(scanner);
        playGame(scanner);
    }

    public void initializeGame(Scanner scanner) {
        System.out.println("Please, enter the secret code's length:");
        int numLength;
        while (true) {
            numLength = scanner.nextInt();
            scanner.nextLine();
            if (numLength < 10) {
                break;
            }
            System.out.println("Error: can't generate a secret number with a length of more than 10 because there aren't enough unique digits.");
        }
        game.generateSecretCode(numLength);
    }

    private void playGame(Scanner scanner) {
        System.out.println("Okay, let's start a game!");
        while (!game.getGameOver()) {
            game.checkGuess(getUserGuess(scanner));
        }
    }

    private String getUserGuess(Scanner scanner) {
        this.turnCount++;
        System.out.printf("Turn %d\n", turnCount);
        return scanner.nextLine();
    }
}

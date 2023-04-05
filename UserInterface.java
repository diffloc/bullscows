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
        int codeLength;
        int charLength;
        System.out.println("Input the length of the secret code:");

        while (true) {
            codeLength = scanner.nextInt();
            scanner.nextLine();
            if (codeLength <= 36) {
                break;
            }
            System.out.println("Error: can't generate a secret code with a length of more than 36 (0-9 + a-z)");
        }
        System.out.println("Input the number of possible symbols in the code:");

        while (true) {
            charLength = scanner.nextInt();
            scanner.nextLine();
            if (charLength <= 36) {
                charLength -= 10;
                break;
            }
            System.out.println("Error: You can only choose a maximum of 26 characters (a-z)");
        }
        game.generateSecretCode(codeLength, charLength);
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

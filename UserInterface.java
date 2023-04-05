package bullscows;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {

    private final GameLogic game;
    private int turnCount;

    public UserInterface() {
        this.game = new GameLogic();
    }

    public void run(Scanner scanner) {
        while (!game.getGameOver()) {
            initializeGame(scanner);
        }
    }

    public void initializeGame(Scanner scanner) {
        int codeLength;
        int charLength;
        System.out.println("Input the length of the secret code:");
        try {
            codeLength = scanner.nextInt();
        } catch (InputMismatchException e) {
            String userInput = scanner.next();
            System.out.printf("Error: \"%s\" isn't a valid number.", userInput);
            game.setGameOver();
            return;
        }
        scanner.nextLine();
        if (codeLength > 36 || codeLength < 1) {
            System.out.println("Error: can't generate a secret code with a length of more than 36 or less than 1.");
            game.setGameOver();
            return;
        }
        System.out.println("Input the number of possible symbols in the code:");
        try {
            charLength = scanner.nextInt();;
        } catch (InputMismatchException e) {
            String userInput = scanner.next();
            System.out.printf("Error: \"%s\" isn't a valid number.", userInput);
            game.setGameOver();
            return;
        }
        scanner.nextLine();
        if (codeLength > charLength) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", codeLength, charLength);
            game.setGameOver();
            return;
        }
        if (charLength > 36 || charLength < 1) {
            System.out.println("Error: Invalid input");
            game.setGameOver();
            return;
        }
        charLength -= 10;
        game.generateSecretCode(codeLength, charLength);
        playGame(scanner);
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

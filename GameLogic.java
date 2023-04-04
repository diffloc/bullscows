package bullscows;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class GameLogic {

    private String secretCode;
    private boolean gameOver;

    public GameLogic() {
        this.gameOver = false;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    public boolean getGameOver() {
        return this.gameOver;
    }

    public void checkGuess(String userGuess) {
        int countBulls = 0;
        int countCows = 0;

        boolean[] used = new boolean[10];
        for (int i = 0; i < secretCode.length(); i++) {
            if (userGuess.charAt(i) == secretCode.charAt(i)) {
                countBulls++;
            } else {
                int digit = secretCode.charAt(i) - '0';
                if (!used[digit] && userGuess.contains(String.valueOf(digit))) {
                    countCows++;
                    used[digit] = true;
                }
            }
        }

        String grade = String.format(
                countBulls == 0 && countCows == 0 ? "None." :
                        countBulls == 0 ? "%d cow(s)." :
                                countCows == 0 ? "%d bull(s)." :
                                        "%d bull(s) and %d cow(s).",
                countBulls, countCows);
        System.out.printf("Grade: %s\n", grade);

        if (countBulls == secretCode.length()) {
            System.out.println("Congratulations! You guessed the secret code.");
            this.gameOver = true;
        }
    }

    public void generateSecretCode(int numLength) {

        Set<Integer> uniqueDigits = new HashSet<>();
        Random random = new Random(System.nanoTime());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numLength; i++) {
            int digit = random.nextInt(10);
            while (uniqueDigits.contains(digit)) {
                digit = random.nextInt(10);
            }
            sb.append(digit);
            uniqueDigits.add(digit);
        }
        setSecretCode(sb.toString());
    }
}

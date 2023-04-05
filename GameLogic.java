package bullscows;

import java.util.*;

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

        Map<Character, Boolean> used = new HashMap<>();
        for (int i = 0; i < secretCode.length(); i++) {
            if (userGuess.charAt(i) == secretCode.charAt(i)) {
                countBulls++;
            } else {
                char c = secretCode.charAt(i);
                if (!used.containsKey(c) && userGuess.contains(String.valueOf(c))) {
                    countCows++;
                    used.put(c, true);
                }
            }
        }

        System.out.println(getGrade(countBulls, countCows));

        if (countBulls == secretCode.length()) {
            System.out.println("Congratulations! You guessed the secret code.");
            this.gameOver = true;
        }
    }

    public String getGrade(int bulls, int cows) {
        String text = "Grade: ";
        if (bulls > 0 && cows > 0) {
            return text + bulls + (bulls == 1 ? " bull" : " bulls") + " and "
                    + cows + (cows == 1 ? " cow." : " cows.");
        }
        if (bulls > 0) {
            return text + bulls + (bulls == 1 ? " bull" : " bulls") + ".";
        }
        if (cows > 0) {
            return text + cows + (cows == 1 ? " cow." : " cows.");
        }
        return text + "None.";
    }

    public void generateSecretCode(int codeLength, int charLength) {

        final String characters = "0123456789abcdefghijklmnopqrstuvwxyz";
        String workingChars = characters.substring(0, 10 + charLength);

        Set<Character> uniqueChars = new HashSet<>();
        Random random = new Random(System.nanoTime());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < codeLength; i++) {
            int index = random.nextInt(workingChars.length());
            while (uniqueChars.contains(workingChars.charAt(index))) {
                index = random.nextInt(workingChars.length());
            }
            sb.append(workingChars.charAt(index));
            uniqueChars.add(workingChars.charAt(index));
        }
        setSecretCode(sb.toString());
        if (codeLength == 1) {
            setSecretCode("1");
        }
        String codeHash = "";
        for (int i = 0; i < codeLength; i++) {
            codeHash += "*";
        }
        String symbols = "";
        if (charLength > 0) {
            symbols = String.format(" (%c-%c)", workingChars.charAt(10), workingChars.charAt(9 + charLength));
        }
        System.out.printf("The secret is prepared: %s (0-9)%s.\n", codeHash, symbols);
        // System.out.println("Secret: " + secretCode);
    }
}

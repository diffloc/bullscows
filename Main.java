package bullscows;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numLength = scanner.nextInt();
        scanner.nextLine();
        run(numLength);
    }


    public static void run(int numLength) {

        if (numLength > 10) {
            System.out.println("Error: can't generate a secret number with a length of more than 10 because there aren't enough unique digits.");
        } else {
            String secretCode = generateSecretCode(numLength);
            System.out.printf("The random secret number is %s.", secretCode);
        }
    }

    private static String generateSecretCode(int numLength) {
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

        return sb.toString();
    }

    public static void gameArchived() {
        Scanner scanner = new Scanner(System.in);

        String secretCode = "9305";
        String userGuess = scanner.nextLine();

        int countBulls = 0;
        int countCows = 0;

        for (int i = 0; i < secretCode.length(); i++) {
            if (userGuess.charAt(i) == secretCode.charAt(i)) {
                countBulls++;
            }

            for (int j = 0; j < userGuess.length(); j++) {
                if (userGuess.charAt(j) == secretCode.charAt(i) && j != i)
                    countCows++;
            }
        }
        String grade = String.format(
                countBulls == 0 && countCows == 0 ? "None." :
                        countBulls == 0 ? "%d cow(s)." :
                                countCows == 0 ? "%d bull(s)." :
                                        "%d bull(s) and %d cow(s).",
                countBulls, countCows);
        System.out.printf("Grade: %s The secret code is %s.", grade, secretCode);
    }

}

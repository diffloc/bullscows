package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

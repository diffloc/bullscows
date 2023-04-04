package bullscows;

public class Main {
    public static void main(String[] args) {

        int turn = 0;
        int code = 1234;
        System.out.println("The secret code is prepared: ****.");

        turn++;
        int guess = 5678;
        System.out.printf("\nTurn %d Answer:\n", turn);
        System.out.println(guess);
        System.out.println("Grade: None.");

        turn++;
        guess = 1234;
        System.out.printf("\nTurn %d Answer:\n", turn);
        System.out.println(guess);
        System.out.println("Grade: 4 bulls.");
        System.out.printf("Congrats! The secret code is %d", code);
    }
}

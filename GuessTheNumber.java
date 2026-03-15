import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int rounds = 3; // total rounds
        int maxAttempts = 10; // attempts per round

        System.out.println("==================================");
        System.out.println("      WELCOME TO GUESS GAME");
        System.out.println("==================================");

        for (int round = 1; round <= rounds; round++) {
            int numberToGuess = random.nextInt(100) + 1; // 1 to 100
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\n🎮 Round " + round + " of " + rounds);
            System.out.println("Guess the number between 1 and 100");
            System.out.println("You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("✅ Correct! You guessed the number in " + attempts + " attempts.");
                    guessedCorrectly = true;

                    // Score based on attempts
                    int points = (maxAttempts - attempts + 1) * 10;
                    totalScore += points;

                    System.out.println("⭐ You earned " + points + " points.");
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("📉 Too low!");
                } else {
                    System.out.println("📈 Too high!");
                }

                System.out.println("Attempts left: " + (maxAttempts - attempts));
            }

            if (!guessedCorrectly) {
                System.out.println("❌ Out of attempts! The correct number was: " + numberToGuess);
            }

            System.out.println("🏆 Current Total Score: " + totalScore);
        }

        System.out.println("\n==================================");
        System.out.println("         GAME OVER");
        System.out.println("==================================");
        System.out.println("🎯 Final Score: " + totalScore);
        System.out.println("Thanks for playing!");

        sc.close();
    }
}
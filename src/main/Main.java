import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner KBIn = new Scanner(System.in);
        System.out.println("Welcome to Ten Pin Bowling!");
        int frame[][] = new int[12][3]; //10x3 matrix in order to store the pin count of 3 possible balls of each frame.
        int scores[] = new int[10]; //Totals for each of the frames

        for (int i = 0; i < 19; i++) {

            if (i == 18) { // The last frame is just so weird I have to have an exception for it.
                System.out.println("Round 10");
                do {
                    System.out.println("How many pins did you knock down with your first ball?");
                    frame[9][0] = KBIn.nextInt();
                } while (frame[9][0] >= 0 && frame[9][0] <= 10); //Input validation

                do {
                    System.out.println("How many pins did you knock down with your first ball?");
                    frame[9][1] = KBIn.nextInt();
                } while (frame[9][1] >= 0 && frame[9][1] <= 10); //Input validation

                if (frame[9][0] + frame[9][1] >= 10) {
                    do {
                        System.out.println("How many pins did you knock down with your first ball?");
                        frame[9][2] = KBIn.nextInt();
                    } while (frame[9][2] >= 0 && frame[9][2] <= 10); //Input validation
                }
                //TODO finish implementing the final frame.

            }

            else {
                int ball = i % 2;
                System.out.println("Round " + (ball == 0 ? (i / 2) + 1 : (i - 1) / 2 + 1));

                System.out.println("How many pins did you knock down with your " + (ball == 0 ? "first" : "second") + " ball?");
                frame[i / 2][ball] = KBIn.nextInt();
                if (frame[i / 2][ball] < 0 || frame[i / 2][ball] > 10 || (frame[i / 2][0] + frame[i / 2][1] > 10 && ball == 1)) { //Input validation
                    System.out.println("Invalid number of pins");
                    // Start this iteration again
                    i--;
                    continue;
                }

                if (frame[i / 2][ball] == 10) {
                    i++; // skip next ball
                }
                if (i >= 4 && frame[i / 2 - 2][0] + frame[i / 2 - 1][0] + frame[i / 2][0] == 30) { //TODO: only allow "Turkey!" to appear every 3 consecutive strikes
                    System.out.println("Turkey!");
                } else if (frame[i / 2][ball] == 10) {
                    System.out.println("Strike!");
                }
                if (ball == 1 && frame[i / 2][0] + frame[i / 2][1] == 10) {
                    System.out.println("Spare");
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            if (frame[i][0] == 10) {
                if (frame[i + 1][0] == 10) { //Two consecutive strikes
                    scores[i] = frame[i][0] + frame[i + 1][0] + frame[i + 2][0];
                } 
                else { //Strike followed by spare
                    scores[i] = frame[i][0] + frame[i + 1][0] + frame[i + 1][1];
                }
            } 
            else if (frame[i][0] + frame[i][1] == 10) {
                scores[i] = frame[i][0] + frame[i][1] + frame[i + 1][0];
            } 
            else {
                scores[i] = frame[i][0] + frame[i][1];
            }
        }
        int score = 0;
        for (int i = 0; i < 9; i++) { //Calculate score total for frames 0-8
            score = score + scores[i];
            System.out.println(scores[i]);
        }
        System.out.println("Total Score " + score);
    }
}
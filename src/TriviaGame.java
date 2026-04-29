import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class TriviaGame {
    public static void main(String[] args) throws IOException {
        Scanner fileScanner = new Scanner(new File("questions.txt"));
        
        int totalLines = 0;
        while (fileScanner.hasNextLine()) {
            fileScanner.nextLine();
            totalLines++;
        }
        fileScanner.close();
        
        TriviaCard[] triviaCards = new TriviaCard[totalLines / 2];
        
        fileScanner = new Scanner(new File("questions.txt"));
        int cardIndex = 0;
        int lineNumber = 0;
        String currentQuestion = "";
        
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            
            if (lineNumber % 2 == 0) {
                currentQuestion = line;
            } else {
                triviaCards[cardIndex] = new TriviaCard(currentQuestion, line);
                cardIndex++;
            }
            lineNumber++;
        }
        fileScanner.close();
        
        Scanner inputScanner = new Scanner(System.in);
        int correctCount = 0;
        
        for (TriviaCard each : triviaCards) {
            System.out.println(each.getQuestion());
            String userAnswer = inputScanner.nextLine();
            
            if (userAnswer.toLowerCase().equals(each.getAnswer().toLowerCase())) {
                System.out.println("Correct!");
                correctCount++;
            } else {
                System.out.println("Incorrect! The answer is: " + each.getAnswer());
            }
            System.out.println();
        }
        
        double percentage = (correctCount / (double) triviaCards.length) * 100;
        System.out.println("Final Score: " + correctCount + " out of " + triviaCards.length);
        System.out.println("Percentage: " + percentage + "%");
        
        inputScanner.close();
    }
}

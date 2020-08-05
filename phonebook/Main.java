package phonebook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void main(String[] args) {
        SearchEngine searchEngine = new SearchEngine();
        SortEngine sortEngine = new SortEngine();

        String pathToDirectory = "C:\\Users\\Leonid\\IdeaProjects\\Phone Book\\Phone Book\\task\\src\\directory.txt";
        String pathToFind = "C:\\Users\\Leonid\\IdeaProjects\\Phone Book\\Phone Book\\task\\src\\find.txt";

        String[] phoneBook = new String[0];
        String[] find = new String[0];

        try {
            phoneBook = readFileAsString(pathToDirectory).split("\n");
            find = readFileAsString(pathToFind).split("\n");
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }


        //linear search
        searchEngine.setSearchMethod(new LinearSearch());
        Timer firstTimer = new Timer();
        Timer secondTimer = new Timer();

        firstTimer.start();
        String[] numbers = searchEngine.searchNumbers(phoneBook, find);
        firstTimer.stop();

        System.out.printf("Start searching (linear search)...\n" +
                "Found %d / 500 entries. Time taken: %d min. %d sec. %d ms.\n",
                numbers.length, firstTimer.minutes, firstTimer.seconds, firstTimer.milliseconds);


        //bubble sort + jumpsearch
        sortEngine.setSortMethod(new BubbleSort());
        searchEngine.setSearchMethod(new JumpSearch());

        firstTimer.start();
        //sortEngine.sort(phoneBook);
        firstTimer.stop();

        String sortTime = String.format("%d min. %d sec. %d ms.",
                firstTimer.minutes, firstTimer.seconds, firstTimer.milliseconds);

        secondTimer.start();

        //numbers = searchEngine.searchNumbers(phoneBook, find);

        secondTimer.stop();

        String searchTime = String.format("%d min. %d sec. %d ms.",
                secondTimer.minutes, secondTimer.seconds, secondTimer.milliseconds);

        firstTimer.stop();

        String resultTime = String.format("%d min. %d sec. %d ms.",
                firstTimer.minutes, firstTimer.seconds, firstTimer.milliseconds);

        System.out.println("Start searching (bubble sort + jump search)...");
        System.out.printf("Found %d / 500 entries.Time taken:  %s\n", numbers.length, resultTime);
        System.out.printf("Sorting time: %s\n", sortTime);
        System.out.printf("Searching time: %s\n", searchTime);

        //quick sort + binary search
        sortEngine.setSortMethod(new QuickSort());
        searchEngine.setSearchMethod((new BinarySearch()));
        firstTimer.start();
        sortEngine.sort(phoneBook);
        firstTimer.stop();

        File sortDirectory = new File("C:\\Users\\Leonid\\IdeaProjects\\Phone Book\\Phone Book\\task\\src\\sortDirectory.txt");
        File findNumbers = new File("C:\\Users\\Leonid\\IdeaProjects\\Phone Book\\Phone Book\\task\\src\\numbers.txt");

        try (FileWriter writer = new FileWriter(sortDirectory)) {
            for (String s : phoneBook) {
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }

        sortTime = String.format("%d min. %d sec. %d ms.",
                firstTimer.minutes, firstTimer.seconds, firstTimer.milliseconds);

        secondTimer.start();

        numbers = searchEngine.searchNumbers(phoneBook, find);

        secondTimer.stop();

        try (FileWriter writer = new FileWriter(findNumbers)) {
            for (String s : numbers) {
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }

        searchTime = String.format("%d min. %d sec. %d ms.",
                secondTimer.minutes, secondTimer.seconds, secondTimer.milliseconds);

        firstTimer.stop();

        resultTime = String.format("%d min. %d sec. %d ms.",
                firstTimer.minutes, firstTimer.seconds, firstTimer.milliseconds);

        System.out.println("Start searching (quick sort + binary search)...");
        System.out.printf("Found %d / 500 entries.Time taken:  %s\n", numbers.length, resultTime);
        System.out.printf("Sorting time: %s\n", sortTime);
        System.out.printf("Searching time: %s\n", searchTime);

    }
}

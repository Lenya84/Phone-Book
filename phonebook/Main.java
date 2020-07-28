package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void main(String[] args) {
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.setSearchMethod(new LinearSeach());
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

        long lStartTime = System.currentTimeMillis();

        String[] numbers = searchEngine.searchNumbers(phoneBook, find);

        long lEndTime = System.currentTimeMillis();

        long output = lEndTime - lStartTime;

        long milliseconds = output % 1000;
        long seconds = (int) (output / 1000) % 60 ;
        long minutes = (int) ((output / (1000*60)) % 60);

        System.out.printf("Start searching...\n" +
                "Found %d / 500 entries. Time taken: %d min. %d sec. %d ms.",
                numbers.length, minutes, seconds, milliseconds);
    }
}

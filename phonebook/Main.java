package phonebook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {

    static SearchEngine searchEngine = new SearchEngine();
    static SortEngine sortEngine = new SortEngine();

    static Timer firstTimer = new Timer();
    static Timer secondTimer = new Timer();

    final static String pathToDirectory = "C:\\Users\\Leonid\\IdeaProjects\\Phone Book\\Phone Book\\task\\src\\directory.txt";
    final static String pathToFind = "C:\\Users\\Leonid\\IdeaProjects\\Phone Book\\Phone Book\\task\\src\\find.txt";

    static File sortDirectory = new File("C:\\Users\\Leonid\\IdeaProjects\\Phone Book\\Phone Book\\task\\src\\sortDirectory.txt");
    static File findNumbers = new File("C:\\Users\\Leonid\\IdeaProjects\\Phone Book\\Phone Book\\task\\src\\numbers.txt");

    static String[] phoneBook = new String[0];
    static String[] find = new String[0];
    static String[] numbers;

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void printInFile(File file, String[] arr) {
        try (FileWriter writer = new FileWriter(file)) {
            for (String s : arr) {
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }


    public static void linearSearch() {
        searchEngine.setSearchMethod(new LinearSearch());

        firstTimer.start();
        numbers = searchEngine.searchNumbers(phoneBook, find);
        firstTimer.stop();

        printInFile(findNumbers, numbers);

        System.out.printf("Start searching (linear search)...\n" +
                        "Found %d / 500 entries. Time taken: %d min. %d sec. %d ms.\n",
                numbers.length, firstTimer.minutes, firstTimer.seconds, firstTimer.milliseconds);
    }

    public static void bubbleSortAndJumpSearch() {
        sortEngine.setSortMethod(new BubbleSort());
        searchEngine.setSearchMethod(new JumpSearch());

        firstTimer.start();
        //sortEngine.sort(phoneBook.clone());
        firstTimer.stop();

        secondTimer.start();
        //numbers = searchEngine.searchNumbers(phoneBook, find);
        secondTimer.stop();

        printInFile(sortDirectory, phoneBook);
        printInFile(findNumbers, numbers);

        String sortTime = firstTimer.getTime();
        String searchTime = secondTimer.getTime();
        String resultTime = Timer.getSumTime(firstTimer, secondTimer);

        System.out.println("Start searching (bubble sort + jump search)...");
        System.out.printf("Found %d / 500 entries.Time taken:  %s\n", numbers.length, resultTime);
        System.out.printf("Sorting time: %s\n", sortTime);
        System.out.printf("Searching time: %s\n", searchTime);
    }

    public static void quickSortAndBinarySearch() {
        sortEngine.setSortMethod(new QuickSort());
        searchEngine.setSearchMethod((new BinarySearch()));

        firstTimer.start();
        sortEngine.sort(phoneBook);
        firstTimer.stop();

        secondTimer.start();
        numbers = searchEngine.searchNumbers(phoneBook.clone(), find);
        secondTimer.stop();

        printInFile(sortDirectory, phoneBook);
        printInFile(findNumbers, numbers);

        String sortTime = firstTimer.getTime();
        String searchTime = secondTimer.getTime();
        String resultTime = Timer.getSumTime(firstTimer, secondTimer);

        System.out.println("Start searching (quick sort + binary search)...");
        System.out.printf("Found %d / 500 entries.Time taken:  %s\n", numbers.length, resultTime);
        System.out.printf("Sorting time: %s\n", sortTime);
        System.out.printf("Searching time: %s\n", searchTime);
    }

    public static void hashTable() {
        HashTable<String> hashTable = new HashTable<>(phoneBook.length);

        firstTimer.start();
        for (String line : phoneBook) {
            String number = line.substring(0, line.indexOf(" "));
            String name = line.substring(line.indexOf(" ") + 1);
            hashTable.put(name, number);
        }
        firstTimer.stop();

        secondTimer.start();
        for (int i = 0; i < find.length; i++) {
            numbers[i] = find[i] + ":" + hashTable.get(find[i]);
        }
        secondTimer.stop();

        printInFile(findNumbers, numbers);

        String creatingTime = firstTimer.getTime();
        String searchTime = secondTimer.getTime();
        String resultTime = Timer.getSumTime(firstTimer, secondTimer);

        System.out.println("Start searching (hash table)...");
        System.out.printf("Found %d / 500 entries.Time taken:  %s\n", numbers.length, resultTime);
        System.out.printf("Creating time: %s\n", creatingTime);
        System.out.printf("Searching time: %s\n", searchTime);
    }

    public static void main(String[] args) {

        try {
            phoneBook = readFileAsString(pathToDirectory).split("\n");
            find = readFileAsString(pathToFind).split("\n");
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }

        numbers = new String[find.length];

        //linear search
        linearSearch();

        //bubble sort + jumpsearch
        bubbleSortAndJumpSearch();

        //quick sort + binary search
        quickSortAndBinarySearch();

        //hash table
        hashTable();

    }
}

package phonebook;

public interface SortMethod {
    void sort(String[] phonebook);
}

class BubbleSort implements SortMethod {

    @Override
    public void sort(String[] phonebook) {
        for (int i = 0; i < phonebook.length - 1; i++) {
            for (int j = 0; j < phonebook.length - i - 1; j++) {
                if (phonebook[j].split(" ")[1].compareToIgnoreCase(phonebook[j + 1].split(" ")[1]) > 0) {
                    String temp = phonebook[j + 1];
                    phonebook[j + 1] = phonebook[j];
                    phonebook[j] = temp;
                }
            }
        }
    }
}

class QuickSort implements SortMethod {

    @Override
    public void sort(String[] phonebook) {
        quickSort(phonebook, 0, phonebook.length - 1);
    }

    public void quickSort(String[] phonebook, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(phonebook, left, right); // the pivot is already on its place
            quickSort(phonebook, left, pivotIndex - 1);  // sort the left subarray
            quickSort(phonebook, pivotIndex + 1, right); // sort the right subarray
        }
    }

    private static int partition(String[] array, int left, int right) {
        String pivot = array[right].substring(array[right].indexOf(" ") + 1);
        int partitionIndex = left;

        /* move large values into the right side of the array */
        for (int i = left; i < right; i++) {
            if (pivot.compareTo(array[i].substring(array[i].indexOf(" ") + 1)) >= 0) {
                swap(array, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(array, partitionIndex, right);

        return partitionIndex;
    }

    private static void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

class SortEngine {

    private SortMethod sortMethod;

    public void setSortMethod(SortMethod sortMethod) {
        this.sortMethod = sortMethod;
    }

    void sort(String[] phoneBook) {
        sortMethod.sort(phoneBook);
    }
}

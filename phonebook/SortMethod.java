package phonebook;

public interface SortMethod {
    void sort(String[] phonebook);
}

class BubbleSort implements SortMethod {

    @Override
    public void sort(String[] phonebook) {
        for (int i = 0; i < phonebook.length - 1; i++) {
            for (int j = 0; j < phonebook.length - i - 1; j++) {
                char letterOfCurrentName = phonebook[j].charAt(phonebook[j].indexOf(" ") + 1);
                char letterOfNextName = phonebook[j + 1].charAt(phonebook[j + 1].indexOf(" ") + 1);
                if (letterOfCurrentName > letterOfNextName) {
                    String temp = phonebook[j + 1];
                    phonebook[j + 1] = phonebook[j];
                    phonebook[j] = temp;
                }
            }
        }
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

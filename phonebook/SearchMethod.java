package phonebook;

public interface SearchMethod {
    String[] searchNumbers(String[] phoneBook, String[] names);
}

class LinearSeach implements SearchMethod{

    @Override
    public String[] searchNumbers(String[] phoneBook, String[] names) {
        String[] numbers = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            for (String line : phoneBook) {
                if (line.contains(names[i])) {
                    numbers[i] = line.split(" ")[0];
                    break;
                }
            }
        }

        return numbers;
    }
}

class SearchEngine {

    private SearchMethod searchMethod;

    public void setSearchMethod(SearchMethod searchMethod) {
        this.searchMethod = searchMethod;
    }

    String[] searchNumbers(String[] phoneBook, String[] names) {
        return searchMethod.searchNumbers(phoneBook, names);
    }
}
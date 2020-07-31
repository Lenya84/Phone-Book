package phonebook;

public interface SearchMethod {
    String[] searchNumbers(String[] phoneBook, String[] names);
}

class LinearSearch implements SearchMethod{

    @Override
    public String[] searchNumbers(String[] phoneBook, String[] names) {
        String[] numbers = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            for (String line : phoneBook) {
                if (line.contains(names[i])) {
                    numbers[i] = line;
                    break;
                }
            }
        }

        return numbers;
    }
}

class JumpSearch implements SearchMethod {

    private String jumpSearch(String[] phonebook, String targetName) {
        int currentRight = 0;
        int prevRight = 0;

        if (phonebook.length == 0) {
            return null;
        }

        if (phonebook[currentRight].equals(phonebook[0].split(" ")[1])) {
            return phonebook[0].split(" ")[0];
        }

        int jumpLength = (int) Math.sqrt(phonebook.length);

        while (currentRight < phonebook.length - 1) {
            currentRight = Math.min(phonebook.length - 1, currentRight + jumpLength);

            String name = phonebook[currentRight].split(" ")[1];
            if (name.charAt(0) >= targetName.charAt(0)) {
                for (int i = currentRight; i > prevRight; i--) {
                    if (phonebook[i].split(" ")[1].equals(targetName)) {
                        return phonebook[i];
                    }
                }
                return null;
            }
        }

        if ((currentRight == phonebook.length - 1)
                && targetName.charAt(0) > phonebook[currentRight].split(" ")[1].charAt(0)) {
            return null;
        }

        return null;

    }

    @Override
    public String[] searchNumbers(String[] phoneBook, String[] names) {
        String[] numbers = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            numbers[i] = jumpSearch(phoneBook, names[i]);
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
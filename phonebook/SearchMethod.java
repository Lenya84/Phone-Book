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

        if (phonebook[currentRight].equals(phonebook[currentRight].substring(phonebook[currentRight].indexOf(" ") + 1))) {
            return phonebook[0].split(" ")[0];
        }

        int jumpLength = (int) Math.sqrt(phonebook.length);

        while (currentRight < phonebook.length - 1) {
            currentRight = Math.min(phonebook.length - 1, currentRight + jumpLength);

            if (phonebook[currentRight].substring(phonebook[currentRight].indexOf(" ") + 1).compareToIgnoreCase(targetName) > 0) {
                for (int i = currentRight; i > prevRight; i--) {
                    if (phonebook[i].split(" ")[1].equals(targetName)) {
                        return phonebook[i];
                    }
                }
                return null;
            }
        }

        if ((currentRight == phonebook.length - 1)
                && targetName.compareTo(phonebook[currentRight].substring(phonebook[currentRight].indexOf(" ") + 1)) > 0) {
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

class BinarySearch implements SearchMethod {

    @Override
    public String[] searchNumbers(String[] phoneBook, String[] names) {
        String[] numbers = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            numbers[i] = binarySearch(phoneBook, names[i], 0, phoneBook.length - 1);
        }

        return numbers;
    }

    public String binarySearch(String[] array, String targetName, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right) >>> 1;
        String arrayName = array[mid].substring(array[mid].indexOf(" ") + 1);

        if (targetName.equals(arrayName)) {
            return array[mid];
        } else if (targetName.compareTo(arrayName) < 0) {
            return binarySearch(array, targetName, left, mid - 1); // go to the left subarray
        } else {
            return binarySearch(array, targetName, mid + 1, right); // go the the right subarray
        }
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

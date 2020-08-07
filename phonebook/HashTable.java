package phonebook;
import java.util.Scanner;

class TableEntry<T> {
    private final String key;
    private final T value;
    private boolean removed;

    public TableEntry(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public void remove() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }
}

class HashTable<T> {
    private int size;
    private TableEntry[] table;

    public HashTable(int size) {
        this.size = size;
        table = new TableEntry[size];
    }

    public boolean put(String key, T value) {
        int idx = findKey(key);

        if (idx == -1) {
            rehash();
            idx = findKey(key);
        }

        table[idx] = new TableEntry<>(key, value);
        return true;
    }

    public T get(String key) {
        int idx = findKey(key);

        if (idx == -1 || table[idx] == null) {
            return null;
        }

        return (T) table[idx].getValue();
    }

        public void remove(String key) {
        int idx = findKey(key);

        if (idx != -1 && table[idx] != null) {
            table[idx].remove();
            table[idx] = null;
        }
    }

    private int findKey(String key) {
        int hash = 7;

        for (int i = 0; i < key.length(); i++) {
            hash = hash*31 + key.charAt(i);
            hash = hash % size;
        }

        int firstIndex = hash;

        while (!(table[hash] == null || key.equals(table[hash].getKey()))) {
            hash = (hash + 1) % size;

            if (hash == firstIndex) {
                return -1;
            }
        }

        return hash;
    }

    private void rehash() {
        this.size *= 2;
        TableEntry[] oldTable = table;
        table = new TableEntry[size];

        for (int i = 0; i < oldTable.length; i++) {
            put(oldTable[i].getKey(), (T) oldTable[i].getValue());
        }
    }

    @Override
    public String toString() {
        StringBuilder tableStringBuilder = new StringBuilder();

        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                tableStringBuilder.append(i + ": null");
            } else {
                tableStringBuilder.append(i + ": key=" + table[i].getKey()
                        + ", value=" + table[i].getValue()
                        + ", removed=" + table[i].isRemoved());
            }

            if (i < table.length - 1) {
                tableStringBuilder.append("\n");
            }
        }

        return tableStringBuilder.toString();
    }
}

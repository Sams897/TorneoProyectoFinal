package model;
import java.util.ArrayList;
import java.util.List;

public class OpenAddressingHashTable { 
    private List<HashEntry> table;
    private int size;

    private static class HashEntry {
        String key;
        int value;

        HashEntry(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public OpenAddressingHashTable() {
        this.size = 21;
        table = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            table.add(null);
        }
    }

    public int get(String key) {
        int hash = getHashCode(key);
        while (table.get(hash) != null && !table.get(hash).key.equals(key)) {
            hash = (hash + 1) % size;
        }
        if (table.get(hash) == null) {
            return -1;
        } else {
            return table.get(hash).value;
        }
    }

    public void add(String key, int value) {
        int hash = getHashCode(key);
        while (table.get(hash) != null && !table.get(hash).key.equals(key)) {
            hash = (hash + 1) % size;
        }
        table.set(hash, new HashEntry(key, value));

        
        if (loadFactor() > 0.7) {
            resize();
        }
    }

    public void remove(String key) {
        int hash = getHashCode(key);
        while (table.get(hash) != null && !table.get(hash).key.equals(key)) {
            hash = (hash + 1) % size;
        }
        if (table.get(hash) != null) {
            table.set(hash, null);
        }
    }

    public boolean search(String key, int value) {
        int hash = getHashCode(key);
        int startHash = hash;
        while (table.get(hash) != null) {
            if (table.get(hash).key.equals(key) && table.get(hash).value == value) {
                return true;
            }
            hash = (hash + 1) % size;
            if (hash == startHash) {
                break;
            }
        }
        return false;
    }

    public void print() {
        System.out.println("[Hash Table]");
        for (int i = 0; i < size; i++) {
            HashEntry entry = table.get(i);
            if (entry == null) {
                System.out.println("Index : [" + i + "] Key: [] Value: []");
            } else {
                System.out.println("Index : [" + i + "] Key: [" + entry.key + "] Value: [" + entry.value + "]");
            }
        }
    }

    private int getHashCode(String key) {
        int sumaValoresASCII = 0;
        for (int i = 0; i < key.length(); i++) {
            char letra = key.charAt(i);
            int valorASCII = (int) letra;
            sumaValoresASCII += valorASCII;
        }
        return sumaValoresASCII % size;
    }

    private double loadFactor() {
        int filled = 0;
        for (HashEntry entry : table) {
            if (entry != null) {
                filled++;
            }
        }
        return (double) filled / size;
    }

    private void resize() {
        List<HashEntry> oldTable = table;
        size = size * 2;
        table = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            table.add(null);
        }
        for (HashEntry entry : oldTable) {
            if (entry != null) {
                add(entry.key, entry.value);
            }
        }
    }
}

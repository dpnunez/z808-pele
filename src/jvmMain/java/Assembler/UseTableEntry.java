package Assembler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UseTableEntry {
    private final String label;
    private final char signal;
    private ArrayList<Short> occurrences;

    public UseTableEntry(String label, char signal) {
        this.label = label;
        this.signal = signal;
        this.occurrences = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public char getSignal() { return signal; }

    public Short getOccurrence() {
        Short occurrence = occurrences.get(0);
        occurrences.remove(0);
        return occurrence;
    }

    public ArrayList<Short> getOccurrences() { return occurrences; }

    public void updateOccurrences(Short address) {
        this.occurrences.add(address);
    }
}

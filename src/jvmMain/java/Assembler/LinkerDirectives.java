package Assembler;

import java.util.ArrayList;
import java.util.HashSet;
public class LinkerDirectives {
    public ArrayList<String> linkerDirectives;

    public LinkerDirectives() {
        linkerDirectives = new ArrayList<>();
        linkerDirectives.add("PUBLIC");
        linkerDirectives.add("EXTRN");
    }

    public boolean isLinkerDirective(String ld) { return linkerDirectives.contains(ld); }
}

package Assembler;

import Assembler.pseudoInstructions.PseudoInstruction;
import Assembler.pseudoInstructions.Segment;

import java.util.ArrayList;
import java.util.List;

public class PseudoInstructions {
    private List<PseudoInstruction> list = new ArrayList<>();

    public PseudoInstructions(){
        PseudoInstruction segmentCS = new Segment("CS");
        list.add(segmentCS);
        PseudoInstruction segmentDS = new Segment("DS");
        list.add(segmentDS);
        PseudoInstruction segmentSS = new Segment("SS");
        list.add(segmentSS);
    }

    public boolean containsInstruction(String i){
        for (PseudoInstruction instrucao : list) {
            if (instrucao instanceof Segment) {
                if (((Segment) instrucao).getNameStart().equals(i) || ((Segment) instrucao).getNameEnd().equals(i)){
                    return true;
                }
            }
        }
        return false;
    }
    public PseudoInstruction getPseudoInstruction(String name){
        for (PseudoInstruction instrucao : list) {
            if (instrucao instanceof Segment) {
                if (((Segment) instrucao).getNameStart().equals(name) || ((Segment) instrucao).getNameEnd().equals(name)) {
                    return instrucao;
                }
            }
        }
        return null;
    }

    // ver se contem na lista true e false

    // get

    // set
}

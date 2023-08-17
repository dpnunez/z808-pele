package Assembler;

import Assembler.pseudoInstructions.DW;
import Assembler.pseudoInstructions.PseudoInstruction;
import Assembler.pseudoInstructions.Segment;

import java.util.ArrayList;
import java.util.List;

public class PseudoInstructions {
    private List<PseudoInstruction> list = new ArrayList<>();
    private String[] tipos = {"SEGMENT", "END", "DW"};

    public PseudoInstructions(){
        PseudoInstruction segmentCS = new Segment("CS");
        list.add(segmentCS);
        PseudoInstruction segmentDS = new Segment("DS");
        list.add(segmentDS);
        PseudoInstruction segmentSS = new Segment("SS");
        list.add(segmentSS);
    }

    public boolean containsInstruction(String name){
        for (String tipo : tipos) {
            if (tipo.equals(name)){
                return true;
            }
        }
        return false;
    }
    public PseudoInstruction getPseudoInstruction(String name){
        for (PseudoInstruction instrucao : list) {
                if (instrucao.getName().equals(name)) {
                    return instrucao;
                }
        }
        return null;
    }

    public void addListDW(String name, String operand){
        DW dw = new DW(name);
        if (!operand.equals("?")){
            dw.setVariavel(Short.parseShort(operand));
        }
        list.add(dw);
    }

    // ver se contem na lista true e false

    // get

    // set
}

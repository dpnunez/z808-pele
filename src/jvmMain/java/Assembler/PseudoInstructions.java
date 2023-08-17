package Assembler;

import Assembler.pseudoInstructions.DW;
import Assembler.pseudoInstructions.Equ;
import Assembler.pseudoInstructions.PseudoInstruction;
import Assembler.pseudoInstructions.Segment;

import java.util.ArrayList;
import java.util.List;

public class PseudoInstructions {
    private final List<PseudoInstruction> list = new ArrayList<>();
    private final String[] tips = {"SEGMENT", "END", "DW", "EQU"};

    public PseudoInstructions(){
        PseudoInstruction segmentCS = new Segment("CS");
        list.add(segmentCS);
        PseudoInstruction segmentDS = new Segment("DS");
        list.add(segmentDS);
        PseudoInstruction segmentSS = new Segment("SS");
        list.add(segmentSS);
    }

    public boolean containsInstruction(String name){
        for (String tipo : tips) {
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

    public void addListDWorDUP(String name, String operand){
        if (!operand.contains("DUP")){
            DW dw = new DW(name);
            if (!operand.equals("?")){
                dw.setVariable(operand);
            }
            list.add(dw);
        } else {
            // Se for DUP vai criar <contador> vezes o dw (nome0, nome1,...)
            String[] tokens = operand.split("-+");
            short count = Short.parseShort(tokens[0]);
            for (int i = 0; i < count; i++) {
                DW dw = new DW(name.concat(String.valueOf(i)), tokens[2]);
                System.out.println(dw.getName());
                list.add(dw);
            }
        }

    }
    public void addListEQU(String name, String operand){
        Equ equ = new Equ(name, operand);
        list.add(equ);
    }
}

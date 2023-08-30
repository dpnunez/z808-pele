package Assembler;

import Assembler.pseudoInstructions.*;

import java.util.ArrayList;
import java.util.List;

public class PseudoInstructions {
    private final List<PseudoInstruction> list = new ArrayList<>();
    private final String[] tips = {"SEGMENT", "END", "DW", "EQU", "ORG", "ASSUME", "PROC", "ENDP"};

    public PseudoInstructions(){
        PseudoInstruction segmentCS = new Segment("CS");
        list.add(segmentCS);
        PseudoInstruction segmentDS = new Segment("DS");
        list.add(segmentDS);
        PseudoInstruction segmentSS = new Segment("SS");
        list.add(segmentSS);
    }

    public boolean containsInstruction(String name){
        for (String tip : tips) {
            if (tip.equals(name)){
                return true;
            }
        }
        return false;
    }
    public PseudoInstruction getPseudoInstruction(String name){
        for (PseudoInstruction instruction : list) {
                if (instruction.getName().equals(name)) {
                    return instruction;
                }
        }
        return null;
    }

    public void addListDWorDUP(String name, String operand, short PC){
        if (!operand.contains("DUP")){
            DW dw = new DW(name, PC);
            if (!operand.equals("?")){
                dw.setVariable(operand);
            }
            list.add(dw);
        } else {
            // Se for DUP vai criar <contador> vezes o dw (nome0, nome1,...)
            // Operador deve ter - no lugar do espaço (0-DUP-10)
            String[] parts = operand.split("-+");
            if (parts[0].equals("OFFSET")){
                DW dw = new DW(name, Short.toString(offSet(parts[1])), PC );
                list.add(dw);
            }else {
                short count = Short.parseShort(parts[0]);
                for (int i = 0; i < count; i++) {
                    DW dw = new DW(name.concat(String.valueOf(i)), parts[2], PC);
                    System.out.println(dw.getName());
                    list.add(dw);
                }
            }
        }

    }
    private Short offSet(String label){
        PseudoInstruction pseudoInstruction = getPseudoInstruction(label);
        Short address = null;
        // Procura o Segmento que se encontra
        for (PseudoInstruction instruction : list) {
            if (instruction instanceof Segment){
                if (((Segment) instruction).getEnd() != null){
                    // não entendi mt bem essa parte... tem que calcular o endereço com aqui em baixo?
                    // ou poderia apenas pegar o endereço direto
                    address = (short) (((Segment) instruction).getStart() - pseudoInstruction.getAddress());
                }
            }
        }
        return address;
    }
    public void addListEQU(String name, String operand){
        Equ equ = new Equ(name, operand);
        list.add(equ);
    }
    public Short org(String operand, short PC){
        if (!operand.isEmpty()) {
            if (operand.charAt(0) == '$' && (operand.length() > 1)) {
                if (operand.charAt(1) == '+' && (operand.length() > 2)) {
                    operand = operand.substring(2);
                    PC += Short.parseShort(operand);
                    return PC;
                } else {
                    operand = operand.substring(1);
                    PC = Short.parseShort(operand);
                    return PC;
                }
            }
        }
        System.out.println("invalid PC value.");
        throw new RuntimeException("invalid PC value.");
    }
    public void assume(String operand, Short PC) {
        if (!operand.isEmpty()) {
            String[] parts = operand.split(":+");
            Segment pseudoInstruction = (Segment) getPseudoInstruction(parts[1]);
            addListDWorDUP(parts[0], Short.toString(pseudoInstruction.getStart()), PC);
        }
    }

    public void procEndp(String label, Short PC) {
        ProcEndp pseudoInstruction = (ProcEndp) getPseudoInstruction(label);
        if (pseudoInstruction == null) {
            ProcEndp procendp = new ProcEndp(label, PC, PC);
            list.add(procendp);
        } else {
            pseudoInstruction.setEnd(PC);
        }
    }
}
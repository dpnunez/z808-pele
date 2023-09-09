package Macro;

public class MacroParameter {
    private String name;
    private int level;
    private int order;
    private String pair;

    // for formal parameters
    public MacroParameter(String name, int level, int order) {
        this.name = name;
        this.level = level;
        this.order = order;
        this.pair = "#(" + level + "," + order + ")";
    }

    // for actual parameters
    public MacroParameter(String value, String pair) {
        this.name = value;
        this.level = pair.charAt(2);
        this.order = pair.charAt(4);
        this.pair = pair;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getOrder() {
        return order;
    }

    public String getPair() {
        return pair;
    }
}
package adhdmc.simpledye.util;

import org.bukkit.DyeColor;

public enum SDColor {
    RED("red", DyeColor.RED, "<#B02E26>"),
    ORANGE("orange", DyeColor.ORANGE, "<#F9801D>"),
    YELLOW("yellow", DyeColor.YELLOW, "<#FED83D>"),
    LIME("lime", DyeColor.LIME, "<#80C71F>"),
    GREEN("green", DyeColor.GREEN, "<#5E7C16>"),
    CYAN("cyan", DyeColor.CYAN, "<#169C9C>"),
    LIGHT_BLUE("light_blue", DyeColor.LIGHT_BLUE, "<#3AB3DA>"),
    BLUE("blue", DyeColor.BLUE, "<#3C44AA>"),
    PURPLE("purple", DyeColor.PURPLE, "<#8932B8>"),
    MAGENTA("magenta", DyeColor.MAGENTA, "<#C74EBD>"),
    PINK("pink", DyeColor.PINK, "<#F38BAA>"),
    WHITE("white", DyeColor.WHITE, "<#FFFFFF>"),
    LIGHT_GRAY("light_gray", DyeColor.LIGHT_GRAY, "<#9D9D97>"),
    GRAY("gray", DyeColor.GRAY, "<#474F52>"),
    BLACK("black", DyeColor.BLACK, "<#1D1D21>"),
    BROWN("brown", DyeColor.BROWN, "<#835432>");
    private final String color;
    private final DyeColor dyeColor;
    private final String msgColor;
    SDColor(String color, DyeColor dyeColor, String msgColor) {
        this.color = color;
        this.dyeColor = dyeColor;
        this.msgColor = msgColor;
    }

    public DyeColor getDyeColor() {
        return dyeColor;
    }

    public String getColor() {
        return color;
    }

    public String getMsgColor() {
        return msgColor;
    }
}

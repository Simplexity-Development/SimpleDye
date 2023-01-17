package adhdmc.simpledye.util;

import org.bukkit.DyeColor;

public enum SDColor {
    SD_RED("red", DyeColor.RED, "<#B02E26>"),
    SD_ORANGE("orange", DyeColor.ORANGE, "<#F9801D>"),
    SD_YELLOW("yellow", DyeColor.YELLOW, "<#FED83D>"),
    SD_LIME("lime", DyeColor.LIME, "<#80C71F>"),
    SD_GREEN("green", DyeColor.GREEN, "<#5E7C16>"),
    SD_CYAN("cyan", DyeColor.CYAN, "<#169C9C>"),
    SD_LIGHT_BLUE("light_blue", DyeColor.LIGHT_BLUE, "<#3AB3DA>"),
    SD_BLUE("blue", DyeColor.BLUE, "<#3C44AA>"),
    SD_PURPLE("purple", DyeColor.PURPLE, "<#8932B8>"),
    SD_MAGENTA("magenta", DyeColor.MAGENTA, "<#C74EBD>"),
    SD_PINK("pink", DyeColor.PINK, "<#F38BAA>"),
    SD_WHITE("white", DyeColor.WHITE, "<#FFFFFF>"),
    SD_LIGHT_GRAY("light_gray", DyeColor.LIGHT_GRAY, "<#9D9D97>"),
    SD_GRAY("gray", DyeColor.GRAY, "<#474F52>"),
    SD_BLACK("black", DyeColor.BLACK, "<#1D1D21>"),
    SD_BROWN("brown", DyeColor.BROWN, "<#835432>")
    ;
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

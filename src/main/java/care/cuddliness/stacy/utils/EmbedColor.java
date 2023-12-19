package care.cuddliness.stacy.utils;

public enum EmbedColor {
    PRIMARY("#BEADFA"), SECONDARY("#82A0D8"), WARNING("#EFB495"), ERROR("#EF9595"), SUCCESS("#C8E4B2")
    ;

    private String color;

    EmbedColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

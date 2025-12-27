package br.maestro.ren.data;

public record SliderItem(
        String subheading,
        String header,
        String description,
        String action1,
        String action2,
        String backgroundImg,
        String backgroundStyle,
        boolean centered) {

    public SliderItem(String subheading, String header, String description,
                      String action1, String action2, String backgroundImg) {
        this(subheading, header, description, action1, action2, backgroundImg, null,
                false);
    }
}

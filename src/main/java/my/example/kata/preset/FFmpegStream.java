package my.example.kata.preset;

import java.util.Objects;

public class FFmpegStream {

    private int width;
    private  int height;
    private  String displayAspectRatio;

    public int getWidth() {
        return width;
    }

    public FFmpegStream setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public FFmpegStream setHeight(int height) {
        this.height = height;
        return this;
    }

    public String getDisplayAspectRatio() {
        return displayAspectRatio;
    }

    public FFmpegStream setDisplayAspectRatio(String displayAspectRatio) {
        this.displayAspectRatio = displayAspectRatio;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FFmpegStream that = (FFmpegStream) o;
        return width == that.width && height == that.height && displayAspectRatio.equals(that.displayAspectRatio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, displayAspectRatio);
    }


    @Override
    public String toString() {
        return "FFmpegStream{" +
                "width=" + width +
                ", height=" + height +
                ", displayAspectRatio='" + displayAspectRatio + '\'' +
                '}';
    }
}

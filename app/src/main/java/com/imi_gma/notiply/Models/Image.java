package com.imi_gma.notiply.Models;

/**
 * Merges all pixel changes of all Users here.
 */
public class Image implements Drawable {
    private int[] argb;
    private int height;
    private int width;

    public int[] getARGB() {
        return argb;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setARGB(int[] newARGB) {
        this.argb = newARGB;
    }

    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    public void setWidth(int newWidth) {
        this.width = newWidth;
    }
}

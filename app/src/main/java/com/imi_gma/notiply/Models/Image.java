package com.imi_gma.notiply.Models;

/**
 * Merges all pixel changes of all Users here.
 */
public class Image implements Drawable {
    private int[] argb;
    private int height;
    private int width;

    @Override
    public int[] getARGB() {
        return argb;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setARGB(int[] newARGB) {
        this.argb = newARGB;
    }

    @Override
    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    @Override
    public void setWidth(int newWidth) {
        this.width = newWidth;
    }
}

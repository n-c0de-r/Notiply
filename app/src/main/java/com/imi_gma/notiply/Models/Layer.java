package com.imi_gma.notiply.Models;

/**
 * Saves User's pixel changes to the canvas.
 * Exchanges these changes with host device.
 * Merges changes down to image class.
 */
public class Layer implements Drawable {
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

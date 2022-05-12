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

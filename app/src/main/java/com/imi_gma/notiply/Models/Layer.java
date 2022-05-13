package com.imi_gma.notiply.Models;

import android.graphics.Color;

/**
 * Saves User's pixel changes to the canvas.
 * Exchanges these changes with host device.
 * Merges changes down to image class.
 */
public class Layer implements DrawableInterface {
    private int[] argb;
    private int height;
    private int width;

    @Override
    public void draw(int x, int y, int width, Color color) {
        int pos = y * width + x;
        // argb[pos] = Color.valueOf(color);
    }

    @Override
    public void erase(int x, int y, int width) {
        int pos = y * width + x;
    }

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

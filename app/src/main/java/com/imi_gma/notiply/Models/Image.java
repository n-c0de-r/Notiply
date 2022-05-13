package com.imi_gma.notiply.Models;

import android.graphics.Color;

/**
 * Merges all pixel changes of all Users here.
 */
public class Image implements DrawableInterface {
    private int[] argb;
    private int height;
    private int width;

    @Override
    public void draw(int x, int y, int width, Color color) {
        return;
    }

    @Override
    public void erase(int x, int y, int width) {
        return;
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

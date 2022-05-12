package com.imi_gma.notiply.Models;

import android.graphics.Color;

/**
 * Data class of the drawing pixels.
 * Initial color is black, line width is 1px.
 */
public class Brush {
    private Color brushColor = new Color();
    private int brushWidth = 1;

    /**
     * Gets the currently set brush color.
     * @return  Current brush color.
     */
    public Color getBrushColor() {
        return brushColor;
    }

    /**
     * Gets the currently set brush width.
     * @return  Current brush width as integer.
     */
    public int getBrushWidth() {
        return brushWidth;
    }

    /**
     * Sets the brush color to a new color
     * @param newColor  New color to set.
     */
    public void setBrushColor(Color newColor) {
        brushColor = newColor;
    }

    /**
     * Sets the brush color to a new width.
     * @param newWidth  New width to set.
     */
    public void setBrushWidth(int newWidth) {
        brushWidth = newWidth;
    }
}
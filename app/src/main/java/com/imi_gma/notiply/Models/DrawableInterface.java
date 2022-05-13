package com.imi_gma.notiply.Models;

import android.graphics.Color;

/**
 * Interface to different drawable classes,
 * such as Image and Layer.
 */
public interface DrawableInterface {

    /**
     * Allows drawing on a layer by user input.
     */
    void draw(int x, int y, int width, Color color);

    /**
     * Erases pixels from a layer by user input.
     */
    void erase(int x, int y, int width);

    /**
     * Get array of pixels from the drawable object.
     * @return  Array of pixels.
     */
    int[] getARGB();

    /**
     * Get height of the drawable object.
     * @return  Integer of height.
     */
    int getHeight();

    /**
     * Get width of the drawable object.
     * @return  Integer of width.
     */
    int getWidth();

    /**
     * Set the pixel array of the drawable object.
     * @param newARGB   New array to set.
     */
    void setARGB(int[] newARGB);

    /**
     * Set height of the drawable object.
     * @param newHeight Height to set.
     */
    void setHeight(int newHeight);

    /**
     * Set width of the drawable object.
     * @param newWidth  Width to set.
     */
    void setWidth(int newWidth);
}

package com.imi_gma.notiply.Models;

/**
 * Interface to different drawable classes,
 * such as Image and Layer.
 */
public interface DrawableInterface {
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

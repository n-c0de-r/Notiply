package com.imi_gma.notiply.Models;

import android.graphics.Bitmap;

public interface Image {
    /**
     * Initialize non-final fields
     * @param width     window width
     * @param height    window height
     */
    void init(int width, int height);

    /**
     * Returns the bitmap object
     * @return bitmap
     */
    Bitmap getBitmap();

    /**
     * Sets the current brush color
     * @param color
     */
    void setColor(int color);

    /**
     * Undo the last stroke.
     */
    void undo();

    /**
     * Fills canvas with background color and clear user's ArrayList
     * User cannot erase other's objects ever
     */
    void clear();

    /**
     * User just touched the screen
     * @param x     x position of touch
     * @param y     y position of touch
     */
    void touchStart(float x, float y);

    /**
     * Handle the movement performed after the first touch
     * @param x     x position of touch
     * @param y     y position of touch
     */
    void touchMove(float x, float y);

    /**
     * Touch ended, draw path to the last known position
     */
    void touchUp();
}

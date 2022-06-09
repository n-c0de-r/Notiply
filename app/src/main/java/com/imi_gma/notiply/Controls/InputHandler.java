package com.imi_gma.notiply.Controls;

/**
 * Interface to the InputHandlerImpl Control Class.
 */
public interface InputHandler {
    /**
     * Handle any touches which are made on the CanvasView object.
     * @param x - the x-ordinate of the touch.
     * @param y - the y-ordinate of the touch.
     * @param action - the action being performed in the touch.
     */
    void handleTouches(float x, float y, int action);

    /**
     * Initialize Image settings
     */
    void initImage(int width, int height);

    /**
     * Sets the current brush color
     * @param color
     */
    void setBrushColor(int color);

    /**
     * Undo the last brush stroke
     */
    void undoStroke();

    /**
     * Clears the current image
     */
    void clearImage();

    /**
     * Saves image to a file on device
     * @return String   save path
     */
    String saveImage();
}

package com.imi_gma.notiply.Models;

import android.graphics.Path;

/**
 * Class which holds the path, color, and stroke to draw
 */
class Brush {
    private final int color;
    private final int width;

    private final Path path;

    /**
     * Constructor for the DrawPath class.
     * @param color
     * @param width
     * @param path
     */
    public Brush(int color, int width, Path path) {
        this.color = color;
        this.width = width;
        this.path = path;
    }

    /**
     * Get the color of the path to be drawn.
     * @return int  color to draw with
     */
    public int getColor() {
        return color;
    }

    /**
     * Returns the width of the path to be drawn.
     * @return int  brush width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the path object to be drawn.
     * @return Path path to draw
     */
    public Path getPath() {
        return path;
    }
}
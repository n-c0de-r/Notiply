package com.imi_gma.notiply.Controls;

import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.imi_gma.notiply.Models.Image;

/**
 * Controls the inputs and hands them on
 */
public class InputHandlerImpl implements InputHandler{
    private final Image image;
    private final Persistence persist;

    public InputHandlerImpl(Image newImage) {
        persist = Persistence.getInstance();
        image = newImage;
    }

    @Override
    public void handleTouches(float x, float y, int action) {
        switch (action) {
            // determine which action is being performed and redraw the canvas
            case MotionEvent.ACTION_DOWN:
                image.touchStart(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                image.touchMove(x, y);
                break;
            case MotionEvent.ACTION_UP:
                image.touchUp();
                break;
        }
    }

    @Override
    public void initImage(int width, int height) {
        image.init(width, height);
    }

    @Override
    public void setBrushColor(int color) {
        image.setColor(color);
    }

    public void undoStroke() {
        image.undo();
    }

    @Override
    public void clearImage(){
        image.clear();
    }

    @Override
    public String saveImage() {
        Bitmap bitmap = image.getBitmap();
        return persist.saveImage(bitmap);
    }
}
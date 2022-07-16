package com.imi_gma.notiply.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * All drawing happens here.
 */
public class ImageImpl extends View implements Image {

    private static final int BG_COLOR = Color.WHITE;
    private static final int DEFAULT_WIDTH = 10;
    private static final int TOUCH_TOLERANCE = 5;

    private final ArrayList<Brush> userPaths;
    private final ArrayList<Brush> othersPaths;
    private final Paint paintBrush;
    private Bitmap bitmap;
    private Canvas canvas;
    private Path path;

    private int brushColor;
    private int brushWidth;

    private float posX, posY;

    /**
     * Constructor sets up final objects
     * @param context   app context
     * @param attrs     xml attributes
     */
    public ImageImpl(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // Initialize finals
        paintBrush = new Paint();
        paintBrush.setAntiAlias(true);
        paintBrush.setStyle(Paint.Style.STROKE);
        paintBrush.setColor(Color.BLACK);
        paintBrush.setStrokeWidth(DEFAULT_WIDTH);
        paintBrush.setStrokeCap(Paint.Cap.ROUND);
        paintBrush.setStrokeJoin(Paint.Join.ROUND);

        userPaths = new ArrayList<>();
        othersPaths = new ArrayList<>();
    }

    @Override
    public void init(int width, int height) {
        brushColor = Color.BLACK;
        brushWidth = DEFAULT_WIDTH;
        paintBrush.setColor(brushColor);
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        clear();
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public void setColor(int color) {
        brushColor = color;
        paintBrush.setColor(brushColor);
    }

    // For testing
    public int getBrushColor() {
        return paintBrush.getColor();
    }
    public void setBrushWidth(int width) {
        brushWidth = width;
        paintBrush.setStrokeWidth(brushWidth);
    }

    public int getBrushWidth() {
        return (int) paintBrush.getStrokeWidth();
    }

    @Override
    public void undo() {
        if (userPaths.size() > 0) {
            // remove last stroke from list
            userPaths.remove(userPaths.size() - 1);

            // clear canvas
            canvas.drawColor(BG_COLOR);

            // loop through Brushes list
            for (Brush brush : userPaths) {
                // redraw all others
                drawPath(brush);
            }
        }
    }

    @Override
    public void clear() {
        // reset the canvas to background color
        canvas.drawColor(BG_COLOR);
        // empty the brush stroke list and redraw the canvas
        userPaths.clear();
        invalidate();
    }

    @Override
    public void touchStart(float x, float y) {
        // create a new Path object
        path = new Path();
        // create a new Brush object
        Brush brush = new Brush(brushColor, brushWidth, path);
        userPaths.add(brush);
        // just move to the coordinates, no drawing here
        path.moveTo(x, y);
        // update global positions
        posX = x;
        posY = y;
        invalidate();
    }

    @Override
    public void touchMove(float x, float y) {
        // calculate differences of current and last touch
        float dx = Math.abs(x - posX);
        float dy = Math.abs(y - posY);

        // if any difference is greater than touch tolerance...
        if ((dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE)) {
            // ...draw line to new position
            path.lineTo(x, y);
            // update global positions
            posX = x;
            posY = y;
        }
        invalidate();
    }

    @Override
    public void touchUp() {
        path.lineTo(posX, posY);
        invalidate();
    }

    /**
     * Draws a given brush, setup and all
     * @param brush
     */
    private void drawPath(Brush brush) {
        // get information of current object to draw
        paintBrush.setColor(brush.getColor());
        paintBrush.setStrokeWidth(brush.getWidth());
        // draw the current path with current settings
        canvas.drawPath(brush.getPath(), paintBrush);
        invalidate();
    }

    /**
     * Draws latest path to screen
     * @param paramCanvas
     */
    @Override
    protected void onDraw(Canvas paramCanvas) {
        paramCanvas.save();

        // Guard against accidental access
        if (userPaths.size() != 0) {
            // get the most recently drawn path
            Brush lastPath = userPaths.get(userPaths.size()-1);
            // draw it
            drawPath(lastPath);
        }
        // draw the bitmap to the canvas
        paramCanvas.drawBitmap(bitmap, 0, 0, paintBrush);
        paramCanvas.restore();
    }
}
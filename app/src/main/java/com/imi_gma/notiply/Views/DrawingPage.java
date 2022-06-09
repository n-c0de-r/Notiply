package com.imi_gma.notiply.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imi_gma.notiply.Controls.InputHandler;
import com.imi_gma.notiply.Controls.InputHandlerImpl;
import com.imi_gma.notiply.Models.ImageImpl;
import com.imi_gma.notiply.R;

public class DrawingPage extends AppCompatActivity {

    InputHandler inputHandler;
    FloatingActionButton btnPaintBrush,btnColorPicker, btnPaintDelete,
            btnColorBlack, btnColorBlue, btnColorGreen, btnColorRed, btnColorYellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_page);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        ImageImpl image = findViewById(R.id.imageView);
        inputHandler = new InputHandlerImpl(image);
        inputHandler.initImage(width, height);
        image.setOnTouchListener((view, motionEvent) -> {
            // Guard so only one finger is used
            if (motionEvent.getPointerCount() == 1) {
                inputHandler.handleTouches(motionEvent.getX(), motionEvent.getY(), motionEvent.getAction());
            }
            return true;
        });

        initButtons();
        setListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.draw_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_undo:
                inputHandler.undoStroke();
                return true;
            case R.id.menu_delete:
                inputHandler.clearImage();
                return true;
            case R.id.menu_save:
                inputHandler.saveImage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initButtons() {
        btnPaintBrush = findViewById(R.id.btnPaintBrush);
        btnColorPicker = findViewById(R.id.btnColorPicker);
        btnPaintDelete = findViewById(R.id.btnPaintDelete);

        btnColorBlack = findViewById(R.id.btnColorBlack);
        btnColorBlue = findViewById(R.id.btnColorBlue);
        btnColorGreen = findViewById(R.id.btnColorGreen);
        btnColorRed = findViewById(R.id.btnColorRed);
        btnColorYellow = findViewById(R.id.btnColorYellow);
    }

    private void setListeners() {
        btnPaintBrush.setOnClickListener(view -> {
            Toast.makeText(this, "Paint Brush Clicked", Toast.LENGTH_SHORT).show();
        });

        btnColorPicker.setOnClickListener(view -> {
            Toast.makeText(this, "Color Picker Clicked", Toast.LENGTH_SHORT).show();
        });

        btnPaintDelete.setOnClickListener(view -> {
            inputHandler.clearImage();
        });

        btnColorBlack.setOnClickListener(view -> {
            Toast.makeText(this, "" + R.color.black, Toast.LENGTH_SHORT).show();
            inputHandler.setBrushColor(R.color.black);
        });

        btnColorBlue.setOnClickListener(view -> {
            int color = (int) R.color.green & 0xFF;
            Toast.makeText(this, "" + color, Toast.LENGTH_SHORT).show();
            inputHandler.setBrushColor(color);
        });

        btnColorGreen.setOnClickListener(view -> {
            inputHandler.setBrushColor(R.color.green);
        });

        btnColorRed.setOnClickListener(view -> {
            inputHandler.setBrushColor(R.color.red);
        });

        btnColorYellow.setOnClickListener(view -> {
            inputHandler.setBrushColor(R.color.yellow);
        });
    }
}
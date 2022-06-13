package com.imi_gma.notiply.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imi_gma.notiply.Controls.InputHandler;
import com.imi_gma.notiply.Controls.InputHandlerImpl;
import com.imi_gma.notiply.Models.ImageImpl;
import com.imi_gma.notiply.R;

public class DrawingPage extends AppCompatActivity {
    public static final int READ_STORAGE_CODE = 108;
    public static final int WRITE_STORAGE_CODE = 109;

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
        String infoText;
        switch (item.getItemId()) {
            case R.id.menu_undo:
                inputHandler.undoStroke();
                return true;
            case R.id.menu_delete:
                inputHandler.clearImage();
                return true;
            case R.id.menu_save:
                if (isWriteStoragePermissionGranted()) {
                    infoText = inputHandler.saveImage();
                    Toast.makeText(this, "Saved in " + infoText, Toast.LENGTH_SHORT).show();
                    inputHandler.saveImage();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initButtons() {
        btnPaintBrush = findViewById(R.id.btnPaintBrush);
        btnColorPicker = findViewById(R.id.btnColorPicker);
        btnPaintDelete = findViewById(R.id.btnPaintDelete);

//        btnColorBlack = findViewById(R.id.btnColorBlack);
//        btnColorBlue = findViewById(R.id.btnColorBlue);
//        btnColorGreen = findViewById(R.id.btnColorGreen);
//        btnColorRed = findViewById(R.id.btnColorRed);
//        btnColorYellow = findViewById(R.id.btnColorYellow);
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

//        btnColorBlack.setOnClickListener(view -> {
//            inputHandler.setBrushColor(R.color.black);
//        });
//
//        btnColorBlue.setOnClickListener(view -> {
//            inputHandler.setBrushColor(R.color.blue);
//        });
//
//        btnColorGreen.setOnClickListener(view -> {
//            inputHandler.setBrushColor(R.color.green);
//        });
//
//        btnColorRed.setOnClickListener(view -> {
//            inputHandler.setBrushColor(R.color.red);
//        });
//
//        btnColorYellow.setOnClickListener(view -> {
//            inputHandler.setBrushColor(R.color.yellow);
//        });
    }

    private  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                requestPermissions( new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_STORAGE_CODE);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }
}
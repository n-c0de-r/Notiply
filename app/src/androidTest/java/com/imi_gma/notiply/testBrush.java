package com.imi_gma.notiply;

import static org.junit.Assert.assertEquals;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.imi_gma.notiply.Controls.Persistence;
import com.imi_gma.notiply.Models.ImageImpl;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class testBrush {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    AppCompatActivity a = new AppCompatActivity();
    ImageImpl image = a.findViewById(R.id.imageView);

    @Test
    public void testBrushWidth() {
        int width = 4;
        image.setBrushWidth(width);

        assertEquals(image.getBrushWidth(), width);
    }

    @Test
    public void testBrushColor() {
        int color = Color.BLACK;
        image.setColor(color);

        assertEquals(image.getBrushColor(), color);
    }

    @Test
    public void testBrushPath() {
        // TODO: make a test
    }
}
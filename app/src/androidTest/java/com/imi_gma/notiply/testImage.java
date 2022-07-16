package com.imi_gma.notiply;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.imi_gma.notiply.Models.ImageImpl;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class testImage {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    AppCompatActivity a = new AppCompatActivity();
    ImageImpl image = a.findViewById(R.id.imageView);

    @Test
    public void testImageTouch() {
        // TODO: implement a test
    }

    @Test
    public void testImageDelete() {
        // TODO: implement a test
    }

    @Test
    public void testImageUndo() {
        // TODO: implement a test
    }
}
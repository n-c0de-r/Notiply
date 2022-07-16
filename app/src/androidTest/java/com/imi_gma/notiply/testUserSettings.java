package com.imi_gma.notiply;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.imi_gma.notiply.Controls.Persistence;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class testUserSettings {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    SharedPreferences prefs = appContext.getSharedPreferences("testNotiplySettings", appContext.MODE_PRIVATE);
    Persistence pers = Persistence.getInstance();

    @Test
    public void testSetUser() {
        pers.saveUserData(prefs, "ThisIsATest");
    }

    @Test
    public void testGetUser() {
        String name = pers.getUserData(prefs);
        assertEquals("ThisIsATest", name);
    }

    public void testSetMACAddress() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        WifiManager wifiManager = (WifiManager) appContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String macAddress = wInfo.getMacAddress();

        SharedPreferences prefs = appContext.getSharedPreferences("testNotiplySettings", appContext.MODE_PRIVATE);
        Persistence pers = Persistence.getInstance();
        pers.saveUserData(prefs, "01:23:45:67:89:AB");

        String name = pers.getUserData(prefs);
        assertEquals("01:23:45:67:89:AB", name);
    }
}
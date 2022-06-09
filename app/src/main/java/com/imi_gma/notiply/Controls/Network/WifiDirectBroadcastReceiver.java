package com.imi_gma.notiply.Controls.Network;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.imi_gma.notiply.Views.FindPage;

public class WifiDirectBroadcastReceiver extends BroadcastReceiver {
    private WifiP2pManager p2pManager;
    private WifiP2pManager.Channel p2pChannel;
    private FindPage callingActivity;

    public WifiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel, FindPage activity) {
        p2pManager = manager;
        p2pChannel = channel;
        callingActivity = activity;
    }

    @Override
    public void onReceive(Context ctx, Intent intent) {
        String action = intent.getAction();

        if (p2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(p2pManager.EXTRA_WIFI_STATE, -1);
            if (state != p2pManager.WIFI_P2P_STATE_ENABLED) {
                // Determine if Wifi P2P mode is enabled or not
                Toast.makeText(ctx, "You need to enable Wifi!", Toast.LENGTH_SHORT).show();
            }
        } else if (p2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            // The peer list has changed!
            if (p2pManager != null) {
                int permissionStatus = ActivityCompat.checkSelfPermission(callingActivity, Manifest.permission.ACCESS_COARSE_LOCATION);

                if (permissionStatus == PackageManager.PERMISSION_DENIED) {
                    String permission = Manifest.permission.ACCESS_COARSE_LOCATION;
                    ActivityCompat.requestPermissions(callingActivity, new String[] {permission}, callingActivity.COARSE_LOCATION);
                    //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
                } else {
                    p2pManager.requestPeers(p2pChannel, callingActivity.peerListListener);
                }
            }
        } else if (p2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            // Connection state changed!
            if(p2pManager == null) {
                return;
            }
            NetworkInfo networkInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);

            if (networkInfo.isConnected()) {
                p2pManager.requestConnectionInfo(p2pChannel, callingActivity.connectionInfoListener);
            } else {
                callingActivity.connectionStatus.setText("Connection lost");
            }

        }
    }
}

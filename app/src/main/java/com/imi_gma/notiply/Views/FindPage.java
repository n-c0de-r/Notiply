package com.imi_gma.notiply.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.imi_gma.notiply.Controls.Network.Connection;
import com.imi_gma.notiply.Controls.Network.ConnectionManager;
import com.imi_gma.notiply.Controls.Network.WifiDirectBroadcastReceiver;
import com.imi_gma.notiply.R;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FindPage extends AppCompatActivity {

    public static final int FINE_LOCATION_CODE = 101;
    public static final int COARSE_LOCATION = 102;
    public static final int ACCESS_NETWORK_CODE = 103;
    public static final int CHANGE_NETWORK_CODE = 104;
    public static final int ACCESS_WIFI_CODE = 105;
    public static final int CHANGE_WIFI_CODE = 106;
    public static final int INTERNET_CODE = 107;

    private  final int MESSAGE_READ = 1;

    Button sendButton, findPeers;
    ListView peersList;
    public TextView connectionStatus, messageText;
    EditText inputText;

    WifiP2pManager p2pManager;
    WifiP2pManager.Channel p2pChannel;
    public WifiP2pManager.PeerListListener peerListListener;
    public WifiP2pManager.ConnectionInfoListener connectionInfoListener;
    BroadcastReceiver broadcastReceiver;
    IntentFilter intentFilter;

    List<WifiP2pDevice> devices;
    String[] deviceNames;
    WifiP2pDevice[] deviceArray;

    Connection connection;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_page);

        // Go to Wifi Settings to turn it on
        //Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        //startActivity(intent);

        init();
        initListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FINE_LOCATION_CODE) {
            // if the results are not empty and have been granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // do stuff
            }
        }
    }

    private void init() {
        sendButton = findViewById(R.id.btn_SendMessage);
        findPeers = findViewById(R.id.btn_FindPeers);
        peersList = findViewById(R.id.list_peerlist);
        connectionStatus = findViewById(R.id.txt_WifiStatus);
        messageText = findViewById(R.id.txt_messageText);
        inputText = findViewById(R.id.edit_MessageInput);

        p2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        p2pChannel = p2pManager.initialize(this, getMainLooper(), null);
        broadcastReceiver = new WifiDirectBroadcastReceiver(p2pManager, p2pChannel, this);

        intentFilter = new IntentFilter();
        intentFilter.addAction(p2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(p2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(p2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        devices = new ArrayList<>();

        handler = new Handler(message -> {
            switch(message.what) {
                case MESSAGE_READ:
                    byte[] readBuffer = (byte[]) message.obj;
                    String tempMsg = new String(readBuffer, 0, message.arg1);
                    messageText.setText(tempMsg);
                    break;
            }
            return true;
        });
    }

    private void initListeners() {
        findPeers.setOnClickListener(view -> {
            int permissionStatus = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE);

            if (permissionStatus == PackageManager.PERMISSION_DENIED) {
                String permission = Manifest.permission.ACCESS_WIFI_STATE;
                ActivityCompat.requestPermissions(this, new String[] {permission}, ACCESS_WIFI_CODE);
            } else {
                p2pManager.discoverPeers(p2pChannel, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        connectionStatus.setText("Searching peers");
                    }

                    @Override
                    public void onFailure(int i) {
                        connectionStatus.setText("Searching peers failed");
                    }
                });
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = inputText.getText().toString();
                connection.sendData(msg.getBytes());
            }
        });

        peerListListener = wifiP2pDeviceList -> {
            Collection<WifiP2pDevice> newDevices = wifiP2pDeviceList.getDeviceList();
            if (!newDevices.equals(devices)) {
                devices.clear();

                deviceNames = new String[newDevices.size()];
                deviceArray = new WifiP2pDevice[newDevices.size()];
                int index = 0;
                for (WifiP2pDevice device : newDevices) {
                    deviceNames[index] = device.deviceName;
                    deviceArray[index] = device;
                    index++;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_expandable_list_item_1, deviceNames);
                peersList.setAdapter(adapter);
            }
            if (newDevices.size() == 0) {
                Toast.makeText(getApplicationContext(), "No devices found.", Toast.LENGTH_SHORT).show();
            }
        };

        int wifiPermissionStatus = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE);
        // Check Permission for Location and GPS
        int locationPermissionStatus = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        // Check Permission for Location and GPS

        if (wifiPermissionStatus == PackageManager.PERMISSION_DENIED) {
            String permission = Manifest.permission.ACCESS_WIFI_STATE;
            ActivityCompat.requestPermissions(this, new String[] {permission}, ACCESS_WIFI_CODE);
        }

        if (locationPermissionStatus == PackageManager.PERMISSION_DENIED) {
            String permission = Manifest.permission.ACCESS_FINE_LOCATION;
            ActivityCompat.requestPermissions(this, new String[] {permission}, FINE_LOCATION_CODE);
        }

        peersList.setOnItemClickListener((adapterView, view, i, l) -> {
            final WifiP2pDevice device = deviceArray[i];
            WifiP2pConfig config = new WifiP2pConfig();
            config.deviceAddress = device.deviceAddress;

                p2pManager.connect(p2pChannel, config, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Connected to " + device.deviceName, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i) {
                        Toast.makeText(getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
                    }
                });
        });

        // TODO connection is never set! Code never reached, NullPointerException
        connectionInfoListener = (wifiP2pInfo -> {
            final InetAddress groupOwnerAddress = wifiP2pInfo.groupOwnerAddress;
                if (wifiP2pInfo.groupFormed && wifiP2pInfo.isGroupOwner) {
                    connectionStatus.setText("Host");
                } else if (wifiP2pInfo.groupFormed && !wifiP2pInfo.isGroupOwner) {
                    connectionStatus.setText("Client");
                }
                connection = new ConnectionManager(groupOwnerAddress, 8888);
            });

    }
}
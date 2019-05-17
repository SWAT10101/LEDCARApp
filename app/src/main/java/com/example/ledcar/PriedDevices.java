package com.example.ledcar;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;

import java.util.Set;

public class PriedDevices {

    String TAG = "PriedDevices";

    private BluetoothAdapter bluetoothAdapter;

    public PriedDevices(BluetoothAdapter bluetoothAdapter) {
        this.bluetoothAdapter = bluetoothAdapter;
    }


    public String[] devices()
    {
        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        String [] nameOfDevice = new String[devices.size()];
        int index = 0;

        if(devices.size() > 0)
        {
            for(BluetoothDevice device:devices)
            {
                nameOfDevice[index] = device.getName() + "\n" + device.getAddress();
                Log.d(TAG, device.getName() + "-----" + device.getAddress());
                index++;
            }

        }
        return nameOfDevice;

    }



}

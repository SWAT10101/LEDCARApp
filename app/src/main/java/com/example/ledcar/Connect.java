package com.example.ledcar;


import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;


public class Connect {

    private static final String TAG = "Connect: ";
    private BluetoothDevice device;
    private static BluetoothSocket socket;
    private BluetoothAdapter bluetoothAdapter;
    private String MAC;
    private UUID MY_UUID;
    private ConnectedThread connectedThread;




    private static boolean isConnect = false;

     static  boolean connection() {
        return isConnect;
    }





     Connect( UUID MY_UUID1 , BluetoothAdapter bluetoothAdapter1, String MAC1)
    {

        MY_UUID = MY_UUID1;
        bluetoothAdapter = bluetoothAdapter1;
        MAC = MAC1;
        device = bluetoothAdapter.getRemoteDevice(MAC);
        Log.d(TAG, "Initialize constrictor..");


    }

     void connectDevice()
    {
        try
        {
            socket = device.createRfcommSocketToServiceRecord(MY_UUID);
            socket.connect();
            connectedThread = new ConnectedThread(socket);
            connectedThread.start();

            Log.d(TAG, "You have connected with: " + MAC);
            isConnect = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Log.d(TAG, "An error has occurred e : " + e);
            isConnect = false;
        }

    }

     void disConnect()
    {
        try
        {
            socket.close();
            Log.d(TAG, "Bluetooth was disconnected ");
            isConnect = false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    // handler to get message from input stream
    @SuppressLint("HandlerLeak")
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if(msg.what == 0)
            {
                String message = (String) msg.obj;
                MainActivity.tvchat.setText(message);
            }


        }
    };



    protected  static class ConnectedThread extends Thread {

        private static InputStream mmInStream;
        private static  OutputStream mmOutputStream;

        ConnectedThread(BluetoothSocket socket) {

            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException ignored) {

            }

            mmInStream = tmpIn;
            mmOutputStream = tmpOut;
        }

        public void run() {


            while (true) {
                try
                {

                    mmInStream = Connect.socket.getInputStream();
                    InputStreamReader aReader = new InputStreamReader( mmInStream );
                    BufferedReader mBufferedReader = new BufferedReader( aReader );
                    String total = mBufferedReader.readLine();

                    Message message = Message.obtain();
                    message.obj = total;

                    handler.sendMessage(message);



                    Log.d(TAG,  total);

                }
                catch (IOException e)
                {
                    Log.d(TAG, "Input stream was disconnected", e);
                    break;
                }
            }


        }

        static void thaySend(String color)
        {
            byte[] msgBuffer = color.getBytes();
            try
            {
                mmOutputStream.write(msgBuffer);
            }
            catch (IOException ignored){

            }
        }



    }





}

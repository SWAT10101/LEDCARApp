package com.example.ledcar;



import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.UUID;
import eltos.simpledialogfragment.color.SimpleColorDialog;
import eltos.simpledialogfragment.list.SimpleListDialog;

public class MainActivity extends AppCompatActivity implements  SimpleColorDialog.OnDialogResultListener{

    private static final String TAG = "MainActivity:";
    private static final String COLOR_DIALOG = "dialogTagColor", CHOICE_DIALOG = "dialogTagChoice";
    private static final int REQUEST_ENABLE_BT = 1;
    int color = 0xff008577;
    TextView tvSendColor;

    @SuppressLint("StaticFieldLeak")
    public static TextView tvRecive;
    @SuppressLint("StaticFieldLeak")
    public static ImageView ivLED;

    MaterialButton material_button_Bluetooth, riandowBT, fradRed, fradOrange, fradYellow, fradBlue, fradWhiteBlue,
                   fradLightBlue, fradClosetoBlue, fradPurple, fradBluishPurple, fradClosetoPink,
                   fradWhitePink, fradPink, fradGreen, fradWhiteGreen, fradWhite, police;

    CollapsingToolbarLayout collapsing_toolbar;
    AppBarLayout appbar;
    Toolbar toolbar;
    BluetoothAdapter mBluetoothAdapter;



   UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
   PriedDevices all;
   Connect connectTo;




    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        all = new PriedDevices(mBluetoothAdapter);


        tvRecive = findViewById(R.id.tvchat);
        tvSendColor = findViewById(R.id.tvSendColor);


        appbar = findViewById(R.id.appbar);
        collapsing_toolbar = findViewById(R.id.collapsing_toolbar);

        material_button_Bluetooth = findViewById(R.id.material_button_Bluetooth);
        riandowBT = findViewById(R.id.riandowBT);
        police = findViewById(R.id.police);
        fradRed =findViewById(R.id.fradRed);
        fradOrange = findViewById(R.id.fradOrange);
        fradYellow = findViewById(R.id.fradYellow);
        fradBlue = findViewById(R.id.fradBlue);
        fradWhiteBlue = findViewById(R.id.fradWhiteBlue);
        fradLightBlue = findViewById(R.id.fradLightBlue);
        fradClosetoBlue = findViewById(R.id.fradClosetoBlue);
        fradPurple = findViewById(R.id.fradPurple);
        fradBluishPurple = findViewById(R.id.fradBluishPurple);
        fradClosetoPink = findViewById(R.id.fradClosetoPink);
        fradWhitePink = findViewById(R.id.fradWhitePink);
        fradPink = findViewById(R.id.fradPink);
        fradGreen = findViewById(R.id.fradGreen);
        fradWhiteGreen = findViewById(R.id.fradWhiteGreen);
        fradWhite = findViewById(R.id.fradWhite);



        ivLED = findViewById(R.id.ivLED);

        // set name for tool bar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Car LED");


        if(mBluetoothAdapter == null){
            Log.d(TAG, "enableDisableBT: Does not have BT capabilities.");
            Toast.makeText(this, "enableDisableBT: Does not have Bluetooth capabilities.", Toast.LENGTH_LONG).show();
            //finish();
        }
        else
        {
            if(!mBluetoothAdapter.isEnabled())
            {
                Log.d(TAG, "enableDisableBT: enabling BT.");
                Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBTIntent, REQUEST_ENABLE_BT);
            }
        }

        riandowColorAnmtion(riandowBT);

        policeAnmtion(police);


        fradRed.post(new Runnable() {
            @Override
            public void run() {
                //set the animation on Button
                Animation anim = new AlphaAnimation(0.0f, 1.0f);

                anim.setDuration(2000); //You can manage the time of the blink with this parameter
                anim.setStartOffset(20);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);

                fradRed.startAnimation(anim);
                fradOrange.startAnimation(anim);
                fradYellow.startAnimation(anim);
                fradWhiteGreen.setAnimation(anim);
                fradGreen.setAnimation(anim);
                fradWhiteBlue.startAnimation(anim);
                fradLightBlue.startAnimation(anim);
                fradClosetoBlue.startAnimation(anim);
                fradBlue.startAnimation(anim);
                fradBluishPurple.startAnimation(anim);
                fradPurple.setAnimation(anim);
                fradClosetoPink.startAnimation(anim);
                fradWhitePink.startAnimation(anim);
                fradPink.startAnimation(anim);
                fradWhite.startAnimation(anim);
            }
        });

    }




    // Riandow Amntion
    private void riandowColorAnmtion(final MaterialButton MTB)
    {

        int red = getResources().getColor(R.color.red);
        int orange = getResources().getColor(R.color.orange);
        int yellow = getResources().getColor(R.color.yellow);
        int whiteGreen = getResources().getColor(R.color.whiteGreen);
        int green = getResources().getColor(R.color.green);
        int lightBlue = getResources().getColor(R.color.lightBlue);
        int whiteBlue = getResources().getColor(R.color.whiteBlue);
        int closetoBlue = getResources().getColor(R.color.closetoBlue);
        int blue = getResources().getColor(R.color.blue);
        int Purple = getResources().getColor(R.color.Purple);
        int bluishPurple = getResources().getColor(R.color.bluishPurple);
        int closetoPink = getResources().getColor(R.color.closetoPink);
        int pink = getResources().getColor(R.color.pink);
        int red2 = getResources().getColor(R.color.red);


        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), red, orange,
                yellow, whiteGreen, green, lightBlue, whiteBlue, closetoBlue, blue, Purple, bluishPurple, closetoPink, pink, red2);


        colorAnimation.setDuration(30000); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                MTB.setBackgroundTintList(ColorStateList.valueOf((int) animator.getAnimatedValue()));
            }
        });

        colorAnimation.setRepeatCount(Animation.INFINITE);
        colorAnimation.start();
    }


    private void policeAnmtion(final MaterialButton MTB)
    {
        int k = getResources().getColor(R.color.black);
        int r = getResources().getColor(R.color.red);
        int w = getResources().getColor(R.color.white);
        int b = getResources().getColor(R.color.blue);

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), k, r, k, r, k, r, w, k, k, b, k, b, k, b);


        colorAnimation.setDuration(1100); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                MTB.setBackgroundTintList(ColorStateList.valueOf((int) animator.getAnimatedValue()));
            }
        });

        colorAnimation.setRepeatCount(Animation.INFINITE);
        colorAnimation.start();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {
            Toast.makeText(this, "Bluetooth is enable." , Toast.LENGTH_SHORT).show();
        }

        if(resultCode == CANCELED)
        {
            Toast.makeText(this, "To use this app you need to enable bluetooth first." , Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, requestCode + "  " + resultCode);
    }




    // ==   C o l o r s   ==
    public void showColorPicker(View view) {

        SimpleColorDialog.build()
                .title("Pick Color")
                .colorPreset(color)
                .allowCustom(true)
                .show(this, COLOR_DIALOG);

    }



    private void newColor(int color){
        this.color = color;

        String hexColor = String.format("#%06X", (0xFFFFFF & color));


        int barColor = Color.parseColor(hexColor);
        appbar.setBackgroundColor(barColor);
        collapsing_toolbar.setContentScrimColor(barColor);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            float[] hsv = new float[3];
            Color.colorToHSV(color, hsv);
            hsv[2] *= 0.75;
            getWindow().setStatusBarColor(Color.HSVToColor(hsv));
        }
    }




    @Override
    public boolean onResult(@NonNull String dialogTag, int which, @NonNull Bundle extras) {

        if(dialogTag.equals(COLOR_DIALOG))
        {
            if(Connect.connection())
            {
              newColor(extras.getInt(SimpleColorDialog.COLOR));
              String hexColor = String.format("#%06X", (0xFFFFFF & extras.getInt(SimpleColorDialog.COLOR)));


                // == To get RGB color value ======================================
                int r = Integer.valueOf( hexColor.substring( 1, 3 ), 16 );
                int g = Integer.valueOf( hexColor.substring( 3, 5 ), 16 );
                int b = Integer.parseInt( hexColor.substring( 5, 7 ), 16 );

                double a = 1 - (0.299 * r + 0.587 * g + 0.114 * b) / 255;
                if (a < 0.5)
                {
                   tvSendColor.setTextColor(Color.BLACK);
                   collapsing_toolbar.setCollapsedTitleTextColor(Color.BLACK);
                   collapsing_toolbar.setExpandedTitleColor(Color.BLACK);
                } else
                {
                    tvSendColor.setTextColor(Color.WHITE);
                    collapsing_toolbar.setCollapsedTitleTextColor(Color.WHITE);
                    collapsing_toolbar.setExpandedTitleColor(Color.WHITE);
                }
                //-----------------------------------------------------------------------------


              tvSendColor.setText(hexColor);

              Log.d(TAG, "You are in color dialog");
              Connect.ConnectedThread.thaySend(   tvSendColor.getText().toString()  + " 0");   // send color + mode number
              return true;
            }
            else
            {
                Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
            }



        }

        if(dialogTag.equals(CHOICE_DIALOG))
        {
            String generalInformation = String.valueOf(extras.getStringArrayList(SimpleListDialog.SELECTED_LABELS)); // to get all name and address
            if(!generalInformation.equals("[]"))
            {

                String addressMac = generalInformation.substring(generalInformation.length() - 18); // to get mac address only
                String mac = addressMac.substring(0,17); // to remove the ] in last from mac address

                Log.d(TAG, "You are in CHOICE_DIALOG");


                    connectTo = new Connect(MY_UUID, mBluetoothAdapter, mac);
                    connectTo.connectDevice();
                    material_button_Bluetooth.setIconResource(R.drawable.ic_bluetooth_connected_black_24dp);
                    Toast.makeText(this, "You have connected with: " + mac, Toast.LENGTH_LONG).show();


            }

        }


        return false;
    }


    public void showBluetooth(View view) {


        if(Connect.connection())
        {
            connectTo.disConnect();
            material_button_Bluetooth.setIconResource(R.drawable.ic_bluetooth_black_24dp);
        }
        else
        {
            //show dialog
            SimpleListDialog.build()
                    .title("Device")
                    .choiceMode(SimpleListDialog.SINGLE_CHOICE_DIRECT)
                    .items(all.devices())
                    .show(this, CHOICE_DIALOG);

        }


    }


    public void mbRianDow(View view) {

        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( "No color 19");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbPolice(View view) {

        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( "No color 18");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbFeadRed(View view) {

        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.red))) + " 1");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbFeadOrange(View view) {

        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.orange))) + " 2");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbFeadYellow(View view) {

        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.yellow))) + " 3");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbFeadWhiteGreen(View view) {

        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.whiteGreen))) + " 4");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }


    public void mbFeadGreen(View view) {
        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.green))) + " 5");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbFeadWhiteBlue(View view) {
        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.whiteBlue))) + " 6");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbFeadLightBlue(View view) {
        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.lightBlue))) + " 7");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbFeadClosetoBlue(View view) {
        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.closetoBlue))) + " 8");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbFeadBlue(View view) {
        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.blue))) + " 9");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbFeadBluishPurple(View view) {
        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.bluishPurple))) + " 10");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbFeadPurple(View view) {
        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.Purple))) + " 11");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbFeadClosetoPink(View view) {
        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.closetoPink))) + " 12");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbFeadWhitePink(View view) {
        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.whitePink))) + " 13");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbFeadPink(View view) {
        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.pink))) + " 14");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbFeadWhite(View view) {
        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.white))) + " 15");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }

    public void mbOff(View view) {

        if(Connect.connection())
        {
            Connect.ConnectedThread.thaySend( String.format("#%06X", (0xFFFFFF & getResources().getColor( R.color.black))) + " 16");   // send color + mode number

        }
        else
        {
            Toast.makeText(this, "Connect to Device first", Toast.LENGTH_LONG).show();
        }
    }
}

package com.example.ledcar;



import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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
    public static TextView tvchat;
    TextView tvSendColor;
    ImageView ivLED;
    MaterialButton material_button_Bluetooth, riandowBT, fradRed, police;
    CollapsingToolbarLayout collapsing_toolbar;
    AppBarLayout appbar;
    BluetoothAdapter mBluetoothAdapter;



   UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
   PriedDevices all;
   Connect connectTo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        all = new PriedDevices(mBluetoothAdapter);


        tvchat = findViewById(R.id.tvchat);
        tvSendColor = findViewById(R.id.tvSendColor);


        appbar = findViewById(R.id.appbar);
        collapsing_toolbar = findViewById(R.id.collapsing_toolbar);

        material_button_Bluetooth = findViewById(R.id.material_button_Bluetooth);
        riandowBT = findViewById(R.id.riandowBT);
        fradRed =findViewById(R.id.fradRed);
        police = findViewById(R.id.police);

        ivLED = findViewById(R.id.ivLED);

        // set name for tool bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Car LED");


        if(mBluetoothAdapter == null){
            Log.d(TAG, "enableDisableBT: Does not have BT capabilities.");
            Toast.makeText(this, "enableDisableBT: Does not have Bluetooth capabilities.", Toast.LENGTH_LONG).show();
            finish();
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
        feadAnmtion(fradRed, R.color.red, R.color.black, R.color.red, 15000);
        policeAnmtion(police);



    }


    // Riandow Amntion
    private void riandowColorAnmtion(final MaterialButton MTB)
    {

        int red = getResources().getColor(R.color.red);
        int orange = getResources().getColor(R.color.orange);
        int yellow = getResources().getColor(R.color.yellow);
        int whiteGreen = getResources().getColor(R.color.whiteGreen);
        int bluishGreen = getResources().getColor(R.color.bluishGreen);
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
                yellow, whiteGreen, bluishGreen, green, lightBlue, whiteBlue, closetoBlue, blue, Purple, bluishPurple, closetoPink, pink, red2);


        colorAnimation.setDuration(30000); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                MTB.setBackgroundTintList(ColorStateList.valueOf((int) animator.getAnimatedValue()));
                Log.d(TAG, animator.getAnimatedValue() + "");
            }
        });

        colorAnimation.setRepeatCount(Animation.INFINITE);
        colorAnimation.start();
    }

    private void feadAnmtion(final MaterialButton MTB , int firstColor, int scoundColor, int thiedColor, int speed)
    {
        int f = getResources().getColor(firstColor);
        int s = getResources().getColor(scoundColor);
        int t = getResources().getColor(thiedColor);

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), f, s, t);


        colorAnimation.setDuration(speed); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                MTB.setBackgroundTintList(ColorStateList.valueOf((int) animator.getAnimatedValue()));
                Log.d(TAG, animator.getAnimatedValue() + "");
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


        colorAnimation.setDuration(900); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                MTB.setBackgroundTintList(ColorStateList.valueOf((int) animator.getAnimatedValue()));
                Log.d(TAG, animator.getAnimatedValue() + "");
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


        ivLED.setColorFilter(barColor);//----------------------------------------------------------->> not here color reciveed from ardinou



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


                // == To get RGB color value ==
                int r = Integer.valueOf( hexColor.substring( 1, 3 ), 16 );
                int g = Integer.valueOf( hexColor.substring( 3, 5 ), 16 );
                int b = Integer.parseInt( hexColor.substring( 5, 7 ), 16 );

                double a = 1 - (0.299 * r + 0.587 * g + 0.114 * b) / 255;
                if (a < 0.5)
                {
                   tvSendColor.setTextColor(Color.BLACK);
                } else
                {
                    tvSendColor.setTextColor(Color.WHITE);
                }
                //------------------------


              tvSendColor.setText(hexColor);

              Log.d(TAG, "You are in color dialog");
              Connect.ConnectedThread.thaySend( tvSendColor.getText().toString());
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

               // if (!isFinishing())
                //{
                    connectTo = new Connect(MY_UUID, mBluetoothAdapter, mac);
                    connectTo.connectDevice();
                    material_button_Bluetooth.setIconResource(R.drawable.ic_bluetooth_connected_black_24dp);
                    Toast.makeText(this, "You have connected with: " + mac, Toast.LENGTH_LONG).show();
               // }

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
}

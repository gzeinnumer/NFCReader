package com.gzeinnumer.nfcreader;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gzeinnumer.nr.helper.EndNFCTagging;
import com.gzeinnumer.nr.helper.NFCCallBack;
import com.gzeinnumer.nr.helper.NFCErrorCallBack;
import com.gzeinnumer.nr.helper.NFCTools;
import com.gzeinnumer.nr.lib.StartNFCTagging;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Main_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (NFCTools.checkNFCHardware(MainActivity.this)) {
            Toast.makeText(this, "NFC Support", Toast.LENGTH_SHORT).show();
            new StartNFCTagging(MainActivity.this);
        } else {
            Toast.makeText(this, "NFC Not Support", Toast.LENGTH_SHORT).show();
        }

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new StartNFCTagging(MainActivity.this, new NFCErrorCallBack() {
                    @Override
                    public void onNotSupport(String msg) {
                        //if Device not Support NFC
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        new EndNFCTagging(intent, getSupportFragmentManager()).observer(new NFCCallBack() {
            @Override
            public void callBack(String idHex, String idReversedHex, String idDec, String idReversedDec) {
                Log.d(TAG, "onNewIntent: Hexadecimal Code          " + idHex);
                Log.d(TAG, "onNewIntent: Reversed Hexadecimal Code " + idReversedHex);
                Log.d(TAG, "onNewIntent: Decimal Code              " + idDec);
                Log.d(TAG, "onNewIntent: Reversed Decimal Code     " + idReversedDec);
            }
        });
    }

}
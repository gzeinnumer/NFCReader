package com.gzeinnumer.nr.lib;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.gzeinnumer.da.dialog.confirmDialog.ConfirmDialog;
import com.gzeinnumer.nr.R;

class DialogActiveNFC {

    public DialogActiveNFC(Activity activity) {
        new ConfirmDialog(((FragmentActivity) activity)
                .getSupportFragmentManager())
                .setBtnOkTitle(activity.getResources().getString(R.string.nfc_reader_ask_active_ok))
                .setBtnCancelTitle(activity.getResources().getString(R.string.nfc_reader_ask_active_cancel))
                .setBtnOkTitleColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.colorPrimary))
                .setBtnCancelTitleColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.colorPrimary))
                .setTitle(activity.getResources().getString(R.string.nfc_reader_ask_active_title))
                .setContent(activity.getResources().getString(R.string.nfc_reader_ask_active_sub))
                .onOkPressedCallBack(new ConfirmDialog.OnOkPressed() {
                    @Override
                    public void onOkPressed() {
                        activity.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                    }
                }).show();
    }
}

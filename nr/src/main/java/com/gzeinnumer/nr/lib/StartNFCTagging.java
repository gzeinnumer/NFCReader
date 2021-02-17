package com.gzeinnumer.nr.lib;

import android.app.Activity;
import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.gzeinnumer.nr.R;
import com.gzeinnumer.nr.helper.NFCErrorCallBack;

public class StartNFCTagging {

    public StartNFCTagging(Activity activity) {
        new StartNFCTagging(activity, null);
    }

    public StartNFCTagging(Activity activity, NFCErrorCallBack nfcErrorCallBack) {
        NfcManager manager = (NfcManager) activity.getSystemService(Context.NFC_SERVICE);
        NfcAdapter adapter = manager.getDefaultAdapter();
        if (adapter != null && adapter.isEnabled()) {
            //Yes NFC available

            FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
            Fragment previous = ((FragmentActivity) activity).getSupportFragmentManager().findFragmentByTag(TaggingDialog.TAG);
            if (previous != null) {
                transaction.remove(previous);
            }
            TaggingDialog dialog = TaggingDialog.newInstance(activity);
            dialog.show(transaction, TaggingDialog.TAG);
        } else if (adapter != null && !adapter.isEnabled()) {
            //NFC is not enabled.Need to enable by the user.

            new DialogActiveNFC(activity);
        } else {
            //NFC is not supported

            if (nfcErrorCallBack != null)
                nfcErrorCallBack.onNotSupport(activity.getResources().getString(R.string.nfc_reader_msg_no_nfc));
        }
    }
}

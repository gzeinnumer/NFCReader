package com.gzeinnumer.nr.helper;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.gzeinnumer.nr.lib.TaggingDialog;

public class EndNFCTagging {
    private final Intent intent;
    private final FragmentManager supportFragmentManager;
    private NFCCallBack nfcCallBack;

    public EndNFCTagging(Intent intent, FragmentManager supportFragmentManager) {
        this.intent = intent;
        this.supportFragmentManager = supportFragmentManager;
    }

    public void observer(NFCCallBack nfcCallBack) {
        this.nfcCallBack = nfcCallBack;
        resolveIntent();
    }

    private void resolveIntent() {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {

            Fragment prev = supportFragmentManager.findFragmentByTag(TaggingDialog.TAG);
            if (prev != null) {
                DialogFragment df = (DialogFragment) prev;
                df.dismiss();
            }

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String idHex = UtilsConverter.getIdHex(tag);
            String idHexReversed = UtilsConverter.getIdReversedHex(tag);
            String idDec = UtilsConverter.getIdDec(tag);
            String idDecReversed = UtilsConverter.getIdReversedDec(tag);
            nfcCallBack.callBack(idHex, idHexReversed, idDec, idDecReversed);
        }
    }
}

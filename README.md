<p align="center">
  <img src="https://wallpaperaccess.com/full/1782494.jpg"/>
</p>

<h1 align="center">
    NFCReader
</h1>

<p align="center">
    <a><img src="https://img.shields.io/badge/Version-2.0.0-brightgreen.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/ID-gzeinnumer-blue.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/Java-Suport-green?logo=java&style=flat"></a>
    <a><img src="https://img.shields.io/badge/Kotlin-Suport-green?logo=kotlin&style=flat"></a>
    <a href="https://github.com/gzeinnumer"><img src="https://img.shields.io/github/followers/gzeinnumer?label=follow&style=social"></a>
    <br>
    <p>Simple NFC Reader.</p>
</p>

---
# Content List
* [Download](#download)
* [Feature List](#feature-list)
* [Usage](#usage)
* [Example Code/App](#example-codeapp)
* [Version](#version)
* [Contribution](#contribution)

---
# Download
Add maven `jitpack.io` and `dependencies` in `build.gradle (Project)` :
```gradle
// build.gradle project
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

// build.gradle app/module
dependencies {
  ...
  implementation 'com.github.gzeinnumer:NFCReader:version'

  //required
  implementation 'com.google.android.material:material:1.2.1'
  implementation 'com.github.gzeinnumer:EasyDialogFragment:2.0.0'
  implementation 'com.github.gzeinnumer:DialogAndroid:3.0.0'
  implementation 'com.github.gzeinnumer:SimpleMaterialStyle:2.0.0'
}
```

---
# Feature List
- [x] [Check NFC Hardware (Type 1)](#check-nfc-hardware-type-1).
- [x] [Start NFC Tagging (Type 1)](#start-nfc-tagging-type-1) Open Dialog And Start Tagging.
- [x] [Combine CheckNFCHardware and StartNFCTagging (Type 2)](#combine-checknfchardware-and-startnfctagging-type-2) .
- [x] [End NFC Tagging](#end-nfc-tagging) Get Result.

---
# Usage

### Permission
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest >

    <uses-feature android:name="android.hardware.nfc" />
    <uses-permission android:name="android.permission.NFC" />

    <application>
    </application>

</manifest>
```

#
### Check NFC Hardware (Type 1)

use `checkNFCHardware` will return `true` if NFC available.
```java
if (NFCTools.checkNFCHardware(MainActivity.this)){
    Toast.makeText(this, "NFC Support", Toast.LENGTH_SHORT).show();
} else {
    Toast.makeText(this, "NFC Not Support", Toast.LENGTH_SHORT).show();
}
```

#
### Start NFC Tagging (Type 1)

If device have the NFC, run this code `new StartNFCTagging(MainActivity.this);`
```java
if (NFCTools.checkNFCHardware(MainActivity.this)){
    new StartNFCTagging(MainActivity.this);
}
```

**Note :** Please make sure your divice have **NFC** feature before run `new StartNFCTagging(MainActivity.this);`. **Only For Type 1**.

#
### Combine [checkNFCHardware](#check-nfc-hardware-type-1) and [StartNFCTagging](#start-nfc-tagging-type-1) (Type 2)

It's save to run this code even when your device don't have NFC hardware
```java
new StartNFCTagging(MainActivity.this, new NFCErrorCallBack() {
    @Override
    public void onNotSupport(String msg) {
        //if Device not Support NFC
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
});
```

#
### End NFC Tagging

`@Override` funcion `onNewIntent` from `AppCompatActivity` in your activity. and use `EndNFCTagging` to get result.
```java
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
```

#

Sample Code [Type 1](https://gist.github.com/gzeinnumer/19b3e80a12a2a269a76e66d3d84f68b2) & [Type 2](https://gist.github.com/gzeinnumer/78268a43351ebbc593cc55a301ab88c4)

Preview :
|<img src="https://github.com/gzeinnumer/NFCReader/blob/master/preview/example5.jpg" width="300"/>|<img src="https://github.com/gzeinnumer/NFCReader/blob/master/preview/example4.jpg" width="300"/>|<img src="https://github.com/gzeinnumer/NFCReader/blob/master/preview/example2.jpg" width="300"/>|
|---|---|---|
|Ask NFC to active|Dialog Tagging|NFC feature not found in divice|

|<img src="https://github.com/gzeinnumer/NFCReader/blob/master/preview/example3.jpg"/>|
|---|
|Result|

#
### Customize

- Design

You can change design of the `Dialog Tagging`.
1. Make `default_tagging_dialog.xml` in `res/layout`
2. Copy this code root [default_tagging_dialog.xml](https://github.com/gzeinnumer/NFCReader/blob/master/nr/src/main/res/layout/default_tagging_dialog.xml)
3. Just Customize it

|<img src="https://github.com/gzeinnumer/NFCReader/blob/master/preview/example1.jpg" width="400"/>|
|---|
|Customize Result|

#
- Message

You can change mesage of the `Dialog Tagging`.
1. Add this name to your `strings.xml`
2. Just Customize it

```xml
<resources>
    <string name="nfc_reader_ask_active_title">Warning</string>
    <string name="nfc_reader_ask_active_sub">NFC not active, enable NFC now?</string>
    <string name="nfc_reader_ask_active_ok">Ok</string>
    <string name="nfc_reader_ask_active_cancel">Cancel</string>
    <string name="nfc_reader_msg_no_nfc">Your phone don\'t have NFC Hardware</string>
</resources>
```

---
# Example Code/App

[Sample Code And App](https://github.com/gzeinnumer/MyLibNFCReaderExample)

---
# Version
- **1.0.0**
  - First Release
- **2.0.0**
  - Support SDK 16

---
# Contribution
You can sent your constibution to `branch` `open-pull`.

---

```
Copyright 2021 M. Fadli Zein
```

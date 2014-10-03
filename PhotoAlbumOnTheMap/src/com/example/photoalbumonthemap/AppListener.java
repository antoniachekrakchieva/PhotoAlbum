package com.example.photoalbumonthemap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AppListener extends BroadcastReceiver {

@Override
public void onReceive(Context context, Intent arg1) {
    // TODO Auto-generated method stub
    Log.w("MY_DEBUG_TAG", "there is a broadcast");
    System.out.println("BroeadcastREceive");
    }
}
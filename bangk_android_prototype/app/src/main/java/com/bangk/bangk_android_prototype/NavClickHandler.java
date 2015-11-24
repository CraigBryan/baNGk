package com.bangk.bangk_android_prototype;

import android.content.Context;
import android.util.Log;

/**
 * Created by craigbryan on 23/11/15.
 */
public class NavClickHandler {

    private Context context;

    public NavClickHandler(Context context) {
        this.context = context;
    }

    public void doNavAction(String action) {
        switch(action) {
            case "signout":
                break;
            default:
                Log.e(
                    "baNGk ERROR", "Unknown nav drawer action done: " + action
                );
        }
    }
}

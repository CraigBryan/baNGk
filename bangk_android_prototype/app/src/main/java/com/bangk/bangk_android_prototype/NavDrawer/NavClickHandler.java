package com.bangk.bangk_android_prototype.NavDrawer;

import android.content.Intent;
import android.util.Log;
import com.bangk.bangk_android_prototype.R;
import com.bangk.bangk_android_prototype.WelcomeActivity;

/**
 * Created by craigbryan on 23/11/15.
 */
public class NavClickHandler {

    private NavDrawerActivity context;

    public NavClickHandler(NavDrawerActivity context) {
        this.context = context;
    }

    public void doNavAction(String action) {
        switch(action) {
            case "viewaccounts":
                context.loadFragment(R.layout.view_accounts);
                break;
            case "signout":
                Intent intent = new Intent(context, WelcomeActivity.class);
                context.finish();
                context.startActivity(intent);
                break;
            default:
                Log.e(
                    "baNGk ERROR", "Unknown nav drawer action done: " + action
                );
        }
    }
}

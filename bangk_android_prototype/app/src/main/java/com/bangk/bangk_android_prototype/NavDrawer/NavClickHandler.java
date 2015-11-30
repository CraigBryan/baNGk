package com.bangk.bangk_android_prototype.NavDrawer;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import com.bangk.bangk_android_prototype.R;
import com.bangk.bangk_android_prototype.WelcomeActivity;

/**
 * Created by craigbryan on 23/11/15.
 */
public class NavClickHandler {

    private NavDrawerActivity context;
    private DrawerLayout drawer;

    public NavClickHandler(NavDrawerActivity context, DrawerLayout drawer) {
        this.context = context;
        this.drawer = drawer;
    }

    public void doNavAction(String action) {
        switch(action) {
            case "viewaccounts":
                context.loadFragment(R.layout.view_accounts);
                break;
            case "transfer":
                context.loadFragment(R.layout.bank_transfer);
                break;
            case "signout":
                Intent intent = new Intent(context, WelcomeActivity.class);
                context.finish();
                context.startActivity(intent);
                break;
            case "map":
                context.loadFragment(R.layout.view_map);
                break;
            default:
                Log.e(
                    "baNGk ERROR", "Unknown nav drawer action done: " + action
                );
                return;
        }
        drawer.closeDrawers();
    }
}

package com.bangk.bangk_android_prototype.NavDrawer;

import android.support.v4.widget.DrawerLayout;
import android.view.View;

/**
 * Simple event listener to handle events from the nav drawer. Routes the
 * action string to a NavClickHandler when the nav drawer item it is registered
 * with is clicked.
 * Created by craigbryan on 23/11/15.
 */
public class NavClickListener implements View.OnClickListener {

    // The handler this click listener delegates to
    private NavClickHandler handler;

    // The action this listener does
    private String action;

    public NavClickListener(
        NavDrawerActivity context,
        String actionName,
        DrawerLayout drawer
    ) {
        handler = new NavClickHandler(context, drawer);
        action = actionName;
    }

    @Override
    public void onClick(View v) {
        handler.doNavAction(action);
    }
}
package com.bangk.bangk_android_prototype.NavDrawer;

import android.support.v4.widget.DrawerLayout;
import android.view.View;

/**
 * Created by craigbryan on 23/11/15.
 */
public class NavClickListener implements View.OnClickListener {

    private NavClickHandler handler;
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
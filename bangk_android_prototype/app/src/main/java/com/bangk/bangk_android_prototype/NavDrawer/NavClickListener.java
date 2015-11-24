package com.bangk.bangk_android_prototype.NavDrawer;

import android.content.Context;
import android.view.View;

/**
 * Created by craigbryan on 23/11/15.
 */
public class NavClickListener implements View.OnClickListener {

    private NavClickHandler handler;
    private String action;

    public NavClickListener(Context context, String actionName) {
        handler = new NavClickHandler(context);
        action = actionName;
    }

    @Override
    public void onClick(View v) {
        handler.doNavAction(action);
    }
}
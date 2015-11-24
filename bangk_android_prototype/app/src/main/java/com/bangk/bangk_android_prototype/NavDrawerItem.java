package com.bangk.bangk_android_prototype;

/**
 * Created by craigbryan on 19/11/15.
 */
public class NavDrawerItem {

    private String labelText;
    private String action;
    private int iconResource;

    public NavDrawerItem(String label, String actionName, int icon) {
        iconResource = icon;
        labelText = label;
        action = actionName;
    }

    public String getLabelText() {
        return labelText;
    }

    public int getIconResource() {
        return iconResource;
    }

    public String getAction() {
        return action;
    }
}

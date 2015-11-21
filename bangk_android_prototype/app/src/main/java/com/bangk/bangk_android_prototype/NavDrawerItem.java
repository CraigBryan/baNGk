package com.bangk.bangk_android_prototype;

/**
 * Created by craigbryan on 19/11/15.
 */
public class NavDrawerItem {

    private String labelText;
    private int iconResource;

    public NavDrawerItem(String label, int icon) {
        iconResource = icon;
        labelText = label;
    }

    public String getLabelText() {
        return labelText;
    }

    public int getIconResource() {
        return iconResource;
    }
}

package com.bangk.bangk_android_prototype;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

/**
 * Utilities functions
 */
public class Utils {

    /**
     * Populates a textview with a currency string.
     * @param context The context the textview exists in
     * @param view the textview being populated
     * @param value the currency amount being put in the textview
     */
    public static void setColouredMoneyText(
        Context context, TextView view, float value
    ) {
        if (value >= 0) {
            view.setText("$" + String.valueOf(value));
            view.setTextColor(
                ContextCompat.getColor(context, R.color.acct_positive_balance)
            );
        } else {
            value = Math.abs(value);
            view.setText("-$" + String.valueOf(value));
            view.setTextColor(
                ContextCompat.getColor(context, R.color.acct_negative_balance)
            );
        }
    }
}

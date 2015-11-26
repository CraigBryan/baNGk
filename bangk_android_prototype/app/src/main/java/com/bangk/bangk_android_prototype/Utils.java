package com.bangk.bangk_android_prototype;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

/**
 * Created by craigbryan on 25/11/15.
 */
public class Utils {

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

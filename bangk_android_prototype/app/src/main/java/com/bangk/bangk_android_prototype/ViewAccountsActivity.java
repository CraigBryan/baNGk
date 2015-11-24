package com.bangk.bangk_android_prototype;

import android.os.Bundle;
import android.widget.TextView;

import com.bangk.bangk_android_prototype.NavDrawer.NavDrawerActivity;

/**
 * Created by craigbryan on 23/11/15.
 */
public class ViewAccountsActivity extends NavDrawerActivity {

    @Override
    protected void setToolbarLabel() {
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(R.string.view_accounts_title);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO THINGS!
    }
}

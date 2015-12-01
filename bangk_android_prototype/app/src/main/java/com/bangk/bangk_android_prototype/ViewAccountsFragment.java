package com.bangk.bangk_android_prototype;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bangk.bangk_android_prototype.NavDrawer.NavDrawerActivity;

/**
 * The fragment associated with the view accounts activity.
 */
public class ViewAccountsFragment extends Fragment {

    // The context this fragment exists in
    private Context context;

    /**
     * Loads and initializes the view for the view accounts activity
     * @param inflater - inflater to create views associated with the underlying
     *                 activity
     * @param container - the parent view that holds the views being loaded
     * @param savedInstanceState - data that allows the program to contain
     *                           state to allow it to be reloaded on app resume
     * @return the View to be displayed by the underlying Activity
     */
    @Override
    public View onCreateView(
        LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        context = getActivity().getApplicationContext();
        View layout = inflater.inflate(
            R.layout.view_accounts, container, false
        );
        ListView listView = (ListView) layout.findViewById(R.id.list);
        listView.setAdapter(initAdapter());

        return layout;
    }

    private ViewAccountAdapter initAdapter() {
        ViewAccountAdapter adapter = new ViewAccountAdapter(
            context, R.layout.view_account_list_item
        );

        adapter.add(new AccountListItem(
            1, "Everyday Account", "#123-5423-45235232", "Chequing", 450.23f
        ));
        adapter.add(new AccountListItem(
            2, "Student Line of Credit", "#123-5073-258274", "Line of Credit",
            -14027.23f
        ));
        adapter.add(new AccountListItem(
            3, "TFSA Account", "#142-6263-845626", "Tax Free Savings",
            4305.01f
        ));

        return adapter;
    }

    private void doAction(int id) {
        switch(id) {
            case 1: // Note faking functionality here
            case 2: // Note faking functionality here
            case 3:
                Intent intent = new Intent(context, NavDrawerActivity.class);
                intent.putExtra(
                    NavDrawerActivity.FRAGMENT_INTENT_STRING,
                    R.layout.view_account_detail
                );
                intent.putExtra(
                    NavDrawerActivity.FRAGMENT_TITLE_STRING,
                    "Everyday Account"
                );
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            default:
                Log.e(
                    "baNKg Error", "Unknown account list action"
                );
                break;
        }
    }

    /**
     * Data model and click handler for accounts in the account list
     */
    public class AccountListItem implements View.OnClickListener {
        private int uniqueId;
        private String accountName;
        private String accountNumber;
        private String accountType;
        private float accountBalance;

        private AccountListItem(
            int id, String acctName, String acctNum, String acctType,
            float balance
        ) {
            this.uniqueId = id;
            this.accountName = acctName;
            this.accountNumber = acctNum;
            this.accountType = acctType;
            this.accountBalance = balance;
        }

        public String getAccountName() {
            return accountName;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getAccountType() {
            return accountType;
        }

        public float getAccountBalance() {
            return accountBalance;
        }

        // Delegates clicks events to the fragment, with the id of the account
        @Override
        public void onClick(View v) {
            doAction(uniqueId);
        }
    }


}

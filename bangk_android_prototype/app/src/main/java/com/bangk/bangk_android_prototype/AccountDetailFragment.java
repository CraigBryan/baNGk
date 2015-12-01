package com.bangk.bangk_android_prototype;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * View that displays a specific account's data and list of transactions.
 */
public class AccountDetailFragment extends Fragment {

    public static final String ACCOUNT_NUMBER_KEY = "acctNum";
    public static final String ACCOUNT_TYPE_KEY = "acctType";
    public static final String ACCOUNT_BALANCE_KEY = "acctBal";

    // The context this fragment exists in
    private Context context;

    /**
     * Loads and initializes the view for the account detail view.
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
            R.layout.view_account_detail, container, false
        );

        populateLayout(layout);

        return layout;
    }
    private void populateLayout(View layout) {
        TextView acctNum = (TextView) layout.findViewById(R.id.ad_acct_num);
        TextView acctType = (TextView) layout.findViewById(R.id.ad_acct_type);
        TextView acctBalance = (TextView) layout.findViewById(
            R.id.ad_acct_balance
        );
        ListView transactionList = (ListView) layout.findViewById(R.id.list);
        TransactionListAdapter adapter = initAdapter();

        acctNum.setText(getArguments().getString(
            ACCOUNT_NUMBER_KEY, "Unknown Account Number"
        ));
        acctType.setText(getArguments().getString(
            ACCOUNT_TYPE_KEY, "Unknown Account Type"
        ));

        Utils.setColouredMoneyText(
            context, acctBalance, getArguments().getFloat(
                ACCOUNT_BALANCE_KEY, 0
            )
        );

        transactionList.setAdapter(adapter);
    }

    // Loads the data in the transaction list.
    private TransactionListAdapter initAdapter() {
        TransactionListAdapter adapter = new TransactionListAdapter(
            context, R.layout.transaction_list_item
        );

        adapter.add(new TransactionListItem(
            "8:15 AM Dec 2, 2015", "#785", -45.23f, "Mac Gas Bar Richmond"
        ));
        adapter.add(new TransactionListItem(
            "7:50 AM Dec 2, 2015", "#784", -7.68f,
            "Quicky Breakfast Bar Ottawa"
        ));
        adapter.add(new TransactionListItem(
            "12:01 AM Dec 1, 2015", "#782", 2413.16f,
            "Freeton Co."
        ));
        adapter.add(new TransactionListItem(
            "6:13 PM Nov 30, 2015", "#781", -61.09f, "Pho Bo Ga King"
        ));
        adapter.add(new TransactionListItem(
            "12:34 PM Nov 30, 2015", "#780", -12.94f, "Mr. Sandwich"
        ));
        adapter.add(new TransactionListItem(
            "4:00 PM Nov 29, 2015", "#779", -2.25f, "O'Coin Irish Laundry"
        ));
        adapter.add(new TransactionListItem(
            "12:45 AM Nov 29, 2015", "#778", -21.34f, "Madeup Fake Bar"
        ));

        return adapter;
    }

    /**
     * The data model for items in the transaction list.
     */
    public class TransactionListItem {
        private String date;
        private String num;
        private float value;
        private String origin;

        public TransactionListItem(
            String date, String num, float value, String origin
        ) {
            this.date = date;
            this.num = num;
            this.value = value;
            this.origin = origin;
        }

        public String getOrigin() {
            return origin;
        }

        public String getDate() {
            return date;
        }

        public String getNum() {
            return num;
        }

        public float getValue() {
            return value;
        }
    }
}

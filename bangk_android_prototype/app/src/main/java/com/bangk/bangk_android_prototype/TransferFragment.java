package com.bangk.bangk_android_prototype;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.bangk.bangk_android_prototype.NavDrawer.NavDrawerActivity;

/**
 * The fragment associated with the bank transfer activity.
 */
public class TransferFragment
    extends Fragment
    implements View.OnClickListener
{
    // There are several spinners on this view, and they are handled the same
    // way. This enum is used to identify the different spinners
    private enum SpinnerId {
        FROM_ACCOUNTS, YOUR_ACCOUNTS, EXT_ACCOUNTS
    }

    // The context this fragment is running within
    private Context context;

    // The main view of this fragment
    private View layout;

    // Three spinner adapters for the three spinners in this fragment view
    private CustomSpinnerAdapter fromAcctAdapter;
    private CustomSpinnerAdapter toOwnAcctAdapter;
    private CustomSpinnerAdapter toExtAcctAdapter;

    // Variables used for feedback to the user after a transaction is done
    private String fromAccount, toAccount;
    private Float amount;

    /**
     * Loads and initializes the view for the transfer view
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
        layout = inflater.inflate(
            R.layout.bank_transfer, container, false
        );
        initSpinners();
        initRadioButtons();
        initAmountInput();
        initButtons();
        return layout;
    }

    /**
     * Handles the clicking on the confirm or cancel buttons
     * @param v the view that was clicked on
     */
    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.transfer_cancel_button:
                Toast.makeText(
                    context, "Transfer cancelled", Toast.LENGTH_LONG
                ).show();
                intent = new Intent(context, NavDrawerActivity.class);
                intent.putExtra(
                    NavDrawerActivity.FRAGMENT_INTENT_STRING,
                    R.layout.view_accounts
                );
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            case R.id.transfer_confirm_button:
                if(fromAccount == null || toAccount == null || amount == null) {
                    Toast.makeText(
                        context,
                        "Please ensure all sections are filled out",
                        Toast.LENGTH_SHORT
                    ).show();
                    return;
                }
                Toast.makeText(
                    context, "Transfer is being processed", Toast.LENGTH_LONG
                ).show();
                Toast.makeText(
                    context, "Transfer completed", Toast.LENGTH_LONG
                ).show();
                intent = new Intent(context, NavDrawerActivity.class);
                intent.putExtra(
                    NavDrawerActivity.FRAGMENT_INTENT_STRING,
                    R.layout.bank_transfer
                );
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
        }
    }

    private void initSpinners() {
        initFromAccountSpinner(
            (Spinner) layout.findViewById(R.id.transfers_from_acct_spinner)
        );

        initToOwnAccountSpinner(
            (Spinner) layout.findViewById(R.id.transfer_to_own_acct_spinner)
        );

        initExtAccountSpinner(
            (Spinner) layout.findViewById(R.id.transfer_to_ext_acct_spinner)
        );
    }

    private void initRadioButtons() {
        RadioButton yours = (RadioButton) layout.findViewById(
            R.id.rb_your_acct
        );
        RadioButton ext = (RadioButton) layout.findViewById(R.id.rb_ext_acct);

        yours.setOnClickListener(new RadioListener());
        ext.setOnClickListener(new RadioListener());
    }

    private void initAmountInput() {
        EditText input = (EditText) layout.findViewById(
            R.id.transfers_amt_editable
        );
        input.addTextChangedListener(new EditTextListener());
    }

    private void initButtons() {
        Button cancelButton = (Button) layout.findViewById(
            R.id.transfer_cancel_button
        );
        Button confirmButton = (Button) layout.findViewById(
            R.id.transfer_confirm_button
        );

        cancelButton.setOnClickListener(this);
        confirmButton.setOnClickListener(this);
    }

    private void initFromAccountSpinner(Spinner spinner) {
        SpinnerListener listener = new SpinnerListener(SpinnerId.FROM_ACCOUNTS);

        spinner.setOnItemSelectedListener(listener);
        fromAcctAdapter = new CustomSpinnerAdapter(
            getActivity().getApplicationContext(),
            R.layout.transfer_spinner_item
        );

        fromAcctAdapter.add(new SpinnerItem("Choose Account", null));
        fromAcctAdapter.add(new SpinnerItem(
            "Everyday Account", "#123-5423-45235232"
        ));
        fromAcctAdapter.add(new SpinnerItem(
            "Student Line of Credit", "#123-5073-258274"
        ));
        fromAcctAdapter.add(new SpinnerItem(
            "TFSA Account", "#142-6263-845626"
        ));

        spinner.setAdapter(fromAcctAdapter);
    }

    private void initToOwnAccountSpinner(Spinner spinner) {
        SpinnerListener listener = new SpinnerListener(SpinnerId.YOUR_ACCOUNTS);

        spinner.setOnItemSelectedListener(listener);
        toOwnAcctAdapter = new CustomSpinnerAdapter(
            getActivity().getApplicationContext(),
            R.layout.transfer_spinner_item
        );

        toOwnAcctAdapter.add(new SpinnerItem("Choose Account", null));
        toOwnAcctAdapter.add(new SpinnerItem(
            "Student Line of Credit", "#123-5073-258274"
        ));
        toOwnAcctAdapter.add(new SpinnerItem(
            "TFSA Account", "#142-6263-845626"
        ));

        spinner.setAdapter(toOwnAcctAdapter);
    }

    private void initExtAccountSpinner(Spinner spinner) {
        SpinnerListener listener = new SpinnerListener(SpinnerId.EXT_ACCOUNTS);

        spinner.setOnItemSelectedListener(listener);
        toExtAcctAdapter = new CustomSpinnerAdapter(
            getActivity().getApplicationContext(),
            R.layout.transfer_spinner_item
        );

        toExtAcctAdapter.add(new SpinnerItem("Choose Account", null));
        toExtAcctAdapter.add(new SpinnerItem(
            "Mastercard Account", "#1234 5678 9012 3456"
        ));
        toExtAcctAdapter.add(new SpinnerItem(
            "Visa Account", "#5892 3492 2534 1089"
        ));
        toExtAcctAdapter.add(new SpinnerItem(
            "University of Ottawa", "#6965144"
        ));

        spinner.setAdapter(toExtAcctAdapter);
    }

    private void afterFromChosen(int chosenPosition) {
        SpinnerItem data = fromAcctAdapter.getItem(chosenPosition);
        fromAccount = data.getMainText();
        layout.findViewById(R.id.transfer_acct_type_container).setVisibility(
            View.VISIBLE
        );
    }

    private void afterTypeChosen(boolean isExt) {
        View ext = layout.findViewById(R.id.transfer_ext_account_container);
        View yours = layout.findViewById(R.id.transfer_your_account_container);
        if (isExt) {
            ext.setVisibility(View.VISIBLE);
            yours.setVisibility(View.GONE);
        } else {
            ext.setVisibility(View.GONE);
            yours.setVisibility(View.VISIBLE);
        }
    }

    private void afterToChosen(int chosenPosition, boolean isExt) {
        SpinnerItem data;

        if (isExt) {
            data = toExtAcctAdapter.getItem(chosenPosition);
        } else {
            data = toOwnAcctAdapter.getItem(chosenPosition);
        }

        toAccount = data.getMainText();

        layout.findViewById(R.id.transfer_amt_container).setVisibility(
            View.VISIBLE
        );
    }

    private void afterAmountEntered(float amount) {
        this.amount = amount;
        layout.findViewById(R.id.confirm_container).setVisibility(View.VISIBLE);
    }

    public class SpinnerItem {

        private String mainText;
        private String subText;

        public SpinnerItem(String main, String sub) {
            mainText = main;
            subText = sub;
        }

        public String getMainText() {
            return mainText;
        }

        public String getSubText() {
            return subText;
        }
    }

    private class SpinnerListener implements AdapterView.OnItemSelectedListener {

        private SpinnerId spinner;

        public SpinnerListener(SpinnerId id) {
            spinner = id;
        }

        @Override
        public void onItemSelected(
            AdapterView<?> parent, View view, int position, long id
        ) {
            if(position == 0) {
                return;
            }

            switch(spinner) {
                case FROM_ACCOUNTS:
                    afterFromChosen(position);
                    break;
                case YOUR_ACCOUNTS:
                    afterToChosen(position, false);
                    break;
                case EXT_ACCOUNTS:
                    afterToChosen(position, true);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    private class RadioListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            boolean checked = ((RadioButton) view).isChecked();

            switch (view.getId()) {
                case R.id.rb_your_acct:
                    if (checked) {
                        afterTypeChosen(false);
                    }
                    break;
                case R.id.rb_ext_acct:
                    if (checked) {
                        afterTypeChosen(true);
                    }
            }
        }
    }

    private class EditTextListener implements TextWatcher {

        @Override
        public void beforeTextChanged(
            CharSequence s, int start, int count, int after
        ) {}

        @Override
        public void onTextChanged(
            CharSequence s, int start, int before, int count
        ) {}

        @Override
        public void afterTextChanged(Editable s) {
            Float result;
            String numString = s.toString();
            if (numString == null || numString.isEmpty()) {
                return;
            } else {
                try {
                    result = Float.parseFloat(numString);
                } catch (NumberFormatException e) {
                    return;
                }
            }

            if (result != null) {
                afterAmountEntered(result);
            }
        }
    }
}

package com.bangk.bangk_android_prototype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by craigbryan on 24/11/15.
 */
public class ViewAccountAdapter extends
    ArrayAdapter<ViewAccountsFragment.AccountListItem>
{
    private Context context;
    private int resourceId;

    public ViewAccountAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        ViewAccountsFragment.AccountListItem data;

        data = getItem(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resourceId, parent, false);
            convertView.setOnClickListener(data);
            initializeViewHolder(viewHolder, convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        populateViewHolder(viewHolder, data);

        return convertView;
    }

    private void initializeViewHolder(ViewHolder vh, View cv) {
        vh.accountName = (TextView) cv.findViewById(R.id.va_acct_name);
        vh.accountNum = (TextView) cv.findViewById(R.id.va_acct_num);
        vh.accountType = (TextView) cv.findViewById(R.id.va_acct_type);
        vh.accountBalance = (TextView) cv.findViewById(R.id.va_acct_balance);

        cv.setTag(vh);
    }

    private void populateViewHolder(
        ViewHolder vh, ViewAccountsFragment.AccountListItem ali
    ) {
        vh.accountName.setText(ali.getAccountName());
        vh.accountNum.setText(ali.getAccountNumber());
        vh.accountType.setText(ali.getAccountType());
        Utils.setColouredMoneyText(
            context, vh.accountBalance, ali.getAccountBalance()
        );
    }

    private static class ViewHolder {
        TextView accountName;
        TextView accountNum;
        TextView accountType;
        TextView accountBalance;
    }
}
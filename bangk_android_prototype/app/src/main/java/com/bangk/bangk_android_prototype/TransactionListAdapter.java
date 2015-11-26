package com.bangk.bangk_android_prototype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by craigbryan on 25/11/15.
 */
public class TransactionListAdapter extends
    ArrayAdapter<AccountDetailFragment.TransactionListItem>
{
    private Context context;
    private int resourceId;

    public TransactionListAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        AccountDetailFragment.TransactionListItem data;

        data = getItem(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resourceId, parent, false);
            initializeViewHolder(viewHolder, convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        populateViewHolder(viewHolder, data);

        return convertView;
    }

    private void initializeViewHolder(ViewHolder vh, View cv) {
        vh.date = (TextView) cv.findViewById(R.id.tli_date);
        vh.number = (TextView) cv.findViewById(R.id.tli_trans_num);
        vh.amount = (TextView) cv.findViewById(R.id.tli_value_label);
        vh.origin = (TextView) cv.findViewById(R.id.tli_place_label);
        cv.setTag(vh);
    }

    private void populateViewHolder(
        ViewHolder vh, AccountDetailFragment.TransactionListItem tli
    ) {
        vh.date.setText(tli.getDate());
        vh.number.setText(tli.getNum());
        vh.origin.setText(tli.getOrigin());
        Utils.setColouredMoneyText(context, vh.amount, tli.getValue());
    }

    private static class ViewHolder {
        TextView date;
        TextView number;
        TextView amount;
        TextView origin;
    }
}

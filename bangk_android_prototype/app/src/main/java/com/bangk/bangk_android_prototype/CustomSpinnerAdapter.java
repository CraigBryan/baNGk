package com.bangk.bangk_android_prototype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Adapter for an Android spinner that allows a custom view to be populated
 * within the spinner. Specifically it displays the top-level, non-dropdown as
 * a single line, and the dropdown items as two lines.
 */
public class CustomSpinnerAdapter
    extends ArrayAdapter<TransferFragment.SpinnerItem>
{
    // The context this adapter exists in
    private Context context;

    // The id of the layout that this adapter populates
    private int resourceId;

    public CustomSpinnerAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resourceId = resource;
    }

    /**
     * Loads the view for the non-dropdown item.
     * @param position - the position of the data in the adapter
     * @param convertView - the view associated with this data
     * @param parent - the spinner view that holds these items
     * @return the view to be displayed
     */
    @Override
    public View getDropDownView(
        int position, View convertView, ViewGroup parent
    ) {
        return getCustomSpinnerView(position, convertView, parent, false);
    }

    /**
     * Loads the view for the dropdown items.
     * @param position - the position of the data in the adapter
     * @param convertView - the view associated with this data
     * @param parent - the spinner view that holds these items
     * @return the view to be displayed
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomSpinnerView(position, convertView, parent, true);
    }

    private View getCustomSpinnerView(
        int position, View convertView, ViewGroup parent, boolean isFirst
    ) {
        ViewHolder viewHolder;
        TransferFragment.SpinnerItem data;

        data = getItem(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resourceId, parent, false);
            initializeViewHolder(viewHolder, convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        populateViewHolder(viewHolder, data, isFirst);

        return convertView;
    }

    private void initializeViewHolder(ViewHolder vh, View cv) {
        vh.mainText = (TextView) cv.findViewById(R.id.spinner_item_main_text);
        vh.subText = (TextView) cv.findViewById(R.id.spinner_item_sub_text);
        cv.setTag(vh);
    }

    private void populateViewHolder(
        ViewHolder vh, TransferFragment.SpinnerItem data, boolean isFirst
    ) {
        vh.mainText.setText(data.getMainText());
        if (isFirst || data.getSubText() == null) {
            vh.subText.setVisibility(View.GONE);
        } else {
            vh.subText.setVisibility(View.VISIBLE);
            vh.subText.setText(data.getSubText());
        }
    }

    // Caching data container to speed up layout inflating.
    private static class ViewHolder {
        TextView mainText;
        TextView subText;
    }
}

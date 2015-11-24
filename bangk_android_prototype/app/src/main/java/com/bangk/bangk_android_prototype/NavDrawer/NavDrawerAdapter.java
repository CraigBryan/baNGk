package com.bangk.bangk_android_prototype.NavDrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangk.bangk_android_prototype.R;

/**
 * Created by craigbryan on 19/11/15.
 */
public class NavDrawerAdapter extends ArrayAdapter<NavDrawerItem> {

    private int navItemViewId;
    private Context context;

    public NavDrawerAdapter(Context context, int resource) {
        super(context, resource);
        this.navItemViewId = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        NavDrawerItem data;

        data = getItem(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(navItemViewId, parent, false);
            convertView.setOnClickListener(
                new NavClickListener(context, data.getAction())
            );
            initializeViewHolder(viewHolder, convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        populateViewHolder(viewHolder, data);

        return convertView;
    }

    private void initializeViewHolder(ViewHolder vh, View cv) {
        vh.itemLabel = (TextView) cv.findViewById(R.id.nav_drawer_label);
        vh.icon = (ImageView) cv.findViewById(R.id.nav_drawer_icon);

        cv.setTag(vh);
    }

    private void populateViewHolder(ViewHolder vh, NavDrawerItem ndi) {
        vh.itemLabel.setText(ndi.getLabelText());
        vh.icon.setImageResource(ndi.getIconResource());
    }

    private static class ViewHolder {
        TextView itemLabel;
        ImageView icon;
    }
}

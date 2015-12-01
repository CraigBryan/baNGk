package com.bangk.bangk_android_prototype.NavDrawer;

import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangk.bangk_android_prototype.R;

/**
 * The adapter that handles the list data in the nav drawer
 */
public class NavDrawerAdapter extends ArrayAdapter<NavDrawerItem> {

    // the id of the layout that is populated by data by this adapter
    private int navItemViewId;

    // The context this adapter exists in
    private NavDrawerActivity context;

    // The nav drawer layout container
    private DrawerLayout drawer;

    public NavDrawerAdapter(
        NavDrawerActivity context, int resource, DrawerLayout drawer
    ) {
        super(context, resource);
        this.navItemViewId = resource;
        this.context = context;
        this.drawer = drawer;
    }

    /**
     * Builds a view that displays the data at a given position in the list
     * @param position - the position of the data being displayed
     * @param convertView - the view that is being populated and displayed
     * @param parent - the view that holds the views in the list
     * @return the view to display the data in
     */
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
                new NavClickListener(context, data.getAction(), drawer)
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

    // Caching data container to speed up layout inflating
    private static class ViewHolder {
        TextView itemLabel;
        ImageView icon;
    }
}

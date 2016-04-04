package com.bjh.android6thclass;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-04-04.
 */
public class MyListAdapter extends BaseAdapter {

    ArrayList<ListItem> items = new ArrayList<>();

    public void add (ListItem item) {
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemView view;
        if (convertView != null) {
            view = (ListItemView) convertView;
        } else {
            view =  new ListItemView(parent.getContext());
        }
        view.setListItemView(items.get(position));
        return view;
    }
}

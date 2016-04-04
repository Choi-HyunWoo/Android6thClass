package com.bjh.android6thclass;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016-04-04.
 */
public class ListItemView extends FrameLayout {

    TextView nameView;

    public ListItemView(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_item, this);
        nameView = (TextView) findViewById(R.id.name);

    }

    public void setListItemView(ListItem item) {
        nameView.setText(item.name);
    }



}

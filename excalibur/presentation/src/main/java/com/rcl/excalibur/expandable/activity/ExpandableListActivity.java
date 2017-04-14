package com.rcl.excalibur.expandable.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.expandable.adapter.ExpandableListAdapter;
import com.rcl.excalibur.expandable.model.ExpandableItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
//TODO: Remove class
public class ExpandableListActivity extends AppCompatActivity {
    @Bind(R.id.text_see_all) TextView seeAllText;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    private ExpandableListAdapter adapter;
    private List<ExpandableItem> itemsToShow;
    private List<ExpandableItem> itemsToAddAndRemove;
    private List<Integer> positions;

    private boolean insertMultipleItems = false;

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, ExpandableListActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(300);
        defaultItemAnimator.setRemoveDuration(300);
        recyclerView.setItemAnimator(defaultItemAnimator);

        adapter = new ExpandableListAdapter(null);
        recyclerView.setAdapter(adapter);

        initNumbers();
    }

    @OnClick(R.id.layout_header_container)
    public void onShowAllClick() {
        if ("See all".equals(seeAllText.getText().toString())) {
            seeAllText.setText("See less");

            adapter.add(positions, itemsToAddAndRemove);

            /*if (insertMultipleItems) {
                insertMultipleItems();
                return;
            }
            insertOneItem();*/
        } else {
            seeAllText.setText("See all");

            adapter.remove(positions, itemsToAddAndRemove);

            /*if (insertMultipleItems) {
                removeMultipleItems();
                return;
            }
            removeOneItem();*/
        }
    }

    private void insertMultipleItems() {
        adapter.add(adapter.getItemCount(), itemsToAddAndRemove);
    }

    private void removeMultipleItems() {
        adapter.remove(3, itemsToAddAndRemove);
    }

    private void insertOneItem() {
        ExpandableItem item = new ExpandableItem();
        item.setNumber("10");
        adapter.add(1, item);
    }

    private void removeOneItem() {
        adapter.remove(1);
    }

    private void initNumbers() {
        List<ExpandableItem> items = new ArrayList<>();

        positions = new ArrayList<>();

        itemsToShow = new ArrayList<>();
        itemsToAddAndRemove = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExpandableItem item = new ExpandableItem();
            item.setNumber(String.valueOf(i));
            if (i == 1 || i == 4 || i == 7) {
                item.setShowing(true);
                itemsToShow.add(item);
            } else {
                item.setShowing(false);
                itemsToAddAndRemove.add(item);
                positions.add(i);
            }
            //items.add(item);
        }
        adapter.addAll(itemsToShow);
        //adapter.addAll(items);
    }
}

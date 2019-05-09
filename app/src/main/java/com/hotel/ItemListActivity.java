package com.hotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItemListActivity extends AppCompatActivity {
    @BindView(R.id.rv_item_list)
    RecyclerView rv_item_list;

    @BindView(R.id.bottom_cart_view)
    View bottom_cart_view;
    String sub_cat_name = "";
    List<ItemListPojo> itemListPojoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        sub_cat_name = getIntent().getStringExtra("sub_cat_name");
        setTitle(sub_cat_name);

        rv_item_list.setHasFixedSize(true);
        rv_item_list.setLayoutManager(new LinearLayoutManager(this));

        setUpList();
    }
    @OnClick(R.id.bottom_cart_view)
    public void onCartViewClick()
    {
        startActivity(new Intent(ItemListActivity.this,CartActivity.class));
    }

    public void setUpList() {
        for (int i = 0; i < 10; i++) {
            itemListPojoList.add(new ItemListPojo("1", "Plain Rice", "", "made up of rice and jeera"));
        }

        ItemListAdapter adapter = new ItemListAdapter(ItemListActivity.this, itemListPojoList);
        rv_item_list.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

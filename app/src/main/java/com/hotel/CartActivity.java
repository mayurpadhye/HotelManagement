package com.hotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.hotel.pojo_classes.CartPojo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity {
@BindView(R.id.rv_cart)
RecyclerView rv_cart;
List<CartPojo> listCart=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        setTitle("Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rv_cart.setHasFixedSize(true);
        rv_cart.setLayoutManager(new LinearLayoutManager(this));
        setUpList();

    }//onCreateClose

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void setUpList() {
        for (int i = 0; i < 10; i++) {
            listCart.add(new CartPojo("1", "Plain Rice", "", "made up of rice and jeera",""));
        }

        CartAdapter adapter = new CartAdapter(CartActivity.this, listCart);
        rv_cart.setAdapter(adapter);

    }



}

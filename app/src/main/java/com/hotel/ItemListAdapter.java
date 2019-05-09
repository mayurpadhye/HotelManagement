package com.hotel;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    Context context;
    List<ItemListPojo> itemListPojoList;

    public ItemListAdapter(Context context, List<ItemListPojo> itemListPojoList) {
        this.context = context;
        this.itemListPojoList = itemListPojoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ItemListPojo items = itemListPojoList.get(position);
        holder.tv_item_name.setText(items.getItem_name());

        //holder.imageView.setImageResource(listdata[position].getImgId());
holder.tv_viewdetails.setVisibility(View.GONE);
        holder.tv_viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemDetailsBottomSheet(items.getId(),items.getItem_name(),items.getItem_image(),items.getItem_contents(),position);
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemDetailsBottomSheet(items.getId(),items.getItem_name(),items.getItem_image(),items.getItem_contents(),position);
            }
        });
        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCookingInstrictionDialog(holder.btn_add,holder.ll_quantity);
            }
        });

        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty= Integer.parseInt(holder.tv_qty.getText().toString().trim());
                holder.tv_qty.setText(""+(qty+1));
            }
        });

        holder.iv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty=Integer.parseInt(holder.tv_qty.getText().toString().trim());
                if (qty==1)
                {
                    holder.ll_quantity.setVisibility(View.GONE);
                    holder.btn_add.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder.tv_qty.setText(""+(qty-1));
                }
            }
        });

    }

    public void openCookingInstrictionDialog(final Button btn_add, final LinearLayout ll_quantity)
    {
        final BottomSheetDialog dialog=new BottomSheetDialog(context);
        dialog.setContentView(R.layout.bottom_sheet_cooking_instructions);
        Button btn_submit=dialog.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_add.setVisibility(View.GONE);
                ll_quantity.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public int getItemCount() {
        return itemListPojoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_item_image;
        public TextView tv_price,tv_item_name,tv_viewdetails;
        public CardView cardView;
        Button btn_add;
        LinearLayout ll_quantity;
        TextView tv_qty;
        ImageView iv_remove,iv_add;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_item_image = (ImageView) itemView.findViewById(R.id.iv_item_image);
            iv_remove = (ImageView) itemView.findViewById(R.id.iv_remove);
            iv_add = (ImageView) itemView.findViewById(R.id.iv_add);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_viewdetails = (TextView) itemView.findViewById(R.id.tv_viewdetails);
            tv_qty = (TextView) itemView.findViewById(R.id.tv_qty);
            tv_item_name = (TextView) itemView.findViewById(R.id.tv_item_name);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            btn_add = (Button) itemView.findViewById(R.id.btn_add);
            ll_quantity = (LinearLayout) itemView.findViewById(R.id.ll_quantity);
            ll_quantity = (LinearLayout) itemView.findViewById(R.id.ll_quantity);
        }
    }

    public void openItemDetailsBottomSheet(String id, String item_name, String item_image, String item_contents, int position)
    {
        BottomSheetDialog dialog=new BottomSheetDialog(context);
        dialog.setContentView(R.layout.bottom_sheet_item_details);
        ImageView iv_item_image = (ImageView) dialog.findViewById(R.id.iv_item_image);
        TextView tv_price = (TextView) dialog.findViewById(R.id.tv_price);
        TextView tv_item_name = (TextView) dialog.findViewById(R.id.tv_item_name);
      Button  btn_add = (Button) dialog.findViewById(R.id.btn_add);
      LinearLayout ll_quantity = (LinearLayout) dialog.findViewById(R.id.ll_quantity);
        dialog.show();



    }//bottomSheetClose

}

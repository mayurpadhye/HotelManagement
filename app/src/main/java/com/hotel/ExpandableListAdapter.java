package com.hotel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.hotel.pojo_classes.pojo_category;
import com.hotel.pojo_classes.pojo_subcategory;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<pojo_category> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<pojo_category, List<pojo_subcategory>> _listDataChild;

    public ExpandableListAdapter(Context context, List<pojo_category> listDataHeader,
                                 HashMap<pojo_category, List<pojo_subcategory>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        //final String childText = (String) getChild(groupPosition, childPosition);
        String childText=_listDataHeader.get(groupPosition).getList().get(childPosition).getSubcategoty();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView tv_sub_menu_name = (TextView) convertView
                .findViewById(R.id.tv_sub_menu_name);

        tv_sub_menu_name.setText(childText);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _context.startActivity(new Intent(_context,ItemListActivity.class).putExtra("sub_cat_name",_listDataHeader.get(groupPosition).getList().get(childPosition).getSubcategoty()));
            }
        });


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = _listDataHeader.get(groupPosition).getCategoty();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView tv_menu_parent = (TextView) convertView
                .findViewById(R.id.tv_menu_parent);
        tv_menu_parent.setTypeface(null, Typeface.BOLD);
        tv_menu_parent.setText(headerTitle);
        if (getChildrenCount(groupPosition) > 0) {
            if (isExpanded) {
                tv_menu_parent.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_collapse_orange, 0);
            } else {
                tv_menu_parent.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand, 0);
            }
        } else {
            tv_menu_parent.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand, 0);
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}




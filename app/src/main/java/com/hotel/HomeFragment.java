package com.hotel;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.hotel.pojo_classes.pojo_category;
import com.hotel.pojo_classes.pojo_subcategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    DataBaseHelper dbclass;
    View view;
    int images[] = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.food};
    MyCustomPagerAdapter myCustomPagerAdapter;
    private OnFragmentInteractionListener mListener;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    ExpandableListAdapter listAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    List<pojo_category> listDataHeader;
    HashMap<pojo_category, List<pojo_subcategory>> listDataChild;

    @BindView(R.id.lvExp)
    NonScrollExpandableListView expListView;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Home");
        dbclass = DataBaseHelper.getInstance(getActivity());

        prepareListData();

        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        myCustomPagerAdapter = new MyCustomPagerAdapter(getActivity(), images);
        viewPager.setAdapter(myCustomPagerAdapter);

        return view;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<pojo_category>();
        listDataChild = new HashMap<pojo_category, List<pojo_subcategory>>();

        // Adding category data
        Cursor cursorcategory = dbclass.getCategiories();
        if (cursorcategory.moveToFirst()) {
            do {
                int categoryid=cursorcategory.getInt(cursorcategory.getColumnIndex("CATEGORY_ID"));
                String categoryname=cursorcategory.getString(cursorcategory.getColumnIndex("CATEGORY_NAME"));
                String categoryimage=cursorcategory.getString(cursorcategory.getColumnIndex("CATEGORY_IMAGE"));

                Cursor cursorsubcategory = dbclass.getSubCategiories(categoryid);
                List<pojo_subcategory> subcategories=new ArrayList<>();
                if (cursorsubcategory.moveToFirst()) {
                    do {
                        int subcategoryid=cursorsubcategory.getInt(cursorsubcategory.getColumnIndex("SUB_CATEGORY_ID"));
                        String subcategoryname=cursorsubcategory.getString(cursorsubcategory.getColumnIndex("SUB_CATEGORY_NAME"));
                        subcategories.add(new pojo_subcategory(subcategoryid,subcategoryname));
                    } while (cursorsubcategory.moveToNext());
                }
                listDataHeader.add(new pojo_category(categoryid,categoryname,categoryimage,subcategories));
                listDataChild.put(listDataHeader.get(cursorcategory.getPosition()), subcategories); // Category, Sub Category data

            } while (cursorcategory.moveToNext());
        }

        // Adding subcategory data
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


}

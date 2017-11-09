package com.example.yashladha.android_seller.navigation;

import android.content.Intent;
import android.view.View;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yashladha.android_seller.HomePageActivity;
import com.example.yashladha.android_seller.LoginActivity;
import com.example.yashladha.android_seller.R;
import com.example.yashladha.android_seller.classes.MyAccountCustomExpandableListAdapter;
import com.example.yashladha.android_seller.classes.MyAccountExpandableListDataPump;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAccountFragment extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    TextView tvMyName,tvContact,tvEmail,tvDeactivate,tvLogOut;
    ImageView ivMyPic,ivEdit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.frag_nav_my_account, container, false);

        ivMyPic = (ImageView)rootView.findViewById(R.id.ivMyPic);
        ivEdit = (ImageView)rootView.findViewById(R.id.ivEdit);

        tvMyName = (TextView)rootView.findViewById(R.id.tvMyName);
        tvContact = (TextView)rootView.findViewById(R.id.tvContact);
        tvEmail = (TextView)rootView.findViewById(R.id.tvEmail);
        tvDeactivate = (TextView)rootView.findViewById(R.id.tvDeactivate);
        tvLogOut = (TextView)rootView.findViewById(R.id.tvLogOut);

        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MyAccountEditActivity.class);
                startActivity(intent);
            }
        });

      tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });
        tvDeactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);
        expandableListDetail = MyAccountExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new MyAccountCustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getActivity(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Account");
    }

}


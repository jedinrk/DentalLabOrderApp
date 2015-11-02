package com.example.naveen.dentalorder.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.naveen.dentalorder.R;
import com.example.naveen.dentalorder.model.Pojo;

/**
 * Created by Naveen on 10/30/2015.
 */
public class FragmentPage1 extends Fragment {

    public Pojo[] list = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page, container, false);


        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
        }
        return super.onOptionsItemSelected(item);
    }

    private Pojo[] setList1(){
        Pojo[] list = new Pojo[10];

        for(int i = 0; i < 10;i++){
            list[i] = new Pojo();
        }
        list[0].typeofwork = "Crown";
        list[1].typeofwork = "Bridge";
        list[2].typeofwork = "Veneer";
        list[3].typeofwork = "Cantilever Bridge";
        list[4].typeofwork = "Full Metal";
        list[5].typeofwork = "Onlay /Inlay";
        list[6].typeofwork = "Post Crown";
        list[7].typeofwork = "Post Core";
        list[8].typeofwork = "Maryland Bridge";
        list[9].typeofwork = "Precision Attachment";

        return list;
    }

    static class viewHolder{
        TextView textView;
        CheckBox checkBox;
    }

    private BaseAdapter listAdaper = new BaseAdapter() {
        @Override
        public int getCount() {
            if (list != null) {
                return list.length;
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            return list;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if(v == null){
                Context ctx = parent.getContext();
                v = LayoutInflater.from(ctx).inflate(R.layout.listview_checkbox, null);
                viewHolder vh = new viewHolder();
                vh.textView = (TextView) v.findViewById(R.id.textView);
                vh.checkBox = (CheckBox) v.findViewById(R.id.checkBox);
                v.setTag(vh);
            }

            viewHolder vh = (viewHolder) v.getTag();
            vh.textView.setText(list[position].typeofwork);
            return v;
        }
    };

}

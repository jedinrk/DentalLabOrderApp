package com.example.naveen.dentalorder.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
public class FragmentPage2 extends Fragment {

    public Pojo[] list = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page, container, false);




        return rootView;
    }

    private Pojo[] setList2(){
        Pojo[] list = new Pojo[7];
        for(int i = 0; i < 7 ;i++){
            list[i] = new Pojo();
        }
        list[0].typeofwork = "Zirconia";
        list[1].typeofwork = "Non-Precious";
        list[2].typeofwork = "Semi-Precious";
        list[3].typeofwork = "Yellow Gold Precious";
        list[4].typeofwork = "H-White Gold Precious";
        list[5].typeofwork = "Empress / Emax";
        list[6].typeofwork = "Ceramage Composite (Shofu)";

        return list;
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

    static class viewHolder{
        TextView textView;
        CheckBox checkBox;
    }


}

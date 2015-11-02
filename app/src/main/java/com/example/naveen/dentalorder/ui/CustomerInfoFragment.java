/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.naveen.dentalorder.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.naveen.dentalorder.R;
import com.example.naveen.dentalorder.model.CustomerInfoPage;

import java.util.Calendar;


public class CustomerInfoFragment extends Fragment {
    private static final String ARG_KEY = "key";

    private PageFragmentCallbacks mCallbacks;
    private String mKey;
    private static String mDate;
    private CustomerInfoPage mPage;
    private TextView mDrNameView;
    private TextView mNameView;
    private TextView mAgeView;
    private static TextView mDateSend;
    private static TextView mDateRequired;

    public static CustomerInfoFragment create(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);

        CustomerInfoFragment fragment = new CustomerInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CustomerInfoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mKey = args.getString(ARG_KEY);
        mPage = (CustomerInfoPage) mCallbacks.onGetPage(mKey);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_customer_info, container, false);
        ((TextView) rootView.findViewById(android.R.id.title)).setText(mPage.getTitle());

        mDrNameView = ((TextView) rootView.findViewById(R.id.surgeon_name));
        mDrNameView.setText(mPage.getData().getString(CustomerInfoPage.DR_NAME_DATA_KEY));

        mNameView = ((TextView) rootView.findViewById(R.id.patient_name));
        mNameView.setText(mPage.getData().getString(CustomerInfoPage.NAME_DATA_KEY));

        mAgeView = ((TextView) rootView.findViewById(R.id.patient_age));
        mAgeView.setText(mPage.getData().getString(CustomerInfoPage.AGE_DATA_KEY));

        mDateSend = ((TextView) rootView.findViewById(R.id.date_send));
        mDateSend.setText(mPage.getData().getString(CustomerInfoPage.DATE_SEND_DATA_KEY));
        mDateSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDatePickerDialog(v);
            }
        });

        mDateRequired = ((TextView) rootView.findViewById(R.id.date_required));
        mDateRequired.setText(mPage.getData().getString(CustomerInfoPage.DATE_REQ_DATA_KEY));
        mDateRequired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reqDatePickerDialog(v);
            }
        });
        return rootView;
    }

    public void sendDatePickerDialog(View v) {
        DialogFragment newFragment = new sendDatePicker();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    public void reqDatePickerDialog(View v) {
        DialogFragment newFragment = new reqDatePicker();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof PageFragmentCallbacks)) {
            throw new ClassCastException("Activity must implement PageFragmentCallbacks");
        }

        mCallbacks = (PageFragmentCallbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
                    int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPage.getData().putString(CustomerInfoPage.NAME_DATA_KEY,
                        (editable != null) ? editable.toString() : null);
                mPage.notifyDataChanged();
            }
        });

        mAgeView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
                    int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPage.getData().putString(CustomerInfoPage.AGE_DATA_KEY,
                        (editable != null) ? editable.toString() : null);
                mPage.notifyDataChanged();
            }
        });
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        // In a future update to the support library, this should override setUserVisibleHint
        // instead of setMenuVisibility.
        if (mNameView != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            if (!menuVisible) {
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            }
        }
    }

    public static class sendDatePicker extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        String date ="";
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            date = day +"-"+month+"-"+year;
            mDateSend.setText(date);
        }
    }

    public static class reqDatePicker extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        String date ="";
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            date = day +"-"+month+"-"+year;
            mDateRequired.setText(date);
        }
    }
}

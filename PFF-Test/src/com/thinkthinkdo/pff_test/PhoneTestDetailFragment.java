package com.thinkthinkdo.pff_test;

import com.thinkthinkdo.pff_test.R;
import com.thinkthinkdo.pff_test.dummy.DummyContent;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.telephony.TelephonyManager;

public class PhoneTestDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    DummyContent.DummyItem mItem;
    android.telephony.TelephonyManager tm;

    public PhoneTestDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_phonetest_detail, container, false);
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.phonetest_detail)).setText(mItem.content);
            if (mItem.id.equalsIgnoreCase("1")){
            	tm = (TelephonyManager)this.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
            	String deviceId = tm.getDeviceId();
            	String line1Number = tm.getLine1Number();
            	((TextView) rootView.findViewById(R.id.phonetest_detail)).setTextSize(14);
            	((TextView) rootView.findViewById(R.id.phonetest_detail)).setText("getDeviceId="+deviceId+"\n");
            	((TextView) rootView.findViewById(R.id.phonetest_detail)).append("getLine1Number="+line1Number+"\n");
            } else {
            	((TextView) rootView.findViewById(R.id.phonetest_detail)).setText("mItem.id="+mItem.id);            	
            }
        }
        return rootView;
    }
}

package com.thinkthinkdo.pff_test;

import com.thinkthinkdo.pff_test.R;
import com.thinkthinkdo.pff_test.dummy.DummyContent;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.telephony.TelephonyManager;

public class PhoneTestDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private static final int UPDATE_LATLNG = 2;

    DummyContent.DummyItem mItem;
    android.telephony.TelephonyManager tm;
    LayoutInflater mInflater;
    ViewGroup mContainer;
    View mRootView;
    Handler mHandler;

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
    	mInflater = inflater;
    	mContainer = container;
        mRootView = inflater.inflate(R.layout.fragment_phonetest_detail, container, false);
        if (mItem != null) {
            ((TextView) mRootView.findViewById(R.id.phonetest_detail)).setText(mItem.content);
            if (mItem.id.equalsIgnoreCase("1")){
            	tm = (TelephonyManager)this.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
            	String deviceId = tm.getDeviceId();
            	String line1Number = tm.getLine1Number();
            	((TextView) mRootView.findViewById(R.id.phonetest_detail)).setTextSize(14);
            	((TextView) mRootView.findViewById(R.id.phonetest_detail)).setText("getDeviceId="+deviceId+"\n");
            	((TextView) mRootView.findViewById(R.id.phonetest_detail)).append("getLine1Number="+line1Number+"\n");
            }
            if (mItem.id.equalsIgnoreCase("2")){
                mHandler = new Handler() {
                    public void handleMessage(Message msg) {
                    	TextView tView = (TextView) mRootView.findViewById(R.id.phonetest_detail);
                    	tView.setTextSize(20);
                    	tView.setText(mItem.content+"\n");
                    	tView.append("Location\n");
                        tView.append((String) msg.obj+"\n");
                    }
                };
            	
            	LocationManager locationManager =
            	        (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
            	if (locationManager != null) {
	            	Criteria criteria = new Criteria();
	            	criteria.setAccuracy(Criteria.ACCURACY_COARSE);
	            	String bestProvider = locationManager.getBestProvider(criteria, true);
	            	locationManager.requestLocationUpdates(bestProvider, 1000, 10, listener);
	                Location location = locationManager.getLastKnownLocation(bestProvider);
	                if (location != null)
	                	updateUILocation(location);
            	} else {
                	TextView tView = (TextView) mRootView.findViewById(R.id.phonetest_detail);
                	tView.setTextSize(14);
                	tView.setText("No locationManager\n");            		
            	}
            }
            if (mItem.id.equalsIgnoreCase("3")){
                mHandler = new Handler() {
                    public void handleMessage(Message msg) {
                    	TextView tView = (TextView) mRootView.findViewById(R.id.phonetest_detail);
                    	tView.setTextSize(20);
                    	tView.setText(mItem.content+"\n");
                    	tView.append("Location\n");
                        tView.append((String) msg.obj+"\n");
                    }
                };
            	
            	LocationManager locationManager =
            	        (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
            	if (locationManager != null) {
	            	Criteria criteria = new Criteria();
	            	criteria.setAccuracy(Criteria.ACCURACY_FINE);
	            	String bestProvider = locationManager.getBestProvider(criteria, true);
	            	locationManager.requestLocationUpdates(bestProvider, 1000, 10, listener);
	                Location location = locationManager.getLastKnownLocation(bestProvider);
	                if (location != null)
	                	updateUILocation(location);
            	} else {
                	TextView tView = (TextView) mRootView.findViewById(R.id.phonetest_detail);
                	tView.setTextSize(14);
                	tView.setText("No locationManager\n");            		
            	}
            }
        }
        return mRootView;
    }
    
    private void updateUILocation(Location location) {
        // We're sending the update to a handler which then updates the UI with the new
        // location.
        Message.obtain(mHandler,
                UPDATE_LATLNG,
                "Latitude: "+ location.getLatitude() + "\n" + 
                "Longitude: "+ location.getLongitude()).sendToTarget();
    }

    private final LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            // A new location update is received.  Do something useful with it.  Update the UI with
            // the location update.
            updateUILocation(location);
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };
}

package com.thinkthinkdo.pff_test;

import com.thinkthinkdo.pff_test.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class PhoneTestListActivity extends FragmentActivity
        implements PhoneTestListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonetest_list);

        if (findViewById(R.id.phonetest_detail_container) != null) {
            mTwoPane = true;
            ((PhoneTestListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.phonetest_list))
                    .setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(PhoneTestDetailFragment.ARG_ITEM_ID, id);
            PhoneTestDetailFragment fragment = new PhoneTestDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.phonetest_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, PhoneTestDetailActivity.class);
            detailIntent.putExtra(PhoneTestDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}

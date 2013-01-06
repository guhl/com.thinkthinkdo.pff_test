package com.thinkthinkdo.pff_test;

import com.thinkthinkdo.pff_test.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class PhoneTestDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonetest_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(PhoneTestDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(PhoneTestDetailFragment.ARG_ITEM_ID));
            PhoneTestDetailFragment fragment = new PhoneTestDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.phonetest_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, PhoneTestListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

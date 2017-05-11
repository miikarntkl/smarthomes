package com.smarthomes.smarthomes;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import layout.LightingFragment;
import layout.ProfilesFragment;
import layout.ReportFragment;
import layout.SafetyFragment;

public class MainActivity extends AppCompatActivity {

    private ColorDrawable LIGHTING_PRIMARY = null;
    private ColorDrawable LIGHTING_DARK = null;
    private ColorDrawable LIGHTING_ACCENT = null;

    private ColorDrawable REPORT_PRIMARY = null;
    private ColorDrawable REPORT_DARK = null;
    private ColorDrawable REPORT_ACCENT = null;

    private ColorDrawable PROFILES_PRIMARY = null;
    private ColorDrawable PROFILES_DARK = null;
    private ColorDrawable PROFILES_ACCENT = null;

    private ColorDrawable SAFETY_PRIMARY = null;
    private ColorDrawable SAFETY_DARK = null;
    private ColorDrawable SAFETY_ACCENT = null;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setColors();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        final AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPageColors(position, mViewPager, appBarLayout);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(1);

    }

    private void setPageColors(int position, ViewPager mViewPager, AppBarLayout appBarLayout) {
        if (position == 0) { //Lighting
            appBarLayout.setBackground(LIGHTING_DARK);
            mViewPager.setBackground(LIGHTING_PRIMARY);
        }
        if (position == 1) { //Report
            appBarLayout.setBackground(REPORT_DARK);
            mViewPager.setBackground(REPORT_PRIMARY);
        }
        if (position == 2) { //Safety
            appBarLayout.setBackground(SAFETY_DARK);
            mViewPager.setBackground(SAFETY_PRIMARY);
        }
        if (position == 3) { //Profiles
            appBarLayout.setBackground(PROFILES_DARK);
            mViewPager.setBackground(PROFILES_PRIMARY);
        }
    }

    private void setColors() {
        LIGHTING_PRIMARY = new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.lightingPrimary, null));
        LIGHTING_DARK = new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.lightingDark, null));
        LIGHTING_ACCENT = new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.lightingAccent, null));

        REPORT_PRIMARY = new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.reportPrimary, null));
        REPORT_DARK = new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.reportDark, null));
        REPORT_ACCENT = new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.reportAccent, null));

        PROFILES_PRIMARY = new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.profilesPrimary, null));
        PROFILES_DARK = new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.profilesDark, null));
        PROFILES_ACCENT = new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.profilesAccent, null));

        SAFETY_PRIMARY = new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.safetyPrimary, null));
        SAFETY_DARK = new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.safetyDark, null));
        SAFETY_ACCENT = new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.safetyAccent, null));
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final int page_count = 4;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    LightingFragment lighting = new LightingFragment();
                    return lighting;
                case 1:
                    ReportFragment report = new ReportFragment();
                    return report;
                case 2:
                    SafetyFragment safety = new SafetyFragment();
                    return safety;
                case 3:
                    ProfilesFragment profiles = new ProfilesFragment();
                    return profiles;
            }

            return null;
        }

        @Override
        public int getCount() {
            return page_count;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Lighting";
                case 1:
                    return "Report";
                case 2:
                    return "Safety";
                case 3:
                    return "Profiles";
            }
            return null;
        }
    }
}

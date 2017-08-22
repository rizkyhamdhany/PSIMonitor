package com.psi.monitor.views.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import com.psi.monitor.R;
import com.psi.monitor.views.fragments.Last24HourFragment;
import com.psi.monitor.views.fragments.PSIMapsFragment;

public class HomeActivity extends BaseActivity {

    private PSIMapsFragment psiMapsFragment;
    private Last24HourFragment last24HourFragment;
    private int activeFragment = 0;

    /**
     *
     */
    @Override
    public void initView() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        psiMapsFragment = new PSIMapsFragment();
        last24HourFragment = new Last24HourFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_content, psiMapsFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void setUICallbacks() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void updateUI() {

    }

    /**
     *
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (activeFragment != 0){
                        activeFragment = 0;
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_content, psiMapsFragment)
                                .commitAllowingStateLoss();
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if (activeFragment != 1){
                        activeFragment = 1;
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_content, last24HourFragment)
                                .commitAllowingStateLoss();
                    }

                    return true;
            }
            return false;
        }

    };
}

package com.psi.monitor.views.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import com.psi.monitor.R;
import com.psi.monitor.views.fragments.PSIMapsFragment;

public class HomeActivity extends BaseActivity {

    private PSIMapsFragment psiMapsFragment;

    /**
     *
     */
    @Override
    public void initView() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        psiMapsFragment = new PSIMapsFragment();
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
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }

    };
}

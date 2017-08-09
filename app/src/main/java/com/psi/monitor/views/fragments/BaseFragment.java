package com.psi.monitor.views.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.psi.monitor.views.activities.BaseActivity;

/**
 * Created by hamdhanywijaya@gmail.com on 8/10/17.
 */

public abstract class BaseFragment extends Fragment implements FragmentInteface {
    private View view;
    protected Activity activity;
    protected boolean hasFetchDataFromAPI;
    protected LayoutInflater inflater;
    protected Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        view = inflater.inflate(getFragmentLayout(), container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateUI();
        setUICallbacks();
//        if (getBaseActivity() != null)
//            getBaseActivity().setActionBarTitle(getPageTitle());
    }

    @Override
    public void onAttach(Activity activity) {
        this.activity = activity;
        super.onAttach(activity);
    }

    public BaseActivity getBaseActivity() {
        if (activity instanceof BaseActivity) {
            return ((BaseActivity) activity);
        } else {
            return null;
        }
    }

    public void replaceFragment(int container, BaseFragment fragment, boolean addBackToStack) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (addBackToStack) {
            ft.addToBackStack(fragment.getPageTitle());
        }
        //ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(container, fragment, fragment.getPageTitle());
        ft.commit();
    }

    public void addFragment(int container, BaseFragment fragment, boolean addBackToStack) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (addBackToStack) {
            ft.addToBackStack(fragment.getPageTitle());
        }
        ft.add(container, fragment);
        ft.commit();
    }

    public BaseFragment findFragment(String tag) {
        Fragment fragment = getFragmentManager().findFragmentByTag(tag);
        if (fragment != null) {
            return (BaseFragment) fragment;
        }
        return null;
    }

    public void addChildFragment(int container, BaseFragment fragment, boolean addBackToStack) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if (addBackToStack) {
            ft.addToBackStack(fragment.getPageTitle());
        }
        ft.add(container, fragment);
        ft.commitAllowingStateLoss();
    }

//    public void loadImage(String url, ImageView imageView, int stubImage) {
//        pictureHelper.loadImage(url, imageView, stubImage, null);
//    }

//    public void loadImage(String url, ImageView imageView, int stubImage, ImageSize imageSize) {
//        pictureHelper.loadImage(url, imageView, stubImage, imageSize);
//    }

//    public void displayImage(String url, ImageView view){
//        pictureHelper.displayImage(url,view);
//    }

    public String checkNullString(String string) {
        return (string == null) ? "" : string;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}

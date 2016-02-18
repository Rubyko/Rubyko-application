package com.rubyko.mainapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by yegor on 14/02/16.
 */
public abstract class BaseActivity extends FragmentActivity {

    protected final void addFragment(final Bundle bundle, final Class fragmentClass){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    BaseFragment fragment = (BaseFragment) fragmentClass.newInstance();
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = BaseActivity.this.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(getContainerId(), fragment, fragment.getTag());
                    fragmentTransaction.commit();
                } catch (InstantiationException e){
                    e.printStackTrace();
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        });
    }

    protected abstract  int getContainerId();

}
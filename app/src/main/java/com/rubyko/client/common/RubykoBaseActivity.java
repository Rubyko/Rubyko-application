package com.rubyko.client.common;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;

import com.rubyko.client.RubykoApplication;

/**
 * Created by yegor on 14/02/16.
 */
public abstract class RubykoBaseActivity extends FragmentActivity {

    public static int screenWidth;
    public static int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
    }


    public <T extends Fragment> void showFragment(final Bundle bundle, final Class<T> fragmentClass){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    RubykoFragment fragment = (RubykoFragment) fragmentClass.newInstance();
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = RubykoBaseActivity.this.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(fragmentClass.getName());
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                            android.R.anim.fade_in, android.R.anim.fade_out);
                    fragment.show(fragmentTransaction, fragmentClass.getName());
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    protected abstract int getContainerId();

    @Override
    public RubykoApplication getApplicationContext() {
        return (RubykoApplication) super.getApplicationContext();
    }
}

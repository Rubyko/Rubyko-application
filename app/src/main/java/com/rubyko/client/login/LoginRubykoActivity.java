package com.rubyko.client.login;

import android.os.Bundle;

import com.rubyko.client.R;
import com.rubyko.client.common.RubykoBaseActivity;
import com.rubyko.client.common.RubykoFragment;
import com.rubyko.client.common.adapter.RubykoPagerAdapter;
import com.rubyko.client.common.view.RubykoParallaxViewPager;
import com.rubyko.client.login.fragment.ChooseFragment;

import tyrantgit.explosionfield.ExplosionField;

/**
 * Created by yegor on 14/02/16.
 */
public class LoginRubykoActivity extends RubykoBaseActivity {

    private ExplosionField explosionField;
    private RubykoParallaxViewPager vpPager;
    private RubykoPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        explosionField = ExplosionField.attach2Window(this);

        vpPager = (RubykoParallaxViewPager) findViewById(R.id.vpPager);
        vpPager.setBackgroundResource(R.drawable.bkg3);
        adapterViewPager = new RubykoPagerAdapter(getSupportFragmentManager(), vpPager);
        adapterViewPager.add(new ChooseFragment(), 0);
        vpPager.setAdapter(adapterViewPager);
    }

    @Override
    protected int getContainerId() {
        return R.id.activityContainer;
    }

    public final void replaceFragment(final Bundle bundle, final Class fragmentClass, final int pos) {
        try {
            RubykoFragment fragment = (RubykoFragment) fragmentClass.newInstance();
            fragment.setArguments(bundle);
            adapterViewPager.add(fragment, pos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        if (vpPager.getCurrentItem() != 0) {
            vpPager.setCurrentItem(vpPager.getCurrentItem() - 1, true);
        } else super.onBackPressed();
    }

}

package com.coopinc.mvpchallenge.ui.kingdoms;

import android.os.Bundle;
import android.view.Menu;

import com.coopinc.mvpchallenge.R;
import com.coopinc.mvpchallenge.ui.BaseActivity;

public class KingdomsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_kingdom);
        if (savedInstanceState == null) {
            container = R.id.container_kingdom;
            KingdomListFragment listFragment = new KingdomListFragment();
            getFragmentManager().beginTransaction().add(container, listFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }
}

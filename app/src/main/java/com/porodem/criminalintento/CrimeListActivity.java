package com.porodem.criminalintento;

import android.support.v4.app.Fragment;

/**
 * Created by porod on 11.02.2018.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}

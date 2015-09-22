package com.iyari.contactos.util;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.iyari.contactos.CrearContactoFragment;
import com.iyari.contactos.ListaContactosFragment;

/**
 * Created by CONVERGENCE.
 */


//se extiende de fragmentPageradapter, va hacer la funcion de los fargment y sobreescribe getcount y fragment getitem y su contructor
public class TabPagerAdapter extends FragmentPagerAdapter {

    public TabPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public int getCount() { // como se tienen 2 fragment se pone 2

        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new CrearContactoFragment();
            case 1: return new ListaContactosFragment();
        }
        return null;
    }
}

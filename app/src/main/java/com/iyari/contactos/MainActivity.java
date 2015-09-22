package com.iyari.contactos;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.iyari.contactos.util.TabPagerAdapter;

public class MainActivity extends Activity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {

   // private EditText name, tel, mail, direcc;
    //private Button contact;
    ///private ArrayAdapter<Contacto> adapter;
   // private ImageView imagViewConatc;
   // private ListView listViewContac;
   // int request_code = 1;


    ///control de fichas
    private ViewPager viewPager;
    private TabPagerAdapter adapter;
    private ActionBar actionBar;
    //Titulos de las fichas
    private String[] titulos = {"Crear Contacto", "Lista contacto"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //primero se llama la lista de contactos

        iniicializarTabs();

    }

    private void iniicializarTabs() {
        viewPager = (ViewPager)findViewById(R.id.pager);  //se inicializa viewpager
        actionBar = getActionBar(); //se crea el action bar
        adapter = new TabPagerAdapter(getFragmentManager());  //se crea el tabspageradapter

        viewPager.setAdapter(adapter);  //el adaptador de viewpager sera el adpatador
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); //el modo de navegacion sera por pestañas o fichas

        //Agregando las fichas de los tabs
        for (String nombre: titulos){
            ActionBar.Tab tab = actionBar.newTab().setText(nombre);
            tab.setTabListener(this);
            actionBar.addTab(tab);
        }
        viewPager.setOnPageChangeListener(this);   //su auditor para cambios de paginas sera la clase actual ya que se agregaran sus metodos aqui

    }


    //<editor-fold desc="Metodos view change listener, lo que sucede dentro de la pestaña">
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override  //el elemento seleccionado sea la posicion seleccionada
    public void onPageSelected(int position) {
        actionBar.setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //</editor-fold>

    //<editor-fold desc="metodos tabs change listener">
    @Override   //solo se ocupa este y se le dice al viewpager q el componente actual sea el q se selecciono por medio de la pestaña
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
    //</editor-fold>
}
















package com.iyari.contactos;

import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.iyari.contactos.util.ContacListAdapter;
import com.iyari.contactos.util.ContactReceiver;
import com.iyari.contactos.util.Contacto;

import java.util.ArrayList;

/**
 * Created by CONVERGENCE.
 */
public class ListaContactosFragment extends Fragment{

    private ArrayAdapter<Contacto> adapater;
    private ListView listViewContac;
    private ContactReceiver receiver;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.lista_contacts_fragment, container, false);
            inicializarComponentes(rootView);
            setHasOptionsMenu(true);  //Habilita el action Bar de este fragment para los botones
            return rootView;
    }

    private void inicializarComponentes(View rootView) {
        listViewContac = (ListView)rootView.findViewById(R.id.listView);
        adapater = new ContacListAdapter(getActivity(), new ArrayList<Contacto>());
        //se configura para que el adpatador tonifique cambios en el dataset automaticamente
        adapater.setNotifyOnChange(true);
        listViewContac.setAdapter(adapater);


    }


    @Override   //se ejecuta cada ves q aparezca el fragmento en nuestro dispositivo
    public void onResume() {
        super.onResume();       //le pasamos nuestra calse contact receiver, registramos el receiver, se lanza y se filtra solo lista contactos
        receiver = new ContactReceiver(adapater);
        getActivity().registerReceiver(receiver, new IntentFilter("listacontactos"));

    }


    @Override   //se pausa la pantalla y se quita el registro
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_eliminar_contacto : //si presiona eliminar contacto
                eliminarContacto(item);  //se va al metodo eliminar contacto seleccionado
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }

    private void eliminarContacto(MenuItem item) {
        //sparseBoolean permite seleccionar los contactos q fueron seleccionados por el usuario y los pondra en el arreglo, atraves de un checkbox itemposicion nos traera las posiciones de los elements seleccionados
        SparseBooleanArray array = listViewContac.getCheckedItemPositions();
        //creamos el arraylist para traer lso contactos seleccionados por el usuario
        ArrayList<Contacto> seleccion = new ArrayList<Contacto>();
        //se crea el for para ir iterando todas las posiciones del array
        for (int i = 0; i < array.size(); i++ ){
            // se obtiene la posicion del contacto dentro del adaptador
            int posicion = array.keyAt(i);
            //nos trae la posicion actual i dentro del adaptador
            if (array.valueAt(i))
                //si el valor dentro dentro del arreglo fue seleccionado
                seleccion.add(adapater.getItem(posicion));
                //se crea un intent listacontactos
            Intent intent = new Intent("listacontactos");
            //se le agrega la operacion
            intent.putExtra("operacion",ContactReceiver.CONTACT_ELIMINADO );
            //como contacto eliminado recibe una lista de contactos vamos a pasarle  los datos y la seleccion de contactos q fue elimino
            intent.putExtra("datos", seleccion);
            //para posterior obtener la actividad asociada a este fragmento y mandar el brodcast con el intent necesario
            getActivity().sendBroadcast(intent);
            //limpiamos la lista
            listViewContac.clearChoices();

        }


    }
}

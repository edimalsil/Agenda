package com.iyari.contactos.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import com.iyari.contactos.util.Contacto;

import java.util.ArrayList;

/**
 * Created by CONVERGENCE.
 */
public class ContactReceiver extends BroadcastReceiver {

    public static final int CONTACT_AGREGADO = 1;
    public static final int CONTACT_ELIMINADO = 2;
    public static final int CONTACT_ACTUALIZADO = 3;

    private final ArrayAdapter<Contacto> adapter;


    public ContactReceiver(ArrayAdapter<Contacto> adapter){
        this.adapter = adapter;
    }

    @Override   //se ejecuta cada vez q el usuaario haga la operacion y por default le damos -1 porq no existe estamos del 1 al 3
    public void onReceive(Context context, Intent intent) {

        int operacion = intent.getIntExtra("operacion", -1);

        switch (operacion){

            case CONTACT_AGREGADO:
                agregarContacto(intent);
                break;

            case CONTACT_ELIMINADO:
                eliminarContacto(intent);
                break;

            case CONTACT_ACTUALIZADO:
                actualizarContacto(intent);
                break;

        }

    }

    public void agregarContacto(Intent intent){
        Contacto contacto = (Contacto)intent.getSerializableExtra("datos");
        adapter.add(contacto);

    }

    public  void eliminarContacto(Intent intent){
        ArrayList<Contacto> lista = (ArrayList<Contacto>)intent.getSerializableExtra("datos");
        for (Contacto c : lista)adapter.remove(c);
    }

    public  void  actualizarContacto(Intent intent){
        Contacto contacto = (Contacto)intent.getSerializableExtra("datos");
        int posicion = adapter.getPosition(contacto);
        adapter.insert(contacto, posicion);

    }
}

package com.iyari.contactos.util;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iyari.contactos.R;

import java.util.List;

/**
 * Created by CONVERGENCE.
 */
public class ContacListAdapter extends ArrayAdapter<Contacto>{

    //activity es un context porq hereda de context
    private Context context;


    //se crea su constructor pasando el contexto y la lista
    public ContacListAdapter(Context context, List<Contacto> contactos){
        super(context, R.layout.listview_item, contactos);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)

        {

            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_item, parent, false);


        }

        Contacto actual = this.getItem(position);
        inicializarCamposdeTexto(convertView, actual);
        return convertView;
    }

    private void inicializarCamposdeTexto(View convertView, Contacto actual) {

        TextView textView = (TextView)convertView.findViewById(R.id.texViewNom);
        textView.setText(actual.getNombre());
        textView = (TextView)convertView.findViewById(R.id.textViewTel);
        textView.setText(actual.getTelefono());
        textView = (TextView)convertView.findViewById(R.id.textViewCorreo);
        textView.setText(actual.getMail());
        textView = (TextView)convertView.findViewById(R.id.textViewDirecc);
        textView.setText(actual.getDireccion());
        ImageView imageViewResult = (ImageView)convertView.findViewById(R.id.imagViewRes);
        imageViewResult.setImageURI(Uri.parse(actual.getImageurl()));  //regresa un string por lo que se convierte con Uri.parse
    }


}

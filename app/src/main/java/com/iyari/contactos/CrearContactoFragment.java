package com.iyari.contactos;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.iyari.contactos.util.ContactReceiver;
import com.iyari.contactos.util.Contacto;
import com.iyari.contactos.util.TextChangedListener;

/**
 * Created by Iyari.
 */
public class CrearContactoFragment extends Fragment implements View.OnClickListener {


    private EditText name, tel, mail, direcc;
    private Button contact;
    private ImageView imagViewConatc;
    int request_code = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.crear_contacto_fragment, container, false);
        inicializarComponentes(rootView);
        return rootView;
    }

    private void inicializarComponentes(final View view) {
        name = (EditText)view.findViewById(R.id.edName);
        tel = (EditText) view.findViewById(R.id.edTel);
        mail = (EditText)view.findViewById(R.id.edCorreo);
        direcc = (EditText)view.findViewById(R.id.edDirec);
        //listViewContac = (ListView)view.findViewById(R.id.listContact); era de lista contactos
        imagViewConatc = (ImageView)view.findViewById(R.id.imagViewContact);
        imagViewConatc.setOnClickListener(this);

        //auditor para activar el campo para escribir y checar cuando cree un nuevo contacto, se creo una clase anonima
        name.addTextChangedListener(new TextChangedListener() {

            @Override    //checa la secuencia de caracteres
            public void onTextChanged(CharSequence seq, int start, int before, int count) {


                //para habilitar el boton si esq esta escrito !caracteresraros, lo convertimos a cadena, se quitan espacios y se ve que no este vacio
                //si no esta vacio se activa el boton
                contact.setEnabled(!seq.toString().trim().isEmpty());

            }
        });
        contact = (Button) view.findViewById(R.id.btnContac);
        contact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == contact) {

            agregarContacto(
                    name.getText().toString(),
                    tel.getText().toString(),
                    mail.getText().toString(),
                    direcc.getText().toString(),
                    String.valueOf(imagViewConatc.getTag()) //obtenemos el atribb tag con la uri de la imagen


            );

            String mensg = String.format("%s ha sido agregado a la lista ", name.getText());
            Toast.makeText(v.getContext(), mensg, Toast.LENGTH_SHORT).show();
            contact.setEnabled(false);
            limpiarCampo();

        }else if(v == imagViewConatc){

            Intent intent = null;
            //verificamos la version de l aplataforma
            if (Build.VERSION.SDK_INT < 19) {
                //versiones anteriores JellyBean 4.3
                //creamos el objeto intent
                intent = new Intent();
                //Le damos una accion que permita accceder a los recursos donde haya contenido
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //le damos el tipo
                //intent.setType("image/*");
                //inicializamos el resultado con este metodo para q nos habra el dialogo yal seleccionar la imagen deseada nos regrese el resultado
                //se declara el request code como un campo para verificar notificaciones para saber de donde viene y cual es

                // startActivityForResult(intent, request_code);

            } else {

                //androi 4.4 kikkat cambia y se debe abrir document
                intent = new Intent();
                //ABRIR DOCUMENT
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                //ABRIR ARCHIVOS Q SE PUEDAN ABRIR
                intent.addCategory(Intent.CATEGORY_OPENABLE);
            }
            //ABRIR EL TIPO DE ARCHIVO
            intent.setType("image/*");
            //lanza el objeto intent y obtiene la respuesta
            startActivityForResult(intent, request_code);

        }

    }


    private void agregarContacto(String nombre, String telefono, String mail, String direccion, String imageurl) {
        Contacto nuevo = (new Contacto(nombre, telefono, mail, direccion, imageurl));
        Intent intent = new Intent("listacontactos");
        intent.putExtra("operacion", ContactReceiver.CONTACT_AGREGADO);
        intent.putExtra("datos", nuevo);
        getActivity().sendBroadcast(intent);


    }

    private void limpiarCampo() {
        //se limpian los campos
        name.getText().clear();
        tel.getText().clear();
        mail.getText().clear();
        direcc.getText().clear();
        //se reestable la imagen por default
        imagViewConatc.setImageResource(R.drawable.avatar);
        //para volver a poner el foco en nombre porq se quedo en direccion para eso se agrega el sig metodo
        name.requestFocus();
    }

    @Override   //se sobre escribe este metodo para obtener el tipo de respuesta para verq hizo el usuario para obtener la imagen, si acepto o cancelo
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == request_code) {
            imagViewConatc.setImageURI(data.getData());
            //utilizamos el atributo TAG para almacenar la uri al archivo seleccionado
            imagViewConatc.setTag(data.getData());

        }



    }


}

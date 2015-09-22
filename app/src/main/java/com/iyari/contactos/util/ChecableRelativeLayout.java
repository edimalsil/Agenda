package com.iyari.contactos.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CONVERGENCE.
 */

//con esta clase se crea el checkbox para q donde toq la pantalla donde sea se seleccione
public class ChecableRelativeLayout extends RelativeLayout implements Checkable{

    private boolean isChecked;
    private List<Checkable>checkableView;

    public ChecableRelativeLayout(Context context) {
        super(context);
        inicializar(null);
    }

    public ChecableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        inicializar(attrs);
    }



    public ChecableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inicializar(attrs);
    }


    //<editor-fold desc="Metdodos checable">
    @Override
    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        for (Checkable c: checkableView)c.setChecked(isChecked);
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override   //dice si esta seleccionado o no
    public void toggle() {
        this.isChecked = !this.isChecked;
        for (Checkable c: checkableView)c.toggle();
    }
    //</editor-fold>


    @Override      //repesenta cuando la pantalla es inicializada
    protected void onFinishInflate() {
        super.onFinishInflate();
        final int childCount = this.getChildCount();  //se buscan los componentes hijo los del item
        for (int i=0; i < childCount; i++){
            buscarComponentesChecable(this.getChildAt(i));
        }
    }

    private void inicializar(AttributeSet attrs) {
        this.isChecked = false;
        this.checkableView = new ArrayList<Checkable>();

    }

    private void buscarComponentesChecable(View view) {  //este es el metodo recursivo se manda a llamar asi mismo se define en terminos de si misma
        if(view instanceof Checkable)
            this.checkableView.add((Checkable) view);
        if(view instanceof ViewGroup){
            final ViewGroup vg = (ViewGroup) view;
            final int childCount = vg.getChildCount();
            for (int i=0; i < childCount; i++){
                buscarComponentesChecable(vg.getChildAt(i));
            }
        }

    }


}


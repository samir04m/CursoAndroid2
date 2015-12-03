package co.edu.unimagdalena.mellanie.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import co.edu.unimagdalena.mellanie.R;
import co.edu.unimagdalena.mellanie.modelos.Imagen;

/**
 * Created by S4M1R on 03/12/2015.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Imagen> imagenes;

    public GridViewAdapter(Context context, ArrayList<Imagen> imagenes) {
        this.context = context;
        this.imagenes = imagenes;
    }

    @Override
    public int getCount() {
        return imagenes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if(convertView == null){
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.item_imagen, null);

            ImageView imagen = (ImageView) gridView.findViewById(R.id.imageView);

            imagen.setImageResource(imagenes.get(position).getRecurso());

        }else{
            gridView = (View) convertView;
        }

        return gridView;
    }
}

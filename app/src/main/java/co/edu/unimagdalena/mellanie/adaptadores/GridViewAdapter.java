package co.edu.unimagdalena.mellanie.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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

    public ArrayList<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<Imagen> imagenes) {
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

            imagenes.get(position).generarUrl();

            Picasso.with(context.getApplicationContext()).setLoggingEnabled(true);

            Picasso.with(context.getApplicationContext()).load(imagenes.get(position).getUrl()).placeholder(R.drawable.com_facebook_profile_picture_blank_portrait).error((R.drawable.com_facebook_button_send_icon)).into(imagen);

        }else{
            gridView = (View) convertView;
        }

        return gridView;
    }
}

package co.edu.unimagdalena.mellanie.modelos;

import java.util.ArrayList;

/**
 * Created by S4M1R on 03/12/2015.
 */
public class FlickPhotoContainer {
    private int total;
    private ArrayList<Imagen> photo;

    public FlickPhotoContainer(int total, ArrayList<Imagen> photo) {
        this.total = total;
        this.photo = photo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<Imagen> getPhoto() {
        return photo;
    }

    public void setPhoto(ArrayList<Imagen> photo) {
        this.photo = photo;
    }
}

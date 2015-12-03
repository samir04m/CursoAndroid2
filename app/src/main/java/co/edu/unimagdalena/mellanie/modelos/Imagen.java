package co.edu.unimagdalena.mellanie.modelos;

/**
 * Created by S4M1R on 03/12/2015.
 */
public class Imagen {
    private int recurso;
    private String titulo;
    private String autor;
    private int likes;

    public Imagen(int recurso, int likes, String autor, String titulo) {
        this.recurso = recurso;
        this.likes = likes;
        this.autor = autor;
        this.titulo = titulo;
    }

    public int getRecurso() {
        return recurso;
    }

    public void setRecurso(int recurso) {
        this.recurso = recurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}

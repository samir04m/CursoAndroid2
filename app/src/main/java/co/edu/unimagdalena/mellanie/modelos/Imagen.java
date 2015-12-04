package co.edu.unimagdalena.mellanie.modelos;

/**
 * Created by S4M1R on 03/12/2015.
 */
public class Imagen {
    private String id;
    private String owner;
    private String farm;
    private String server;
    private String secret;
    private String title;
    private String url;

    public Imagen(String id, String owner, String farm, String server, String secret, String title) {
        this.id = id;
        this.owner = owner;
        this.farm = farm;
        this.server = server;
        this.secret = secret;
        this.title = title;


    }

    public void generarUrl(){
        this.url = "http://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + ".jpg";
        System.out.println("URL generated: "+url);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

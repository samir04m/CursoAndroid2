package co.edu.unimagdalena.mellanie;

        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.ImageView;
        import android.widget.TextView;

        import co.edu.unimagdalena.mellanie.modelos.FlickPhotoContainer;
        import co.edu.unimagdalena.mellanie.modelos.Imagen;
        import com.google.gson.Gson;
        import com.squareup.okhttp.Call;
        import com.squareup.okhttp.Callback;
        import com.squareup.okhttp.OkHttpClient;
        import com.squareup.okhttp.Request;
        import com.squareup.okhttp.Response;
        import com.squareup.picasso.Picasso;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;
        import java.util.ArrayList;

public class DetallesActivity extends AppCompatActivity {

    TextView titulo;
    TextView autor;
    ImageView imageView;
    Imagen imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        TextView titulo = (TextView) findViewById(R.id.titleimagelbl);
        TextView autor = (TextView) findViewById(R.id.autorimagelbl);
        ImageView imageView = (ImageView) findViewById(R.id.imageView2);


        String idPhoto;

        idPhoto = getIntent().getExtras().getString("foto");

        titulo.setText(idPhoto);


        makeRequest("https://api.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key=605aa2be67eed97265bd04c3af596495&photo_id="+idPhoto+"&format=json&nojsoncallback=1");


    }

    private void makeRequest(String url) {
        // Descargamos el JSON de Flickr
        OkHttpClient client = new OkHttpClient();

        System.out.println("URL: " + url);

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);


        final Handler handler = new Handler(getApplicationContext().getMainLooper());

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(final Response response) throws IOException {


                System.out.println("Respuesta: ");
                final String responseJson = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        System.out.println(responseJson);
                        try {
                            JSONObject jsonObject = new JSONObject(responseJson);


                            Gson gson = new Gson();


                            imagen = gson.fromJson(jsonObject.getJSONObject("photo").toString(), Imagen.class);

                            imagen.generarUrl();
                            Picasso.with(getApplicationContext()).load(imagen.getUrl()).placeholder(R.drawable.com_facebook_button_background).into(imageView);

                            titulo.setText(jsonObject.getJSONObject("photo").getJSONObject("title").getString("_content"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


            }
        });
    }
}

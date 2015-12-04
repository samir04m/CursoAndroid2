package co.edu.unimagdalena.mellanie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.google.gson.Gson;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.unimagdalena.mellanie.adaptadores.GridViewAdapter;
import co.edu.unimagdalena.mellanie.modelos.FlickPhotoContainer;
import co.edu.unimagdalena.mellanie.modelos.Imagen;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ProfilePictureView profilePictureView;
    TextView nombrefb;
    TextView emailfb;
    MaterialSearchView search;
    GridView gridView;
    private ProfileTracker mProfileTracker;
    private static String FLICKR_URL = "https://api.flickr.com/services/rest/?method";
    private static String FLICKR_JSON = "json";
    private static String FLICKR_API_KEY = "af11210581b94bbb60b9e89999582d28";
    private GridViewAdapter gridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        setTitle("Flick App");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Profile profile = Profile.getCurrentProfile();

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);

            profilePictureView = (ProfilePictureView) view.findViewById(R.id.profilePicture);

            nombrefb = (TextView) view.findViewById(R.id.nombrefblbl);
            emailfb = (TextView) view.findViewById(R.id.emailfblbl);

            System.out.println("Profile ID: " + profile.getId());
            System.out.println("Profile Name: " + profile.getFirstName());

            profilePictureView.setProfileId(profile.getId());

            gridView = (GridView) findViewById(R.id.gridView);

            nombrefb.setText(profile.getName());

            search = (MaterialSearchView) findViewById(R.id.searchbtn);

            search.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                 }
                 });


        final ArrayList<Imagen> imagenes = new ArrayList<>();


        gridViewAdapter = new GridViewAdapter(this, imagenes);
        gridView.setAdapter(gridViewAdapter);


        //Descargamos el JSON Flickr
        OkHttpClient client = new OkHttpClient();

        String url = FLICKR_URL + "=flickr.photos.getRecent&format=" + FLICKR_JSON + "&api_key=" + FLICKR_API_KEY + "&nojsoncallback=1&per_page=10";

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

                        try {
                            System.out.println(responseJson);

                            JSONObject jsonObject = new JSONObject(responseJson);
                            Gson gson = new Gson();

                            FlickPhotoContainer flickPhotoContainer = gson.fromJson(jsonObject.getJSONObject("photos").toString(), FlickPhotoContainer.class);

                            System.out.println("NUM Fotos: ");
                            System.out.println(flickPhotoContainer.getTotal());

                            ArrayList<Imagen> imagenesTemp;

                            imagenesTemp = flickPhotoContainer.getPhoto();

                            gridViewAdapter.setImagenes(imagenesTemp);

                            gridViewAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        search.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        /*---------------------------------------------------
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }--------------------------------------------------*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_share) {
            LoginManager.getInstance().logOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.example.lenovo.albatros;

import android.app.DatePickerDialog;
import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.*;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

import java.net.DatagramPacket;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,DatePickerDialog.OnDateSetListener{
    //ImageSwticher variables
    private ImageSwitcher imageSwitcher;
    private int[] gallery = { R.mipmap.verano, R.mipmap.boleto_linea, R.mipmap.factura,
            R.mipmap.viaja, R.mipmap.viaja_rapido, R.mipmap.wifi };
    private int position;
    private static final Integer DURATION = 4000;
    private Timer timer = null;
    public int origen2;
    //spiner variables
   public String origen,destino,origen_completo,destino_completo;
    Spinner lista_origen;
    Spinner lista_destino;
    String [] datos={"Hermosillo","Puerto Peñasco","Obregon","Caborca","Nogales","Navojoa","Alamos",
            "Huatabampo","Etchojoa","Santa Ana","Magdalena","Guaymas","La Y"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

//Swticher

//Spinner
        lista_origen=(Spinner) findViewById(R.id.spinner_origen);
        lista_destino=(Spinner) findViewById(R.id.spinner_destino);
      ArrayAdapter<String> adaptador= new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,datos);
        lista_origen.setAdapter(adaptador);
        lista_destino.setAdapter(adaptador);
        lista_origen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        origen="HMO";
                        origen_completo="Hermosillo";
                        break;
                    case 1:
                        origen="PPE";
                        origen_completo="Puerto Peñasco";
                        break;

                    case 2:
                        origen="OBR";
                        origen_completo="Obregon";
                        break;
                    case 3:
                        origen="CAB";
                        origen_completo="Caborca";
                        break;
                    case 4:
                        origen="NOG";
                        origen_completo="Nogales";
                        break;
                    case 5:
                        origen="NAV";
                        origen_completo="Navojoa";
                        break;
                    case 6:
                        origen="ALA";
                        origen_completo="Alamos";
                        break;
                    case 7:
                        origen="HUA";
                        origen_completo="Huatabampo";
                        break;
                    case 8:
                        origen="ETC";
                        origen_completo="Etchojoa";
                        break;
                    case 9:
                        origen="SAN";
                        origen_completo="Santa Ana";
                        break;
                    case 10:
                        origen="MAG";
                        origen_completo="Magdalena";
                        break;
                    case 11:
                        origen="GUA";
                        origen_completo="Guaymas";
                        break;
                    case 12:
                        origen="LAY";
                        origen_completo="La Y";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
lista_destino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                destino="HMO";
                destino_completo="Hermosillo";
                break;
            case 1:
                destino="PPE";
                destino_completo="Puerto Peñasco";
                break;

            case 2:
                destino="OBR";
                destino_completo="Obregon";
                break;
            case 3:
                destino="CAB";
                destino_completo="Caborca";
                break;
            case 4:
                destino="NOG";
                destino_completo="Nogales";
                break;
            case 5:
                destino="NAV";
                destino_completo="Navojoa";
                break;
            case 6:
                destino="ALA";
                destino_completo="Alamos";
                break;
            case 7:
                destino="HUA";
                destino_completo="Huatabampo";
                break;
            case 8:
                destino="ETC";
                destino_completo="Etchojoa";
                break;
            case 9:
                destino="SAN";
                destino_completo="Santa Ana";
                break;
            case 10:
                destino="MAG";
                destino_completo="Magdalena";
                break;
            case 11:
                destino="GUA";
                destino_completo="Guaymas";
                break;
            case 12:
                destino="LAY";
                destino_completo="La Y";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
//boton
        Button btnMostrar_fechas= (Button) findViewById(R.id.btn_horarios);
        btnMostrar_fechas.setOnClickListener(this);
//Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//Menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_nosotros) {
            // Handle the camera action
            Intent i = new Intent (this,Nosotros.class);
            startActivity(i);
        } else if (id == R.id.nav_autobuses) {
            Intent i = new Intent (this,Camiones.class);
            startActivity(i);
        } else if (id == R.id.nav_paqueteria) {
            Intent i = new Intent (this,Paqueteria.class);
            startActivity(i);
        } else if (id == R.id.nav_oficinas) {

        } else if (id == R.id.nav_factura) {

        } else if (id == R.id.nav_tips) {

        }
        else if (id == R.id.nav_contacto) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
        public void startSlider() {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {

                public void run() {
                    // avoid exception:
                    // "Only the original thread that created a view hierarchy can touch its views"
                    runOnUiThread(new Runnable() {
                        public void run() {
                            imageSwitcher.setImageResource(gallery[position]);
                            position++;
                            if (position == gallery.length) {
                                position = 0;
                            }
                        }
                    });
                }

            }, 0, DURATION);
        }
public void datePicker(View view){
    DatePickerFragment fragment=new DatePickerFragment();
    fragment.show(getSupportFragmentManager(),"date");

}
    private void setDate(final Calendar calendar){
final DateFormat dateFormat= DateFormat.getDateInstance(DateFormat.MEDIUM);
        ( (TextView) findViewById(R.id.mostrar_fecha)).setText(dateFormat.format(calendar.getTime()));

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
Calendar cal= new GregorianCalendar(year,month,dayOfMonth);
        setDate(cal);
    }
//metodo boton consultar horarios
    @Override
    public void onClick(View v) {
        TextView mostrar_fecha= (TextView) findViewById(R.id.mostrar_fecha);
        String fecha= mostrar_fecha.getText().toString();

        Intent i = new Intent(MainActivity.this, mostrar_fechas.class);
Bundle bundle= new Bundle();
        bundle.putString("origen",origen);
          //  i.putExtra("origen",origen.toString() );
        i.putExtras(bundle);
          i.putExtra("destino",destino.toString());
        i.putExtra("destino_completo",destino_completo);
        i.putExtra("origen_completo",origen_completo);
        startActivity(i);

    }

    public static class DatePickerFragment extends android.support.v4.app.DialogFragment{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar c= Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);

return new DatePickerDialog(getActivity(),
        (DatePickerDialog.OnDateSetListener)
        getActivity(), year,month,day);

        }
    }



}

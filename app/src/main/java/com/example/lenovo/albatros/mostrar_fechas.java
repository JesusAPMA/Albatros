package com.example.lenovo.albatros;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static android.R.attr.data;
import static android.R.attr.focusable;

public class mostrar_fechas extends AppCompatActivity {
ListView listado;
    TextView prueba;
    String verificar_origen;
    String verificar_destino;
    String origen;
    String destino;
    String origen_completo;
    String destino_completo;
    String extra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_fechas);
Bundle bundle = getIntent().getExtras();
        origen=bundle.getString("origen");
       // origen = getIntent().getExtras().getString("origen");
       destino = getIntent().getStringExtra("destino");
origen_completo=getIntent().getStringExtra("origen_completo");
        destino_completo=getIntent().getStringExtra("destino_completo");

         //temp= origen;


       // Toast.makeText(this.getBaseContext(),(ipAddress),
       //         Toast.LENGTH_SHORT).show();
        if (origen=="HERMOSILLO"){
            verificar_origen="HMO";
        }
        if (origen=="Puerto Peñasco")verificar_origen="PPE";
        if (origen=="Obregon")verificar_origen="OBR";
        if (origen=="Caborca")verificar_origen="CAB";
        if (origen=="Nogales")verificar_origen="NOG";
        if (origen=="Navojoa")verificar_origen="NAV";
        if (origen=="Alamos")verificar_origen="ALA";
        if (origen=="Huatabampo")verificar_origen="HUA";
        if (origen=="Etchojoa")verificar_origen="ETC";
        if (origen=="Santa Ana")verificar_origen="SAN";
        if (origen=="Magdalena")verificar_origen="MAG";
        if (origen=="Guaymas")verificar_origen="GUA";
        if (origen=="La Y")verificar_origen="LAY";


        if (destino=="Hermosillo")verificar_destino="HMO";
        if (destino=="Puerto Peñasco")verificar_destino="PPE";
        if (destino=="Obregon")verificar_destino="OBR";
        if (destino=="Caborca")verificar_destino="CAB";
        if (destino=="Nogales")verificar_destino="NOG";
        if (destino=="Navojoa")verificar_destino="NAV";
        if (destino=="Alamos")verificar_destino="ALA";
        if (destino=="Huatabampo")verificar_destino="HUA";
        if (destino=="Etchojoa")verificar_destino="ETC";
        if (destino=="Santa Ana")verificar_destino="SAN";
        if (destino=="Magdalena")verificar_destino="MAG";
        if (destino=="Guaymas")verificar_destino="GUA";
        if (destino=="La Y")verificar_destino="LAY";



        listado= (ListView) findViewById(R.id.listview);
        //Listview en accion
        /*
        listado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */

        ObtDatos();
    }
    public void CargaLista(ArrayList<String> datos){
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        listado.setAdapter(adapter);
    }
public void ObtDatos(){
    AsyncHttpClient client= new AsyncHttpClient();
    //consulta_app.php?origen=NAV&destino=ALA
    //?origen="+verificar_origen+"&destino="+verificar_destino
    String url="http://www.albatrosmexico.com/jesus/consulta_app.php";
    WifiManager wifiMgr = (WifiManager) getSystemService(WIFI_SERVICE);
    WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
    int ip = wifiInfo.getIpAddress();
    String ipAddress = Formatter.formatIpAddress(ip);
    RequestParams parametros = new RequestParams();


    parametros.put("origen",origen);
    parametros.put("destino",destino);
    parametros.put("ip",ipAddress);
    client.post(url, parametros, new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            if (statusCode==200){
        CargaLista(obtDatosJson(new String(responseBody)));
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

        }
    });
}
    public ArrayList<String> obtDatosJson(String response){
        ArrayList<String> listado = new ArrayList<String>();
        try {
            JSONArray jsonArray= new JSONArray(response);
            String texto;
            for (int i=0;i<jsonArray.length();i++){
                String temp1=""+jsonArray.getJSONObject(i).getString("hora").charAt(0);
                String temp2=""+jsonArray.getJSONObject(i).getString("hora").charAt(1);
                String temp3=""+jsonArray.getJSONObject(i).getString("hora").charAt(2);
                String temp4=""+jsonArray.getJSONObject(i).getString("hora").charAt(3);
                String temp=temp1+temp2+":"+temp3+temp4;
                if(jsonArray.getJSONObject(i).getString("extra").charAt(0)=='V'){
                    extra="Express";

                }
                if (jsonArray.getJSONObject(i).getString("extra").charAt(0)=='F'){
                    extra="Normal";
                }
                texto = origen_completo+" - "+
                        destino_completo+" "+temp+" "+extra;

                listado.add(texto);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listado;
    }
}

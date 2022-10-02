package com.robiultech.jsonarrayrecylerview.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.robiultech.jsonarrayrecylerview.R;
import com.robiultech.jsonarrayrecylerview.adapters.RecyclerAdapter;
import com.robiultech.jsonarrayrecylerview.models.Aime;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String JSON_URL ="https://robiulislamsumon.000webhostapp.com/apps/jsonexample.json";
    private final String phpurl ="https://robiulislamsumon.000webhostapp.com/apps/adview.php";

    private JsonArrayRequest jsonArrayRequest;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private RequestQueue requestQueue1;
    private List<Aime> isAime;
    private RecyclerView recyclerView;
    LottieAnimationView lottieAnimationView;
    AdView adView;
    public static boolean SHOW_AD= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lottieAnimationView=findViewById(R.id.myLottie);
        adView=findViewById(R.id.adView);
        //Init Admob Ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        requestQueue1= Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.GET,phpurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.contains("showads")){
                    SHOW_AD= true;
                    adView.setVisibility(View.VISIBLE);
                    AdRequest adRequest= new AdRequest.Builder().build();
                    adView.loadAd(adRequest);

                }
                else {
                    adView.setVisibility(View.GONE);
                    SHOW_AD=false;

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Not Work", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue1.add(stringRequest);
        recyclerView=findViewById(R.id.recyclerView);
        isAime= new ArrayList<>();

        jsonrequest();

    }

    private void jsonrequest() {
        jsonArrayRequest= new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                lottieAnimationView.setVisibility(View.GONE);
                JSONObject jsonObject= null;
                for(int i= 0; i< response.length();i++){
                    try {
                       jsonObject=  response.getJSONObject(i);
                       Aime aime= new Aime();
                       aime.setName(jsonObject.getString("name"));
                       aime.setEmail(jsonObject.getString("email"));
                       aime.setPhone(jsonObject.getString("phone"));
                       aime.setDistrict(jsonObject.getString("district"));
                       aime.setImgUrl(jsonObject.getString("img"));
                       isAime.add(aime);

                    }catch (Exception e){
                        e.printStackTrace();

                    }

                }
                setuprecyclerViiew(isAime);

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                lottieAnimationView.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "not work", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonArrayRequest);
    }
    private void setuprecyclerViiew(List<Aime> isAime) {
        RecyclerAdapter adapter = new RecyclerAdapter(this,isAime);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);


    }
}
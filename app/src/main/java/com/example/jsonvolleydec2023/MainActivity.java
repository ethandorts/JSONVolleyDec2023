package com.example.jsonvolleydec2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = findViewById(R.id.textView);
        String url = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
        callVolley(url);
    }

    private void callVolley(String newURL) {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, newURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray hero = obj.getJSONArray("heroes");

                            for(int i = 0; i < hero.length(); i++) {
                                JSONObject heroObject = hero.getJSONObject(i);
                                String name = heroObject.getString("name");
                                String imageUrl = heroObject.getString("imageurl");
                                txtView.append("Name: " + name + "\n" + " ImageURL: " + imageUrl + "\n");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
}
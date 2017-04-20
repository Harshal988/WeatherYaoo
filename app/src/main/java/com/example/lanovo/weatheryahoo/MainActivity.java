package com.example.lanovo.weatheryahoo;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lanovo.weatheryahoo.Data.Channel;
import com.example.lanovo.weatheryahoo.Data.Item;
import com.example.lanovo.weatheryahoo.Service.WeatherServiceCallBack;
import com.example.lanovo.weatheryahoo.Service.YahooWeatherService;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallBack {

    private ImageView weatherIcon;
    private TextView temp,condition,location;
    private YahooWeatherService service;
private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherIcon = (ImageView)findViewById(R.id.weatherIconImageView);
        temp = (TextView)findViewById(R.id.temperatureTextView);
        condition = (TextView)findViewById(R.id.conditionTextView);
        location = (TextView)findViewById(R.id.locationTextView);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        service.refreshWeather("Surat,Gujarat");

    }

    @Override
    public void serviceSuccess(Channel channel) {
     dialog.hide();
        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/icon_"+ item.getCondition().getCode(),null,getPackageName());

        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);
weatherIcon.setImageDrawable(weatherIconDrawable);
        temp.setText(item.getCondition().getTempareture()+"\u00B0 "+channel.getUnits().getTempareture());
        condition.setText(item.getCondition().getDescription());
        location.setText(service.getLocation());
    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}

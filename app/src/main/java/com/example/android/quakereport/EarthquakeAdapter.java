package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPERATOR = " of ";
    private String locationString, locationOffsetString;
    private double magnitudeColor;

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

        }

        Earthquake currentEarthquakePosition = getItem(position);

        // Find the text views
        TextView locationView = (TextView) listItemView.findViewById(R.id.earthquakeLocation);
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.earthquakeLocationOffset);
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.earthquakeMag);
        TextView timeView = (TextView) listItemView.findViewById(R.id.earthquakeTime);
        TextView dateView = (TextView) listItemView.findViewById(R.id.earthquakeDate);
        GradientDrawable magCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get and set the bacnkground color for the magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquakePosition.getMag());
        magCircle.setColor(magnitudeColor);


        // Set the values
        Date dateObject = new Date(currentEarthquakePosition.getTimeInMilliSeconds());
        String location = currentEarthquakePosition.getLocation();

        if (location.contains(LOCATION_SEPERATOR)) {
            String[] splitLocationString = location.split(LOCATION_SEPERATOR);
            locationOffsetString = splitLocationString[0] + LOCATION_SEPERATOR;
            locationString = splitLocationString[1];
        } else {
            locationString = location;
            locationOffsetString = getContext().getString(R.string.near_the);
        }
        locationView.setText(locationString);
        locationOffsetView.setText(locationOffsetString);

        String formattedTime = formatTime(dateObject);
        String formattedDate = formatDate(dateObject);
        String formattedMag = formatDecimal(currentEarthquakePosition.getMag());

        timeView.setText(formattedTime);
        dateView.setText(formattedDate);
        magnitudeView.setText(formattedMag);

        return listItemView;


    }

    private String formatDecimal(double decimalFormat) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(decimalFormat);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.US);
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.US);
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}

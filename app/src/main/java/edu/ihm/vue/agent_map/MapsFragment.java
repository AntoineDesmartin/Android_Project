package edu.ihm.vue.agent_map;

import static android.content.Context.LOCATION_SERVICE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;


import edu.ihm.vue.R;
import edu.ihm.vue.main_activities.AgentActivity;
import edu.ihm.vue.models.DechetSignalement;
import edu.ihm.vue.models.Signalement;
import edu.ihm.vue.agent_signalements_view.AgentSignalementInfoDisplayActivity;
import edu.ihm.vue.agent_signalements_view.Clickable;

public class MapsFragment extends Fragment implements OnMapReadyCallback,SignalementListenerAdapter,Clickable{

    private final int FINE_PERMISSION_CODE=1;
    private Location currentLocation;
    private GoogleMap myMap;
    private SignalementListenerAdapter listener=this;
    private Clickable clickable = this;
    private final String TAG = "tonio2 "+getClass().getSimpleName();

    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        @Override
        public void onMapReady(GoogleMap googleMap) {
            myMap=googleMap;
            /*List<Dechet> dechets = new ArrayList<>();
            // Ajout d'un déchet à la liste
            Dechet d1 = new Dechet("testmap", new Date(), "gros", "nice");
            dechets.add(d1);

            // Ajout d'autres déchets à la liste
            dechets.add(new Dechet("dechet2", new Date(), "petit", "paris"));
            dechets.add(new Dechet("dechet3", new Date(), "moyen", "berlin"));
            dechets.add(new Dechet("dechet4", new Date(), "gros", "londres"));*/

            //float zoomLevel = 12.0f;
            //myMap.moveCamera( CameraUpdateFactory.newLatLngZoom( new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),10f ) );

            for (int i = 0; i < AgentActivity.mesSignalements.size(); i++) {
                Signalement dechet = AgentActivity.mesSignalements.get(i);
                //obtenir la longitude et la latitude
                getLocationFromAddress(dechet.getAddress()+", "+dechet.getZipCode()+" "+dechet.getCity(),dechet);

                LatLng dechetPosition = new LatLng(dechet.getLat(),dechet.getLon());
                MarkerOptions markerOptions = new MarkerOptions().position(dechetPosition).title(dechet.getTitle());
                googleMap.addMarker(markerOptions);
            }

            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    int i = getIndexOfDechet(marker.getPosition(), AgentActivity.mesSignalements);
                    if (i != -1) {
                        clickable.onClickButton(i);
                        //listener.onClickDechet(dechets.get(i));
                    }
                }
            });
        }

        // Méthode pour obtenir l'indice du déchet associé à une position de marqueur
        private int getIndexOfDechet(LatLng position, List<Signalement> dechets) {
            for (int i = 0; i < dechets.size(); i++) {
                Signalement dechet = dechets.get(i);
                LatLng dechetPosition = new LatLng(dechet.getLat(), dechet.getLon());
                if (position.equals(dechetPosition)) {
                    return i;
                }
            }
            return -1; // Retourne -1 si aucun déchet n'est trouvé à cette position
        }



    };
    public void getLocationFromAddress(String strAddress, Signalement d) {
        Geocoder coder = new Geocoder(getContext(), Locale.getDefault());
        List<Address> addressList;

        try {
            // Attempt to get a list of addresses that match the provided address
            addressList = coder.getFromLocationName(strAddress, 5);
            if (addressList == null || addressList.isEmpty()) {
                // Handle the case where no addresses were found
                return;
            }

            // Get the first address from the list
            Address location = addressList.get(0);

            // Extract the latitude and longitude
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Log.d(TAG,latitude+" getLat after");
            Log.d(TAG,longitude+" getLat after");
            d.setLat(latitude);
            d.setLon(longitude);
            // Output the latitude and longitude to the console (or use as needed)
            System.out.println("Latitude: " + latitude + " Longitude: " + longitude);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        boolean permissionGranted = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        Log.d(TAG,"permission : "+permissionGranted);

        if (permissionGranted) {
            LocationListener listener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    Log.d(TAG,"test map loc : " + location);
                    currentLocation = location;
                    //myMap.moveCamera( CameraUpdateFactory.newLatLngZoom( new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),15f ) );

                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {
                    Log.d(TAG, "satus changed=" + s);
                }

                @Override
                public void onProviderEnabled(String s) {
                    Log.d(TAG, s + " sensor ON");
                }

                @Override
                public void onProviderDisabled(String s) {
                    Log.d(TAG, s + " sensor OFF");
                }
            };
            LocationManager locationManager = (LocationManager) (getActivity().getSystemService(LOCATION_SERVICE));
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, listener);
        }
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }

    @Override
    public void onClickDechet(Signalement item) {
        Log.d(TAG, "getView: "+item);

        Intent intent = new Intent(getContext(), AgentSignalementInfoDisplayActivity.class);
        intent.putExtra("signalement", (Parcelable) item );
        startActivity(intent);
    }

    @Override
    public void onClickButton(int position) {
        Intent intent = new Intent(getContext(), AgentSignalementInfoDisplayActivity.class);
        intent.putExtra("signalement",(Parcelable) AgentActivity.mesSignalements.get(position));
        startActivity(intent);
    }
}

package edu.ihm.vue.agent_map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import edu.ihm.vue.R;
import edu.ihm.vue.main_activities.AgentActivity;
import edu.ihm.vue.models.Signalement;
import edu.ihm.vue.agent_signalements_view.AgentSignalementInfoDisplayActivity;
import edu.ihm.vue.agent_signalements_view.Clickable;

public class MapsFragment extends Fragment implements OnMapReadyCallback,SignalementListenerAdapter,Clickable{

    private SignalementListenerAdapter listener=this;
    private Clickable clickable = this;
    private final String TAG = "tonio "+getClass().getSimpleName();

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        /*@Override
        public void onMapReady(GoogleMap googleMap) {
            Dechet d1 = new Dechet("testmap",new Date(),"gros","nice",-34, 151);
            LatLng sydney = new LatLng(-34, 151);
            LatLng sydney2 = new LatLng(-36, 151);
            float zoomLevel = 12.0f;
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));


            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));

            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    listener.onClickDechet(dechets.get(i));

                    //Intent intent = new Intent(getContext(), MainActivity.class);
                    //startActivity(intent);
                }

            });
        }*/
        @Override
        public void onMapReady(GoogleMap googleMap) {
            /*List<Dechet> dechets = new ArrayList<>();
            // Ajout d'un déchet à la liste
            Dechet d1 = new Dechet("testmap", new Date(), "gros", "nice");
            dechets.add(d1);

            // Ajout d'autres déchets à la liste
            dechets.add(new Dechet("dechet2", new Date(), "petit", "paris"));
            dechets.add(new Dechet("dechet3", new Date(), "moyen", "berlin"));
            dechets.add(new Dechet("dechet4", new Date(), "gros", "londres"));*/

            float zoomLevel = 12.0f;

           // googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));

            for (int i = 0; i < AgentActivity.mesSignalements.size(); i++) {
                Signalement dechet = AgentActivity.mesSignalements.get(i);
                //EPHEMERE :
                //------------------------------------------------------------
                Random random = new Random();
                double lat = -random.nextInt(21) + 20;
                dechet.setLat(lat);
                double lon = random.nextInt(21) + 140;
                dechet.setLon(lon);
                //----------------------------------------------------
                //LatLng dechetPosition = new LatLng(getLat(),getLon());
                LatLng dechetPosition = new LatLng(lat,lon);
                MarkerOptions markerOptions = new MarkerOptions().position(dechetPosition).title(dechet.getTitreSignalement());
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
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

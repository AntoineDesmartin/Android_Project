package edu.ihm.vue.signalemet_fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import android.Manifest;

import edu.ihm.vue.IPictureActivity;
import edu.ihm.vue.R;
import edu.ihm.vue.SignalementListener;

public class CameraSignalement extends Fragment {
    ImageView imageView;
    private SignalementListener mListener;
    Bitmap image;

    public CameraSignalement() {
    }
    public CameraSignalement(Bitmap image) {
        this.image=image;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_camera_signalement, container, false);
        imageView = rootView.findViewById(R.id.photoincident);
        if(this.image!=null)
            setImage(image);
        rootView.findViewById(R.id.prendrephoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA},
                            IPictureActivity.REQUEST_CAMERA);
                } else {
                    takePicture();
                }
            }
        });
        Button buttonAnnuler = rootView.findViewById(R.id.annuler);
        buttonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.annulerSignalement();
                }
            }
        });

        Button back = rootView.findViewById(R.id.retour);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.backToDateSignalementFragment();
                }
            }

        });

        Button suivant = rootView.findViewById(R.id.suivant);
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.goToAdresseSignalementFragment();
                }
            }
        });
        return rootView;
    }


    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getActivity().startActivityForResult(intent, IPictureActivity.REQUEST_CAMERA);
    }

    public void setImage(Bitmap image) {
        imageView.setImageBitmap(image);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SignalementListener) {
            mListener = (SignalementListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SignalementListener");
        }
    }
}
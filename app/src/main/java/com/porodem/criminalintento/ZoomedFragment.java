package com.porodem.criminalintento;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Date;

/**
 * Created by porod on 03.03.2018.
 */

public class ZoomedFragment extends DialogFragment {

    private static final String ARG_PATH = "path";

    private ImageView mImageView;

    public static ZoomedFragment newInstance(String date){
        Bundle args = new Bundle();
        args.putString(ARG_PATH, date);

        ZoomedFragment fragment = new ZoomedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String path = getArguments().getString(ARG_PATH);

        View v = inflater.inflate(R.layout.dialog_zoom,container, false);
        mImageView = v.findViewById(R.id.zoomView);

        Bitmap bitmap = PictureUtils.getScaledBitmap(
                path, getActivity());
        mImageView.setImageBitmap(bitmap);

        return v;
    }
}

package com.incomm.payithere.views.locations;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by agodambe on 5/1/2017.
 */

public class TouchableMapFragment extends SupportMapFragment {
    public View mOriginalContentView;
    public TouchableWrapper mTouchView;
    private MapTouchListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        mOriginalContentView = super.onCreateView(inflater, parent, savedInstanceState);
        mTouchView = new TouchableWrapper(getContext());
        mTouchView.addView(mOriginalContentView);
        return mTouchView;
    }

    @Override
    public View getView() {
        return mOriginalContentView;
    }

    public void setMapTouchListener(MapTouchListener listener) {
        mListener = listener;
    }

    public interface MapTouchListener {
        boolean onTouch(MotionEvent motionEvent);
    }

    public class TouchableWrapper extends FrameLayout {

        public TouchableWrapper(Context context) {
            super(context);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            if (mListener != null) {
                mListener.onTouch(event);
            }
            return super.dispatchTouchEvent(event);
        }
    }
}

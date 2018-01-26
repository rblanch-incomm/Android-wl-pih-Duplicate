package com.incomm.payithere.views.locations;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.incomm.payithere.R;
import com.incomm.payithere.factories.DialogFactory;
import com.incomm.payithere.models.services.response.Store;
import com.incomm.payithere.repositories.LocationsCMSRepository;
import com.incomm.payithere.services.GetStoresService;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationsFragment extends ViewFragment implements LocationsMVP.View, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, TouchableMapFragment.MapTouchListener,EditText.OnEditorActionListener {

    @BindView(R.id.detail_store_name)
    TextView detailsStoreName;
    @BindView(R.id.detail_store_phone)
    TextView detailsStorePhone;
    @BindView(R.id.detail_store_addr_street)
    TextView detailsStoreAddrStreet;
    @BindView(R.id.detail_store_addr_2)
    TextView detailsStoreAddress2;
    @BindView(R.id.detail_store_addr_city)
    TextView detailsStoreAddrCity;
    @BindView(R.id.detail_store_addr_province)
    TextView detailsStoreAddrProvince;
    @BindView(R.id.detail_store_addr_zipcode)
    TextView detailsStoreAddrZipcode;
    @BindView(R.id.detail_maps_text)
    TextView detailsMapText;
    @BindView(R.id.details_call_button)
    ImageView detailsCallButton;
    @BindView(R.id.details_maps_button)
    ImageView detailsMapsButton;
//    @BindView(R.id.details_store_layout)
//    LinearLayout detailsStoreLayout;
    @BindView(R.id.button_search)
    ImageView searchButton;
    @BindView(R.id.edittext_search)
    EditText editTextSearch;
    @BindView(R.id.search_view)
    LinearLayout searchView;
    @BindView(R.id.gps_button)
    ImageView gpsButton;
    @BindView(R.id.details_store_card_layout)
    CardView detailsStoreLayout;


    private MainTabInterface mListener;
    private LocationsPresenter presenter;
    public final static int REQUEST_LOCATION = 199;
    private final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 124;
    private GoogleApiClient mGoogleApiClient;
    private boolean searchButtonClicked,gpsButtonClicked = false;
    private DisplayImageOptions imageOptions;
    private ImageLoader imageManager = ImageLoader.getInstance();
    private GoogleMap mGoogleMap;
    private LatLng mLatLng;
    private String mAddress, mAddressprivate, mCurrentStoreLatitude, mCurrentStoreLongitude, mPhoneNumber;
    private boolean detailsViewShown,actionDown, actionMove = false;
    private ArrayList<Marker> mMarkersList = new ArrayList<>();
    private Marker mCurrLocationMarker, mPreviousLocationMarker, mStoreMarker,mMarkerSelected;
    private TouchableMapFragment mapFrag;
    private LatLngBounds.Builder mLatLngBuilder;
    private HashMap<Marker, Store> storeHashMap;
    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 2;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    private LocationManager locationManager;
    private Location mLastLocation;
    private boolean mHideElements = true;
    private LocationRequest mLocationRequest;
    private float mDensity, viewOffset, viewStartYPos, viewStartYPosGpsButton, viewStartPosSearch, viewOffsetTop, topViewMaxDisp;
    private Point start,end;


    public LocationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildGoogleApiClient();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        imageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .imageScaleType(ImageScaleType.NONE)
                .build();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //TODO: Create a copy from the below xml, rename appropriately
        View rootView = inflater.inflate(R.layout.fragment_locations, container, false);
        ButterKnife.bind(this, rootView);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(Color.parseColor("#007e58"));

        editTextSearch.setOnEditorActionListener(this);
        presenter = new LocationsPresenter(this, new LocationsCMSRepository(), new GetStoresService());
        presenter.getViewElements();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTabInterface) {
            mListener = (MainTabInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainTabInterface");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setTitle();
        gpsButtonClicked = false;
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    protected synchronized void buildGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        } else {
            // fragmentBecameVisible();
        }
    }

    @OnClick(R.id.gps_button)
    void onGPSButtonClick() {
        //checkGpsAndTakeAction();
        hideSoftKeyboard(getActivity());
        gpsButtonClicked = true;
        checkLocationsPermissions();
    }

    @OnClick(R.id.button_search)
    void onSearchButtonClick() {
        mAddress = editTextSearch.getText().toString();
        hideSoftKeyboard(getActivity());
        getStoresByAddress();
        detailsStoreLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.detail_maps_text)
    void onMapsButtonClick(){
        String uri = "google.navigation:q=" + mCurrentStoreLatitude + "," + mCurrentStoreLongitude;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        getActivity().startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
            presenter.cancelAllCalls();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setupUi() {
        presenter.setTitle();
        presenter.getSearchIcon();
        presenter.getSearchPlaceholderText();
        presenter.getCurrentLocationIcon();
        presenter.getCallIcon();
        presenter.getDirectionsIcon();
        presenter.getDirectionsText();
    }

    @Override
    public void setViewFeatures() {

    }

    @Override
    public void displayTitle(String title) {
        mListener.onSetToolbarTitle(title);
    }

    @Override
    public void showError(String error) {
        displayErrorAlert(error);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.getUiSettings().setMapToolbarEnabled(false);
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        checkLocationsPermissions();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (!presenter.isFromTab()) {
            fragmentBecameVisible();
        }


    }

    public void fragmentBecameVisible() {
        mapFrag = (TouchableMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
        mapFrag.setMapTouchListener(this);
        presenter.setIsFromTab(false);
    }

    @Override
    public void showStoresOnMap(List<Store> storesList) {
        if (mMarkersList != null) {
            mMarkersList.clear();
            mPreviousLocationMarker = null;
            mLatLngBuilder = new LatLngBounds.Builder();
            storeHashMap = new HashMap<>();
            mGoogleMap.clear();
            detailsStoreLayout.setVisibility(View.GONE);
            gpsButtonClicked = false;
        }

        for (Store current : storesList) {
            double latitude = current.getCoordinates().getLatitude();
            double longitude = current.getCoordinates().getLongitude();
            LatLng latLng = new LatLng(latitude, longitude);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.icon(ActivityUtils.getMarkerIcon(presenter.getThemeColor()));
            mStoreMarker = mGoogleMap.addMarker(markerOptions);
            mStoreMarker.setTag(current);
            /*Bitmap bitmap = imageManager.loadImageSync(current.getMerchant().getIconUrl(), imageOptions);
            Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 80, 80, false);
            mStoreMarker.setIcon(BitmapDescriptorFactory.fromBitmap(newBitmap));*/
            mGoogleMap.addMarker(markerOptions);
            mMarkersList.add(mStoreMarker);
            mLatLngBuilder.include(latLng);
            storeHashMap.put(mStoreMarker, current);
        }
        LatLngBounds bounds = mLatLngBuilder.build();
        int padding = 120; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mGoogleMap.animateCamera(cu);
        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Store store = (Store) marker.getTag();
                mMarkerSelected = marker;
                if (store != null) {
                    showStoreDetailsView(store);
                    mPreviousLocationMarker = marker;
                }
                marker.setTitle(store.getMerchant().getName());
                return false;
            }
        });
    }

    @Override
    public void showSearchIcon(String icon) {
        imageManager.displayImage(icon, searchButton, imageOptions);
    }

    @Override
    public void showPlaceholderText(String text) {
        editTextSearch.setHint(text);
    }

    @Override
    public void showCurrentLocationIcon(String location) {
        imageManager.displayImage(location, gpsButton, imageOptions);
    }

    @Override
    public void showCallIcon(String callIcon) {
        imageManager.displayImage(callIcon, detailsCallButton, imageOptions);
    }

    @Override
    public void showDirectionsIcon(String directions) {
        imageManager.displayImage(directions, detailsMapsButton, imageOptions);
    }

    @Override
    public void displayDirectionsText(String text) {
        detailsMapText.setText(text);
        detailsMapText.setTextColor(Color.parseColor(presenter.getThemeColor()));
    }

    public void showStoreDetailsView(Store store) {
        detailsViewShown = true;
        detailsStoreLayout.setVisibility(View.VISIBLE);
        detailsStoreName.setText(store.getMerchant().getName());

        if (!store.getPhones().isEmpty()) {
            mPhoneNumber = store.getPhones().get(0);
            if (mPhoneNumber.length() < 10) {
                detailsStorePhone.setVisibility(View.GONE);
                detailsCallButton.setVisibility(View.GONE);
            } else {
                detailsStorePhone.setVisibility(View.VISIBLE);
                detailsCallButton.setVisibility(View.VISIBLE);
                detailsStorePhone.setText(PhoneNumberUtils.formatNumber(mPhoneNumber,"US"));
                detailsStorePhone.setTextColor(Color.parseColor(presenter.getThemeColor()));
            }
        } else {
            detailsStorePhone.setVisibility(View.GONE);
            detailsCallButton.setVisibility(View.GONE);
        }


        if (null == store.getCoordinates().getLatitude() && null == store.getCoordinates().getLongitude()) {
            detailsMapsButton.setVisibility(View.GONE);
            detailsMapText.setVisibility(View.GONE);
        } else {
            detailsMapsButton.setVisibility(View.VISIBLE);
            detailsMapText.setVisibility(View.VISIBLE);

        }
        detailsStoreAddrStreet.setText(store.getAddress().getAddress1());
        if(store.getAddress().getAddress2() != null && !store.getAddress().getAddress2().equals("")){
            detailsStoreAddress2.setText(store.getAddress().getAddress2());
            detailsStoreAddress2.setVisibility(View.VISIBLE);
        }else {
            detailsStoreAddress2.setVisibility(View.GONE);
        }
        detailsStoreAddrProvince.setText(store.getAddress().getState());
        detailsStoreAddrCity.setText(store.getAddress().getCity());
        detailsStoreAddrZipcode.setText(store.getAddress().getZipCode());
        mCurrentStoreLatitude = store.getCoordinates().getLatitude().toString();
        mCurrentStoreLongitude = store.getCoordinates().getLongitude().toString();
    }

    public void getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        } else {
            //mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void checkLocationsPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            boolean isNeverAskAccessClicked = presenter.isAccessLocationNeverAskAgainClicked();
            if (isNeverAskAccessClicked && (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)) {
                if(gpsButtonClicked){
                    AlertDialog permissionDialog = DialogFactory.createConfirmAlertDialog(getContext(), "Please go to settings to enable locations", "Settings", "Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    });
                    permissionDialog.show();
                }


            }else if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            } else {
                //startStoreSearch();
                //getCurrentLocation();
                checkGpsAndTakeAction();
            }
        } else {
            // API < 22
            //startStoreSearch();
            //getCurrentLocation();
            checkGpsAndTakeAction();
        }
    }

    private void startStoreSearch() {
        if (!searchButtonClicked) {
            checkGpsAndTakeAction();
        } else {
            getStoresByAddress();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                    /*if (!searchButtonClicked) {
                        checkGpsAndTakeAction();
                    } else {
                        getStoresByAddress();
                    }*/
                    getCurrentLocation();
                } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    // permission denied, boo! Disable the functionality that depends on this permission.
                    boolean showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!showRationale) {
                        // user also CHECKED "never ask again"
                        presenter.setAccessLocationNeverAskAgainClicked(true);
                        Log.d("LocationsFragment", "Location permission Never ask again clicked");
                        // the app setting
                    } else if (Manifest.permission.ACCESS_FINE_LOCATION.equals(permissions[0])) {
                    }
                } else {
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    process_phone_LAUNCH(mPhoneNumber);
                    // permission was granted, yay! Do the contacts-related task you need to do.
                } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    // permission denied, boo! Disable the functionality that depends on this permission.
                    boolean showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!showRationale) {
                        presenter.setCallPhoneNeverAskAgainClicked(true);
                        Log.d("LocationsFragment", " Call phone Never ask again clicked");
                        // the app setting
                    } else if (Manifest.permission.CALL_PHONE.equals(permissions[0])) {
                        // user did NOT check "never ask again"
                    }
                } else {
                }
                return;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("onActivityResult()", Integer.toString(resultCode));

        switch (requestCode) {
            case REQUEST_LOCATION:
                switch (resultCode) {
                    case Activity.RESULT_OK: {
                        // All required changes were successfully made
                        //Toast.makeText(getContext(), "Location enabled by user!", Toast.LENGTH_LONG).show();
                        Log.e("LocationsFragment", "Location enabled by user!");
                        mListener.onSetToolbarTitle("Locations");
                        /*if (searchButtonClicked) {
                            getStoresByAddress();
                        } else {
                            getCurrentLocation();
                        }*/
                        getCurrentLocation();
                        break;
                    }
                    case Activity.RESULT_CANCELED: {
                        // The user was asked to change settings, but chose not to
                        Log.e("LocationsFragment", "Location disabled by user!");
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
        }
    }

    private void getStoresByAddress() {
        presenter.getStoresByAddress(mAddress);
        //mGoogleMap.clear();
    }

    private void checkGpsAndTakeAction() {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //Toast.makeText(getContext(), "Locations enabled", Toast.LENGTH_SHORT).show();
            /*if (searchButtonClicked) {
                getStoresByAddress();
            } else {
                getCurrentLocation();
            }*/
            getCurrentLocation();
        } else {
            //Toast.makeText(getContext(), "Locations disabled", Toast.LENGTH_SHORT).show();
            presenter.displayLocationSettingsRequest(getContext(), getActivity(), mGoogleApiClient);
        }
    }

    private void process_phone_LAUNCH(String careNumber) {

        String numberText = careNumber;
        String number = numberText.replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("-", "")
                .replace("—", "");

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        mLatLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        Log.e("LocationsFragment", "Latitude : " + location.getLatitude() + " | Longitude : " + location.getLongitude());
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

        getStoresByLocation(mLatLng);
    }

    private void getStoresByLocation(LatLng location) {
        if (getContext() != null) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
            presenter.getStoresByLocation(location.latitude, location.longitude);
        }
        //mGoogleMap.clear();
    }

    @Override
    public boolean onTouch(MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_MOVE:
                   // Log.v("TouchableWrapper", "Google maps action move");
                break;
            case MotionEvent.ACTION_DOWN:
                Log.v("TouchableWrapper", "Google maps action down");
                start = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
                break;
            case MotionEvent.ACTION_UP:

                Log.v("TouchableWrapper", "Google maps action up");
                end = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
                double displacement = (Math.sqrt(Math.pow(start.x-end.x, 2) + Math.pow(start.y-end.y, 2)));
                Log.v("TouchableWrapper", "Displacement is "+displacement);

                if(mMarkerSelected==null){
                    break;
                }



                if(displacement < 30){
                        if(mHideElements){
                            if(!mMarkerSelected.isInfoWindowShown() && detailsStoreLayout.getVisibility()==View.VISIBLE){
                                hideTopViews();
                                hideStoreDetailView();
                                mHideElements = false;
                            }

                        }else {
                            showStoreDetailView();
                            showTopViews();
                            mHideElements = true;
                        }

                }
                break;

        }
        return false;
    }

    public void showStoreDetailView() {
        if (detailsViewShown) {
            viewOffset = detailsStoreLayout.getY() - viewStartYPos;
            detailsStoreLayout.animate().translationYBy(-viewOffset).setDuration(250);
        }
    }

    public void showTopViews() {
        viewOffsetTop = viewStartYPosGpsButton - gpsButton.getY();
        gpsButton.animate().translationYBy(viewOffsetTop).setDuration(250);
        searchView.animate().translationYBy(viewOffsetTop).setDuration(250);
    }

    public void hideTopViews() {
        viewStartYPosGpsButton = gpsButton.getY();
        viewStartPosSearch = searchView.getY();
        viewOffsetTop = 0;
        topViewMaxDisp = (gpsButton.getHeight() + searchView.getHeight()) * 1.5f;
        gpsButton.animate().translationYBy(-topViewMaxDisp).setDuration(250);
        searchView.animate().translationYBy(-topViewMaxDisp).setDuration(250);
    }

    public void hideStoreDetailView() {
        viewStartYPos = 0;
        viewOffset = 0;

        if (detailsViewShown) {
            viewStartYPos = detailsStoreLayout.getY();
            Log.d("DetailsViewCoordinates", "Move down getX() : " + detailsStoreLayout.getX() + " getY() : " + detailsStoreLayout.getY());
            detailsStoreLayout.animate().translationYBy(detailsStoreLayout.getHeight()+30).setDuration(250);
        }
    }

    @OnClick(R.id.detail_store_phone)
    void onCallButtonClick(){
        if (Build.VERSION.SDK_INT >= 23) {
            // Call some material design APIs here
            if(presenter.isCallPhoneNeverAskAgainClicked() && (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED)) {
                AlertDialog permissionDialog = DialogFactory.createConfirmAlertDialog(getContext(),"Please go to settings to turn on permissions", "Settings", "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                });
                permissionDialog.show();

            }else if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }else {
                process_phone_LAUNCH(mPhoneNumber);
            }
        } else {
            // Implement this feature without material design
            process_phone_LAUNCH(mPhoneNumber);
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            searchButton.performClick();
            return true;
        }
        return false;
    }
}

package com.yuriy.fyberapp;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.yuriy.fyberapp.list.ListAdapterData;
import com.yuriy.fyberapp.list.OfferAdapter;
import com.yuriy.fyberapp.net.UrlBuilder;
import com.yuriy.fyberapp.service.DownloadingService;
import com.yuriy.fyberapp.utils.DeviceInfoHelper;
import com.yuriy.fyberapp.utils.ImageFetcher;
import com.yuriy.fyberapp.utils.ImageFetcherFactory;
import com.yuriy.fyberapp.vo.OfferVO;
import com.yuriy.fyberapp.vo.OffersVO;
import com.yuriy.fyberapp.vo.RequestParametersVO;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends FragmentActivity {

    /**
     * Tag for the logging message.
     */
    private static final String CLASS_NAME = MainActivity.class.getSimpleName();

    /**
     * Key to keep adapter data in the save instance bundle
     */
    private static final String KEY_ADAPTER_DATA = "KEY_ADAPTER_DATA";

    /**
     * Display progress of download
     */
    private ProgressDialog mProgressDialog;

    /**
     * Stores an instance of {@link com.yuriy.fyberapp.MainActivity.DownloadHandler}.
     */
    private Handler mDownloadHandler = null;

    /**
     * List adapter for the list of Offers.
     */
    private OfferAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the downloadHandler.
        mDownloadHandler = new DownloadHandler(this);

        // Handles loading the  image in a background thread
        final ImageFetcher mImageFetcher = ImageFetcherFactory.getSmallImageFetcher(this);

        // Create and set empty adapter
        mListAdapter = new OfferAdapter(this, mImageFetcher);

        final ListView listView = (ListView) findViewById(R.id.offers_list_view);
        listView.setAdapter(mListAdapter);

        if (savedInstanceState == null) {

            showRequestDialog();

            return;
        }

        // Restore Activity state

        final ListAdapterData<OfferVO> listItems =
                (ListAdapterData<OfferVO>) savedInstanceState.getSerializable(KEY_ADAPTER_DATA);
        if (listItems == null) {
            return;
        }
        setListAdapterData(listItems.getItems());
    }

    @Override
    protected void onResume() {
        super.onResume();

        mListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {

        // Save current state of the Activity
        outState.putSerializable(KEY_ADAPTER_DATA, mListAdapter.getData());

        super.onSaveInstanceState(outState);
    }

    /**
     * Click handler od the "Make request" button.
     *
     * @param view View related to the button.
     */
    public void onMakeRequestHandler(final View view) {
        showRequestDialog();
    }

    /**
     * Show dialog for the request to the Fyber API.
     */
    private void showRequestDialog() {
        // Create an instance of the dialog fragment and show it
        final DialogFragment settingsDialog = new RequestParametersDialog();
        settingsDialog.show(getFragmentManager(), RequestParametersDialog.FRAGMENT_TAG);
    }

    /**
     * Display the Dialog to the User.
     *
     * @param message The String to display.
     */
    private void showDialog(final String message) {
        mProgressDialog = ProgressDialog.show(this, "Download", message, true);
    }

    /**
     * Dismiss the Dialog
     */
    private void dismissDialog() {
        if (mProgressDialog == null) {
            return;
        }
        mProgressDialog.dismiss();
    }

    /**
     * Add collection of the {@link com.yuriy.fyberapp.vo.OfferVO}'s into adapter.
     * @param data Collection of the {@link com.yuriy.fyberapp.vo.OfferVO}
     */
    private void setListAdapterData(final List<OfferVO> data) {
        final ListView listView = (ListView) findViewById(R.id.offers_list_view);
        if (listView == null) {
            return;
        }
        listView.setVisibility(View.VISIBLE);

        mListAdapter.addItems(data);
        mListAdapter.notifyDataSetChanged();
    }

    /**
     * Callback method that is called from the {@link com.yuriy.fyberapp.RequestParametersDialog}.
     *
     * @param apiKey            API Key
     * @param appId             Application ID
     * @param pub0              Pub0 (custom parameter)
     * @param userId            User ID
     * @param isUseFakeResponse Set to True is it is necessary to receive fake response
     */
    public void onRequestParametersDialogResult(final String userId, final String apiKey,
                                                final String appId, final String pub0,
                                                final boolean isUseFakeResponse) {

        hideResponseErrorMessage();

        final ListView listView = (ListView) findViewById(R.id.offers_list_view);
        listView.setVisibility(View.GONE);

        mListAdapter.clear();
        mListAdapter.notifyDataSetChanged();

        showDialog("Download Offers");

        // Collect request parameters
        final RequestParametersVO requestParametersVO = RequestParametersVO.createInstance();
        requestParametersVO.setAppId(appId);
        requestParametersVO.setPub0(pub0);
        requestParametersVO.setUserId(userId);
        requestParametersVO.setTimestamp(String.valueOf(System.currentTimeMillis() / 1000));
        requestParametersVO.setLocale(DeviceInfoHelper.DEVICE_LOCALE);
        requestParametersVO.setDeviceId(DeviceInfoHelper.DEVICE_ID);

        // Build request url
        final String url = UrlBuilder.getUrlForRequest(requestParametersVO, apiKey);

        // Create an Intent to download weather data in the background via a Service.
        // The downloaded data is later displayed in the
        // UI Thread via the downloadHandler() method defined below.
        final Intent intent = DownloadingService.makeRequestIntent(this,
                Uri.parse(url), apiKey, isUseFakeResponse,
                mDownloadHandler);

        // Start the DownloadService.
        startService(intent);
    }

    /**
     * Show response error message.
     *
     * @param message Response Error message.
     */
    private void showResponseErrorMessage(final String message) {
        Log.d(CLASS_NAME, "Error message:" + message);
        final TextView textView = (TextView) findViewById(R.id.response_error_msg_view);
        textView.setVisibility(View.VISIBLE);
        textView.setText(message);
    }

    /**
     * Hide response error message.
     */
    private void hideResponseErrorMessage() {
        final TextView textView = (TextView) findViewById(R.id.response_error_msg_view);
        textView.setText("");
        textView.setVisibility(View.GONE);
    }

    /**
     * An inner class that inherits from {@link android.os.Handler}
     * and uses its {@link android.os.Handler#handleMessage(android.os.Message)}
     * hook method to process Messages
     * sent to it from the {@link com.yuriy.fyberapp.service.DownloadingService}.
     */
    private static class DownloadHandler extends Handler {

        /**
         * Allows Activity to be garbage collected properly.
         */
        private WeakReference<MainActivity> mActivity;

        /**
         * Class constructor constructs {@link #mActivity} as weak reference
         * to the activity.
         *
         * @param activity The corresponding activity.
         */
        public DownloadHandler(final MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        /**
         * This hook method is dispatched in response to receiving the
         * pathname back from the {@link com.yuriy.fyberapp.service.DownloadingService}.
         */
        public void handleMessage(final Message message) {

            final MainActivity activity = mActivity.get();

            // Bail out if the MainActivity is gone.
            if (activity == null) {
                return;
            }

            // Stop displaying the progress dialog.
            activity.dismissDialog();

            // Get message Id
            final int what = message.what;

            switch (what) {
                case DownloadingService.ServiceHandler.MSG_MAKE_REQUEST:
                    // Get Offers VO
                    final OffersVO offersVO = DownloadingService.getOffers(message);
                    // Check it for the NULL value
                    if (offersVO == null)
                        return;
                    // If response is not successful - show error message
                    if (!DownloadingService.isResponseCodeOK(offersVO)) {
                        // Get error message from the Offer VO by passing it to the service.
                        // This is allows to separate UI from the any non UI logic.
                        String errorMessage = DownloadingService.getResponseMessage(offersVO);
                        if (errorMessage.isEmpty()) {
                            errorMessage = "Error " + DownloadingService.getResponseCode(offersVO)
                                    + " during request";
                        }
                        // This is simple way to inform UI about un-successful result
                        // This is a good place to implement Command pattern in order to
                        // show appropriate view which is depends on the return Error Code or
                        // Error Message
                        activity.showResponseErrorMessage(errorMessage);
                    } else {
                        activity.setListAdapterData(DownloadingService.getOffers(offersVO));
                    }
                    break;

                default:
                    Log.d(CLASS_NAME, "Unknown message id received:" + what);
            }
        }
    }
}

package com.yuriy.fyberapp;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.yuriy.fyberapp.service.DownloadingService;
import com.yuriy.fyberapp.util.DeviceInfoHelper;
import com.yuriy.fyberapp.util.UrlBuilder;
import com.yuriy.fyberapp.vo.RequestParametersVO;

import java.lang.ref.WeakReference;

public class MainActivity extends Activity {

    private static final String CLASS_NAME = MainActivity.class.getSimpleName();

    /**
     * Display progress of download
     */
    private ProgressDialog mProgressDialog;

    /**
     * Stores an instance of {@link com.yuriy.fyberapp.MainActivity.DownloadHandler}.
     */
    private Handler mDownloadHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the downloadHandler.
        mDownloadHandler = new DownloadHandler(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Create an instance of the dialog fragment and show it
        final DialogFragment settingsDialog = new RequestParametersDialog();
        settingsDialog.show(getFragmentManager(), RequestParametersDialog.FRAGMENT_TAG);
    }

    /**
     * Callback method that is called from the {@link com.yuriy.fyberapp.RequestParametersDialog}.
     *
     * @param apiKey API Key
     * @param appId  Application ID
     * @param pub0   Pub0 (custom parameter)
     * @param userId User ID
     */
    public void onRequestParametersDialogResult(final String userId, final String apiKey,
                                                final String appId, final String pub0) {

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
                Uri.parse(url),
                mDownloadHandler);

        // Start the DownloadService.
        startService(intent);
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
            //activity.dismissDialog();

            // Get message Id
            final int what = message.what;

            switch (what) {
                case DownloadingService.ServiceHandler.MSG_MAKE_REQUEST:

                    break;

                default:
                    Log.d(CLASS_NAME, "Unknown message id received:" + what);
            }
        }
    }
}

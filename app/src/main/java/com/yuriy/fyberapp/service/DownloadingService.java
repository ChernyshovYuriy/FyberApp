package com.yuriy.fyberapp.service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.yuriy.fyberapp.R;
import com.yuriy.fyberapp.api.APIServiceProvider;
import com.yuriy.fyberapp.api.APIServiceProviderImpl;
import com.yuriy.fyberapp.business.DataParser;
import com.yuriy.fyberapp.business.JSONDataParserImpl;
import com.yuriy.fyberapp.net.Downloader;
import com.yuriy.fyberapp.net.HTTPDownloaderImpl;
import com.yuriy.fyberapp.utils.AppUtils;
import com.yuriy.fyberapp.vo.FyberResponseCode;
import com.yuriy.fyberapp.vo.OfferVO;
import com.yuriy.fyberapp.vo.OffersVO;

import java.util.List;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/26/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */
public class DownloadingService extends IntentService {

    /**
     * Tag for the logging messages.
     */
    private static final String CLASS_NAME = DownloadingService.class.getSimpleName();

    /**
     * Key for the {@link android.os.Bundle} store to hold a {@link android.os.Messenger}
     */
    protected static final String BUNDLE_KEY_MESSENGER = "MESSENGER";

    /**
     * Key for the {@link android.os.Bundle} store to indicates whether it is necessary
     * to use fake response
     */
    protected static final String BUNDLE_KEY_USE_FAKE_RESPONSE = "USE_FAKE_RESPONSE";

    /**
     * Key for the {@link android.os.Bundle} store to hold a
     * {@link com.yuriy.fyberapp.vo.OffersVO}
     */
    private static final String BUNDLE_KEY_OFFERS = "OFFERS";

    /**
     * Processes Messages sent to it from onStartCommnand() that
     * indicate which images to download from a remote server.
     */
    private volatile ServiceHandler mServiceHandler;

    /**
     * Looper associated with the HandlerThread.
     */
    private volatile Looper mServiceLooper;

    public DownloadingService() {
        super(DownloadingService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Create and start a background HandlerThread since by
        // default a Service runs in the UI Thread, which we don't
        // want to block.
        final HandlerThread thread = new HandlerThread("DownloadingServiceThread");
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler.
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // This method is call in NON UI Thread so there is no need to perform
        // further operation in separate thread.

        // Create a Message that will be sent to ServiceHandler to
        // retrieve a data based on the URI in the Intent.
        Message message = mServiceHandler.makeDownloadDataMessage(intent);

        // Send the Message to ServiceHandler to retrieve an image
        // based on contents of the Intent.
        mServiceHandler.sendMessage(message);
    }

    /**
     * Factory method to make the desired {@link android.content.Intent}.
     */
    public static Intent makeRequestIntent(final Context context,
                                           final Uri uri,
                                           final boolean useFakeResponse,
                                           final Handler downloadHandler) {
        // Create the Intent that's associated to the WeatherService class.
        final Intent intent = new Intent(context, DownloadingService.class);

        // Set the URI as data in the Intent.
        intent.setData(uri);

        // Create and pass a Messenger as an "extra" so the
        // WeatherService can send back the pathname.
        if (downloadHandler != null) {
            intent.putExtra(BUNDLE_KEY_MESSENGER, new Messenger(downloadHandler));
        }

        intent.putExtra(BUNDLE_KEY_USE_FAKE_RESPONSE, useFakeResponse);

        return intent;
    }

    /**
     * Helper method that returns {@link com.yuriy.fyberapp.vo.OffersVO}
     * if download succeeded.
     */
    public static OffersVO getOffers(final Message message) {
        // Extract the data from Message, which is in the form
        // of a Bundle that can be passed across processes.
        final Bundle data = message.getData();

        // Extract the Weather VO from the Bundle.
        final OffersVO currentWeatherVO = (OffersVO) data.get(BUNDLE_KEY_OFFERS);

        // Check to see if the download succeeded.
        if (message.arg1 != Activity.RESULT_OK || currentWeatherVO == null)
            return null;
        else
            return currentWeatherVO;
    }

    /**
     * Helper method to extract response message from the {@link com.yuriy.fyberapp.vo.OffersVO}.
     *
     * @param offersVO Instance of the {@link com.yuriy.fyberapp.vo.OffersVO}
     * @return Response Message.
     */
    public static String getResponseMessage(final OffersVO offersVO) {
        if (offersVO.getMessage() == null) {
            return "";
        }
        return offersVO.getMessage();
    }

    /**
     * Helper method to extract response code from the {@link com.yuriy.fyberapp.vo.OffersVO}.
     *
     * @param offersVO Instance of the {@link com.yuriy.fyberapp.vo.OffersVO}
     * @return Response Message.
     */
    public static String getResponseCode(final OffersVO offersVO) {
        if (offersVO.getCode() == null) {
            return "";
        }
        return offersVO.getCode();
    }

    /**
     * Helper method to validate response.
     *
     * @param offersVO Instance of the {@link com.yuriy.fyberapp.vo.OffersVO}
     * @return True of response return OK message, False - otherwise.
     */
    public static boolean isResponseCodeOK(final OffersVO offersVO) {
        return offersVO.getCode() != null
                && offersVO.getCode().trim().equals(FyberResponseCode.OK.toString());
    }

    /**
     * Helper method to extract collection of the Offers from the
     * {@link com.yuriy.fyberapp.vo.OffersVO}.
     *
     * @param offersVO Instance of the {@link com.yuriy.fyberapp.vo.OffersVO}
     * @return Collection of the Offers.
     */
    public static List<OfferVO> getOffers(final OffersVO offersVO) {
        return offersVO.getOffers();
    }

    /**
     * An inner class that inherits from {@link android.os.Handler} and uses its
     * {@link #handleMessage(android.os.Message)} hook method to process Messages sent to
     * it from {@link #onHandleIntent(android.content.Intent)} that indicate which
     * data to download.
     */
    public final class ServiceHandler extends Handler {

        /**
         * Make the request to the API passing the params and the authentication hash.
         */
        public static final int MSG_MAKE_REQUEST = 1;

        /**
         * Class constructor initializes the Looper.
         *
         * @param looper The Looper that we borrow from HandlerThread.
         */
        public ServiceHandler(final Looper looper) {
            super(looper);
        }

        /**
         * Hook method that retrieves an image from a remote server.
         */
        public void handleMessage(final Message message) {
            // Download the data and reply to the
            // MainActivity via the Messenger sent with the Intent.
            downloadWeatherDataAndReply((Intent) message.obj);
        }

        /**
         * Retrieves the designated weather data and reply to the
         * {@link com.yuriy.fyberapp.MainActivity} via the {@link android.os.Messenger}
         * sent with the {@link android.content.Intent}.
         */
        private void downloadWeatherDataAndReply(final Intent intent) {
            Log.i(CLASS_NAME, "Request URL:" + intent.getData().toString());

            // Extract the Messenger.
            final Messenger messenger = (Messenger) intent.getExtras().get(BUNDLE_KEY_MESSENGER);
            final boolean isUseFakeResponse
                    = intent.getExtras().getBoolean(BUNDLE_KEY_USE_FAKE_RESPONSE);

            // Download the requested data.
            final OffersVO offersVO = downloadOffers(intent.getData(), isUseFakeResponse);

            // Send the response via the Messenger.
            sendOffers(messenger, offersVO);
        }

        /**
         * Download the requested data and return the instance of the
         * {@link com.yuriy.fyberapp.vo.OffersVO} .
         *
         * @param uri URI of the weather data.
         * @return Instance of the {@link com.yuriy.fyberapp.vo.OffersVO}
         */
        public OffersVO downloadOffers(final Uri uri, final boolean isUseFakeResponse) {
            // Instantiate appropriate downloader (HTTP one)
            final Downloader downloader = new HTTPDownloaderImpl();

            // Instantiate appropriate parse (JSON one)
            final DataParser dataParser = new JSONDataParserImpl();

            // Instantiate appropriate API service provider
            final APIServiceProvider serviceProvider = new APIServiceProviderImpl(dataParser);

            // Get and return Offers
            if (!isUseFakeResponse) {
                return serviceProvider.getCurrentOffers(downloader, uri);
            }

            return ((APIServiceProviderImpl)serviceProvider).getFakeOffers(downloader, uri,
                    AppUtils.getStringResource(R.raw.response, getApplicationContext()));
        }

        /**
         * Send the offersVO back to the DownloadActivity via the Messenger.
         *
         * @param messenger {@link android.os.Messenger}
         * @param offersVO {@link com.yuriy.fyberapp.vo.OffersVO}
         */
        private void sendOffers(final Messenger messenger, final OffersVO offersVO) {
            // Call factory method to create Message.
            final Message message = makeReplyMessageWithWeatherData(offersVO);

            try {
                // Send offersVO to back to the DownloadActivity.
                message.what = MSG_MAKE_REQUEST;
                messenger.send(message);
            } catch (RemoteException e) {
                Log.e(CLASS_NAME, "Exception while sending:" + e.getMessage());
            }
        }

        /**
         * A factory method that creates a Message to return to the
         * {@link com.yuriy.fyberapp.MainActivity} with the
         * {@link com.yuriy.fyberapp.vo.OffersVO} of the downloaded data.
         *
         * @param offersVO Instance of the {@link com.yuriy.fyberapp.vo.OffersVO}
         */
        private Message makeReplyMessageWithWeatherData(final OffersVO offersVO) {
            final Message message = Message.obtain();

            // Return the result to indicate whether the download
            // succeeded or failed.
            message.arg1 = offersVO == null
                    ? Activity.RESULT_CANCELED
                    : Activity.RESULT_OK;

            final Bundle data = new Bundle();

            // Data of the downloaded weather.
            data.putSerializable(BUNDLE_KEY_OFFERS, offersVO);
            message.setData(data);
            return message;
        }

        /**
         * A factory method that creates a {@link android.os.Message} that contains
         * information on the weather data to download.
         */
        private Message makeDownloadDataMessage(final Intent intent) {

            final Message message = Message.obtain();
            // Include Intent in Message to indicate which URI to retrieve.
            message.obj = intent;
            message.what = MSG_MAKE_REQUEST;
            return message;
        }
    }
}

package com.yuriy.fyberapp.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Messenger;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/26/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */
public class DownloadingService extends IntentService {

    /**
     * Key for the {@link android.os.Bundle} store to hold a {@link android.os.Messenger}
     */
    protected static final String BUNDLE_KEY_MESSENGER = "MESSENGER";

    public DownloadingService() {
        super(DownloadingService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    /**
     * Factory method to make the desired {@link android.content.Intent}.
     */
    public static Intent makeRequestIntent(final Context context,
                                                       final Uri uri,
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
        return intent;
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
    }
}

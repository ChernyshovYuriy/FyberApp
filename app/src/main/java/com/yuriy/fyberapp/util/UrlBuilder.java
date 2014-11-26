package com.yuriy.fyberapp.util;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/26/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */

import android.content.Context;

/**
 * {@link com.yuriy.fyberapp.util.UrlBuilder} is a helper class to provide abilities to
 * build request URLs according to API specification.
 */
public class UrlBuilder {

    /**
     * AppId key.
     */
    private static final String KEY_APPID = "appid";

    /**
     * Device Id key.
     */
    private static final String KEY_DEVICE_ID = "device_id";

    /**
     *  IP key.
     */
    private static final String KEY_IP = "ip";

    /**
     * Locale key.
     */
    private static final String KEY_LOCALE = "locale";

    /**
     * Timestamp key.
     */
    private static final String KEY_PS_TIME = "ps_time";

    /**
     * Pub0 key.
     */
    private static final String KEY_PUB0 = "pub0";

    /**
     * Unix Timestamp key.
     */
    private static final String KEY_TIMESTAMP = "timestamp";

    /**
     * Offer types key.
     */
    private static final String KEY_OFFER_TYPES = "offer_types";

    /**
     * Google Ad Id key.
     */
    private static final String KEY_GAID = "google_ad_id";

    /**
     * Google Ad Id limited tracking enabled key.
     */
    private static final String KEY_GAID_LIM_TRACK_ENABLED
            = "google_ad_id_limited_tracking_enabled";

    /**
     * Hash Key key.
     */
    private static final String KEY_HASH_KEY = "hashkey";

    /**
     * Helper method to build Url from the provided parameters.
     *
     * @param pub0
     * @param appId
     * @param apiKey
     * @param uId
     * @return Uri for the API request.
     */
    public static String getUrlForRequest(final String uId, final String apiKey,
                                          final String appId, final String pub0) {


        return "";
    }
}

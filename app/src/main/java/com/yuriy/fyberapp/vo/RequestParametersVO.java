package com.yuriy.fyberapp.vo;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/27/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * {@link com.yuriy.fyberapp.vo.RequestParametersVO} is a Value Object which holds
 * parameters for the API request.
 */
public class RequestParametersVO {

    /**
     * The unique User ID key, as used internally in the application.
     */
    protected static final String KEY_UID = "uid";

    /**
     * AppId key.
     */
    protected static final String KEY_APP_ID = "appid";

    /**
     * Device Id key.
     */
    protected static final String KEY_DEVICE_ID = "device_id";

    /**
     *  IP key.
     */
    protected static final String KEY_IP = "ip";

    /**
     * Locale key.
     */
    protected static final String KEY_LOCALE = "locale";

    /**
     * Page key.
     */
    protected static final String KEY_PAGE = "page";

    /**
     * Timestamp key.
     */
    protected static final String KEY_PS_TIME = "ps_time";

    /**
     * Pub0 key.
     */
    protected static final String KEY_PUB0 = "pub0";

    /**
     * Unix Timestamp key.
     */
    protected static final String KEY_TIMESTAMP = "timestamp";

    /**
     * Offer types key.
     */
    protected static final String KEY_OFFER_TYPES = "offer_types";

    /**
     * Google Ad Id key.
     */
    protected static final String KEY_GAID = "google_ad_id";

    /**
     * Google Ad Id limited tracking enabled key.
     */
    protected static final String KEY_GAID_LIM_TRACK_ENABLED
            = "google_ad_id_limited_tracking_enabled";

    /**
     * Map which is keep pairs of the key - value, where 'key' is a name of the parameter
     * and 'value' is its value.
     */
    private final Map<String, String> parametersMap = new TreeMap<String, String>();

    /**
     * Private constructor.
     */
    private RequestParametersVO() { }

    /**
     * @return The Fyber Application ID for your application.
     *         An empty string is return if no value found. Never return null.
     */
    public String getAppId() {
        return getParameterByKey(KEY_APP_ID);
    }

    /**
     * Set the Fyber Application ID for your application.
     *
     * @param value ID of the Application.
     */
    public void setAppId(final String value) {
        setParameterForKey(KEY_APP_ID, value);
    }

    /**
     * @return The ID of the device the user is using.
     *         An empty string is return if no value found. Never return null.
     */
    public String getDeviceId() {
        return getParameterByKey(KEY_DEVICE_ID);
    }

    /**
     * Set the ID of the device the user is using.
     *
     * @param value ID of the device.
     */
    public void setDeviceId(final String value) {
        setParameterForKey(KEY_DEVICE_ID, value);
    }

    /**
     * @return The IP address of the device of your user.
     *         If the parameter is not given, the IP address of the request will be used.
     *         An empty string is return if no value found. Never return null.
     */
    public String getIp() {
        return getParameterByKey(KEY_IP);
    }

    /**
     * Set The IP address of the device of your user.
     * If the parameter is not given, the IP address of the request will be used.
     *
     * @param value IP address.
     */
    public void setIp(final String value) {
        setParameterForKey(KEY_IP, value);
    }

    /**
     * @return The locale used for the offer descriptions.
     *         An empty string is return if no value found. Never return null.
     */
    public String getLocale() {
        return getParameterByKey(KEY_LOCALE);
    }

    /**
     * Set the locale used for the offer descriptions.
     *
     * @param value Locale of the device.
     */
    public void setLocale(final String value) {
        setParameterForKey(KEY_LOCALE, value);
    }

    /**
     * @return The page of the response set that you are requesting.
     *         An empty string is return if no value found. Never return null.
     */
    public String getPage() {
        return getParameterByKey(KEY_PAGE);
    }

    /**
     * Set the page of the response set that you are requesting.
     *
     * @param value Page number of the response set that you are requesting.
     */
    public void setPage(final String value) {
        setParameterForKey(KEY_PAGE, value);
    }

    /**
     * @return The creation date of the user’s account in your game in Unix Timestamp format.
     *         An empty string is return if no value found. Never return null.
     */
    public String getPsTime() {
        return getParameterByKey(KEY_PS_TIME);
    }

    /**
     * Set the creation date of the user’s account in your game in Unix Timestamp format.
     *
     * @param value Creation date of the user's account.
     */
    public void setPsTime(final String value) {
        setParameterForKey(KEY_PS_TIME, value);
    }

    /**
     * @return Custom parameter.
     *         An empty string is return if no value found. Never return null.
     */
    public String getPub0() {
        return getParameterByKey(KEY_PUB0);
    }

    /**
     * Set custom parameter.
     *
     * @param value Custom parameter.
     */
    public void setPub0(final String value) {
        setParameterForKey(KEY_PUB0, value);
    }

    /**
     * @return The time the request is being sent by the device.
     *         An empty string is return if no value found. Never return null.
     */
    public String getTimestamp() {
        return getParameterByKey(KEY_TIMESTAMP);
    }

    /**
     * Set The time the request is being sent by the device..
     *
     * @param value Timestamp of the request.
     */
    public void setTimestamp(final String value) {
        setParameterForKey(KEY_TIMESTAMP, value);
    }

    /**
     * @return The unique User ID, as used internally in your application.
     *         An empty string is return if no value found. Never return null.
     */
    public String getUserId() {
        return getParameterByKey(KEY_UID);
    }

    /**
     * Set the unique User ID, as used internally in your application.
     *
     * @param value User ID.
     */
    public void setUserId(final String value) {
        setParameterForKey(KEY_UID, value);
    }

    /**
     * @return The set of the keys stored in the map.
     */
    public Set<String> getKeySet() {
        return parametersMap.keySet();
    }

    /**
     * Return parameter from the map by the provided key.
     * @param key Key of the parameter.
     * @return Value, associated with the key, or empty string if key is not found.
     */
    public String getParameterByKey(final String key) {
        final String value = parametersMap.get(key);
        if (value == null) {
            return "";
        }
        return value;
    }

    /**
     * Factory method to create instance of the {@link com.yuriy.fyberapp.vo.RequestParametersVO}.
     * @return Instance of the {@link com.yuriy.fyberapp.vo.RequestParametersVO}
     */
    public static RequestParametersVO createInstance() {
        return new RequestParametersVO();
    }

    /**
     * Set provided value at the specified key in the map.
     *
     * @param key   Key of the parameter
     * @param value Value of the parameter.
     */
    private void setParameterForKey(final String key, final String value) {
        parametersMap.put(key, value);
    }
}

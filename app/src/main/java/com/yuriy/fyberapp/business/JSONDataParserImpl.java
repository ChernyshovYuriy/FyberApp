package com.yuriy.fyberapp.business;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/22/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * {@link com.yuriy.fyberapp.business.JSONDataParserImpl} is implementation of the
 * {@link com.yuriy.fyberapp.business.DataParser} interface and allows to parse raw
 * data of the weather which are represents by the JSON data format.
 */
public class JSONDataParserImpl implements DataParser {

    /**
     * JSON keys
     */

    /**
     * Tag to use in the logging.
     */
    private static final String CLASS_NAME = JSONDataParserImpl.class.getSimpleName();



    /**
     * This method converts raw data which comes from the server into the JSON object.
     *
     * @param rawData Raw data from the weather server.
     * @return Instance of the {@link org.json.JSONObject}.
     */
    protected JSONObject getJSONFromRawData(final String rawData) {
        if (rawData == null) {
            Log.w(CLASS_NAME, "Can not convert raw data to JSON, raw data is null");
            return new JSONObject();
        }
        if (rawData.isEmpty()) {
            Log.w(CLASS_NAME, "Can not convert raw data to JSON, raw data is empty");
            return new JSONObject();
        }
        try {
            return new JSONObject(rawData);
        } catch (JSONException e) {
            Log.e(CLASS_NAME, "Can not convert raw data to JSON:" + e.getMessage());
            return new JSONObject();
        }
    }

    /**
     * This method allows extract JSON Object from the main JSON Object.
     *
     * @param inputData Raw input data.
     * @param key       Key which is used to find a JSON Object.
     * @return Found JSON Object or empty one.
     */
    private JSONObject getJSONObjectFromMainJSONObject(final String inputData, final String key) {
        final JSONObject mainJSON = getJSONFromRawData(inputData);
        JSONObject returnJSON = new JSONObject();
        if (key == null) {
            return returnJSON;
        }
        if (mainJSON.length() == 0) {
            return returnJSON;
        }
        if (!mainJSON.has(key)) {
            return returnJSON;
        }
        try {
            returnJSON = mainJSON.getJSONObject(key);
        } catch (JSONException e) {
            Log.e(CLASS_NAME, "Parse " + key + " from main JSON exception:" + e.getMessage());
        }
        return returnJSON;
    }
}

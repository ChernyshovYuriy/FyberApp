package com.yuriy.fyberapp.business;

import android.util.Log;

import com.yuriy.fyberapp.vo.OfferVO;
import com.yuriy.fyberapp.vo.OffersVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/22/14
 * E-Mail: chernyshov.yuriy@gmail.com
 *
 * {@link JSONDataParserImpl} is implementation of the
 * {@link DataParser} interface and allows to parse raw
 * data of the weather which are represents by the JSON data format.
 */
public class JSONDataParserImpl implements DataParser {

    /**
     * Tag to use in the logging.
     */
    private static final String CLASS_NAME = JSONDataParserImpl.class.getSimpleName();

    @Override
    public String getCode(final String inputData) {
        final JSONObject mainJSON = getJSONFromRawData(inputData);
        if (mainJSON.length() == 0) {
            // TODO : Implement return some default value of the response code
            return "";
        }
        try {
            return mainJSON.getString(OffersVO.KEY_CODE);
        } catch (JSONException e) {
            Log.e(CLASS_NAME, "Parse Response Code exception:" + e.getMessage());
            // TODO : Implement return some default value of the response code
            return "";
        }
    }

    @Override
    public String getMessage(final String inputData) {
        final JSONObject mainJSON = getJSONFromRawData(inputData);
        if (mainJSON.length() == 0) {
            // TODO : Implement return some default value of the response message
            return "";
        }
        try {
            return mainJSON.getString(OffersVO.KEY_MESSAGE);
        } catch (JSONException e) {
            Log.e(CLASS_NAME, "Parse Response Message exception:" + e.getMessage());
            // TODO : Implement return some default value of the response message
            return "";
        }
    }

    @Override
    public List<OfferVO> getOffers(final String inputData) {
        final List<OfferVO> offersItems = new ArrayList<>();
        final JSONObject jsonObject = getJSONFromRawData(inputData);
        if (!jsonObject.has(OffersVO.KEY_OFFERS)) {
            return offersItems;
        }
        final JSONArray offersJSON = getJSONArray(jsonObject, OffersVO.KEY_OFFERS);
        JSONObject item;
        String title;
        String teaser;
        String thumbnailHires;
        double payout;
        OfferVO offerItem;
        try {
            for (int i = 0; i < offersJSON.length(); i++) {
                item = offersJSON.getJSONObject(i);
                if (item == null) {
                    continue;
                }
                //TODO: Initialize by some of the default values
                title = "";
                teaser = "";
                thumbnailHires = "";
                payout = 0.0;

                // Extract Data
                if (item.has(OfferVO.KEY_TITLE)) {
                    title = item.getString(OfferVO.KEY_TITLE);
                }
                if (item.has(OfferVO.KEY_TEASER)) {
                    teaser = item.getString(OfferVO.KEY_TEASER);
                }
                if (item.has(OfferVO.KEY_PAYOUT)) {
                    payout = item.getDouble(OfferVO.KEY_PAYOUT);
                }
                if (item.has(OfferVO.KEY_THUMBNAIL) ) {
                    JSONObject thumbs = item.getJSONObject(OfferVO.KEY_THUMBNAIL);
                    if (thumbs.has(OfferVO.KEY_HIRES)) {
                        thumbnailHires = thumbs.getString(OfferVO.KEY_HIRES);
                    }
                }

                // Create Offers Value Object
                offerItem = OfferVO.createInstance();
                offerItem.setTitle(title);
                offerItem.setTeaser(teaser);
                offerItem.setThumbnailHires(thumbnailHires);
                offerItem.setPayout(payout);
                offersItems.add(offerItem);
            }
        } catch (JSONException e) {
            Log.e(CLASS_NAME, "Parse offers exception:" + e.getMessage());
        }
        return offersItems;
    }

    /**
     * This method converts raw data which comes from the server into the JSON object.
     *
     * @param rawData Raw data from the server.
     * @return Instance of the {@link JSONObject}.
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
     * This method allows to extract JSONArray from the JSONObject by the provided key.
     *
     * @param jsonObject Source JSONObject.
     * @param key        Key for the JSONArray.
     * @return Extracted JSONArray
     */
    private JSONArray getJSONArray(final JSONObject jsonObject, final String key) {
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = jsonObject.getJSONArray(key);
        } catch (JSONException e) {
            Log.e(CLASS_NAME, "Parse JSONArray for key '" + key + "' throw exception:"
                    + e.getMessage());
        }
        return jsonArray;
    }
}

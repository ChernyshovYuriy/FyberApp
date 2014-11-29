package com.yuriy.fyberapp.api;

import android.net.Uri;
import android.util.Log;

import com.yuriy.fyberapp.business.DataParser;
import com.yuriy.fyberapp.net.Downloader;
import com.yuriy.fyberapp.vo.OfferVO;
import com.yuriy.fyberapp.vo.OffersVO;
import com.yuriy.fyberapp.vo.ResponseVO;

import java.util.List;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/28/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */
public class APIServiceProviderImpl implements APIServiceProvider {

    /**
     * Tag string to use in logging messages.
     */
    private static final String CLASS_NAME = APIServiceProviderImpl.class.getSimpleName();

    /**
     * Implementation of the {@link com.yuriy.fyberapp.business.DataParser} allows to
     * parse raw response of the data into different formats.
     */
    private DataParser mDataParser;

    /**
     * Constructor.
     *
     * @param dataParser Implementation of the {@link com.yuriy.fyberapp.business.DataParser}.
     */
    public APIServiceProviderImpl(final DataParser dataParser) {
        if (dataParser == null) {
            Log.w(CLASS_NAME, "Constructor -> data parser is null");
        }
        mDataParser = dataParser;
    }

    @Override
    public OffersVO getCurrentOffers(final Downloader downloader, final Uri uri) {
        // Initialize return object.
        final OffersVO offersVO = OffersVO.createInstance();

        // Download response from the server
        final ResponseVO responseVO = getResponseVO(downloader, uri);

        // Get response data
        final byte[] responseBytes = responseVO.getData();

        // Ignore null response
        if (responseBytes == null) {
            Log.w(CLASS_NAME, "Can not parse offers data, response byes are null");
            return offersVO;
        }

        // Get Response String
        final String response = new String(responseBytes);

        Log.i(CLASS_NAME, "Offers Response:\n" + response);

        // Ignore empty response
        if (response.isEmpty()) {
            Log.w(CLASS_NAME, "Can not parse offers data, response is empty");
            
            if (responseVO.getResponseCode() != 0) {
                offersVO.setCode(String.valueOf(responseVO.getResponseCode()));
            }

            return offersVO;
        }

        if (mDataParser == null) {
            Log.w(CLASS_NAME, "Can not parse offers data, parser is null");
            return offersVO;
        }

        // Extract response code
        offersVO.setCode(mDataParser.getCode(response));

        // Extract response message
        offersVO.setMessage(mDataParser.getMessage(response));

        // Extract Offers
        final List<OfferVO> collection = mDataParser.getOffers(response);
        for (OfferVO offer : collection) {
            offersVO.addOfferVO(offer);
        }

        return offersVO;
    }

    /**
     * Get response from the server.
     *
     * @param downloader Implementation of the {@link com.yuriy.fyberapp.net.Downloader}
     * @param uri        Provided Uri
     * @return Downloaded data as bytes array.
     */
    private ResponseVO getResponseVO(final Downloader downloader, final Uri uri) {
        if (downloader == null) {
            Log.w(CLASS_NAME, "getResponseVO -> downloader is null");
            return null;
        }
        if (uri == null) {
            Log.w(CLASS_NAME, "getResponseVO -> uri is null");
            return null;
        }

        // Download response from the server
        return downloader.downloadDataFromUri(uri);
    }
}

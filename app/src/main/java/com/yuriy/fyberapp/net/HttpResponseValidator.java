package com.yuriy.fyberapp.net;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/29/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * {@link com.yuriy.fyberapp.net.HttpResponseValidator} is a concrete implementation of the
 * {@link com.yuriy.fyberapp.net.ResponseValidator} interface designed to validate HTTP Responses.
 */
public class HttpResponseValidator implements ResponseValidator {

    private static final String HEADER_NAME = "X-Sponsorpay-Response-Signature";

    /**
     * API Key.
     */
    private String mApiKey;

    /**
     * Http Headers of the response.
     */
    private Header[] mHttpHeaders;

    @Override
    public boolean isValid(final byte[] response) {

        // Stop validation if input data are null
        if (response == null) {
            return false;
        }
        if (mHttpHeaders == null) {
            return false;
        }
        if (mHttpHeaders.length == 0) {
            return false;
        }

        // Extract necessary header
        String headerValue = "";
        for (Header header : mHttpHeaders) {
            if (header.getName().equals(HEADER_NAME)) {
                headerValue = header.getValue();
                break;
            }
        }

        // Get response as string
        final String responseString = new String(response);

        // Create result which is going to be compared with header
        String result;
        try {
            result = UrlBuilder.SHA1(responseString + mApiKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }

        // Comparing
        return result.equals(headerValue);
    }

    @Override
    public void setApiKey(final String apiKey) {
        mApiKey = apiKey;
    }

    /**
     * Set Http headers of the response
     * @param value Http Headers.
     */
    public void setHttpHeaders(final Header[] value) {
        mHttpHeaders = value;
    }

    /**
     * Factory method to instantiate {@link com.yuriy.fyberapp.net.HttpResponseValidator}
     *
     * @return Instance of the {@link com.yuriy.fyberapp.vo.OffersVO}
     */
    public static HttpResponseValidator createInstance() {
        return new HttpResponseValidator();
    }
}

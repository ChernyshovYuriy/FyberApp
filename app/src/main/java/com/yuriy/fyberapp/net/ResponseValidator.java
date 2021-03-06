package com.yuriy.fyberapp.net;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/29/14
 * E-Mail: chernyshov.yuriy@gmail.com
 *
 *  {@link ResponseValidator} is an interface which allows to implement
 * different validate implementations of the response from the server.
 */
public interface ResponseValidator {

    /**
     * Validate response from the server.
     *
     * @param response Response bytes array.
     * @return True is the response is valid, False - otherwise.
     */
    boolean isValid(final byte[] response);

    /**
     * Set API Key.
     *
     * @param apiKey API Key.
     */
    void setApiKey(final String apiKey);
}

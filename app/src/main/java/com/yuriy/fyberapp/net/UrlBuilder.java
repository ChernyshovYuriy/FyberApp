package com.yuriy.fyberapp.net;

import com.yuriy.fyberapp.vo.RequestParametersVO;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/26/14
 * E-Mail: chernyshov.yuriy@gmail.com
 *
 * {@link UrlBuilder} is a helper class to provide abilities to
 * build request URLs according to API specification.
 */
public class UrlBuilder {

    /**
     * API Key
     */
    public static final String API_KEY = "1c915e3b5d42d05136185030892fbb846c278927";

    /**
     * Hash Key key.
     */
    private static final String KEY_HASH_KEY = "hashkey";

    /**
     * Equal sign to separate a key from it's value.
     */
    private static final String EQUAL_SIGN = "=";

    /**
     * Ampersand key to separate pair of the key-value.
     */
    private static final String AMPERSAND_SIGN = "&";

    /**
     * Base URL for the API requests
     */
    protected static final String BASE_URL = "http://api.sponsorpay.com/feed/v1/offers.json?";

    /**
     * Helper method to build Url from the provided parameters.
     *
     * @param apiKey              API Key.
     * @param requestParametersVO Value Object that stores all necessary requests parameters.
     * @return Url for the API request.
     */
    public static String getUrlForRequest(final RequestParametersVO requestParametersVO,
                                          final String apiKey) {

        // Build URL string
        final StringBuilder builder = new StringBuilder();
        for(String key: requestParametersVO.getKeySet()) {
            if (requestParametersVO.getParameterByKey(key).isEmpty()) {
                continue;
            }
            builder.append(key).append(EQUAL_SIGN)
                    .append(requestParametersVO.getParameterByKey(key)).append(AMPERSAND_SIGN);
        }

        // Append API key
        builder.append(apiKey);

        // Calculate SHA1
        String sha1 = "";
        try {
            sha1 = SHA1(builder.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Extract API key
        builder.replace(builder.length() - apiKey.length() - 1, builder.length(), "");

        // Append SHA1
        builder.append(AMPERSAND_SIGN);
        builder.append(KEY_HASH_KEY).append(EQUAL_SIGN).append(sha1);

        // Append base url fro the request
        builder.insert(0, BASE_URL);

        return builder.toString();
    }

    /**
     * Calculate SHA1 from the provided string.
     *
     * @param text String to encrypt to.
     * @return Encrypted value.
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String SHA1(final String text) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(text.getBytes("iso-8859-1"), 0, text.length());
        byte[] sha1hash = messageDigest.digest();
        return convertToHex(sha1hash);
    }

    /**
     * Convert encrypted bytes array into string.
     *
     * @param data Encrypted bytes array.
     * @return String value of the bytes array.
     */
    private static String convertToHex(final byte[] data) {
        final StringBuilder stringBuffer = new StringBuilder();
        for (byte aByte : data) {
            int halfByte = (aByte >>> 4) & 0x0F;
            int twoHalves = 0;
            do {
                if ((0 <= halfByte) && (halfByte <= 9)) {
                    stringBuffer.append((char) ('0' + halfByte));
                } else {
                    stringBuffer.append((char) ('a' + (halfByte - 10)));
                }
                halfByte = aByte & 0x0F;
            } while (twoHalves++ < 1);
        }
        return stringBuffer.toString();
    }
}

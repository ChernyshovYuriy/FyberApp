package com.yuriy.fyberapp.util;

import com.yuriy.fyberapp.vo.RequestParametersVO;

import junit.framework.TestCase;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/27/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */
public class UrlBuilderTest extends TestCase {

    private static final String USER_ID = "player1";
    private static final String API_KEY = "e95a21621a1865bcbae3bee89c4d4f84";
    private static final String APP_ID = "157";
    private static final String PUB0 = "campaign2";
    private static final String IP = "212.45.111.17";
    private static final String PAGE = "2";
    private static final String PS_TIME = "1312211903";
    private static final String TIMESTAMP = "1312553361";

    private final static String SHA1_INPUT
            = "appid=" + APP_ID + "&device_id=" + DeviceInfoHelper.DEVICE_ID +
            "&ip=" + IP + "&locale=" + DeviceInfoHelper.DEVICE_LOCALE + "&page=" + PAGE +
            "&ps_time=" + PS_TIME + "&pub0=" + PUB0 + "&timestamp=" + TIMESTAMP +
            "&uid=" + USER_ID;

    private final static String SHA1_RESULT = "7a2b1604c03d46eec1ecd4a686787b75dd693c4d";

    public void testSHA1() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        final String result = UrlBuilder.SHA1(SHA1_INPUT + "&" + API_KEY);

        assertThat(result, is(SHA1_RESULT));
    }

    public void testBuildUrl() {
        final RequestParametersVO requestParametersVO = RequestParametersVO.createInstance();
        requestParametersVO.setAppId(APP_ID);
        requestParametersVO.setPub0(PUB0);
        requestParametersVO.setUserId(USER_ID);
        requestParametersVO.setTimestamp(TIMESTAMP);
        requestParametersVO.setPsTime(PS_TIME);
        requestParametersVO.setPage(PAGE);
        requestParametersVO.setIp(IP);
        requestParametersVO.setLocale(DeviceInfoHelper.DEVICE_LOCALE);
        requestParametersVO.setDeviceId(DeviceInfoHelper.DEVICE_ID);

        final String result = UrlBuilder.getUrlForRequest(requestParametersVO, API_KEY);

        assertThat(result, is(UrlBuilder.BASE_URL + SHA1_INPUT + "&hashkey=" + SHA1_RESULT));
    }
}

package com.yuriy.fyberapp.vo;

import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/27/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */
public class RequestParametersVOTest extends TestCase {

    private RequestParametersVO mParametersVO;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mParametersVO = RequestParametersVO.createInstance();
    }

    public void testFactoryMethodShouldCreateInstance() {
        assertThat("Instance should be created", mParametersVO, notNullValue());
    }

    /**
     * User ID section
     */

    public void testUIDShouldBeSetCorrectly() {
        final String uid = "UID_Value";
        mParametersVO.setUserId(uid);

        assertThat(mParametersVO.getUserId(), is(uid));
        assertThat((String) mParametersVO.getParameterByKey(RequestParametersVO.KEY_UID), is(uid));
    }

    public void testUIDNullShouldReturnNull() {
        final String uid = null;
        mParametersVO.setUserId(uid);

        assertThat(mParametersVO.getUserId(), nullValue());
    }

    public void testDefaultUIDShouldReturnNull() {
        assertThat(mParametersVO.getUserId(), nullValue());
    }

    /**
     * Application ID section
     */

    public void testAppIDShouldBeSetCorrectly() {
        final String appid = "AppID_Value";
        mParametersVO.setAppId(appid);

        assertThat(mParametersVO.getAppId(), is(appid));
        assertThat((String) mParametersVO.getParameterByKey(RequestParametersVO.KEY_APP_ID),
                is(appid));
    }

    public void testAppIDNullShouldReturnNull() {
        final String appId = null;
        mParametersVO.setAppId(appId);

        assertThat(mParametersVO.getAppId(), nullValue());
    }

    public void testDefaultAppIDShouldReturnNull() {
        assertThat(mParametersVO.getAppId(), nullValue());
    }

    /**
     * Pub0 section
     */

    public void testPub0ShouldBeSetCorrectly() {
        final String pub0 = "Pub0_Value";
        mParametersVO.setPub0(pub0);

        assertThat(mParametersVO.getPub0(), is(pub0));
        assertThat((String) mParametersVO.getParameterByKey(RequestParametersVO.KEY_PUB0),
                is(pub0));
    }

    public void testPub0NullShouldReturnNull() {
        final String pub0 = null;
        mParametersVO.setPub0(pub0);

        assertThat(mParametersVO.getPub0(), nullValue());
    }

    public void testDefaultPub0ShouldReturnNull() {
        assertThat(mParametersVO.getPub0(), nullValue());
    }

    /**
     * IP section
     */

    public void testIPShouldBeSetCorrectly() {
        final String ip = "255.255.255.255";
        mParametersVO.setIp(ip);

        assertThat(mParametersVO.getIp(), is(ip));
        assertThat((String) mParametersVO.getParameterByKey(RequestParametersVO.KEY_IP), is(ip));
    }

    public void testIPNullShouldReturnNull() {
        final String ip = null;
        mParametersVO.setIp(ip);

        assertThat(mParametersVO.getIp(), nullValue());
    }

    public void testDefaultIPShouldReturnNull() {
        assertThat(mParametersVO.getIp(), nullValue());
    }

    /**
     * Page section
     */

    public void testPageShouldBeSetCorrectly() {
        final String page = "255";
        mParametersVO.setPage(page);

        assertThat(mParametersVO.getPage(), is(page));
        assertThat((String) mParametersVO.getParameterByKey(RequestParametersVO.KEY_PAGE),
                is(page));
    }

    public void testPageNullShouldReturnNull() {
        final String page = null;
        mParametersVO.setPage(page);

        assertThat(mParametersVO.getPage(), nullValue());
    }

    public void testDefaultPageShouldReturnNull() {
        assertThat(mParametersVO.getPage(), nullValue());
    }

    /**
     * Device ID section
     */

    public void testDeviceIdShouldBeSetCorrectly() {
        final String deviceId = "6598rgr86rfbhmkl5";
        mParametersVO.setDeviceId(deviceId);

        assertThat(mParametersVO.getDeviceId(), is(deviceId));
        assertThat((String) mParametersVO.getParameterByKey(RequestParametersVO.KEY_DEVICE_ID),
                is(deviceId));
    }

    public void testDeviceIdNullShouldReturnNull() {
        final String deviceId = null;
        mParametersVO.setDeviceId(deviceId);

        assertThat(mParametersVO.getDeviceId(), nullValue());
    }

    public void testDefaultDeviceIdShouldReturnNull() {
        assertThat(mParametersVO.getDeviceId(), nullValue());
    }

    /**
     * Locale section
     */

    public void testLocaleShouldBeSetCorrectly() {
        final String locale = "de";
        mParametersVO.setLocale(locale);

        assertThat(mParametersVO.getLocale(), is(locale));
        assertThat((String) mParametersVO.getParameterByKey(RequestParametersVO.KEY_LOCALE),
                is(locale));
    }

    public void testLocaleNullShouldReturnNull() {
        final String locale = null;
        mParametersVO.setLocale(locale);

        assertThat(mParametersVO.getLocale(), nullValue());
    }

    public void testDefaultLocaleShouldReturnNull() {
        assertThat(mParametersVO.getLocale(), nullValue());
    }

    /**
     * PsTime section
     */

    public void testPsTimeShouldBeSetCorrectly() {
        final String psTime = "159786354";
        mParametersVO.setPsTime(psTime);

        assertThat(mParametersVO.getPsTime(), is(psTime));
        assertThat((String) mParametersVO.getParameterByKey(RequestParametersVO.KEY_PS_TIME),
                is(psTime));
    }

    public void testPsTimeNullShouldReturnNull() {
        final String psTime = null;
        mParametersVO.setPsTime(psTime);

        assertThat(mParametersVO.getPsTime(), nullValue());
    }

    public void testDefaultPsTimeShouldReturnNull() {
        assertThat(mParametersVO.getPsTime(), nullValue());
    }

    /**
     * Timestamp section
     */

    public void testTimestampShouldBeSetCorrectly() {
        final String timestamp = "159786354";
        mParametersVO.setTimestamp(timestamp);

        assertThat(mParametersVO.getTimestamp(), is(timestamp));
        assertThat((String) mParametersVO.getParameterByKey(RequestParametersVO.KEY_TIMESTAMP),
                is(timestamp));
    }

    public void testTimestampNullShouldReturnNull() {
        final String timestamp = null;
        mParametersVO.setTimestamp(timestamp);

        assertThat(mParametersVO.getTimestamp(), nullValue());
    }

    public void testDefaultTimestampShouldReturnNull() {
        assertThat(mParametersVO.getTimestamp(), nullValue());
    }

    public void testAllValuesAreNullByDefault() {
        for(String key: mParametersVO.getKeySet()) {
            assertThat(mParametersVO.getParameterByKey(key), nullValue());
        }
    }
}

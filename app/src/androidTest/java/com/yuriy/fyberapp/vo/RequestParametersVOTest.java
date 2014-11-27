package com.yuriy.fyberapp.vo;

import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;
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
        assertThat(mParametersVO.getParameterByKey(RequestParametersVO.KEY_UID), is(uid));
    }

    public void testUIDNullShouldReturnEmptyString() {
        final String uid = null;
        mParametersVO.setUserId(uid);

        assertThat(mParametersVO.getUserId(), isEmptyString());
    }

    public void testDefaultUIDShouldReturnEmptyString() {
        assertThat(mParametersVO.getUserId(), isEmptyString());
    }

    /**
     * Application ID section
     */

    public void testAppIDShouldBeSetCorrectly() {
        final String appid = "AppID_Value";
        mParametersVO.setAppId(appid);

        assertThat(mParametersVO.getAppId(), is(appid));
        assertThat(mParametersVO.getParameterByKey(RequestParametersVO.KEY_APP_ID), is(appid));
    }

    public void testAppIDNullShouldReturnEmptyString() {
        final String appId = null;
        mParametersVO.setAppId(appId);

        assertThat(mParametersVO.getAppId(), isEmptyString());
    }

    public void testDefaultAppIDShouldReturnEmptyString() {
        assertThat(mParametersVO.getAppId(), isEmptyString());
    }

    /**
     * Pub0 section
     */

    public void testPub0ShouldBeSetCorrectly() {
        final String pub0 = "Pub0_Value";
        mParametersVO.setPub0(pub0);

        assertThat(mParametersVO.getPub0(), is(pub0));
        assertThat(mParametersVO.getParameterByKey(RequestParametersVO.KEY_PUB0), is(pub0));
    }

    public void testPub0NullShouldReturnEmptyString() {
        final String pub0 = null;
        mParametersVO.setPub0(pub0);

        assertThat(mParametersVO.getPub0(), isEmptyString());
    }

    public void testDefaultPub0ShouldReturnEmptyString() {
        assertThat(mParametersVO.getPub0(), isEmptyString());
    }

    /**
     * IP section
     */

    public void testIPShouldBeSetCorrectly() {
        final String ip = "255.255.255.255";
        mParametersVO.setIp(ip);

        assertThat(mParametersVO.getIp(), is(ip));
        assertThat(mParametersVO.getParameterByKey(RequestParametersVO.KEY_IP), is(ip));
    }

    public void testIPNullShouldReturnEmptyString() {
        final String ip = null;
        mParametersVO.setIp(ip);

        assertThat(mParametersVO.getIp(), isEmptyString());
    }

    public void testDefaultIPShouldReturnEmptyString() {
        assertThat(mParametersVO.getIp(), isEmptyString());
    }

    /**
     * Page section
     */

    public void testPageShouldBeSetCorrectly() {
        final String page = "255";
        mParametersVO.setPage(page);

        assertThat(mParametersVO.getPage(), is(page));
        assertThat(mParametersVO.getParameterByKey(RequestParametersVO.KEY_PAGE), is(page));
    }

    public void testPageNullShouldReturnEmptyString() {
        final String page = null;
        mParametersVO.setPage(page);

        assertThat(mParametersVO.getPage(), isEmptyString());
    }

    public void testDefaultPageShouldReturnEmptyString() {
        assertThat(mParametersVO.getPage(), isEmptyString());
    }

    /**
     * Device ID section
     */

    public void testDeviceIdShouldBeSetCorrectly() {
        final String deviceId = "6598rgr86rfbhmkl5";
        mParametersVO.setDeviceId(deviceId);

        assertThat(mParametersVO.getDeviceId(), is(deviceId));
        assertThat(mParametersVO.getParameterByKey(RequestParametersVO.KEY_DEVICE_ID), is(deviceId));
    }

    public void testDeviceIdNullShouldReturnEmptyString() {
        final String deviceId = null;
        mParametersVO.setDeviceId(deviceId);

        assertThat(mParametersVO.getDeviceId(), isEmptyString());
    }

    public void testDefaultDeviceIdShouldReturnEmptyString() {
        assertThat(mParametersVO.getDeviceId(), isEmptyString());
    }

    /**
     * Locale section
     */

    public void testLocaleShouldBeSetCorrectly() {
        final String locale = "de";
        mParametersVO.setLocale(locale);

        assertThat(mParametersVO.getLocale(), is(locale));
        assertThat(mParametersVO.getParameterByKey(RequestParametersVO.KEY_LOCALE), is(locale));
    }

    public void testLocaleNullShouldReturnEmptyString() {
        final String locale = null;
        mParametersVO.setLocale(locale);

        assertThat(mParametersVO.getLocale(), isEmptyString());
    }

    public void testDefaultLocaleShouldReturnEmptyString() {
        assertThat(mParametersVO.getLocale(), isEmptyString());
    }

    /**
     * PsTime section
     */

    public void testPsTimeShouldBeSetCorrectly() {
        final String psTime = "159786354";
        mParametersVO.setPsTime(psTime);

        assertThat(mParametersVO.getPsTime(), is(psTime));
        assertThat(mParametersVO.getParameterByKey(RequestParametersVO.KEY_PS_TIME), is(psTime));
    }

    public void testPsTimeNullShouldReturnEmptyString() {
        final String psTime = null;
        mParametersVO.setPsTime(psTime);

        assertThat(mParametersVO.getPsTime(), isEmptyString());
    }

    public void testDefaultPsTimeShouldReturnEmptyString() {
        assertThat(mParametersVO.getPsTime(), isEmptyString());
    }

    /**
     * Timestamp section
     */

    public void testTimestampShouldBeSetCorrectly() {
        final String timestamp = "159786354";
        mParametersVO.setTimestamp(timestamp);

        assertThat(mParametersVO.getTimestamp(), is(timestamp));
        assertThat(mParametersVO.getParameterByKey(RequestParametersVO.KEY_TIMESTAMP), is(timestamp));
    }

    public void testTimestampNullShouldReturnEmptyString() {
        final String timestamp = null;
        mParametersVO.setTimestamp(timestamp);

        assertThat(mParametersVO.getTimestamp(), isEmptyString());
    }

    public void testDefaultTimestampShouldReturnEmptyString() {
        assertThat(mParametersVO.getTimestamp(), isEmptyString());
    }

    public void testAllValuesAreEmptyByDefault() {
        for(String key: mParametersVO.getKeySet()) {
            assertThat(mParametersVO.getParameterByKey(key), isEmptyString());
        }
    }
}

package com.yuriy.fyberapp.business;

import com.yuriy.fyberapp.ApplicationTest;
import com.yuriy.fyberapp.vo.OfferVO;
import com.yuriy.fyberapp.vo.OffersVO;

import junit.framework.TestCase;

import org.json.JSONObject;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/29/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */
public class JSONDataParserImplTest extends TestCase {

    private DataParser mDataParser;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mDataParser = new JSONDataParserImpl();
    }

    public void testJSONFromRawDataShouldNotBeEmpty() {
        final JSONDataParserImpl parser = new JSONDataParserImpl();
        final JSONObject jsonObject = parser.getJSONFromRawData(ApplicationTest.RAW_RESPONSE);

        assertThat("JSON Object should not be null", jsonObject, notNullValue());
        assertTrue("JSON Object should not be empty", jsonObject.length() > 0);
        assertThat("JSON Object should contains key:" + OffersVO.KEY_CODE,
                jsonObject.has(OffersVO.KEY_CODE), is(true));
        assertThat("JSON Object should contains key:" + OffersVO.KEY_MESSAGE,
                jsonObject.has(OffersVO.KEY_MESSAGE), is(true));
        assertThat("JSON Object should contains key:" + OffersVO.KEY_OFFERS,
                jsonObject.has(OffersVO.KEY_OFFERS), is(true));
    }

    public void testJSONFromNullRawDataShouldBeEmpty() {
        final JSONDataParserImpl parser = new JSONDataParserImpl();
        final JSONObject jsonObject = parser.getJSONFromRawData(null);

        assertThat("JSON Object should not be null", jsonObject, notNullValue());
        assertTrue("JSON Object should be empty", jsonObject.length() == 0);
    }

    public void testJSONFromEmptyRawDataShouldBeEmpty() {
        final JSONDataParserImpl parser = new JSONDataParserImpl();
        final JSONObject jsonObject = parser.getJSONFromRawData("");

        assertThat("JSON Object should not be null", jsonObject, notNullValue());
        assertTrue("JSON Object should be empty", jsonObject.length() == 0);
    }

    public void testParseCodeSuccess() {
        final String code = mDataParser.getCode(ApplicationTest.RAW_RESPONSE);

        assertThat("Code should be same as original", code, is("OK"));
    }

    public void testParseMessageSuccess() {
        final String message = mDataParser.getMessage(ApplicationTest.RAW_RESPONSE);

        assertThat("Code should be same as original", message, is("OK"));
    }

    public void testParseOffersSuccess() {
        final List<OfferVO> offerVOs = mDataParser.getOffers(ApplicationTest.RAW_RESPONSE);

        assertThat("Instance should not be null", offerVOs, notNullValue());
        assertThat("Size should be same as original", offerVOs.size(), is(1));
        assertThat("Payout should be same as original", offerVOs.get(0).getPayout(),
                is((double)90));
        assertThat("Teaser should be same as original", offerVOs.get(0).getTeaser(),
                is("Download and START"));
        assertThat("Title should be same as original", offerVOs.get(0).getTitle(),
                is("Tap  Fish"));
        assertThat("Thumb Hires should be same as original", offerVOs.get(0).getThumbnailHires(),
                is("http://cdn.sponsorpay.com/assets/1808/icon175x175-2_square_175.png"));
    }

    public void testParseOffersFailButValuesNotNull() {
        List<OfferVO> offerVOs = mDataParser.getOffers(null);

        assertThat("Instance should not be null", offerVOs, notNullValue());
        assertThat("Size should be same as original", offerVOs.size(), is(0));

        offerVOs = mDataParser.getOffers("");

        assertThat("Instance should not be null", offerVOs, notNullValue());
        assertThat("Size should be same as original", offerVOs.size(), is(0));
    }
}

package com.yuriy.fyberapp.vo;

import junit.framework.TestCase;

import org.hamcrest.core.IsNull;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/28/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */
public class OffersVOTest extends TestCase {

    private OffersVO mOffersVO;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mOffersVO = OffersVO.createInstance();
    }

    public void testFactoryMethodShouldCreateInstance() {
        assertThat("Instance should be created", mOffersVO, notNullValue());
    }

    /**
     * Code section
     */

    public void testCodeShouldBeSetCorrectly() {
        final String code = "200";
        mOffersVO.setCode(code);

        assertThat(mOffersVO.getCode(), is(code));
        assertThat((String) mOffersVO.getParameterByKey(OffersVO.KEY_CODE), is(code));
    }

    public void testCodeNullShouldReturnNull() {
        final String code = null;
        mOffersVO.setCode(code);

        assertThat(mOffersVO.getCode(), nullValue());
    }

    public void testDefaultCodeShouldReturnNull() {
        assertThat(mOffersVO.getCode(), nullValue());
    }

    /**
     * Code section
     */

    public void testMessageShouldBeSetCorrectly() {
        final String message = "Message is here";
        mOffersVO.setMessage(message);

        assertThat(mOffersVO.getMessage(), is(message));
        assertThat((String) mOffersVO.getParameterByKey(OffersVO.KEY_MESSAGE), is(message));
    }

    public void testTitleNullShouldReturnNull() {
        final String message = null;
        mOffersVO.setMessage(message);

        assertThat(mOffersVO.getMessage(), nullValue());
    }

    public void testDefaultMessageShouldReturnNull() {
        assertThat(mOffersVO.getMessage(), nullValue());
    }

    /**
     * Offers
     */

    public void testDefaultOffersSizeIsZero() {
        assertThat(mOffersVO.getOffersSize(), is(0));
    }

    public void testAddOfferShouldIncreaseSizeAndReturnCorrectValue() {
        final OfferVO offerVO = OfferVO.createInstance();
        mOffersVO.addOfferVO(offerVO);

        assertThat(mOffersVO.getOffersSize(), is(1));
        assertThat(mOffersVO.getOfferVOAt(0), is(offerVO));

        mOffersVO.addOfferVO(null);

        assertThat(mOffersVO.getOffersSize(), is(2));
        assertThat(mOffersVO.getOfferVOAt(1), nullValue());
    }
}

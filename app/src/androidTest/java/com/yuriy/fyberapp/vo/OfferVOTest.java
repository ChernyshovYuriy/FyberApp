package com.yuriy.fyberapp.vo;

import junit.framework.TestCase;

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
public class OfferVOTest extends TestCase {

    private OfferVO mOfferVO;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mOfferVO = OfferVO.createInstance();
    }

    public void testFactoryMethodShouldCreateInstance() {
        assertThat("Instance should be created", mOfferVO, notNullValue());
    }

    /**
     * Title section
     */

    public void testTitleShouldBeSetCorrectly() {
        final String title = "Offer Title";
        mOfferVO.setTitle(title);

        assertThat(mOfferVO.getTitle(), is(title));
        assertThat((String) mOfferVO.getParameterByKey(OfferVO.KEY_TITLE), is(title));
    }

    public void testTitleNullShouldReturnNull() {
        final String uid = null;
        mOfferVO.setTitle(uid);

        assertThat(mOfferVO.getTitle(), nullValue());
    }

    public void testDefaultTitleShouldReturnNull() {
        assertThat(mOfferVO.getTitle(), nullValue());
    }

    /**
     * Teaser section
     */

    public void testTeaserShouldBeSetCorrectly() {
        final String teaser = "Offer Teaser";
        mOfferVO.setTeaser(teaser);

        assertThat(mOfferVO.getTeaser(), is(teaser));
        assertThat((String) mOfferVO.getParameterByKey(OfferVO.KEY_TEASER), is(teaser));
    }

    public void testTeaserNullShouldReturnNull() {
        final String teaser = null;
        mOfferVO.setTeaser(teaser);

        assertThat(mOfferVO.getTeaser(), nullValue());
    }

    public void testDefaultTeaserShouldReturnNull() {
        assertThat(mOfferVO.getTeaser(), nullValue());
    }

    /**
     * Hires Thumbnail section
     */

    public void testThumbnailHiresShouldBeSetCorrectly() {
        final String path = "path://to.the.image";
        mOfferVO.setThumbnailHires(path);

        assertThat(mOfferVO.getThumbnailHires(), is(path));
        assertThat((String) mOfferVO.getParameterByKey(OfferVO.KEY_HIRES), is(path));
    }

    public void testThumbnailHiresNullShouldReturnNull() {
        final String path = null;
        mOfferVO.setTitle(path);

        assertThat(mOfferVO.getThumbnailHires(), nullValue());
    }

    public void testDefaultThumbnailHiresShouldReturnNull() {
        assertThat(mOfferVO.getThumbnailHires(), nullValue());
    }

    /**
     * Payout section
     */

    public void testPayoutShouldBeSetCorrectly() {
        final double payout = 90.56d;
        mOfferVO.setPayout(payout);

        assertThat(mOfferVO.getPayout(), is(payout));
        assertThat((Double) mOfferVO.getParameterByKey(OfferVO.KEY_PAYOUT), is(payout));
    }

    public void testDefaultPayoutShouldReturnNull() {
        assertThat(mOfferVO.getPayout(), nullValue());
    }
}

package com.yuriy.fyberapp.vo;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/28/14
 * E-Mail: chernyshov.yuriy@gmail.com
 *
 * {@link OfferVO} is a Value Object that holds actual data about
 * concrete offer provided by the designed request.
 */
public class OfferVO extends BaseVO {

    /**
     * Key for the Title parameter.
     */
    public static final String KEY_TITLE = "title";

    /**
     * Key for the Teaser parameter.
     */
    public static final String KEY_TEASER = "teaser";

    /**
     * Key for the Hires Thumbnail parameter.
     */
    public static final String KEY_HIRES = "hires";

    /**
     * Key for the Thumbnail parameter.
     */
    public static final String KEY_THUMBNAIL = "thumbnail";

    /**
     * Key for the Payout parameter.
     */
    public static final String KEY_PAYOUT = "payout";

    /**
     * Private Constructor.
     */
    public OfferVO() { }

    /**
     * @return Title of the offer.
     */
    public String getTitle() {
        return getParameterByKey(KEY_TITLE);
    }

    /**
     * Set title of the offer.
     *
     * @param value Title of the offer.
     */
    public void setTitle(final String value) {
        setParameterForKey(KEY_TITLE, value);
    }

    /**
     * @return Teaser of the offer.
     */
    public String getTeaser() {
        return getParameterByKey(KEY_TEASER);
    }

    /**
     * Set the teaser of the offer.
     *
     * @param value Teaser of the offer.
     */
    public void setTeaser(final String value) {
        setParameterForKey(KEY_TEASER, value);
    }

    /**
     * @return Path to the hires thumbnail of the offer.
     */
    public String getThumbnailHires() {
        return getParameterByKey(KEY_HIRES);
    }

    /**
     * Set path to the hires thumbnail of the offer.
     * @param value Path to the hires thumbnail of the offer.
     */
    public void setThumbnailHires(final String value) {
        setParameterForKey(KEY_HIRES, value);
    }

    /**
     * @return Payout value of the offer.
     */
    public Double getPayout() {
        return getParameterByKey(KEY_PAYOUT);
    }

    /**
     * Set Payout value of the offer.
     *
     * @param value Payout value of the offer.
     */
    public void setPayout(final double value) {
        setParameterForKey(KEY_PAYOUT, value);
    }

    /**
     * Factory method to instantiate {@link OfferVO}
     *
     * @return Instance of the {@link OfferVO}
     */
    public static OfferVO createInstance() {
        return new OfferVO();
    }
}

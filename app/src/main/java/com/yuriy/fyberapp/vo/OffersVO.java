package com.yuriy.fyberapp.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/28/14
 * E-Mail: chernyshov.yuriy@gmail.com
 *
 * {@link OffersVO} is a Value Object that holds actual data about
 * offers provided by the designed request.
 */
public class OffersVO extends BaseVO {

    /**
     * Key for the Code parameter.
     */
    public static final String KEY_CODE = "code";

    /**
     * Key for the Message parameter.
     */
    public static final String KEY_MESSAGE = "message";

    /**
     * Key for the Offers parameter.
     */
    public static final String KEY_OFFERS = "offers";

    /**
     * Collection of the {@link OfferVO}'s
     */
    private final List<OfferVO> mOfferVOs = new ArrayList<OfferVO>();

    /**
     * Private Constructor.
     */
    private OffersVO() { }

    public String getCode() {
        return getParameterByKey(KEY_CODE);
    }

    public void setCode(final String value) {
        setParameterForKey(KEY_CODE, value);
    }

    public String getMessage() {
        return getParameterByKey(KEY_MESSAGE);
    }

    public void setMessage(final String value) {
        setParameterForKey(KEY_MESSAGE, value);
    }

    /**
     * Return {@link OfferVO} from the specified position, or an empty
     * Offer is position is out of bounds.
     *
     * @param position Position of the Offer in the collection.
     * @return {@link OfferVO}.
     */
    public OfferVO getOfferVOAt(final int position) {
        // In case of out of bounds position - return an empty Offer.
        if (position < 0 || position > mOfferVOs.size() - 1) {
            return OfferVO.createInstance();
        }
        return mOfferVOs.get(position);
    }

    /**
     * Set {@link OfferVO} into the collection.
     *
     * @param value Instance of the {@link OfferVO}.
     */
    public void addOfferVO(final OfferVO value) {
        mOfferVOs.add(value);
    }

    /**
     * @return Size of the Offers collection.
     */
    public int getOffersSize() {
        return mOfferVOs.size();
    }

    /**
     * @return Copy of the collection of the Offers.
     */
    public List<OfferVO> getOffers() {
        return new ArrayList<>(mOfferVOs);
    }

    /**
     * Factory method to instantiate {@link OffersVO}
     *
     * @return Instance of the {@link OffersVO}
     */
    public static OffersVO createInstance() {
        return new OffersVO();
    }
}

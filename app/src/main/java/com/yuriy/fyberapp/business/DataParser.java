package com.yuriy.fyberapp.business;

import com.yuriy.fyberapp.vo.OfferVO;

import java.util.List;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/22/14
 * E-Mail: chernyshov.yuriy@gmail.com
 *
 * {@link DataParser} interface provides common methods
 * for the data parsing. Different implementation can parse raw string data into JSON,
 * XML or any other format.
 */
public interface DataParser {

    /**
     * This method allows to extract response code from the response data.
     *
     * @param inputData Raw data which is received from the service.
     * @return Response Code.
     */
    String getCode(final String inputData);

    /**
     * This method allows to extract response message from the response data.
     *
     * @param inputData Raw data which is received from the service.
     * @return Response Message.
     */
    String getMessage(final String inputData);

    /**
     * This method allows to extract collection of the Offers from the response.
     *
     * @param inputData Raw data which is received from the service.
     * @return Collection of the Offers, or an empty collection.
     */
    List<OfferVO> getOffers(final String inputData);
}

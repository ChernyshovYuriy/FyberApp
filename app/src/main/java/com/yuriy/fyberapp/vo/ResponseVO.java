package com.yuriy.fyberapp.vo;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/28/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */

/**
 * {@link com.yuriy.fyberapp.vo.ResponseVO} is a Value Object that holds actual data of the
 * response, e.g. response code, response data.
 */
public class ResponseVO {

    /**
     * Response code for the network request.
     */
    private int mResponseCode;

    /**
     * Data of the successful response.
     */
    private byte[] mData;

    /**
     * Private Constructor.
     */
    private ResponseVO() { }

    /**
     * @return Response code for the network request.
     */
    public int getResponseCode() {
        return mResponseCode;
    }

    /**
     * Set Response code for the network request.
     * @param value Response code for the network request.
     */
    public void setResponseCode(final int value) {
        mResponseCode = value;
    }

    /**
     * @return Response data.
     */
    public byte[] getData() {
        return mData;
    }

    /**
     * Set Response data.
     *
     * @param value Response data.
     */
    public void setData(final byte[] value) {
        mData = value;
    }

    /**
     * Factory method to instantiate {@link com.yuriy.fyberapp.vo.ResponseVO}
     *
     * @return Instance of the {@link com.yuriy.fyberapp.vo.ResponseVO}
     */
    public static ResponseVO createInstance() {
        return new ResponseVO();
    }
}

package com.yuriy.fyberapp.net;

import android.content.Context;
import android.net.Uri;

import com.yuriy.fyberapp.R;
import com.yuriy.fyberapp.utils.AppUtils;
import com.yuriy.fyberapp.vo.ResponseVO;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/29/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */

/**
 * {@link com.yuriy.fyberapp.net.FakeDownloader} is a helper class which provides ability to
 * simulate real data response.
 */
public class FakeDownloader implements Downloader {

    /**
     * application context.
     */
    private Context mContext;

    /**
     * Private constructor.
     */
    private FakeDownloader() { }

    /**
     * Set application context.
     *
     * @param value Application context.
     */
    public void setContext(Context value) {
        mContext = value;
    }

    @Override
    public ResponseVO downloadDataFromUri(final Uri uri) {
        final ResponseVO responseVO = ResponseVO.createInstance();
        responseVO.setResponseCode(200);
        if (mContext != null) {
            responseVO.setData(AppUtils.getResource(R.raw.response, mContext));
        }
        return responseVO;
    }

    /**
     * Factory method to instantiate {@link com.yuriy.fyberapp.net.FakeDownloader}
     *
     * @return Instance of the {@link com.yuriy.fyberapp.net.FakeDownloader}
     */
    public static FakeDownloader createInstance() {
        return new FakeDownloader();
    }
}

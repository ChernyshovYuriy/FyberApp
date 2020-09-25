package com.yuriy.fyberapp.net;

import android.net.Uri;

import com.yuriy.fyberapp.vo.ResponseVO;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/21/14
 * E-Mail: chernyshov.yuriy@gmail.com
 * <p>
 * {@link Downloader} interface provides method which allows to perform
 * download operations. Different implementations will allows to perform downloading via different
 * protocols: HTTP, FTP, etc ...
 */
public interface Downloader {

    /**
     * Method to download data from provided {@link Uri}.
     *
     * @param uri Provided {@link Uri}.
     * @return {@link ResponseVO}.
     */
    ResponseVO downloadDataFromUri(final Uri uri);
}

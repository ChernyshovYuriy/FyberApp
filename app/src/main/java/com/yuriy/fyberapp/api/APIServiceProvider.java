package com.yuriy.fyberapp.api;

import android.net.Uri;

import com.yuriy.fyberapp.net.Downloader;
import com.yuriy.fyberapp.net.ResponseValidator;
import com.yuriy.fyberapp.vo.OffersVO;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/21/14
 * E-Mail: chernyshov.yuriy@gmail.com
 *
 * {@link APIServiceProvider} interface provides various methods
 * to operate with Fyber Service API.<br>
 * http://developer.fyber.com/content/android/offer-wall/offer-api/<br>
 * This interface can be extended as much as API allows.
 */
public interface APIServiceProvider {

    /**
     * Access current offer data for any location on Earth.
     *
     * @param downloader        Implementation of the {@link Downloader}.
     * @param uri               {@link android.net.Uri} of the request.
     * @param responseValidator Implementation of the {@link ResponseValidator}.
     * @return {@link OffersVO}.
     */
    OffersVO getCurrentOffers(final Downloader downloader, final Uri uri,
                              final ResponseValidator responseValidator);
}

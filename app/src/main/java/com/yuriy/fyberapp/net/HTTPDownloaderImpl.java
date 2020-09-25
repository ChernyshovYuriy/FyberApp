package com.yuriy.fyberapp.net;

import android.net.Uri;
import android.util.Log;

import com.yuriy.fyberapp.vo.ResponseVO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/21/14
 * E-Mail: chernyshov.yuriy@gmail.com
 *
 * Implementation of the {@link Downloader} interface.
 * {@link HTTPDownloaderImpl} allows to download offers data from the
 * wer resource over HTTP protocol.
 */
public class HTTPDownloaderImpl implements Downloader {

    /**
     * Tag to use in logging message.
     */
    private static final String CLASS_NAME = HTTPDownloaderImpl.class.getSimpleName();

    @Override
    public ResponseVO downloadDataFromUri(final Uri uri) {
        HttpGet request = null;
        final ResponseVO responseVO = ResponseVO.createInstance();
        responseVO.setData(new byte[0]);
        try {
            request = new HttpGet(uri.toString());
        } catch (IllegalArgumentException e) {
            Log.e(CLASS_NAME, "IllegalArgumentException error: " + e.getMessage());
        }

        if (request == null) {
            return responseVO;
        }

        final HttpClient httpClient = new DefaultHttpClient();
        try {
            final HttpResponse httpResponse = httpClient.execute(request);
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            Log.d(CLASS_NAME, "Response code: " + responseCode);

            responseVO.setResponseCode(responseCode);
            responseVO.setHeaders(httpResponse.getAllHeaders());

            if (responseCode == 200) {
                final HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    try {
                        responseVO.setData(EntityUtils.toByteArray(entity));
                        return responseVO;
                    } catch (IOException e) {
                        Log.e(CLASS_NAME, "EntityUtils error: " + e.getMessage());
                    }
                }
            }
        } catch (ClientProtocolException e) {
            Log.e(CLASS_NAME, "ClientProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(CLASS_NAME, "IOException: " + e.getMessage());
        } catch (SecurityException e) {
            Log.e(CLASS_NAME, "SecurityException error: " + e.getMessage());
        }
        return responseVO;
    }
}

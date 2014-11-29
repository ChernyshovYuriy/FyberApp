package com.yuriy.fyberapp.business;

import android.net.Uri;
import android.test.InstrumentationTestCase;

import com.yuriy.fyberapp.ApplicationTest;
import com.yuriy.fyberapp.api.APIServiceProvider;
import com.yuriy.fyberapp.api.APIServiceProviderImpl;
import com.yuriy.fyberapp.net.Downloader;
import com.yuriy.fyberapp.vo.ResponseVO;

import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/29/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */
public class DataParserTest extends InstrumentationTestCase {

    /**
     * Downloader mock
     */
    private Downloader mDownloader;

    /**
     * Data parser mock
     */
    private DataParser mDataParserMock;

    /**
     * Service Provide mock
     */
    private APIServiceProvider mServiceProvider;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        ApplicationTest.setupMocking(this);
        mDownloader = mock(Downloader.class);
        mDataParserMock = mock(JSONDataParserImpl.class);
        mServiceProvider = new APIServiceProviderImpl(mDataParserMock);

        final ResponseVO responseVO = ResponseVO.createInstance();
        responseVO.setData(ApplicationTest.RAW_RESPONSE.getBytes());

        // when downloader asks to download data - return real data
        when(mDownloader.downloadDataFromUri(Uri.parse(""), "")).thenReturn(responseVO);
    }

    public void testParseCurrentOffersCall() {
        mServiceProvider.getCurrentOffers(mDownloader, Uri.parse(""), "");

        final ArgumentCaptor<String> codeCapture = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<String> messageCapture = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<String> offersCapture = ArgumentCaptor.forClass(String.class);

        verify(mDataParserMock, times(1)).getOffers(offersCapture.capture());
        verify(mDataParserMock, times(1)).getCode(codeCapture.capture());
        verify(mDataParserMock, times(1)).getMessage(messageCapture.capture());

        assertThat(ApplicationTest.RAW_RESPONSE, is(codeCapture.getValue()));
        assertThat(ApplicationTest.RAW_RESPONSE, is(messageCapture.getValue()));
        assertThat(ApplicationTest.RAW_RESPONSE, is(offersCapture.getValue()));
    }
}

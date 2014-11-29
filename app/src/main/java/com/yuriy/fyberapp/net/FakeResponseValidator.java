package com.yuriy.fyberapp.net;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/29/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */

/**
 * {@link com.yuriy.fyberapp.net.FakeResponseValidator} is a helper class to help pass validation
 * in case of fake response
 */
public class FakeResponseValidator implements ResponseValidator {

    @Override
    public boolean isValid(byte[] response) {
        return true;
    }

    @Override
    public void setApiKey(String apiKey) {

    }

    /**
     * Private constructor.
     */
    private FakeResponseValidator() { }

    /**
     * Factory method to instantiate {@link com.yuriy.fyberapp.net.FakeResponseValidator}
     *
     * @return Instance of the {@link com.yuriy.fyberapp.net.FakeResponseValidator}
     */
    public static FakeResponseValidator createInstance() {
        return new FakeResponseValidator();
    }
}

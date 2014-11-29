package com.yuriy.fyberapp;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.InstrumentationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    public static final String RAW_RESPONSE = "{\n" +
            " \"code\": \"OK\" ,\n" +
            " \"message\": \"OK\",\n" +
            " \"count\": 1,\n" +
            " \"pages\": 1,\n" +
            " \"information\" : {\n" +
            "  \"app_name\": \"SP Test App\" ,\n" +
            "  \"appid\": 157,\n" +
            "  \"virtual_currency\": \"Coins\",\n" +
            "  \"country\": \" US\" ,\n" +
            "  \"language\": \" EN\" ,\n" +
            "  \"support_url\": \"http://iframe.sponsorpay.com/mobile/DE/157/my_offers\"\n" +
            " },\n" +
            " \"offers\": [\n" +
            "  {\n" +
            "    \"title\": \"Tap  Fish\",\n" +
            "    \"offer_id\": 13554,\n" +
            "    \"teaser\": \"Download and START\" ,\n" +
            "    \"required_actions\": \"Download and START\",\n" +
            "    \"link\": \"http://iframe.sponsorpay.com/mbrowser?appid=157&lpid=11387&uid=player1\",\n" +
            "    \"offer_types\" : [\n" +
            "     {\n" +
            "      \"offer_type_id\": 101,\n" +
            "      \"readable\": \"Download\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"offer_type_id\": 112,\n" +
            "      \"readable\": \"Free\"\n" +
            "     }\n" +
            "    ] ,\n" +
            "    \"thumbnail\" : {\n" +
            "     \"lowres\": \"http://cdn.sponsorpay.com/assets/1808/icon175x175-2_square_60.png\",\n" +
            "     \"hires\": \"http://cdn.sponsorpay.com/assets/1808/icon175x175-2_square_175.png\"\n" +
            "    },\n" +
            "    \"payout\": 90,\n" +
            "    \"time_to_payout\" : {\n" +
            "     \"amount\": 1800 ,\n" +
            "     \"readable\": \"30 minutes\"\n" +
            "    }\n" +
            "  }\n" +
            " ]\n" +
            "}";

    public ApplicationTest() {
        super(Application.class);
    }

    public static void setupMocking(final InstrumentationTestCase testCase) {
        System.setProperty("dexmaker.dexcache", testCase.getInstrumentation()
                .getTargetContext()
                .getCacheDir()
                .getPath());
    }
}
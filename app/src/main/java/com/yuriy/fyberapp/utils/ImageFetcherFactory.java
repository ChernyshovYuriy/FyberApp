package com.yuriy.fyberapp.utils;

import androidx.fragment.app.FragmentActivity;

import com.yuriy.fyberapp.R;

/**
 * Created with Android Studio.
 * User: Chernyshov Yuriy
 * Date: 18.05.14
 * Time: 21:14
 */

/**
 * This class provides factory methods to create
 * {@link ImageFetcher} instance
 */
public class ImageFetcherFactory {

    private static final String SMALL_IMAGE_CACHE_DIR = "thumbs_small";
    private static final String LARGE_IMAGE_CACHE_DIR = "thumbs_large";

    /**
     * Create {@link ImageFetcher} instance to fetch
     * small images for the List View
     * @param context {@link android.content.Context}
     * @return {@link ImageFetcher} instance
     */
    public static ImageFetcher getSmallImageFetcher(FragmentActivity context) {
        int imageThumbSize = context.getResources().getDimensionPixelSize(R.dimen.list_item_width);
        return getImageFetcher(context, imageThumbSize, SMALL_IMAGE_CACHE_DIR);
    }

    /**
     * Create {@link ImageFetcher} instance to fetch
     * small images for the Details View
     * @param context {@link android.content.Context}
     * @return {@link ImageFetcher} instance
     */
    public static ImageFetcher getLargeImageFetcher(FragmentActivity context) {
        return getImageFetcher(context, AppUtils.getLongestScreenSize(context),
                LARGE_IMAGE_CACHE_DIR);
    }

    /**
     * Create {@link ImageFetcher} instance
     * @param context        {@link android.content.Context}
     * @param imageThumbSize desired size of the fetched image
     * @param imageDir       directory to save images at
     * @return {@link ImageFetcher} instance
     */
    private static ImageFetcher getImageFetcher(FragmentActivity context, int imageThumbSize,
                                               String imageDir) {

        ImageCache.ImageCacheParams cacheParams =
                new ImageCache.ImageCacheParams(context, imageDir);

        cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to 25% of app memory

        // The ImageFetcher takes care of loading images into our ImageView children asynchronously
        ImageFetcher imageFetcher = new ImageFetcher(context, imageThumbSize);
        imageFetcher.setLoadingImage(R.drawable.img_placeholder);
        imageFetcher.addImageCache(context.getSupportFragmentManager(), cacheParams);
        imageFetcher.setImageFadeIn(true);

        return imageFetcher;
    }
}
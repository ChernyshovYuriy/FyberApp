package com.yuriy.fyberapp.list;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/29/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */

import android.widget.ImageView;
import android.widget.TextView;

/**
 * {@link com.yuriy.fyberapp.list.ListAdapterViewHolder} is a helper class to keep references for
 * the View elements of the single row in the List
 */
public class ListAdapterViewHolder {

    /**
     * Title text view.
     */
    public TextView mTitleView;

    /**
     * Teaser text view.
     */
    public TextView mTeaser;

    /**
     * Payout text view.
     */
    public TextView mPayout;

    /**
     * Thumbnail hires view.
     */
    public ImageView mThumbHires;
}

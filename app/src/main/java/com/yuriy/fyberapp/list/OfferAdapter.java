package com.yuriy.fyberapp.list;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuriy.fyberapp.R;
import com.yuriy.fyberapp.utils.ImageFetcher;
import com.yuriy.fyberapp.vo.OfferVO;

import java.util.List;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/29/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */
public class OfferAdapter extends BaseAdapter {

    private ListAdapterViewHolder mViewHolder;
    private Activity mCurrentActivity;
    private ImageFetcher mImageFetcher;
    private final ListAdapterData<OfferVO> mAdapterData = new ListAdapterData<OfferVO>(null);

    /**
     * Constructor.
     *
     * @param activity     current {@link android.app.Activity}
     * @param imageFetcher {@link com.yuriy.fyberapp.utils.ImageFetcher} instance
     */
    public OfferAdapter(final FragmentActivity activity, final ImageFetcher imageFetcher) {
        mCurrentActivity = activity;
        mImageFetcher = imageFetcher;
    }

    @Override
    public int getCount() {
        return mAdapterData.getItemsCount();
    }

    @Override
    public Object getItem(final int position) {
        return mAdapterData.getItem(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final OfferVO offerVO = (OfferVO) getItem(position);
        convertView = prepareViewAndHolder(convertView, R.layout.list_item);
        mViewHolder.mTitleView.setText(offerVO.getTitle());
        mViewHolder.mTeaser.setText(offerVO.getTeaser());
        mViewHolder.mPayout.setText(String.valueOf(offerVO.getPayout()));

        if (!offerVO.getThumbnailHires().equals("")) {
            // Load the image asynchronously into the ImageView, this also takes care of
            // setting a placeholder image while the background thread runs
            mImageFetcher.loadImage(offerVO.getThumbnailHires(), mViewHolder.mThumbHires);
        }
        return convertView;
    }

    /**
     * Add {@link com.yuriy.fyberapp.vo.OfferVO} into the collection.
     * @param value {@link com.yuriy.fyberapp.vo.OfferVO}
     */
    public void addItem(final OfferVO value) {
        mAdapterData.addItem(value);
    }

    /**
     * Add {@link com.yuriy.fyberapp.vo.OfferVO}s into the collection.
     * @param items Collection of the {@link com.yuriy.fyberapp.vo.OfferVO}
     */
    public void addItems(final List<OfferVO> items) {
        for (OfferVO item : items) {
            addItem(item);
        }
    }

    /**
     * @return Adapter data.
     */
    public ListAdapterData<OfferVO> getData() {
        return mAdapterData;
    }

    /**
     * Clear adapter data.
     */
    public void clear() {
        mAdapterData.clear();
    }

    /**
     * Prepare view holder for the item rendering
     * @param convertView      {@link android.view.View} associated with List Item
     * @param inflateViewResId Id of the View layout
     * @return View
     */
    private View prepareViewAndHolder(View convertView, final int inflateViewResId) {
        // If there is no View created - create it here and set it's Tag
        if (convertView == null) {
            convertView = LayoutInflater.from(mCurrentActivity).inflate(inflateViewResId, null);
            mViewHolder = createViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            // Get View by provided Tag
            mViewHolder = (ListAdapterViewHolder) convertView.getTag();
        }
        return convertView;
    }

    /**
     * Create View holder to keep reference to the layout items
     * @param view {@link android.view.View}
     * @return {@link ListAdapterViewHolder} object
     */
    private ListAdapterViewHolder createViewHolder(final View view) {
        final ListAdapterViewHolder viewHolder = new ListAdapterViewHolder();
        viewHolder.mTitleView = (TextView) view.findViewById(R.id.title_view);
        viewHolder.mTeaser = (TextView) view.findViewById(R.id.teaser_view);
        viewHolder.mPayout = (TextView) view.findViewById(R.id.payout_view);
        viewHolder.mThumbHires = (ImageView) view.findViewById(R.id.img_view);
        return viewHolder;
    }
}

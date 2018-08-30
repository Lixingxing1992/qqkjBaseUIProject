package com.base.ui.org.view.edittext.remember;

import android.support.v7.widget.AppCompatEditText;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.base.ui.org.R;

import java.util.LinkedList;
import java.util.List;

/**
 * 一个自定义EditText View，可以记住该view的最近几次输入，使开发者不用到处去单独做cache逻辑。
 * Created by lixingxing on 2018/8/9.
 */
public class BaseRememberEditText extends AppCompatEditText {

    private static final String PREFERENCE_KEY = "RememberEditText";

    private int mRememberCount = 1;
    private String mRememberId;
    private boolean mAutoFill = true;
    private Drawable mDeleteDrawable;
    private Drawable mDropDownDrawable;
    private Drawable mDropUpDrawable;

    private PopupWindow pop;
    private Rect mDropDownIconRect = new Rect();
    private Rect mDeleteIconRect = new Rect();
    private int mIconStatus = ICON_ABSENT;
    private int mIconMargin = 0;

    private static final int ICON_SHOW_DROP_DOWN = 1;
    private static final int ICON_SHOW_DROP_UP = 2;
    private static final int ICON_ABSENT = 3;
    private static final int DEFAULT_REMEMBER_COUNT = 3;
    private static final int DEFAULT_ICON_MARGIN_IN_DP = 15;

    private static PersistedMap mCacheMap;
    protected List<String> mCacheDataList;

    public BaseRememberEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        initCacheMap(context);
        initData();
    }

    public BaseRememberEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initCacheMap(context);
        initData();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.BaseRememberEditText);
        try {
            mDeleteDrawable = getResources().getDrawable(a.getResourceId(
                    R.styleable.BaseRememberEditText_deleteIcon, R.mipmap.edit_remember_ic_delete));
            if (mDeleteDrawable != null) {
                mDeleteDrawable.setBounds(0, 0, mDeleteDrawable.getIntrinsicWidth(), mDeleteDrawable.getIntrinsicHeight());
            }

            mDropDownDrawable = getResources().getDrawable(a.getResourceId(
                    R.styleable.BaseRememberEditText_dropDownIcon, R.mipmap.edit_remember_ic_drop_down));
            if (mDropDownDrawable != null) {
                mDropDownDrawable.setBounds(0, 0, mDropDownDrawable.getIntrinsicWidth(), mDropDownDrawable.getIntrinsicHeight());
            }

            mDropUpDrawable = getResources().getDrawable(a.getResourceId(
                    R.styleable.BaseRememberEditText_dropUpIcon, R.mipmap.edit_remember_ic_drop_down));
            if (mDropUpDrawable != null) {
                mDropUpDrawable.setBounds(0, 0, mDropUpDrawable.getIntrinsicWidth(), mDropUpDrawable.getIntrinsicHeight());
            }

            mRememberCount = a.getInt(R.styleable.BaseRememberEditText_rememberCount, DEFAULT_REMEMBER_COUNT);
            mRememberId = a.getString(R.styleable.BaseRememberEditText_rememberId);
            // if not set rememberId, use view id
            if (null == mRememberId) {
                mRememberId = String.valueOf(getId());
            }

            mAutoFill = a.getBoolean(R.styleable.BaseRememberEditText_autoFill, true);
        } finally {
            a.recycle();
        }
    }

    /**
     * restore last recent input
     */
    private void initData() {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        float density = metrics.density;
        mIconMargin = Math.round(DEFAULT_ICON_MARGIN_IN_DP * density);

        mCacheDataList = new LinkedList<>();

        String lastCache = mCacheMap.get(mRememberId);

        if (mAutoFill) {
            setText(lastCache);
        }

        if (lastCache != null) {
            mCacheDataList.add(0, lastCache);
            // Retrieve all history data
            for (int i = 1; i < mRememberCount; ++i) {
                String data = mCacheMap.get(mRememberId + i);
                if (data != null) {
                    mCacheDataList.add(i, data);
                }
            }
            onCacheDataChanged();
        }
    }

    /**
     * Called when cached data changed(init or deleted).
     */
    private void onCacheDataChanged() {
        if (mCacheDataList.size() >= 1) {
            mIconStatus = ICON_SHOW_DROP_DOWN;
        } else {
            mIconStatus = ICON_ABSENT;
        }
    }

    /**
     * Do cache flush jobs when view detached.
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // if have text, save it
        String text = getText().toString();
        // save newest input to default key
        if (text.length() > 0) {
            mCacheMap.put(mRememberId, text);
            if (mCacheDataList.isEmpty() || !text.equals(mCacheDataList.get(0))) {
                mCacheDataList.add(0, text);
            }
        }
        // flush history
        for (int i = 1; i < mCacheDataList.size(); ++i) {
            mCacheMap.put(mRememberId + i, mCacheDataList.get(i));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        final int compoundPaddingTop = getCompoundPaddingTop();
        final int compoundPaddingBottom = getCompoundPaddingBottom();

        int vspace = getBottom() - getTop() - compoundPaddingBottom - compoundPaddingTop;
        int drawableHeight = mDeleteDrawable.getIntrinsicHeight();
        int dropDownWidth = mDropDownDrawable.getIntrinsicWidth();
        int deleteWidth = mDeleteDrawable.getIntrinsicWidth();

        int offsetX = getMeasuredWidth() - getCompoundPaddingRight() - dropDownWidth - mIconMargin - deleteWidth;
        int offsetY = getCompoundPaddingTop() + getScrollY() + (vspace - drawableHeight) / 2;

        canvas.translate(offsetX, offsetY);
        if (getText() != null && getText().length() != 0) {
            mDeleteDrawable.draw(canvas);
        }

        canvas.translate(deleteWidth + mIconMargin, 0);
        switch (mIconStatus) {
            case ICON_SHOW_DROP_DOWN:
                mDropDownDrawable.draw(canvas);
                break;
            case ICON_SHOW_DROP_UP:
                mDropUpDrawable.draw(canvas);
                break;
            case ICON_ABSENT:
                break;
        }

        mDeleteIconRect.set(offsetX, offsetY, offsetX + deleteWidth, offsetY + drawableHeight);
        mDropDownIconRect.set(offsetX + deleteWidth + mIconMargin, offsetY,
                offsetX + deleteWidth + mIconMargin + dropDownWidth, offsetY + drawableHeight);

        canvas.restore();
    }

    /**
     * Override onTouchEvent, check icon click event. See
     * {@link android.text.method.ArrowKeyMovementMethod} for EditText touch event handle.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // If touch icon fields, intercept event to prevent further handle.
        boolean handled = false;
        final int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (action == MotionEvent.ACTION_UP) {
            if (isInRect(mDropDownIconRect, x, y)) {
                handled = true;
                if (pop == null) {
                    showPopupWindow();
                } else {
                    disMissOrUpdatePopupWindow();
                }
            } else if (isInRect(mDeleteIconRect, x, y)) {
                setText("");
                handled = true;
            }
        }
        return handled || super.onTouchEvent(event);
    }

    /**
     * Different from {@link Rect}'s contains method, it is more tolerant.
     */
    private boolean isInRect(Rect rect, int x, int y) {
        return x > rect.left - mIconMargin / 2  && x < rect.right + mIconMargin / 2;
    }

    private static void initCacheMap(Context context) {
        if (mCacheMap == null) {
            mCacheMap = new PersistedMap(context, PREFERENCE_KEY);
        }
    }

    /**
     * Clear all cache managed by RememberEditText.
     */
    public static void clearCache(Context context) {
        initCacheMap(context);
        mCacheMap.clear();
    }

    // ===================== popup window =====================
    private LinearLayout mCacheListWrapperLinearLayout;
    private ListView mCacheListView;
    private boolean mKeyboardShown;
    private RememberListAdapter mListAdapter;
    private int mPopupWindowHeight;

    public void showPopupWindow() {
        // no cache data, return directly
        if (mCacheDataList == null || mCacheDataList.size() == 0) {
            return;
        }
        if (mListAdapter == null) {
            mListAdapter = new RememberListAdapter();
        }

        // if soft keyboard is shown, hide it before show popup
        if (mKeyboardShown) {
            // hide keyboard
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindowToken(), 0);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    willShowPopupWindow();
                }
            }, 550);
        } else {
            willShowPopupWindow();
        }
    }

    private void willShowPopupWindow() {
        int userCount = mListAdapter.getCount();
        if (userCount > mRememberCount) {
            userCount = mRememberCount;
        }

        float itemHeight1 = getResources().getDimension(R.dimen.dp_50);
        int itemHeight2 = getResources().getDimensionPixelSize(R.dimen.dp_50);

        int itemHeight = getResources().getDimensionPixelSize(R.dimen.dp_50);
        mPopupWindowHeight = itemHeight * userCount + (userCount - 1);
        if (pop == null) {
            mCacheListView = new ListView(getContext());
            mCacheListView.setCacheColorHint(0);
            mCacheListView.setScrollingCacheEnabled(false);
            mCacheListView.setFadingEdgeLength(0);
            mCacheListView.setDivider(new ColorDrawable(Color.GRAY));
            mCacheListView.setDividerHeight(1);

            LayoutParams params = new LayoutParams(getWidth(), mPopupWindowHeight);
            mCacheListView.setLayoutParams(params);

            mCacheListWrapperLinearLayout = new LinearLayout(getContext());
            mCacheListWrapperLinearLayout.setLayoutParams(params);
            mCacheListWrapperLinearLayout.addView(mCacheListView);
            pop = new SafePopupWindow(mCacheListWrapperLinearLayout, getWidth(), LayoutParams.WRAP_CONTENT);
            mCacheListView.setAdapter(mListAdapter);
        }
        pop.setAnimationStyle(R.style.PopupWindowAnim);
        pop.showAsDropDown(this);
        mListAdapter.notifyDataSetChanged();
        mIconStatus = ICON_SHOW_DROP_UP;
    }

    public void disMissOrUpdatePopupWindow() {
        if (pop != null) {
            mIconStatus = ICON_SHOW_DROP_DOWN;
            if (pop.isShowing()) {
                pop.dismiss();
                pop = null;
            }
        }
    }

    /**
     * Update EditText text after selected
     * @param position selected position in cached list
     * @return whether position is legal and event is handled
     */
    private boolean selectItem(int position) {
        disMissOrUpdatePopupWindow();
        if (position >= 0 && mCacheDataList.size() > position) {
            setText(mCacheDataList.get(position));
            return true;
        }
        return false;
    }

    /**
     * RememberEditText's dropdown list adapter.
     */
    class RememberListAdapter extends BaseAdapter {

        LayoutInflater mInflater;

        public RememberListAdapter() {
            mInflater = LayoutInflater.from(getContext());
        }

        @Override
        public int getCount() {
            if (mCacheDataList != null) {
                return mCacheDataList.size() > mRememberCount ? mRememberCount : mCacheDataList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mCacheDataList != null) {
                return mCacheDataList.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Holder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_remember_cache_list, null);
                holder = new Holder();
                holder.wrapper = convertView.findViewById(R.id.popupWrapper);
                holder.view = (TextView) convertView.findViewById(R.id.popupContent);
                holder.button = (ImageView) convertView.findViewById(R.id.popupDelete);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            final String cacheData = (String) getItem(position);
            if (holder != null && cacheData != null) {
                convertView.setId(position);
                holder.setId(position);
                holder.view.setText(cacheData);
                convertView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectItem(position);
                    }
                });
                holder.button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mCacheDataList != null && mCacheDataList.size() > 0 && position < mCacheDataList.size()) {
                            mCacheDataList.remove(position);
                        }
                        if (mCacheDataList == null || (mCacheDataList != null && mCacheDataList.size() < 1)) {
                            onCacheDataChanged();
                        }
                        if (getText().toString().trim().equalsIgnoreCase(cacheData)) {
                            setText("");
                        }
                        mListAdapter.notifyDataSetChanged();

                        if (mCacheDataList.size() == 0) {
                            disMissOrUpdatePopupWindow();
                            return;
                        }
                        if (mCacheDataList.size() < mRememberCount) {
                            int itemHeight = getResources().getDimensionPixelSize(R.dimen.dp_50);
                            int currHeight = itemHeight * mCacheDataList.size() + (mCacheDataList.size() - 1);
                            mCacheListView.getLayoutParams().height = currHeight;
                            mCacheListWrapperLinearLayout.getLayoutParams().height = currHeight;
                            mCacheListView.requestLayout();
                            mPopupWindowHeight = currHeight;
                        }
                    }
                });
            }
            return convertView;
        }

        class Holder {
            View wrapper;
            TextView view;
            ImageView button;

            void setId(int position) {
                view.setId(position);
                button.setId(position);
                wrapper.setId(position);
            }
        }
    }
}

package com.youlehuo.app.aboutview.pobupwindow.bubbleEngine;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youlehuo.app.R;


public class HelpPopupWindow extends PopupWindow implements BubbleRelativeLayout.BubbleLayoutChangeListener {

	private static final String VERSION_KEY = "version_number";

	private float mTopPos = 0;
	private float mLeftPos = 0;
	private float mRightPos = 0;
	private float mBottomPos = 0;
	private float mXoffsetPercentage = 50f;
	private float mYoffsetPercentage = 50f;

	private View mParent = null;
	private Integer mID = HelpPopupManager.INVALID_ID_VALUE;
	private Integer mForVersionCode = HelpPopupManager.INVALID_ID_VALUE;
	private BubbleRelativeLayout mBubbleRelativeLayout = null;

	private View.OnClickListener mOnNotifyNextClick = null;
	private View.OnClickListener mOnNotifyCancelClick = null;
	private OnDismissListener mOnNotifyDismissListener = null;

	private OnDismissListener mOnDismissListener = new OnDismissListener() {

		@Override
		public void onDismiss() {

			if (mOnNotifyDismissListener != null) {
				mOnNotifyDismissListener.onDismiss();
			}
		}
	};

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mID == null) ? 0 : mID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HelpPopupWindow other = (HelpPopupWindow) obj;
		if (mID == null) {
			if (other.mID != null)
				return false;
		} else if (!mID.equals(other.mID))
			return false;
		return true;
	}

	private View.OnClickListener mOnNextClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			mOnNotifyDismissListener = null;

			dismiss();

			if (mOnNotifyNextClick != null) {
				mOnNotifyNextClick.onClick(v);
			}
		}
	};

	private View.OnClickListener mOnCancelClickListener = new View.OnClickListener() {

		@Override
		public void onClick(final View v) {

			AppCompatActivity activity = (AppCompatActivity) getContentView().getContext();

			new AlertDialogFragment(R.string.alert_title_info, R.string.alert_cancel_help, new AlertDialogFragment.AlertDialogFragmentListener() {

				@Override
				public void onPositiveClick() {
					mOnNotifyDismissListener = null;

					dismiss();

					if (mOnNotifyCancelClick != null) {
						mOnNotifyCancelClick.onClick(v);
					}
				}

				@Override
				public void onNegativeClick() {
					// TODO Auto-generated method stub

				}
			}).showDialog(activity.getSupportFragmentManager());
		}
	};

	public void setOnNotifyCancelClick(View.OnClickListener mOnNotifyCancelClick) {
		this.mOnNotifyCancelClick = mOnNotifyCancelClick;
	}

	public void setOnNotifyDismissListener(OnDismissListener mOnNotifyDismissListener) {
		this.mOnNotifyDismissListener = mOnNotifyDismissListener;
	}

	public void setOnNotifyNextClick(View.OnClickListener mOnNotifyNextClick) {
		this.mOnNotifyNextClick = mOnNotifyNextClick;
	}

	public HelpPopupWindow(final int forVersionCode, final int ID, final View parent, final String content) {

		this(forVersionCode, ID, parent, 50f, 50f, content);

	}

	public HelpPopupWindow(final int forVersionCode, final int ID, final View parent, final float xOffsetPercentage, final float yOffsetPercentage, final String content) {
		super(parent.getContext());

		mID = ID;
		mParent = parent;
		mForVersionCode = forVersionCode;
		mXoffsetPercentage = xOffsetPercentage;
		mYoffsetPercentage = yOffsetPercentage;

		final LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		final View contentView = inflater.inflate(R.layout.help_popup_window, null);
		setContentView(contentView);

		setFocusable(true);
		setOutsideTouchable(false);
		setClippingEnabled(false);

		setBackgroundDrawable(new ColorDrawable(0));
		setAnimationStyle(R.style.AnimationPopup);

		final Button buttonNext = (Button) contentView.findViewById(R.id.buttonNext);
		buttonNext.setOnClickListener(mOnNextClickListener);

		final Button buttonCancel = (Button) contentView.findViewById(R.id.buttonCancel);
		buttonCancel.setOnClickListener(mOnCancelClickListener);

		setTouchInterceptor(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {

				if (motionEvent.getAction() == KeyEvent.ACTION_UP) {
					return false;
				}

				final View touchAllowedControls[] = new View[] { buttonNext, buttonCancel };

				for (final View touchView : touchAllowedControls) {
					Rect rect = new Rect();
					int location[] = new int[2];

					touchView.getHitRect(rect);
					touchView.getLocationOnScreen(location);
					rect.offsetTo(location[0], location[1]);

					if (rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
						return false;
					}
				}

				return true;
			}
		});

		setOnDismissListener(mOnDismissListener);

		final TextView textViewContent = (TextView) contentView.findViewById(R.id.txtContent);
		textViewContent.setText(content);

		mBubbleRelativeLayout = (BubbleRelativeLayout) contentView;
		mBubbleRelativeLayout.setBubbleLayoutChangeListener(this);

	}

	public void setParent(final View parent) {
		mParent = parent;
	}

	public View getParent() {
		return mParent;
	}

	public Integer getID() {
		return mID;
	}

	public Integer getForVersionCode() {
		return mForVersionCode;
	}
	public void show() {
		showOrUpdatePopup(false);
	}

	private void showOrUpdatePopup(final boolean update) {

		final DisplayMetrics dispMetrics = new DisplayMetrics();

		final WindowManager wm = (WindowManager) mParent.getContext().getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dispMetrics);

		final RelativeLayout contentView = (RelativeLayout) getContentView();
		contentView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));

		final int widthMeasureSpec = MeasureSpec.makeMeasureSpec(dispMetrics.widthPixels, MeasureSpec.AT_MOST);
		final int heightMeasureSpec = MeasureSpec.makeMeasureSpec(dispMetrics.heightPixels, MeasureSpec.AT_MOST);

		contentView.measure(widthMeasureSpec, heightMeasureSpec);

		final float bubbleWidth = contentView.getMeasuredWidth();
		final float bubbleHeight = contentView.getMeasuredHeight();

		setWindowLayoutMode(1, 1);

		setWidth((int) bubbleWidth);
		setHeight((int) bubbleHeight + 10);

		final int[] absolutePosition = new int[2];
		mParent.getLocationOnScreen(absolutePosition);

		final float xOff = mParent.getWidth() * mXoffsetPercentage / 100f;
		final float yOff = mParent.getHeight() * mYoffsetPercentage / 100f;

		mLeftPos = (float) (absolutePosition[0] + xOff);
		mRightPos = (float) (dispMetrics.widthPixels - mLeftPos);
		mTopPos = (float) (absolutePosition[1] + mParent.getHeight() - yOff);
		mBottomPos = (float) (dispMetrics.heightPixels - mTopPos);

		final BubbleRelativeLayout.BubbleLegOrientation bubbleOrientation = resolveBubbleLegOrientation(bubbleWidth - BubbleRelativeLayout.PADDING, bubbleHeight - BubbleRelativeLayout.PADDING,
				dispMetrics);

		final float bubbleLegOffset = computeBubbleLegOffset(dispMetrics, bubbleOrientation, bubbleWidth, bubbleHeight);

		computePinPointAndShow(update, bubbleOrientation, bubbleLegOffset, bubbleWidth, bubbleHeight);
	}

	private BubbleRelativeLayout.BubbleLegOrientation resolveBubbleLegOrientation(final float bubbleWidth, final float bubbleHeight, final DisplayMetrics dispMetrics) {

		final boolean isMinTopBottomLegDistance = Math.min(mTopPos, mBottomPos) > BubbleRelativeLayout.MIN_LEG_DISTANCE;
		final boolean isMinLeftRightLegDistance = Math.min(mLeftPos, mRightPos) > BubbleRelativeLayout.MIN_LEG_DISTANCE;

		BubbleRelativeLayout.BubbleLegOrientation bubbleOrientation = (dispMetrics.widthPixels > (bubbleWidth + mLeftPos)) && isMinTopBottomLegDistance ? BubbleRelativeLayout.BubbleLegOrientation.LEFT
				: BubbleRelativeLayout.BubbleLegOrientation.RIGHT;

		if (bubbleOrientation == BubbleRelativeLayout.BubbleLegOrientation.RIGHT) {

			bubbleOrientation = (bubbleWidth < mLeftPos) && isMinTopBottomLegDistance ? bubbleOrientation : BubbleRelativeLayout.BubbleLegOrientation.BOTTOM;

			if (bubbleOrientation == BubbleRelativeLayout.BubbleLegOrientation.BOTTOM) {

				bubbleOrientation = (bubbleHeight < mTopPos) && isMinLeftRightLegDistance ? bubbleOrientation : BubbleRelativeLayout.BubbleLegOrientation.TOP;

				if (bubbleOrientation == BubbleRelativeLayout.BubbleLegOrientation.TOP) {

					bubbleOrientation = (dispMetrics.heightPixels > (bubbleHeight + mTopPos)) && isMinLeftRightLegDistance ? bubbleOrientation : BubbleRelativeLayout.BubbleLegOrientation.NONE;
				}
			}
		}

		return bubbleOrientation;
	}

	private float computeBubbleLegOffset(final DisplayMetrics dispMetrics, final BubbleRelativeLayout.BubbleLegOrientation bubbleLegOrientation, final float bubbleWidth, final float bubbleHeight) {

		if (bubbleLegOrientation == BubbleRelativeLayout.BubbleLegOrientation.BOTTOM || bubbleLegOrientation == bubbleLegOrientation.TOP) {

			if (mLeftPos < mRightPos) {

				return Math.min(mLeftPos, bubbleWidth / 2f);

			} else {

				return Math.max(bubbleWidth - mRightPos, bubbleWidth / 2f);
			}

		}

		if (mTopPos < mBottomPos) {

			return Math.min(mTopPos, bubbleHeight / 2f);

		} else {

			return Math.max(bubbleHeight - mBottomPos, bubbleHeight / 2f);
		}

	}

	private void computePinPointAndShow(final boolean update, final BubbleRelativeLayout.BubbleLegOrientation bubbleLegOrientation, final float legOffset, final float bubbleWidth,
										final float bubbleHeight) {

		mBubbleRelativeLayout.setBubbleParams(bubbleLegOrientation, legOffset);

		float xOff = mParent.getWidth() * mXoffsetPercentage / 100f;
		float yOff = -mParent.getHeight() * mYoffsetPercentage / 100f;

		if (bubbleLegOrientation == BubbleRelativeLayout.BubbleLegOrientation.TOP) {

			xOff -= legOffset;
			yOff += 0;

		} else if (bubbleLegOrientation == BubbleRelativeLayout.BubbleLegOrientation.BOTTOM) {

			xOff -= legOffset;
			yOff -= bubbleHeight;

		} else if (bubbleLegOrientation == BubbleRelativeLayout.BubbleLegOrientation.LEFT) {

			xOff += 0;
			yOff -= legOffset;

		} else if (bubbleLegOrientation == BubbleRelativeLayout.BubbleLegOrientation.RIGHT) {

			xOff -= bubbleWidth;
			yOff -= legOffset;
		}

		if (update) {
			getContentView().invalidate();
			update(mParent, (int) xOff, (int) yOff, (int) bubbleWidth, (int) bubbleHeight);

		} else {
			showAsDropDown(mParent, (int) xOff, (int) yOff);

		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				showOrUpdatePopup(true);
			}
		}, 100);
	}
}

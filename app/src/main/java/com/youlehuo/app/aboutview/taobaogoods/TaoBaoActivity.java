package com.youlehuo.app.aboutview.taobaogoods;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.youlehuo.app.R;

public class TaoBaoActivity extends FragmentActivity {

	private VerticalFragment1 fragment1;
	private VerticalFragment3 fragment3;
	private DragLayout draglayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.taobaoactivity);
		initView();
	}

	/**
	 * 初始化View
	 */
	private void initView() {
		fragment1 = new VerticalFragment1();
		fragment3 = new VerticalFragment3();

		getSupportFragmentManager().beginTransaction()
				.add(R.id.first, fragment1).add(R.id.second, fragment3)
				.commit();

		DragLayout.ShowNextPageNotifier nextIntf = new DragLayout.ShowNextPageNotifier() {
			@Override
			public void onDragNext() {
				fragment3.initView();
			}
		};
		draglayout = (DragLayout) findViewById(R.id.draglayout);
		draglayout.setNextPageListener(nextIntf);
	}

}

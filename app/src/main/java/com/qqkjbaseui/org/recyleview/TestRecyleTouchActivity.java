package com.qqkjbaseui.org.recyleview;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

import com.base.ui.org.itemtouchhelper.ItemTouchHelper;
import com.base.ui.org.itemtouchhelper.SimpleItemTouchCallback;
import com.base.ui.org.itemtouchhelper.StartDragListener;
import com.qqkjbaseui.org.R;

public class TestRecyleTouchActivity extends AppCompatActivity implements StartDragListener {

	private RecyclerView recyclerview;
	private ItemTouchHelper itemTouchHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_recyclerview_touch);
		
		recyclerview = (RecyclerView)findViewById(R.id.recyclerview);
		
		List<QQMessage> list = DataUtils.init();

		//recyclerview使用
		MessageAdapter adapter = new MessageAdapter(list,this);
		recyclerview.setLayoutManager(new LinearLayoutManager(this));
		recyclerview.setAdapter(adapter);



		ItemTouchHelper.Callback callback = new SimpleItemTouchCallback(adapter);
		itemTouchHelper = new ItemTouchHelper(callback);
		itemTouchHelper.attachToRecyclerView(recyclerview);


		
	}

	@Override
	public void onStartDrag(ViewHolder viewHolder) {
		itemTouchHelper.startDrag(viewHolder);
	}
	
	

}

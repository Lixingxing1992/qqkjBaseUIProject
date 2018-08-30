package com.base.ui.org.view.map;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.base.ui.org.R;

import org.w3c.dom.Document;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * 测试版
 * Created by lixingxing on 2018/8/1.
 */
public class MapSVGView extends View {
    private Context context;

    public MapSVGView(Context context) {
        super(context);
        init(context);
    }

    public MapSVGView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MapSVGView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, Context context1) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    Thread loadThread = new Thread() {
        @Override
        public void run() {
            try {
//                InputStream inputStream = context.getResources().openRawResource(R.raw.);
//
//                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//                DocumentBuilder builder = factory.newDocumentBuilder();
//
//                Document document = builder.parse(inputStream);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };
}

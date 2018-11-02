package com.example.power.welcomepage.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.power.welcomepage.Adapter.GuideViewPagerAdapter;
import com.example.power.welcomepage.R;
import com.example.power.welcomepage.Util.SharedPreferencesUtil;
import com.example.power.welcomepage.WelcomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Power on 2018/11/2.
 */

public class WelcomeGuideActivity extends Activity implements View.OnClickListener {
    private ViewPager viewPager;
    private GuideViewPagerAdapter adapter;
    private List<View> views;
    private Button startBtn;

    /*引导页图片资源*/
    private static final int[] pics = {  R.layout.guid_view1,
            R.layout.guid_view2, R.layout.guid_view3, R.layout.guid_view4 };

    /*底部小点图片*/
    private ImageView[] dots;

    /*用于记录当前选中位置*/
    private int currentIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        views = new ArrayList<View>();

        /*初始化引导页视图列表,需要对资源进行处理*/
        for(int i = 0; i < pics.length; i++){
            View view = LayoutInflater.from(this).inflate(pics[i], null);

            if(i == pics.length - 1){
                startBtn = (Button)view.findViewById(R.id.btn_login);
                /*这里使用setTag方法进行标注。在View中的setTag(Onbect)表示给View
                添加一个格外的数据，以后可以用getTag()将这个数据取出来。可以用在
                多个Button添加一个监听器，每个Button都设置不同的setTag。这个监听
                器就通过getTag来分辨是哪个Button 被按下。*/
                startBtn.setTag("enter");
                startBtn.setOnClickListener(this);
            }
            views.add(view);
        }

        viewPager = (ViewPager)findViewById(R.id.vp_guide);
        /*初始化adapter*/
        adapter = new GuideViewPagerAdapter(views);
        viewPager.setAdapter(adapter);
        /*需要设置页面改变的监听器，这样我们能把我页面改变时的具体操作细节，所以
        需要创建PageChangeListener，实现OnPageChangeListener接口*/
        viewPager.addOnPageChangeListener(new PageChangeListener());
        initDots();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*如果切换到后台，就设置下次不进入功能引导页*/
        SharedPreferencesUtil.setBoolean(WelcomeGuideActivity.this, SharedPreferencesUtil.FIRST_OPEN, false);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initDots(){
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.ll);
        dots = new ImageView[pics.length];

        /*循环取得小点图片*/
        for(int i = 0; i < pics.length; i++){
            /*得到一个LinearLayout下面的每一个子元素*/
            dots[i] = (ImageView)linearLayout.getChildAt(i);
            dots[i].setEnabled(false);//设置成灰色
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);//设置位置tag，方便取出与当前位置对应,原理同上
        }
        currentIndex = 0;
        dots[currentIndex].setEnabled(true); // 设置为白色，即选中状态
    }

    /**
     * 设置当前view
     *
     * @param position
     */
    private void  setCurrentView(int position){
        if(position < 0 || position > pics.length){
            return;
        }
        viewPager.setCurrentItem(position);
    }

    /**
     * 设置当前指示点
     *
     * @param position
     */
    private void setCurDot(int position) {
        if (position < 0 || position > pics.length || currentIndex == position) {
            return;
        }
        dots[position].setEnabled(true);
        dots[currentIndex].setEnabled(false);
        currentIndex = position;
    }

    @Override
    public void onClick(View v) {
        if(v.getTag().equals("enter")){
            enterMainActivity();
            return;
        }
        int position = (Integer) v.getTag();
        setCurrentView(position);
        setCurDot(position);
    }

    private void enterMainActivity(){
        Intent intent = new Intent(WelcomeGuideActivity.this, WelcomeActivity.class);
        startActivity(intent);
        SharedPreferencesUtil.setBoolean(WelcomeGuideActivity.this, SharedPreferencesUtil.FIRST_OPEN, false);
        finish();
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener{
        /*当滑动状态改变时调用*/

        @Override
        public void onPageScrollStateChanged(int state) {
            /*arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。*/
        }

        /*当前页面被滑动时调用*/

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // arg0 :当前页面，及你点击滑动的页面
            // arg1:当前页面偏移的百分比
            // arg2:当前页面偏移的像素位置
        }

        /*当新的页面被选中时调用*/
        @Override
        public void onPageSelected(int position) {
            setCurDot(position);
        }
    }
}

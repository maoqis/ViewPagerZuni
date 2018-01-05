package com.maoqis.test.viewpagerzuni;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maoqis.test.viewpagerzuni.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MyViewPager mBbv;
    private LinearLayout mLlTop;
    private MyFragmentPagerAdapter adapter;
    private Integer integer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());

        mBbv.setAdapter(adapter);
    }

    private void initView() {
        mBbv = (MyViewPager) findViewById(R.id.bbv);
        mLlTop = (LinearLayout) findViewById(R.id.ll_top);

        mBbv.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                integer = adapter.getData().get(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        addTextView("set 4 item", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<Integer> data = adapter.getData();
                data.clear();
                data.add(0);
                data.add(1);
                data.add(2);
                data.add(3);
                adapter.notifyDataSetChanged();
                integer = adapter.getData().get(0);
            }
        });
        addTextView("add at end", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<Integer> data = adapter.getData();
                data.add(data.get(data.size() - 1) + 1);
                adapter.notifyDataSetChanged();
            }
        });

        addTextView("add at start", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<Integer> data = adapter.getData();
                final Integer integer = data.get(0);
                data.add(0, integer - 1);
                adapter.notifyDataSetChanged();
            }
        });
        addTextView("delete at start", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<Integer> data = adapter.getData();
                data.remove(0);
                adapter.notifyDataSetChanged();
            }
        });
        addTextView("delete at end", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<Integer> data = adapter.getData();
                data.remove(data.size() - 1);
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void addTextView(String title, View.OnClickListener onClickListener) {
        TextView textView = new TextView(this);
        textView.setText(title);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 96);
        textView.setOnClickListener(onClickListener);
        mLlTop.addView(textView, layoutParams);
    }


    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        List<Integer> mData = new ArrayList<>();

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(TAG, "getItem() called with: position = [" + position + "]" + " data=" + mData.get(position));
            Bundle bundle = new Bundle();
            bundle.putInt("pos", mData.get(position));
            return Fragment.instantiate(MainActivity.this, TextFragment.class.getName(), bundle);
        }

        @Override
        public int getItemPosition(Object object) {

            Log.d(TAG, "getItemPosition() called with: object = [" + object + "]" + " net pos=" );
            return super.getItemPosition(object);
        }

        @Override
        public long getItemId(int position) {

            return super.getItemId(position);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        public List<Integer> getData() {
            return mData;
        }

    }
}

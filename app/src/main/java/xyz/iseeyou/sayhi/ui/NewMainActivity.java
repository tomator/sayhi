package xyz.iseeyou.sayhi.ui;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.viewpagerindicator.TabPageIndicator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import xyz.iseeyou.sayhi.R;
import xyz.iseeyou.sayhi.ui.fragment.ContactFragment;
import xyz.iseeyou.sayhi.ui.fragment.RecentFragment;
import xyz.iseeyou.sayhi.ui.fragment.SettingsFragment;
import xyz.iseeyou.sayhi.ui.fragment.ShowFragment;
import xyz.iseeyou.sayhi.util.Log;

public class NewMainActivity extends ActivityBase {

    @InjectView(R.id.notifyBtn)
    ImageButton notifyBtn;
    @InjectView(R.id.profileBtn)
    ImageButton profileBtn;
    @InjectView(R.id.topView)
    LinearLayout topView;
    @InjectView(R.id.indicator)
    TabPageIndicator indicator;
    @InjectView(R.id.pager)
    ViewPager pager;
    private FragmentPagerAdapter adapter;
    public static Point point = new Point();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
        ButterKnife.inject(this);
        getWindowManager().getDefaultDisplay().getSize(point);
        Log.d(getClass()+" window size = "+point);
        adapter = new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);
        indicator.setCurrentItem(0);
    }


    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ShowFragment();
                case 1:
                    return new ContactFragment();
                case 2:
                    return new RecentFragment();
                default:
                    return new SettingsFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int index) {
            switch (index){
                case 0:
                    return "缘分";
                case 1:
                    return "好友";
                case 2:
                    return "聊天";
                default:
                    return "个人";
            }
        }


        @Override
        public int getCount() {
            return 4;
        }
    }
}

// Generated code from Butter Knife. Do not modify!
package xyz.iseeyou.sayhi.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class NewMainActivity$$ViewInjector {
  public static void inject(Finder finder, final xyz.iseeyou.sayhi.ui.NewMainActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558657, "field 'notifyBtn'");
    target.notifyBtn = (android.widget.ImageButton) view;
    view = finder.findRequiredView(source, 2131558658, "field 'profileBtn'");
    target.profileBtn = (android.widget.ImageButton) view;
    view = finder.findRequiredView(source, 2131558656, "field 'topView'");
    target.topView = (android.widget.LinearLayout) view;
    view = finder.findRequiredView(source, 2131558511, "field 'indicator'");
    target.indicator = (com.viewpagerindicator.TabPageIndicator) view;
    view = finder.findRequiredView(source, 2131558512, "field 'pager'");
    target.pager = (android.support.v4.view.ViewPager) view;
  }

  public static void reset(xyz.iseeyou.sayhi.ui.NewMainActivity target) {
    target.notifyBtn = null;
    target.profileBtn = null;
    target.topView = null;
    target.indicator = null;
    target.pager = null;
  }
}

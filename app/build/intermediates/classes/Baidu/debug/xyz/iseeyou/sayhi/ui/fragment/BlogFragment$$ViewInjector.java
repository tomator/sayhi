// Generated code from Butter Knife. Do not modify!
package xyz.iseeyou.sayhi.ui.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class BlogFragment$$ViewInjector {
  public static void inject(Finder finder, final xyz.iseeyou.sayhi.ui.fragment.BlogFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492936, "field 'listView'");
    target.listView = (android.widget.ListView) view;
    view = finder.findRequiredView(source, 2131492935, "field 'refreshView'");
    target.refreshView = (in.srain.cube.views.ptr.PtrFrameLayout) view;
  }

  public static void reset(xyz.iseeyou.sayhi.ui.fragment.BlogFragment target) {
    target.listView = null;
    target.refreshView = null;
  }
}

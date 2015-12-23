// Generated code from Butter Knife. Do not modify!
package xyz.iseeyou.sayhi.ui.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ShowFragment$$ViewInjector {
  public static void inject(Finder finder, final xyz.iseeyou.sayhi.ui.fragment.ShowFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492957, "field 'recyclerView'");
    target.recyclerView = (android.support.v7.widget.RecyclerView) view;
    view = finder.findRequiredView(source, 2131492935, "field 'refreshView'");
    target.refreshView = (in.srain.cube.views.ptr.PtrFrameLayout) view;
  }

  public static void reset(xyz.iseeyou.sayhi.ui.fragment.ShowFragment target) {
    target.recyclerView = null;
    target.refreshView = null;
  }
}

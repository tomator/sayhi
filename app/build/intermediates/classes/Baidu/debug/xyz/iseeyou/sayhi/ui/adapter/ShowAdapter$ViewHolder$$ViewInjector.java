// Generated code from Butter Knife. Do not modify!
package xyz.iseeyou.sayhi.ui.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ShowAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final xyz.iseeyou.sayhi.ui.adapter.ShowAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558542, "field 'avatarView'");
    target.avatarView = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131558652, "field 'nameView'");
    target.nameView = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131558653, "field 'ageView'");
    target.ageView = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131558654, "field 'addressView'");
    target.addressView = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131558655, "field 'distanceView'");
    target.distanceView = (android.widget.TextView) view;
  }

  public static void reset(xyz.iseeyou.sayhi.ui.adapter.ShowAdapter.ViewHolder target) {
    target.avatarView = null;
    target.nameView = null;
    target.ageView = null;
    target.addressView = null;
    target.distanceView = null;
  }
}

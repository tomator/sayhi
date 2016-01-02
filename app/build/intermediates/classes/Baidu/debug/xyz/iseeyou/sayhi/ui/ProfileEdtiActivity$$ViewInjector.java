// Generated code from Butter Knife. Do not modify!
package xyz.iseeyou.sayhi.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ProfileEdtiActivity$$ViewInjector {
  public static void inject(Finder finder, final xyz.iseeyou.sayhi.ui.ProfileEdtiActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558513, "field 'gridView'");
    target.gridView = (android.widget.GridView) view;
    view = finder.findRequiredView(source, 2131558514, "field 'nicknameHint'");
    target.nicknameHint = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131558515, "field 'nicknameEdit'");
    target.nicknameEdit = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131558516, "field 'descptionHint'");
    target.descptionHint = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131558517, "field 'descptionEdit'");
    target.descptionEdit = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131558519, "field 'genderView'");
    target.genderView = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131558518, "field 'genderContainer' and method 'genderClick'");
    target.genderContainer = (android.widget.LinearLayout) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.genderClick();
        }
      });
    view = finder.findRequiredView(source, 2131558521, "field 'birthdayView'");
    target.birthdayView = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131558520, "field 'birthdayContainer' and method 'onStartTimeClick'");
    target.birthdayContainer = (android.widget.LinearLayout) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onStartTimeClick();
        }
      });
  }

  public static void reset(xyz.iseeyou.sayhi.ui.ProfileEdtiActivity target) {
    target.gridView = null;
    target.nicknameHint = null;
    target.nicknameEdit = null;
    target.descptionHint = null;
    target.descptionEdit = null;
    target.genderView = null;
    target.genderContainer = null;
    target.birthdayView = null;
    target.birthdayContainer = null;
  }
}

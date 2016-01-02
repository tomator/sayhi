// Generated code from Butter Knife. Do not modify!
package xyz.iseeyou.sayhi.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class RegisterActivity$$ViewInjector {
  public static void inject(Finder finder, final xyz.iseeyou.sayhi.ui.RegisterActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558497, "field 'phoneView'");
    target.phoneView = (xyz.iseeyou.sayhi.view.AccountInputView) view;
    view = finder.findRequiredView(source, 2131558522, "field 'getAuthcodeBtn' and method 'getAuthcodeBtnClick'");
    target.getAuthcodeBtn = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.getAuthcodeBtnClick();
        }
      });
    view = finder.findRequiredView(source, 2131558523, "field 'authcodeView'");
    target.authcodeView = (xyz.iseeyou.sayhi.view.AccountInputView) view;
    view = finder.findRequiredView(source, 2131558498, "field 'passwordView'");
    target.passwordView = (xyz.iseeyou.sayhi.view.AccountInputView) view;
    view = finder.findRequiredView(source, 2131558500, "field 'registerBtn' and method 'registerBtn'");
    target.registerBtn = (android.widget.TextView) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.registerBtn();
        }
      });
  }

  public static void reset(xyz.iseeyou.sayhi.ui.RegisterActivity target) {
    target.phoneView = null;
    target.getAuthcodeBtn = null;
    target.authcodeView = null;
    target.passwordView = null;
    target.registerBtn = null;
  }
}

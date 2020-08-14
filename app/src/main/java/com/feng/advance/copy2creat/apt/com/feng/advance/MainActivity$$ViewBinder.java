// Generated code from Butter Knife. Do not modify!
package com.feng.advance.copy2creat.apt.com.feng.advance;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.feng.advance.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296501, "field 'mToolBar'");
    //target.mToolBar = finder.castView(view, 2131296501, "field 'mToolBar'");
    view = finder.findRequiredView(source, 2131296287, "field 'mAppBar'");
    //target.mAppBar = finder.castView(view, 2131296287, "field 'mAppBar'");
  }

  @Override public void unbind(T target) {
    //target.mToolBar = null;
    //target.mAppBar = null;
  }
}

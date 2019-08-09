package com.erik.android.androidlean.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.erik.android.androidlean.view.NavigationBar;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentNewlistBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout fgList;

  @NonNull
  public final ListView listNews;

  @NonNull
  public final NavigationBar navBar;

  protected FragmentNewlistBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout fgList, ListView listNews, NavigationBar navBar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.fgList = fgList;
    this.listNews = listNews;
    this.navBar = navBar;
  }

  @NonNull
  public static FragmentNewlistBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_newlist, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentNewlistBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentNewlistBinding>inflateInternal(inflater, com.erik.android.androidlean.R.layout.fragment_newlist, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentNewlistBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_newlist, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentNewlistBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentNewlistBinding>inflateInternal(inflater, com.erik.android.androidlean.R.layout.fragment_newlist, null, false, component);
  }

  public static FragmentNewlistBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentNewlistBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentNewlistBinding)bind(component, view, com.erik.android.androidlean.R.layout.fragment_newlist);
  }
}

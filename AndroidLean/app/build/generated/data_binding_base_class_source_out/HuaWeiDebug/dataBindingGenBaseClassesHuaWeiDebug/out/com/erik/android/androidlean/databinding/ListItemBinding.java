package com.erik.android.androidlean.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.erik.android.androidlean.bean.NewBean;
import com.erik.android.androidlean.view.CustomRoundAngleImageView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ListItemBinding extends ViewDataBinding {
  @NonNull
  public final CustomRoundAngleImageView ivHead;

  @NonNull
  public final LinearLayout rlList;

  @NonNull
  public final TextView txtItemContent;

  @NonNull
  public final TextView txtItemTitle;

  @Bindable
  protected NewBean mUser;

  protected ListItemBinding(Object _bindingComponent, View _root, int _localFieldCount,
      CustomRoundAngleImageView ivHead, LinearLayout rlList, TextView txtItemContent,
      TextView txtItemTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.ivHead = ivHead;
    this.rlList = rlList;
    this.txtItemContent = txtItemContent;
    this.txtItemTitle = txtItemTitle;
  }

  public abstract void setUser(@Nullable NewBean user);

  @Nullable
  public NewBean getUser() {
    return mUser;
  }

  @NonNull
  public static ListItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.list_item, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ListItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ListItemBinding>inflateInternal(inflater, com.erik.android.androidlean.R.layout.list_item, root, attachToRoot, component);
  }

  @NonNull
  public static ListItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.list_item, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ListItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ListItemBinding>inflateInternal(inflater, com.erik.android.androidlean.R.layout.list_item, null, false, component);
  }

  public static ListItemBinding bind(@NonNull View view) {
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
  public static ListItemBinding bind(@NonNull View view, @Nullable Object component) {
    return (ListItemBinding)bind(component, view, com.erik.android.androidlean.R.layout.list_item);
  }
}

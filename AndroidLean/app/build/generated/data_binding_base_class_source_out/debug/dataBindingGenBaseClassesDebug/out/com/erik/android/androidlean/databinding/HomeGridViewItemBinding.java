package com.erik.android.androidlean.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.erik.android.androidlean.bean.ClassBean;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class HomeGridViewItemBinding extends ViewDataBinding {
  @NonNull
  public final ConstraintLayout clLayout;

  @NonNull
  public final ImageView ivIcon;

  @NonNull
  public final TextView tvTitle;

  @Bindable
  protected ClassBean mBean;

  protected HomeGridViewItemBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ConstraintLayout clLayout, ImageView ivIcon, TextView tvTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.clLayout = clLayout;
    this.ivIcon = ivIcon;
    this.tvTitle = tvTitle;
  }

  public abstract void setBean(@Nullable ClassBean bean);

  @Nullable
  public ClassBean getBean() {
    return mBean;
  }

  @NonNull
  public static HomeGridViewItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.home_grid_view_item, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static HomeGridViewItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<HomeGridViewItemBinding>inflateInternal(inflater, com.erik.android.androidlean.R.layout.home_grid_view_item, root, attachToRoot, component);
  }

  @NonNull
  public static HomeGridViewItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.home_grid_view_item, null, false, component)
   */
  @NonNull
  @Deprecated
  public static HomeGridViewItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<HomeGridViewItemBinding>inflateInternal(inflater, com.erik.android.androidlean.R.layout.home_grid_view_item, null, false, component);
  }

  public static HomeGridViewItemBinding bind(@NonNull View view) {
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
  public static HomeGridViewItemBinding bind(@NonNull View view, @Nullable Object component) {
    return (HomeGridViewItemBinding)bind(component, view, com.erik.android.androidlean.R.layout.home_grid_view_item);
  }
}

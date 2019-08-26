package com.erik.android.androidlean.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.erik.android.androidlean.bean.LiveBean;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class HomeLiveItemBinding extends ViewDataBinding {
  @NonNull
  public final Button btnAppoint;

  @NonNull
  public final ImageView ivHead;

  @NonNull
  public final TextView tvSubtitle;

  @NonNull
  public final TextView tvTitle;

  @Bindable
  protected LiveBean mLive;

  protected HomeLiveItemBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button btnAppoint, ImageView ivHead, TextView tvSubtitle, TextView tvTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnAppoint = btnAppoint;
    this.ivHead = ivHead;
    this.tvSubtitle = tvSubtitle;
    this.tvTitle = tvTitle;
  }

  public abstract void setLive(@Nullable LiveBean live);

  @Nullable
  public LiveBean getLive() {
    return mLive;
  }

  @NonNull
  public static HomeLiveItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.home_live_item, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static HomeLiveItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<HomeLiveItemBinding>inflateInternal(inflater, com.erik.android.androidlean.R.layout.home_live_item, root, attachToRoot, component);
  }

  @NonNull
  public static HomeLiveItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.home_live_item, null, false, component)
   */
  @NonNull
  @Deprecated
  public static HomeLiveItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<HomeLiveItemBinding>inflateInternal(inflater, com.erik.android.androidlean.R.layout.home_live_item, null, false, component);
  }

  public static HomeLiveItemBinding bind(@NonNull View view) {
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
  public static HomeLiveItemBinding bind(@NonNull View view, @Nullable Object component) {
    return (HomeLiveItemBinding)bind(component, view, com.erik.android.androidlean.R.layout.home_live_item);
  }
}

package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.erik.android.androidlean.activity.ChangeWallPagerActivity;
import com.erik.android.androidlean.activity.DetailActivity;
import com.erik.android.androidlean.activity.DialogActivity;
import com.erik.android.androidlean.activity.DownloadActivity;
import com.erik.android.androidlean.activity.DrawBoardActivity;
import com.erik.android.androidlean.activity.ImageActivity;
import com.erik.android.androidlean.activity.LargerImageActivity;
import com.erik.android.androidlean.activity.LocationActivity;
import com.erik.android.androidlean.activity.LoginActivity;
import com.erik.android.androidlean.activity.MatrixActivity;
import com.erik.android.androidlean.activity.PreforenceActivity;
import com.erik.android.androidlean.activity.QRCodeActivity;
import com.erik.android.androidlean.activity.VolumeActivity;
import com.erik.android.androidlean.activity.WebViewActivity;
import java.lang.Override;
import java.lang.String;
import java.util.Map;

/**
 * DO NOT EDIT THIS FILE!!! IT WAS GENERATED BY AROUTER. */
public class ARouter$$Group$$detail implements IRouteGroup {
  @Override
  public void loadInto(Map<String, RouteMeta> atlas) {
    atlas.put("/detail/content", RouteMeta.build(RouteType.ACTIVITY, DetailActivity.class, "/detail/content", "detail", new java.util.HashMap<String, Integer>(){{put("name", 8); put("user", 8); }}, -1, -2147483648));
    atlas.put("/detail/dialog", RouteMeta.build(RouteType.ACTIVITY, DialogActivity.class, "/detail/dialog", "detail", new java.util.HashMap<String, Integer>(){{put("name", 8); }}, -1, -2147483648));
    atlas.put("/detail/download", RouteMeta.build(RouteType.ACTIVITY, DownloadActivity.class, "/detail/download", "detail", new java.util.HashMap<String, Integer>(){{put("name", 8); }}, -1, -2147483648));
    atlas.put("/detail/drawboard", RouteMeta.build(RouteType.ACTIVITY, DrawBoardActivity.class, "/detail/drawboard", "detail", new java.util.HashMap<String, Integer>(){{put("name", 8); }}, -1, -2147483648));
    atlas.put("/detail/imageview", RouteMeta.build(RouteType.ACTIVITY, ImageActivity.class, "/detail/imageview", "detail", new java.util.HashMap<String, Integer>(){{put("name", 8); }}, -1, -2147483648));
    atlas.put("/detail/location", RouteMeta.build(RouteType.ACTIVITY, LocationActivity.class, "/detail/location", "detail", new java.util.HashMap<String, Integer>(){{put("name", 8); }}, -1, -2147483648));
    atlas.put("/detail/login", RouteMeta.build(RouteType.ACTIVITY, LoginActivity.class, "/detail/login", "detail", new java.util.HashMap<String, Integer>(){{put("name", 8); }}, -1, -2147483648));
    atlas.put("/detail/matrixcolor", RouteMeta.build(RouteType.ACTIVITY, MatrixActivity.class, "/detail/matrixcolor", "detail", new java.util.HashMap<String, Integer>(){{put("name", 8); }}, -1, -2147483648));
    atlas.put("/detail/preforence", RouteMeta.build(RouteType.ACTIVITY, PreforenceActivity.class, "/detail/preforence", "detail", new java.util.HashMap<String, Integer>(){{put("name", 8); }}, -1, -2147483648));
    atlas.put("/detail/qrcode", RouteMeta.build(RouteType.ACTIVITY, QRCodeActivity.class, "/detail/qrcode", "detail", new java.util.HashMap<String, Integer>(){{put("name", 8); }}, -1, -2147483648));
    atlas.put("/detail/viewlargerimage", RouteMeta.build(RouteType.ACTIVITY, LargerImageActivity.class, "/detail/viewlargerimage", "detail", new java.util.HashMap<String, Integer>(){{put("name", 8); }}, -1, -2147483648));
    atlas.put("/detail/volume", RouteMeta.build(RouteType.ACTIVITY, VolumeActivity.class, "/detail/volume", "detail", new java.util.HashMap<String, Integer>(){{put("name", 8); }}, -1, -2147483648));
    atlas.put("/detail/wallpapper", RouteMeta.build(RouteType.ACTIVITY, ChangeWallPagerActivity.class, "/detail/wallpapper", "detail", new java.util.HashMap<String, Integer>(){{put("name", 8); }}, -1, -2147483648));
    atlas.put("/detail/webview", RouteMeta.build(RouteType.ACTIVITY, WebViewActivity.class, "/detail/webview", "detail", new java.util.HashMap<String, Integer>(){{put("name", 8); }}, -1, -2147483648));
  }
}

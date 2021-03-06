package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import com.erik.android.androidlean.ohter.DegradeServiceImpl;
import com.erik.android.androidlean.ohter.HelloServiceImpl;
import java.lang.Override;
import java.lang.String;
import java.util.Map;

/**
 * DO NOT EDIT THIS FILE!!! IT WAS GENERATED BY AROUTER. */
public class ARouter$$Providers$$app implements IProviderGroup {
  @Override
  public void loadInto(Map<String, RouteMeta> providers) {
    providers.put("com.erik.android.androidlean.ohter.HelloService", RouteMeta.build(RouteType.PROVIDER, HelloServiceImpl.class, "/helloService/hello", "helloService", null, -1, -2147483648));
    providers.put("com.alibaba.android.arouter.facade.service.DegradeService", RouteMeta.build(RouteType.PROVIDER, DegradeServiceImpl.class, "/degrade/service", "degrade", null, -1, -2147483648));
  }
}

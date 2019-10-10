package com.erik.android.androidlean;

import android.databinding.DataBinderMapper;
import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import com.erik.android.androidlean.databinding.ActivityDetailBindingImpl;
import com.erik.android.androidlean.databinding.FragmentNewlistBindingImpl;
import com.erik.android.androidlean.databinding.HomeGridViewItemBindingImpl;
import com.erik.android.androidlean.databinding.HomeLiveItemBindingImpl;
import com.erik.android.androidlean.databinding.ListItemBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYDETAIL = 1;

  private static final int LAYOUT_FRAGMENTNEWLIST = 2;

  private static final int LAYOUT_HOMEGRIDVIEWITEM = 3;

  private static final int LAYOUT_HOMELIVEITEM = 4;

  private static final int LAYOUT_LISTITEM = 5;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(5);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.erik.android.androidlean.R.layout.activity_detail, LAYOUT_ACTIVITYDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.erik.android.androidlean.R.layout.fragment_newlist, LAYOUT_FRAGMENTNEWLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.erik.android.androidlean.R.layout.home_grid_view_item, LAYOUT_HOMEGRIDVIEWITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.erik.android.androidlean.R.layout.home_live_item, LAYOUT_HOMELIVEITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.erik.android.androidlean.R.layout.list_item, LAYOUT_LISTITEM);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYDETAIL: {
          if ("layout/activity_detail_0".equals(tag)) {
            return new ActivityDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTNEWLIST: {
          if ("layout/fragment_newlist_0".equals(tag)) {
            return new FragmentNewlistBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_newlist is invalid. Received: " + tag);
        }
        case  LAYOUT_HOMEGRIDVIEWITEM: {
          if ("layout/home_grid_view_item_0".equals(tag)) {
            return new HomeGridViewItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for home_grid_view_item is invalid. Received: " + tag);
        }
        case  LAYOUT_HOMELIVEITEM: {
          if ("layout/home_live_item_0".equals(tag)) {
            return new HomeLiveItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for home_live_item is invalid. Received: " + tag);
        }
        case  LAYOUT_LISTITEM: {
          if ("layout/list_item_0".equals(tag)) {
            return new ListItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for list_item is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new com.android.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(11);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "password");
      sKeys.put(2, "headimg");
      sKeys.put(3, "sex");
      sKeys.put(4, "id");
      sKeys.put(5, "user");
      sKeys.put(6, "live");
      sKeys.put(7, "bean");
      sKeys.put(8, "age");
      sKeys.put(9, "username");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(5);

    static {
      sKeys.put("layout/activity_detail_0", com.erik.android.androidlean.R.layout.activity_detail);
      sKeys.put("layout/fragment_newlist_0", com.erik.android.androidlean.R.layout.fragment_newlist);
      sKeys.put("layout/home_grid_view_item_0", com.erik.android.androidlean.R.layout.home_grid_view_item);
      sKeys.put("layout/home_live_item_0", com.erik.android.androidlean.R.layout.home_live_item);
      sKeys.put("layout/list_item_0", com.erik.android.androidlean.R.layout.list_item);
    }
  }
}

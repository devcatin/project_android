package android.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.erik.android.androidlean.DataBinderMapperImpl());
  }
}

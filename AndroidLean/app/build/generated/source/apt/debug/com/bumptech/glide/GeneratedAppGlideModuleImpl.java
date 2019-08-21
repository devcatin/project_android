package com.bumptech.glide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import cc.shinichi.library.glide.sunfusheng.progress.ProgressLibraryGlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule;
import com.erik.android.androidlean.ohter.TestAppGlideModule;
import java.lang.Class;
import java.lang.Override;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.Set;

@SuppressWarnings("deprecation")
final class GeneratedAppGlideModuleImpl extends GeneratedAppGlideModule {
  private final TestAppGlideModule appGlideModule;

  GeneratedAppGlideModuleImpl() {
    appGlideModule = new TestAppGlideModule();
    if (Log.isLoggable("Glide", Log.DEBUG)) {
      Log.d("Glide", "Discovered AppGlideModule from annotation: com.erik.android.androidlean.ohter.TestAppGlideModule");
      Log.d("Glide", "Discovered LibraryGlideModule from annotation: cc.shinichi.library.glide.sunfusheng.progress.ProgressLibraryGlideModule");
      Log.d("Glide", "Discovered LibraryGlideModule from annotation: com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule");
    }
  }

  @Override
  public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
    appGlideModule.applyOptions(context, builder);
  }

  @Override
  public void registerComponents(@NonNull Context context, @NonNull Glide glide,
      @NonNull Registry registry) {
    new ProgressLibraryGlideModule().registerComponents(context, glide, registry);
    new OkHttpLibraryGlideModule().registerComponents(context, glide, registry);
    appGlideModule.registerComponents(context, glide, registry);
  }

  @Override
  public boolean isManifestParsingEnabled() {
    return appGlideModule.isManifestParsingEnabled();
  }

  @Override
  @NonNull
  public Set<Class<?>> getExcludedModuleClasses() {
    return Collections.emptySet();
  }

  @Override
  @NonNull
  GeneratedRequestManagerFactory getRequestManagerFactory() {
    return new GeneratedRequestManagerFactory();
  }
}

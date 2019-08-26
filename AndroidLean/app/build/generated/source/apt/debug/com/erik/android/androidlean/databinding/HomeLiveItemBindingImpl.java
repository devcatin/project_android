package com.erik.android.androidlean.databinding;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HomeLiveItemBindingImpl extends HomeLiveItemBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.btn_appoint, 4);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HomeLiveItemBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private HomeLiveItemBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[4]
            , (android.widget.ImageView) bindings[1]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[2]
            );
        this.ivHead.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvSubtitle.setTag(null);
        this.tvTitle.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.live == variableId) {
            setLive((com.erik.android.androidlean.bean.LiveBean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setLive(@Nullable com.erik.android.androidlean.bean.LiveBean Live) {
        this.mLive = Live;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.live);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String liveHeadimg = null;
        java.lang.String liveSubtitle = null;
        com.erik.android.androidlean.bean.LiveBean live = mLive;
        java.lang.String liveTitle = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (live != null) {
                    // read live.headimg
                    liveHeadimg = live.getHeadimg();
                    // read live.subtitle
                    liveSubtitle = live.getSubtitle();
                    // read live.title
                    liveTitle = live.getTitle();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.erik.android.androidlean.adapter.GridViewAdapter.setImageResource(this.ivHead, liveHeadimg);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvSubtitle, liveSubtitle);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle, liveTitle);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): live
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}
package com.erik.android.androidlean.databinding;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityDetailBindingImpl extends ActivityDetailBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.nav_bar, 4);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityDetailBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private ActivityDetailBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[1]
            , (android.widget.ImageView) bindings[3]
            , (com.erik.android.androidlean.view.NavigationBar) bindings[4]
            );
        this.editdetail.setTag(null);
        this.editname.setTag(null);
        this.ivHead.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
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
        if (BR.user == variableId) {
            setUser((com.erik.android.androidlean.bean.UserBean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setUser(@Nullable com.erik.android.androidlean.bean.UserBean User) {
        updateRegistration(0, User);
        this.mUser = User;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.user);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeUser((com.erik.android.androidlean.bean.UserBean) object, fieldId);
        }
        return false;
    }
    private boolean onChangeUser(com.erik.android.androidlean.bean.UserBean User, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.username) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.password) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        else if (fieldId == BR.headimg) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
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
        com.erik.android.androidlean.bean.UserBean user = mUser;
        java.lang.String userUsername = null;
        java.lang.String userPassword = null;
        java.lang.String userHeadimg = null;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x13L) != 0) {

                    if (user != null) {
                        // read user.username
                        userUsername = user.getUsername();
                    }
            }
            if ((dirtyFlags & 0x15L) != 0) {

                    if (user != null) {
                        // read user.password
                        userPassword = user.getPassword();
                    }
            }
            if ((dirtyFlags & 0x19L) != 0) {

                    if (user != null) {
                        // read user.headimg
                        userHeadimg = user.getHeadimg();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x15L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.editdetail, userPassword);
        }
        if ((dirtyFlags & 0x13L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.editname, userUsername);
        }
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            com.erik.android.androidlean.adapter.GridViewAdapter.setImageResource(this.ivHead, userHeadimg);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): user
        flag 1 (0x2L): user.username
        flag 2 (0x3L): user.password
        flag 3 (0x4L): user.headimg
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}
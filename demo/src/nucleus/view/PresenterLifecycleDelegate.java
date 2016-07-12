package nucleus.view;

import nucleus.factory.PresenterFactory;
import nucleus.factory.PresenterStorage;
import nucleus.presenter.Presenter;
import android.os.Bundle;
import android.os.Parcelable;

/**
 * This class adopts a View lifecycle to the Presenter`s lifecycle.
 *
 * @param <P> a type of the presenter.
 */
public final class PresenterLifecycleDelegate<P extends Presenter> {

    private static final String PRESENTER_KEY = "presenter";
    private static final String PRESENTER_ID_KEY = "presenter_id";

    private PresenterFactory<P> presenterFactory;
    private P presenter;
    private Bundle bundle;

    public PresenterLifecycleDelegate(PresenterFactory<P> presenterFactory) {
        this.presenterFactory = presenterFactory;
    }

    /**
     * {@link ViewWithPresenter#getPresenterFactory()}
     */
    
    public PresenterFactory<P> getPresenterFactory() {
        return presenterFactory;
    }

    /**
     * {@link ViewWithPresenter#setPresenterFactory(PresenterFactory)}
     */
    public void setPresenterFactory(PresenterFactory<P> presenterFactory) {
        if (presenter != null)
            throw new IllegalArgumentException("setPresenterFactory() should be called before onResume()");
        this.presenterFactory = presenterFactory;
    }

    /**
     * {@link ViewWithPresenter#getPresenter()}
     */
    public P getPresenter() {
        if (presenterFactory != null) {
            if (presenter == null && bundle != null){
                presenter = PresenterStorage.INSTANCE.getPresenter(bundle.getString(PRESENTER_ID_KEY));
            }

            if (presenter == null) {
                presenter = presenterFactory.createPresenter();
                PresenterStorage.INSTANCE.add(presenter);
                presenter.create(bundle == null ? null : bundle.getBundle(PRESENTER_KEY));
            }
            bundle = null;
        }
        return presenter;
    }

    /**
     * {@link android.app.Activity#onSaveInstanceState(Bundle)}, {@link android.app.Fragment#onSaveInstanceState(Bundle)}, {@link android.view.View#onSaveInstanceState()}.
     */
    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        getPresenter();
        if (presenter != null) {
            Bundle presenterBundle = new Bundle();
            presenter.save(presenterBundle);
            bundle.putBundle(PRESENTER_KEY, presenterBundle);
            bundle.putString(PRESENTER_ID_KEY, PresenterStorage.INSTANCE.getId(presenter));
        }
        return bundle;
    }

    /**
     * {@link android.app.Activity#onCreate(Bundle)}, {@link android.app.Fragment#onCreate(Bundle)}, {@link android.view.View#onRestoreInstanceState(Parcelable)}.
     */
    public void onRestoreInstanceState(Bundle presenterState) {
        if (presenter != null)
            throw new IllegalArgumentException("onRestoreInstanceState() should be called before onResume()");
        this.bundle = ParcelFn.unmarshall(ParcelFn.marshall(presenterState));
    }

    /**
     * {@link android.app.Activity#onResume()}, {@link android.app.Fragment#onResume()}, {@link android.view.View#onAttachedToWindow()}
     */
    public void onResume(Object view) {
        getPresenter();
        if (presenter != null)
            //noinspection unchecked
            presenter.takeView(view);
    }

    /**
     * {@link android.app.Activity#onPause()}, {@link android.app.Fragment#onPause()}, {@link android.view.View#onDetachedFromWindow()}
     */
    public void onPause(boolean destroy) {
        if (presenter != null) {
            presenter.dropView();
            if (destroy) {
                presenter.destroy();
                presenter = null;
            }
        }
    }
}

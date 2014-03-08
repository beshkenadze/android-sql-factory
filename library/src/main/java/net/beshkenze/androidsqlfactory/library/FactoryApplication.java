package net.beshkenze.androidsqlfactory.library;

import android.app.Application;
import timber.log.Timber;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class FactoryApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        Timber.plant(new Timber.DebugTree());
    }
}

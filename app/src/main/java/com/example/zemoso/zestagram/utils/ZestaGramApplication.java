package com.example.zemoso.zestagram.utils;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by zemoso on 19/9/17.
 */

public class ZestaGramApplication extends Application {

      public static final String TAG = ZestaGramApplication.class.getSimpleName();

      private static ZestaGramApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("contactinfo.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

}

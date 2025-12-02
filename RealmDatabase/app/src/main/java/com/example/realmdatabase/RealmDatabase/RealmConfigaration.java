package com.example.realmdatabase.RealmDatabase;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmConfigaration extends Application {

    // on create method
    @Override
    public void onCreate() {
        super.onCreate();

        // now making the realm configuration
        // first initializing the Realm Database
        Realm.init(this);

        // Setting up a default configuration
        RealmConfiguration config = new RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}

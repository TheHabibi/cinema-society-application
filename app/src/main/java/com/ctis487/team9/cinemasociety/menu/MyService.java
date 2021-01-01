package com.ctis487.team9.cinemasociety.menu;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import com.ctis487.team9.cinemasociety.MainActivity;
import com.ctis487.team9.cinemasociety.R;

/*
https://developer.android.com/guide/topics/manifest/service-element
https://www.vogella.com/tutorials/AndroidServices/article.html

- Android service is a component that is used to perform operations in the background
such as long running operations, playing music, handle network transacti-ions, downloading file,
interacting with content providers, checking for new data, data processing etc.

- Service doesn't have user interface
- Service runs in the background indefinitely even if applications is terminated.
- Service has higher piority than inactive or invisible activities. It is less likely that Android operating
system terminates them.
- Service can be reconfigured to be restarted if they get terminated by operating system when resources sufficent again.
- Service use the same thread with application therefore, you need to use asynchronous processing
in the service to perform intensive task in the background.
- Service had to be dined in the manifest file
- Android provides and runs predefined services and every android app can use them.
- Services have their own life cycle independent of the activity or fragment that they were created in even app not visible to user
- operates on the same thread that they're instantiated on


<service
    android:name=".MyService"
    android:enabled=["true" | "false"]
    android:exported=["true" | "false"]
    android:icon="drawable resource"
    android:label="string resource"
    ...
    ...
   >
</service>

android:enabled=["true" | "false"]
Whether or not the service can be instantiated by the system — "true" if it can be, and "false" if not.
The default value is "true".

android:exported=["true" | "false"]
Whether or not components of other applications can invoke the service or interact with it — "true"
if they can, and "false" if not. When the value is "false", only components of the same application or
applications with the same user ID can start the service or bind to it.

android:icon="drawable resource"
An icon representing the service.f it is not set, the icon specified for the application as a whole is
used instead

android:label
A name for the service that can be displayed to users. If this attribute is not set,
the label set for the application is used instead


*/
public class MyService extends Service {
    boolean isPlaying= false;

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        MenuActivity.ring= MediaPlayer.create(this, R.raw.lacosta);
        MenuActivity.ring.start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MenuActivity.ring.stop();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
    }
}
/*
Service.START_STICKY
Service is restarted if it gets terminated. Intent data passed to the onStartCommand method is null.
Used for services which manages their own state and do not depend on the Intent data.

Service.START_NOT_STICKY
Service is not restarted. Used for services which are periodically triggered anyway.
The service is only restarted if the runtime has pending startService() calls since the service termination.

Service.START_REDELIVER_INTENT
Similar to Service.START_STICKY but the original Intent is re-delivered to the onStartCommand method.

 */
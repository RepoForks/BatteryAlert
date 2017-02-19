package james.batteryalert;

import android.app.Application;
import android.content.Intent;

public class BatteryAlert extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, BatteryService.class));
    }
}

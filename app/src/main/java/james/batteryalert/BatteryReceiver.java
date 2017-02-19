package james.batteryalert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class BatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int alertValue = PreferenceManager.getDefaultSharedPreferences(context).getInt(MainActivity.KEY_BATTERY_PERCENT, 20);

        int status = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        float level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        float scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || plugged == BatteryManager.BATTERY_PLUGGED_USB || plugged == BatteryManager.BATTERY_PLUGGED_AC;
        float value = (level * 100f) / scale;

        if (!isCharging && (int) value == alertValue) {
            ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(3000);
            Toast.makeText(context, String.format(context.getString(R.string.msg_low_battery), (int) value), Toast.LENGTH_LONG).show();
        }
    }

}

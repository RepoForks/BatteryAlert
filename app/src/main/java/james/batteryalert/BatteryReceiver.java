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

        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float value = (level / (float) scale) * 100;

        if (status != BatteryManager.BATTERY_STATUS_CHARGING && status != BatteryManager.BATTERY_STATUS_FULL && (int) value == alertValue) {
            ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(3000);
            Toast.makeText(context, String.format(context.getString(R.string.msg_low_battery), (int) value), Toast.LENGTH_LONG).show();
        }
    }
}

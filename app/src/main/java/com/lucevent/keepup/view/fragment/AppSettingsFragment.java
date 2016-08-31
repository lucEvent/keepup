package com.lucevent.keepup.view.fragment;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;

import com.lucevent.keepup.AppSettings;
import com.lucevent.keepup.Main;
import com.lucevent.keepup.ProSettings;
import com.lucevent.keepup.kernel.AppCode;
import com.lucevent.keepup.R;

import java.util.Random;

public class AppSettingsFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.app_preferences);

    }

    @Override
    public void onResume()
    {
        super.onResume();
        getActivity().setTitle(R.string.settings);
        setUpPreferenceSummaries(0xfff);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        if (key.equals(AppSettings.PREF_PRO_CODE_KEY)) {

            String code = sharedPreferences.getString(AppSettings.PREF_PRO_CODE_KEY, "");
            if (code.isEmpty()) return;

            int resIdMsg = ProSettings.checkProCode(code);

            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            if (resIdMsg == R.string.msg_invalid_code) {
                dialog.setMessage(resIdMsg)
                        .setPositiveButton(R.string.ok, null)
                        .show();
            } else {
                dialog.setTitle(resIdMsg)
                        .setMessage(R.string.msg_app_must_restart_to_apply_changes)
                        .setPositiveButton(R.string.restart, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                restartApp();
                            }
                        })
                        .show();
            }
            ((EditTextPreference) findPreference(AppSettings.PREF_PRO_CODE_KEY)).setText("");

        }
    }

    private void setUpPreferenceSummaries(int preferencesMask)
    {
      /*  if ((preferencesMask & PREF_MASK_KEEP_NEWS) != 0) {
            ListPreference p = (ListPreference) findPreference(AppSettings.PREF_KEEP_NEWS_KEY);

            CharSequence currentValue = p.getValue();
            int currentPosition = 0;
            for (CharSequence v : p.getEntryValues()) {
                if (v.equals(currentValue))
                    break;
                currentPosition++;
            }
            p.setSummary(p.getEntries()[currentPosition]);
        }*/
    }


    private void restartApp()
    {
        Intent intent = new Intent(getActivity(), Main.class);
        intent.putExtra(AppCode.RESTART, AppCode.RESTART);
        int requestCode = new Random().nextInt();
        PendingIntent mPendingIntent = PendingIntent.getActivity(getActivity(), requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);
    }

}

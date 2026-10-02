package app.morphe.extension.tiktok.settings.preference.categories;

import android.content.Context;
import android.preference.PreferenceScreen;

import app.morphe.extension.tiktok.settings.Settings;
import app.morphe.extension.tiktok.settings.SettingsStatus;
import app.morphe.extension.tiktok.settings.preference.TogglePreference;

@SuppressWarnings("deprecation")
public class IncognitoPreferenceCategory extends ConditionalPreferenceCategory {
    public IncognitoPreferenceCategory(Context context, PreferenceScreen screen) {
        super(context, screen);
        setTitle("Incognito mode");
    }

    @Override
    public boolean getSettingsStatus() {
        return SettingsStatus.incognitoEnabled;
    }

    @Override
    public void addPreferences(Context context) {
        addPreference(new TogglePreference(
                context,
                "Enable incognito",
                "Watch TikTok without feeding the algorithm. Events are not sent while enabled.",
                Settings.INCOGNITO
        ));
        addPreference(new TogglePreference(
                context,
                "Lock local data",
                "Require a password to access incognito data (likes, favourites).",
                Settings.INCOGNITO_LOCK
        ));
    }
}

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
        setTitle("Инкогнито");
    }

    @Override
    public boolean getSettingsStatus() {
        return SettingsStatus.incognitoEnabled;
    }

    @Override
    public void addPreferences(Context context) {
        addPreference(new TogglePreference(
                context,
                "Включить инкогнито",
                "Смотри TikTok без следа для алгоритма. События не отправляются, пока режим включён. (Пока заглушка — реальная блокировка появится в следующих версиях.)",
                Settings.INCOGNITO
        ));
        addPreference(new TogglePreference(
                context,
                "Заблокировать локальные данные",
                "Требовать пароль для доступа к данным инкогнито (лайки, избранное). (Пока заглушка.)",
                Settings.INCOGNITO_LOCK
        ));
    }
}

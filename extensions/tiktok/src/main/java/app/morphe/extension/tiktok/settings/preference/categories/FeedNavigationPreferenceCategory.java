package app.morphe.extension.tiktok.settings.preference.categories;

import android.content.Context;
import android.preference.PreferenceScreen;

import app.morphe.extension.tiktok.settings.Settings;
import app.morphe.extension.tiktok.settings.SettingsStatus;
import app.morphe.extension.tiktok.settings.preference.TabSelectionPreference;
import app.morphe.extension.tiktok.settings.preference.TogglePreference;

@SuppressWarnings("deprecation")
public class FeedNavigationPreferenceCategory extends ConditionalPreferenceCategory {
    public FeedNavigationPreferenceCategory(Context context, PreferenceScreen screen) {
        super(context, screen);
        setTitle("Навигация");
    }

    @Override
    public boolean getSettingsStatus() {
        return SettingsStatus.feedNavigationEnabled;
    }

    @Override
    public void addPreferences(Context context) {
        addPreference(group(context, "Вкладки ленты"));
        addPreference(new TogglePreference(
                context,
                "Фильтровать вкладки ленты",
                "Выбери, какие загруженные вкладки TikTok должны оставаться видимыми.",
                Settings.FEED_NAVIGATION
        ));
        addPreference(new TabSelectionPreference(
                context,
                Settings.FEED_NAVIGATION_TABS
        ));
        addPreference(new TogglePreference(
                context,
                "Блокировать новые вкладки TikTok",
                "Скрывать вкладки, которые TikTok добавляет позже, пока ты их не разрешишь.",
                Settings.FEED_NAVIGATION_BLOCK_NEW_TABS
        ));

        addPreference(group(context, "Нижняя навигация"));
        addPreference(new TogglePreference(
                context,
                "Фильтровать нижние вкладки",
                "Выбери, какие загруженные нижние вкладки TikTok должны оставаться видимыми.",
                Settings.BOTTOM_NAVIGATION
        ));
        addPreference(new TabSelectionPreference(
                context,
                Settings.BOTTOM_NAVIGATION_TABS,
                true
        ));
        addPreference(new TogglePreference(
                context,
                "Блокировать новые нижние вкладки",
                "Скрывать нижние вкладки, которые TikTok добавляет позже, пока ты их не разрешишь.",
                Settings.BOTTOM_NAVIGATION_BLOCK_NEW_TABS
        ));

        addPreference(group(context, "Другая навигация"));
        addPreference(new TogglePreference(
                context,
                "Скрыть Tako AI",
                "Скрывает пузырь Tako AI над кнопкой профиля.",
                Settings.HIDE_TAKO_AI
        ));
    }
}

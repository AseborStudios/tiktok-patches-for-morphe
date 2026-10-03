/*
 * Forked from:
 * https://github.com/ReVanced/revanced-patches/blob/377d4e15016296b45d809697f7f69bce74badd3a/extensions/tiktok/src/main/java/app/revanced/extension/tiktok/settings/preference/categories/FeedFilterPreferenceCategory.java
 */

package app.morphe.extension.tiktok.settings.preference.categories;

import android.content.Context;
import android.preference.PreferenceScreen;

import app.morphe.extension.tiktok.settings.Settings;
import app.morphe.extension.tiktok.settings.SettingsStatus;
import app.morphe.extension.tiktok.settings.preference.RangeValuePreference;
import app.morphe.extension.tiktok.settings.preference.TogglePreference;

@SuppressWarnings("deprecation")
public class FeedFilterPreferenceCategory extends ConditionalPreferenceCategory {
    public FeedFilterPreferenceCategory(Context context, PreferenceScreen screen) {
        super(context, screen);
        setTitle("Лента");
    }

    @Override
    public boolean getSettingsStatus() {
        return SettingsStatus.feedFilterEnabled || SettingsStatus.hideAiContentEnabled
                || SettingsStatus.hideFypSlopEnabled;
    }

    @Override
    public void addPreferences(Context context) {
        if (SettingsStatus.feedFilterEnabled) {
            addPreference(group(context, "Типы контента"));
            addPreference(new TogglePreference(
                    context,
                    "Убрать рекламу", "Убирает рекламу из ленты.",
                    Settings.REMOVE_ADS
            ));
            addPreference(new TogglePreference(
                    context,
                    "Скрыть TikTok Shop", "Скрывает магазин TikTok из ленты.",
                    Settings.HIDE_SHOP
            ));
            addPreference(new TogglePreference(
                    context,
                    "Скрыть стримы", "Скрывает прямые эфиры из ленты.",
                    Settings.HIDE_LIVE
            ));
            addPreference(new TogglePreference(
                    context,
                    "Скрыть истории", "Скрывает истории из ленты.",
                    Settings.HIDE_STORY
            ));
            addPreference(new TogglePreference(
                    context,
                    "Скрыть фото-видео", "Скрывает фото-видео из ленты.",
                    Settings.HIDE_IMAGE
            ));
        }
        if (SettingsStatus.hideAiContentEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Скрыть ИИ-контент",
                    "Скрывает посты, помеченные как созданные или изменённые ИИ (TikTok или автором). Непомеченный ИИ-контент может всё ещё появляться.",
                    Settings.HIDE_AI_CONTENT
            ));
        }
        if (SettingsStatus.hideFypSlopEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Скрыть мусор из рекомендаций",
                    "Скрывает некоторые партии неперсонализированных постов, которые появляются в ленте рекомендаций.",
                    Settings.HIDE_ALTERNATE_FOR_YOU_BATCHES
            ));
        }

        if (SettingsStatus.feedFilterEnabled) {
            addPreference(group(context, "Лимиты популярности"));
            addPreference(new RangeValuePreference(
                    context,
                    "Мин/макс просмотров", "Минимальное или максимальное количество просмотров у видео.",
                    Settings.MIN_MAX_VIEWS
            ));
            addPreference(new RangeValuePreference(
                    context,
                    "Мин/макс лайков", "Минимальное или максимальное количество лайков у видео.",
                    Settings.MIN_MAX_LIKES
            ));

            addPreference(group(context, "Оффлайн-запас"));
            addPreference(new TogglePreference(
                    context,
                    "Фильтровать оффлайн-видео",
                    "Применять общие фильтры контента и популярности к скачанным запасным видео. Отдельно установленные фильтры ИИ и мусора всё равно работают.",
                    Settings.FILTER_OFFLINE_FALLBACK_VIDEOS
            ));
        }
    }
}

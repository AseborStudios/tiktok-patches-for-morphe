/*
 * Copyright 2026 icysymmetra/tiktok-patches-for-morphe contributors
 * https://github.com/icysymmetra/tiktok-patches-for-morphe
 */
package app.morphe.extension.tiktok.settings.preference.categories;

import android.content.Context;
import android.preference.PreferenceScreen;

import app.morphe.extension.tiktok.settings.Settings;
import app.morphe.extension.tiktok.settings.SettingsStatus;
import app.morphe.extension.tiktok.settings.preference.TogglePreference;

@SuppressWarnings("deprecation")
public final class InterfacePreferenceCategory extends ConditionalPreferenceCategory {
    public InterfacePreferenceCategory(Context context, PreferenceScreen screen) {
        super(context, screen);
        setTitle("Интерфейс");
    }

    @Override
    public boolean getSettingsStatus() {
        return SettingsStatus.captchaPopupSuppressionEnabled
                || SettingsStatus.promotionalBannersEnabled
                || SettingsStatus.hideFeedFollowButtonEnabled
                || SettingsStatus.hideFeedSaveButtonEnabled
                || SettingsStatus.hideFeedLiveButtonEnabled
                || SettingsStatus.hideFeedSearchButtonEnabled
                || SettingsStatus.alwaysShowPublishDateEnabled;
    }

    @Override
    public void addPreferences(Context context) {
        if (SettingsStatus.hideFeedFollowButtonEnabled
                || SettingsStatus.hideFeedSaveButtonEnabled
                || SettingsStatus.hideFeedLiveButtonEnabled
                || SettingsStatus.hideFeedSearchButtonEnabled) {
            addPreference(group(context, "Управление лентой"));
        }
        if (SettingsStatus.hideFeedFollowButtonEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Скрыть кнопку подписки",
                    "Скрывает кнопку + подписки под аватаром автора в ленте. Требуется перезапуск.",
                    Settings.HIDE_FEED_FOLLOW_BUTTON
            ));
        }
        if (SettingsStatus.hideFeedSaveButtonEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Скрыть кнопку сохранения",
                    "Скрывает кнопку сохранения/избранного с панели взаимодействия в ленте. Требуется перезапуск.",
                    Settings.HIDE_FEED_SAVE_BUTTON
            ));
        }
        if (SettingsStatus.hideFeedLiveButtonEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Скрыть кнопку LIVE",
                    "Скрывает кнопку LIVE в левом верхнем углу ленты. Требуется перезапуск.",
                    Settings.HIDE_FEED_LIVE_BUTTON
            ));
        }
        if (SettingsStatus.hideFeedSearchButtonEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Скрыть кнопку поиска",
                    "Скрывает кнопку поиска в правом верхнем углу ленты. Требуется перезапуск.",
                    Settings.HIDE_FEED_SEARCH_BUTTON
            ));
        }

        if (SettingsStatus.promotionalBannersEnabled
                || SettingsStatus.captchaPopupSuppressionEnabled) {
            addPreference(group(context, "Промо и диалоги"));
        }
        if (SettingsStatus.promotionalBannersEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Скрыть плавающие промо",
                    "Скрывает плавающие промо-значки, монеты и таймеры на главной.",
                    Settings.HIDE_HOMEPAGE_COIN
            ));
        }
        if (SettingsStatus.captchaPopupSuppressionEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Скрыть CAPTCHA-попапы",
                    "Скрывает диалоги с пазлами при просмотре и в LIVE. Вход и верификация аккаунта остаются доступны.",
                    Settings.HIDE_CAPTCHA_POPUPS
            ));
        }

        if (SettingsStatus.alwaysShowPublishDateEnabled) {
            addPreference(group(context, "Информация о видео"));
            addPreference(new TogglePreference(
                    context,
                    "Всегда показывать дату публикации",
                    "Всегда показывать дату публикации в информации об авторе видео. Требуется перезапуск.",
                    Settings.ALWAYS_SHOW_PUBLISH_DATE
            ));
        }
    }
}

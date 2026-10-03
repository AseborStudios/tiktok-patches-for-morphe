/*
 * Forked from:
 * https://github.com/ReVanced/revanced-patches/blob/377d4e15016296b45d809697f7f69bce74badd3a/extensions/tiktok/src/main/java/app/revanced/extension/tiktok/settings/preference/categories/ExtensionPreferenceCategory.java
 */

package app.morphe.extension.tiktok.settings.preference.categories;

import android.content.Context;
import android.preference.PreferenceScreen;

import app.morphe.extension.shared.settings.BaseSettings;
import app.morphe.extension.tiktok.settings.Settings;
import app.morphe.extension.tiktok.settings.SettingsStatus;
import app.morphe.extension.tiktok.settings.preference.TogglePreference;

@SuppressWarnings("deprecation")
public class ExtensionPreferenceCategory extends ConditionalPreferenceCategory {
    public ExtensionPreferenceCategory(Context context, PreferenceScreen screen) {
        super(context, screen);
        setTitle("Поведение");
    }

    @Override
    public boolean getSettingsStatus() {
        return true;
    }

    @Override
    public void addPreferences(Context context) {
        addPreference(group(context, "Ссылки и отправка"));
        addPreference(new TogglePreference(
                context,
                "Очищать ссылки при отправке",
                "Убирает трекинг-параметры из отправляемых ссылок.",
                BaseSettings.SANITIZE_SHARING_LINKS
        ));
        if (SettingsStatus.externalBrowserEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Открывать внешние ссылки напрямую",
                    "Открывает ссылки на сайты в профиле и историях в системном браузере, а не во встроенном браузере TikTok.",
                    Settings.OPEN_EXTERNAL_LINKS
            ));
        }
        if (SettingsStatus.autoScrollEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Принудительно показать автопрокрутку",
                    "Показывает нативное действие «Автопрокрутка» на подходящих видео в рекомендациях, даже если TikTok скрывает его. TikTok всё равно решает, поддерживает ли видео и экран эту функцию.",
                    Settings.FORCE_SHOW_AUTO_SCROLL
            ));
        }

        addPreference(group(context, "Воспроизведение"));
        addPreference(new TogglePreference(
                context,
                "Показывать полосу прокрутки",
                "Показывает нативную полосу прокрутки на видео, где TikTok обычно её скрывает.",
                Settings.SHOW_SEEKBAR
        ));
        if (SettingsStatus.seekbarThumbnailEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Показывать превью при перемотке",
                    "Показывает миниатюру видео при перетаскивании полосы прокрутки.",
                    Settings.SHOW_SEEKBAR_THUMBNAIL
            ));
        }
        if (SettingsStatus.stopVideoLoopingEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Останавливать видео в конце",
                    "Останавливает видео в конце вместо повторного воспроизведения.",
                    Settings.STOP_VIDEO_LOOPING
            ));
        }
        if (SettingsStatus.resumeVideoAfterScrollEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Продолжать видео после прокрутки",
                    "Продолжает поддерживаемые видео с того места, где ты остановился, когда прокручиваешь к ним обратно.",
                    Settings.RESUME_VIDEO_AFTER_SCROLL
            ));
        }

        if (SettingsStatus.longPressSpeedLockEnabled
                || SettingsStatus.disableLongPressQuickShareEnabled
                || SettingsStatus.disableLongPressRepostEnabled) {
            addPreference(group(context, "Жесты"));
        }
        if (SettingsStatus.longPressSpeedLockEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Удерживай и свайпни для 2x",
                    "Использует нативный жест TikTok (удерживай, свайпни вниз, отпусти) для фиксации скорости 2x.",
                    Settings.ENABLE_LONG_PRESS_SPEED_LOCK
            ));
        }
        if (SettingsStatus.disableLongPressQuickShareEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Отключить быструю отправку",
                    "Убирает быстрое меню отправки при долгом нажатии на «Поделиться».",
                    Settings.DISABLE_LONG_PRESS_QUICK_SHARE
            ));
        }
        if (SettingsStatus.disableLongPressRepostEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Отключить репост по долгому нажатию",
                    "Убирает меню репоста при долгом нажатии на «Нравится».",
                    Settings.DISABLE_LONG_PRESS_REPOST
            ));
        }

        if (SettingsStatus.hideSuggestedAccountsEnabled
                || SettingsStatus.nonPersonalizedSearchEnabled
                || SettingsStatus.liveSearchEnabled) {
            addPreference(group(context, "Обнаружение и поиск"));
        }
        if (SettingsStatus.hideSuggestedAccountsEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Скрыть предлагаемые аккаунты",
                    "Убирает предлагаемые аккаунты из профиля и входящих.",
                    Settings.HIDE_SUGGESTED_ACCOUNTS
            ));
        }
        if (SettingsStatus.nonPersonalizedSearchEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Неперсонализированный поиск",
                    "Использует неперсонализированный поиск TikTok вместо сохранённого выбора аккаунта.",
                    Settings.ENABLE_NON_PERSONALIZED_SEARCH
            ));
        }
        if (SettingsStatus.liveSearchEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Показывать поиск в Live",
                    "Показывает поиск TikTok в разделе Live, где это поддерживается.",
                    Settings.ENABLE_LIVE_SEARCH
            ));
        }
    }
}

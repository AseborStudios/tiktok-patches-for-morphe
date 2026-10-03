/*
 * Forked from:
 * https://github.com/ReVanced/revanced-patches/blob/377d4e15016296b45d809697f7f69bce74badd3a/extensions/tiktok/src/main/java/app/revanced/extension/tiktok/settings/preference/categories/DownloadsPreferenceCategory.java
 */

package app.morphe.extension.tiktok.settings.preference.categories;

import android.content.Context;
import android.preference.PreferenceScreen;

import app.morphe.extension.tiktok.settings.Settings;
import app.morphe.extension.tiktok.settings.SettingsStatus;
import app.morphe.extension.tiktok.settings.preference.DownloadPathPreference;
import app.morphe.extension.tiktok.settings.preference.DownloadQualityPreference;
import app.morphe.extension.tiktok.settings.preference.InputTextPreference;
import app.morphe.extension.tiktok.settings.preference.NumberInputPreference;
import app.morphe.extension.tiktok.settings.preference.TogglePreference;
import app.morphe.extension.tiktok.download.DownloadDestination;
import app.morphe.extension.tiktok.offline.CustomOfflineVideosLimitPatch;

@SuppressWarnings("deprecation")
public class DownloadsPreferenceCategory extends ConditionalPreferenceCategory {
    public DownloadsPreferenceCategory(Context context, PreferenceScreen screen) {
        super(context, screen);
        setTitle("Скачивание");
    }

    @Override
    public boolean getSettingsStatus() {
        return SettingsStatus.downloadEnabled;
    }

    @Override
    public void addPreferences(Context context) {
        addPreference(group(context, "Скачивание видео"));
        addPreference(new DownloadQualityPreference(
                context,
                Settings.DOWNLOAD_VIDEO_QUALITY
        ));
        addPreference(new TogglePreference(
                context,
                "Убрать водяной знак",
                "Применяется к скачиванию видео и фото.",
                Settings.DOWNLOAD_WATERMARK
        ));

        addPreference(group(context, "Пути сохранения"));
        addPreference(new DownloadPathPreference(
                context,
                "Видео",
                Settings.DOWNLOAD_VIDEO_PATH,
                DownloadDestination.Kind.VIDEO
        ));
        addPreference(new DownloadPathPreference(
                context,
                "Фото",
                Settings.DOWNLOAD_PHOTO_PATH,
                DownloadDestination.Kind.PHOTO
        ));
        addPreference(new DownloadPathPreference(
                context,
                "Стикеры",
                Settings.DOWNLOAD_STICKER_PATH,
                DownloadDestination.Kind.STICKER
        ));

        addPreference(group(context, "Имена файлов"));
        addPreference(new InputTextPreference(
                context,
                "Имя видеофайла",
                "Токены: {creator}, {date}, {video_id}, {story_id}. Story ID — это псевдоним ID текущего видео. Расширение файла добавляется автоматически.",
                Settings.DOWNLOAD_VIDEO_FILENAME_TEMPLATE
        ));
        addPreference(new InputTextPreference(
                context,
                "Имя фотофайла",
                "Токены: {creator}, {date}, {video_id}, {story_id}, {index}. Story ID — это псевдоним ID текущего видео. Расширение файла добавляется автоматически.",
                Settings.DOWNLOAD_PHOTO_FILENAME_TEMPLATE
        ));
        addPreference(new InputTextPreference(
                context,
                "Имя медиа из комментариев",
                "Токены: {date}, {media_id}. Работает для стикеров-картинок и стикеров-видео.",
                Settings.DOWNLOAD_COMMENT_MEDIA_FILENAME_TEMPLATE
        ));

        addPreference(group(context, "Оффлайн-просмотр"));
        addPreference(new TogglePreference(
                context,
                "Свои оффлайн-видео",
                "Добавляет свой пункт в меню оффлайн-видео TikTok после перезапуска.",
                Settings.CUSTOM_OFFLINE_VIDEOS
        ));
        addPreference(new NumberInputPreference(
                context,
                "Лимит оффлайн-видео",
                "Выбери от 1 до 1000 видео. Значения вне диапазона заменяются ближайшим допустимым. Перезапусти TikTok после сохранения.",
                Settings.CUSTOM_OFFLINE_VIDEO_LIMIT,
                CustomOfflineVideosLimitPatch.MIN_LIMIT,
                CustomOfflineVideosLimitPatch.MAX_LIMIT
        ));

    }
}

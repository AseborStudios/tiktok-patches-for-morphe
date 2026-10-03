package app.morphe.extension.tiktok.settings.preference.categories;

import android.content.Context;
import android.preference.PreferenceScreen;

import app.morphe.extension.tiktok.settings.Settings;
import app.morphe.extension.tiktok.settings.SettingsStatus;
import app.morphe.extension.tiktok.settings.preference.NumberInputPreference;
import app.morphe.extension.tiktok.settings.preference.TogglePreference;

@SuppressWarnings("deprecation")
public class CommentsPreferenceCategory extends ConditionalPreferenceCategory {
    public CommentsPreferenceCategory(Context context, PreferenceScreen screen) {
        super(context, screen);
        setTitle("Комментарии");
    }

    @Override
    public boolean getSettingsStatus() {
        return SettingsStatus.commentTranslationEnabled
                || SettingsStatus.hideCommentQuickReactionsEnabled
                || SettingsStatus.copyCommentsWithoutUsernameEnabled
                || SettingsStatus.commentSortControlsEnabled
                || SettingsStatus.foldableSplitViewEnabled;
    }

    @Override
    public void addPreferences(Context context) {
        if (SettingsStatus.commentSortControlsEnabled) {
            addPreference(group(context, "Сортировка комментариев"));
            addPreference(new TogglePreference(
                    context,
                    "Принудительно показать сортировку",
                    "Открывает нативное меню сортировки TikTok вместо того, чтобы ждать развёртывания. "
                            + "Включает сортировку по «горячему» и по времени. «Медиа» и «Автор» "
                            + "появляются только если TikTok сообщает о совпадающих комментариях.",
                    Settings.COMMENT_SORT_FORCE_SHOW
            ));
        }

        if (SettingsStatus.commentTranslationEnabled) {
            addPreference(group(context, "Перевод"));
            addPreference(new TogglePreference(
                    context,
                    "Автоперевод комментариев",
                    "Автоматически переводит загруженные партии комментариев через систему перевода TikTok.",
                    Settings.COMMENT_BATCH_TRANSLATION
            ));
        }

        if (SettingsStatus.hideCommentQuickReactionsEnabled
                || SettingsStatus.copyCommentsWithoutUsernameEnabled) {
            addPreference(group(context, "Действия с комментариями"));
        }
        if (SettingsStatus.hideCommentQuickReactionsEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Скрыть быстрые реакции",
                    "Скрывает строку быстрых эмодзи в поддерживаемых полях ввода комментариев.",
                    Settings.HIDE_COMMENT_QUICK_REACTIONS
            ));
        }
        if (SettingsStatus.copyCommentsWithoutUsernameEnabled) {
            addPreference(new TogglePreference(
                    context,
                    "Копировать без имени пользователя",
                    "Копирует только текст комментария при использовании действия «Копировать комментарий».",
                    Settings.COPY_COMMENTS_WITHOUT_USERNAME
            ));
        }

        if (SettingsStatus.foldableSplitViewEnabled) {
            addPreference(group(context, "Раскладка для больших экранов"));
            addPreference(new TogglePreference(
                    context,
                    "Раздельный вид видео/комментариев",
                    "Показывает комментарии рядом с видео, а не снизу, когда ширина экрана не меньше "
                            + "порога ниже. Предназначено для складных устройств, которые TikTok не "
                            + "считает планшетами. Требуется перезапуск.",
                    Settings.FOLDABLE_SPLIT_VIEW
            ));
            addPreference(new NumberInputPreference(
                    context,
                    "Порог ширины раздельного вида (dp)",
                    "Минимальная ширина экрана в dp, при которой включается раздельный вид. Экспортируй "
                            + "диагностический отчёт после открытия комментариев к видео, чтобы увидеть "
                            + "измеренную ширину устройства. Требуется перезапуск.",
                    Settings.FOLDABLE_SPLIT_VIEW_MIN_WIDTH_DP,
                    200,
                    1200
            ));
        }
    }
}

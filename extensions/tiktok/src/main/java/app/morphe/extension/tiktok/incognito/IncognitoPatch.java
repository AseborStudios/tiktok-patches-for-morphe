package app.morphe.extension.tiktok.incognito;

import app.morphe.extension.shared.Logger;
import app.morphe.extension.tiktok.settings.Settings;

@SuppressWarnings("unused")
public class IncognitoPatch {
    
    /**
     * Проверка, включён ли режим инкогнито.
     */
    public static boolean isIncognitoEnabled() {
        return Settings.INCOGNITO.get();
    }
    
    /**
     * Вызывается вместо метода отправки события аналитики.
     * Если инкогнито включён — просто ничего не делаем.
     */
    public static void logEvent(String eventName) {
        if (Settings.INCOGNITO.get()) {
            Logger.printDebug(() -> "Incognito: blocked event: " + eventName);
            return;
        }
        Logger.printDebug(() -> "Incognito: event passed: " + eventName);
    }
}

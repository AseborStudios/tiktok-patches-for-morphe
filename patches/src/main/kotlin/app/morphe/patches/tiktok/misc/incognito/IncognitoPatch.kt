package app.morphe.patches.tiktok.misc.incognito

import app.morphe.patcher.extensions.InstructionExtensions.addInstruction
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.patches.tiktok.misc.extension.sharedExtensionPatch
import app.morphe.patches.tiktok.misc.settings.SettingsStatusLoadFingerprint
import app.morphe.patches.tiktok.misc.settings.settingsPatch

@Suppress("unused")
val incognitoPatch = bytecodePatch(
    name = "Incognito mode",
    description = "Watch TikTok without feeding the algorithm. Events are not sent while enabled.",
    default = false,
) {
    dependsOn(
        sharedExtensionPatch,
        settingsPatch,
    )

    compatibleWith(*AppCompatibilities.tiktok4623())

    execute {
        // Пока просто регистрируем патч в SettingsStatus.
        // Логика блокировки будет добавлена позже.
        SettingsStatusLoadFingerprint.method.addInstruction(
            0,
            "invoke-static {}, Lapp/morphe/extension/tiktok/settings/SettingsStatus;->enableIncognito()V",
        )
    }
}

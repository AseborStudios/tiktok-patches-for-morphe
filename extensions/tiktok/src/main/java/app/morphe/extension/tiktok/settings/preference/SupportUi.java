package app.morphe.extension.tiktok.settings.preference;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

final class SupportUi {
    private SupportUi() {
    }

    static void openSupport() {
        // Отключено в Vertex
    }

    static View createPill(Context context) {
        View view = new View(context);
        view.setVisibility(View.GONE);
        return view;
    }

    static Drawable createHeartDrawable() {
        return null;
    }

    static Drawable createRowBackground(Context context) {
        return null;
    }
}

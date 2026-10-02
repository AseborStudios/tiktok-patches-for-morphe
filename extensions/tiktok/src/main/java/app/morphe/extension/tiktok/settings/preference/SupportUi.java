@Override
        public int getIntrinsicHeight() {
            return 24;
        }
    }

    private static final class SupportBackgroundDrawable extends Drawable {
        private final Paint fill = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final Paint line = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final float horizontalInset;
        private final float verticalInset;
        private final float radius;

        SupportBackgroundDrawable(
                Context context,
                int horizontalInsetDp,
                int verticalInsetDp,
                int radiusDp
        ) {
            fill.setColor(SettingsUi.isDarkMode()
                    ? Color.argb(255, 35, 17, 25)
                    : Color.argb(255, 255, 247, 250));
            fill.setStyle(Paint.Style.FILL);
            line.setColor(SettingsUi.isDarkMode()
                    ? Color.argb(56, 240, 45, 99)
                    : Color.argb(52, 240, 45, 99));
            line.setStyle(Paint.Style.STROKE);
            line.setStrokeWidth(Math.max(1, SettingsUi.dp(context, 1)));
            horizontalInset = SettingsUi.dp(context, horizontalInsetDp);
            verticalInset = SettingsUi.dp(context, verticalInsetDp);
            radius = SettingsUi.dp(context, radiusDp);
        }

        @Override
        public void draw(Canvas canvas) {
            RectF bounds = new RectF(
                    getBounds().left + horizontalInset,
                    getBounds().top + verticalInset,
                    getBounds().right - horizontalInset,
                    getBounds().bottom - verticalInset
            );
            canvas.drawRoundRect(bounds, radius, radius, fill);
            canvas.drawRoundRect(bounds, radius, radius, line);
        }

        @Override
        public void setAlpha(int alpha) {
            fill.setAlpha(alpha);
            line.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {
            fill.setColorFilter(colorFilter);
            line.setColorFilter(colorFilter);
        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }
    }
}

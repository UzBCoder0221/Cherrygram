package org.telegram.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.R;

public class LauncherIconController {
    public static void tryFixLauncherIconIfNeeded() {
        for (LauncherIcon icon : LauncherIcon.values()) {
            if (isEnabled(icon)) {
                return;
            }
        }

        setIcon(LauncherIcon.CHERRY);
    }

    public static boolean isEnabled(LauncherIcon icon) {
        Context ctx = ApplicationLoader.applicationContext;
        int i = ctx.getPackageManager().getComponentEnabledSetting(icon.getComponentName(ctx));
        return i == PackageManager.COMPONENT_ENABLED_STATE_ENABLED || i == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT && icon == LauncherIcon.CHERRY;
    }

    public static void setIcon(LauncherIcon icon) {
        Context ctx = ApplicationLoader.applicationContext;
        PackageManager pm = ctx.getPackageManager();
        for (LauncherIcon i : LauncherIcon.values()) {
            pm.setComponentEnabledSetting(i.getComponentName(ctx), i == icon ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED :
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        }
    }

    public enum LauncherIcon {
        CHERRY("CG_Icon_Cherry", R.drawable.icon_background_default, R.drawable.icon_foreground_cherry, R.string.AP_ChangeIcon_Default),
        MONET_CHERRY_SAMSUNG("CG_Icon_Monet_Samsung", R.color.icon_background_cherry_samsung, R.drawable.icon_foreground_cherry_samsung, R.string.AP_ChangeIcon_Monet_Samsung),
        MONET_CHERRY_PIXEL("CG_Icon_Monet_Pixel", R.color.icon_background_cherry_pixel, R.drawable.icon_foreground_cherry_pixel, R.string.AP_ChangeIcon_Monet_Pixel),
        DARK_CHERRY("CG_Icon_Dark", R.drawable.icon_background_dark, R.drawable.icon_foreground_cherry, R.string.AP_ChangeIcon_Dark),
        DARK_CHERRY_BRA("CG_Icon_Dark_Bra", R.drawable.icon_background_dark, R.drawable.icon_foreground_cherry_bra, R.string.AP_ChangeIcon_Bra),
        WHITE_CHERRY("CG_Icon_White_Cherry", R.drawable.icon_background_white, R.drawable.icon_foreground_cherry_white, R.string.AP_ChangeIcon_White),
        WHITE_CHERRY_BRA("CG_Icon_White_Cherry_Bra", R.drawable.icon_background_white, R.drawable.icon_foreground_cherry_white_bra, R.string.AP_ChangeIcon_Bra),
        LAGUNA_CHERRY("CG_Icon_Laguna", R.drawable.icon_background_laguna, R.drawable.icon_foreground_cherry, R.string.AP_ChangeIcon_Laguna),
        AQUA_CHERRY("CG_Icon_Aqua", R.drawable.icon_background_aqua, R.drawable.icon_foreground_cherry, R.string.AppIconAqua),
        GREEN_CHERRY("CG_Icon_Green", R.drawable.icon_background_green, R.drawable.icon_foreground_cherry, R.string.AP_ChangeIcon_Green),
        LAVANDA_CHERRY("CG_Icon_Lavanda", R.drawable.icon_background_lavanda, R.drawable.icon_foreground_cherry, R.string.AP_ChangeIcon_Lavanda),
        VIOLET_SUNSET_CHERRY("CG_Icon_Violet_Sunset", R.drawable.icon_background_violet_sunset, R.drawable.icon_foreground_cherry, R.string.AP_ChangeIcon_Violet_Sunset),
        VIOLET_SUNSET_CHERRY_BRA("CG_Icon_Violet_Sunset_Bra", R.drawable.icon_background_violet_sunset, R.drawable.icon_foreground_cherry_bra, R.string.AP_ChangeIcon_Bra),
        SUNSET_CHERRY("CG_Icon_Sunset", R.drawable.icon_background_sunset, R.drawable.icon_foreground_cherry, R.string.AP_ChangeIcon_Sunset),
        SUNRISE_CHERRY("CG_Icon_Sunrise", R.drawable.icon_background_sunrise, R.drawable.icon_foreground_cherry, R.string.AP_ChangeIcon_Sunrise),
        TURBO_CHERRY("CG_Icon_Turbo", R.drawable.icon_5_background_sa, R.drawable.icon_foreground_cherry, R.string.AppIconTurbo),
        NOX_CHERRY("CG_Icon_Night", R.mipmap.icon_2_background_sa, R.drawable.icon_foreground_cherry, R.string.AppIconNox),
        DARK_NY("CG_Icon_Dark_NY", R.drawable.icon_background_dark_ny, R.drawable.icon_foreground_cherry_bra, R.string.AP_ChangeIcon_Cherry_NY),

        PREMIUM("PremiumIcon", R.drawable.icon_3_background_sa, R.mipmap.icon_3_foreground, R.string.AppIconPremium, true),
        TURBO("TurboIcon", R.drawable.icon_5_background_sa, R.mipmap.icon_5_foreground, R.string.AppIconTurbo, true),
        NOX("NoxIcon", R.mipmap.icon_2_background_sa, R.drawable.icon_foreground, R.string.AppIconNox, true);

        public final String key;
        public final int background;
        public final int foreground;
        public final int title;
        public final boolean premium;

        private ComponentName componentName;

        public ComponentName getComponentName(Context ctx) {
            if (componentName == null) {
                componentName = new ComponentName(ctx.getPackageName(), "uz.unnarsx.cherrygram." + key);
            }
            return componentName;
        }

        LauncherIcon(String key, int background, int foreground, int title) {
            this(key, background, foreground, title, false);
        }

        LauncherIcon(String key, int background, int foreground, int title, boolean premium) {
            this.key = key;
            this.background = background;
            this.foreground = foreground;
            this.title = title;
            this.premium = premium;
        }
    }

    public static void updateMonetIcon() {
        if (isEnabled(LauncherIcon.MONET_CHERRY_SAMSUNG)) {
            setIcon(LauncherIcon.CHERRY);
            setIcon(LauncherIcon.MONET_CHERRY_SAMSUNG);
        }
        if (isEnabled(LauncherIcon.MONET_CHERRY_PIXEL)) {
            setIcon(LauncherIcon.CHERRY);
            setIcon(LauncherIcon.MONET_CHERRY_PIXEL);
        }
    }
}

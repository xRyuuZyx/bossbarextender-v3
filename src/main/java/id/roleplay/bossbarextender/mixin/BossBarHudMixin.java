package id.roleplay.bossbarextender.mixin;

import id.roleplay.bossbarextender.BossBarExtenderConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.BossBarHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Expands only the height value used by the vanilla BossBarHud render cutoff.
 * The original BossBarHud.render method remains in control, so vanilla still
 * draws the BossBar title through TextRenderer and the active resource pack.
 */
@Mixin(BossBarHud.class)
public abstract class BossBarHudMixin {
    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowHeight()I"
            )
    )
    private int bossbarExtender$expandVanillaRenderHeight(DrawContext context) {
        int actualHeight = context.getScaledWindowHeight();
        int percent = BossBarExtenderConfig.get().maxScreenUsagePercent();

        // Vanilla applies its one-third-screen threshold to this value.
        // Return 3x the usable screen height so the same vanilla loop can
        // place BossBars over the configured portion of the full GUI screen.
        return (int) Math.ceil(actualHeight * 3.0 * percent / 100.0);
    }
}

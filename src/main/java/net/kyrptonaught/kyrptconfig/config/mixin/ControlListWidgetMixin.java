package net.kyrptonaught.kyrptconfig.config.mixin;

import net.kyrptonaught.kyrptconfig.config.keybinding.NonConflicting.NonConflictingKeyBinding;
import net.minecraft.client.gui.screen.option.ControlsListWidget;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ControlsListWidget.KeyBindingEntry.class)
public class ControlListWidgetMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;equals(Lnet/minecraft/client/option/KeyBinding;)Z"))
    public boolean dontConflict(KeyBinding instance, KeyBinding other) {
        if (instance instanceof NonConflictingKeyBinding || other instanceof NonConflictingKeyBinding)
            return false;
        return instance.equals(other);
    }
}

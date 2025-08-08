package com.cinemamod.mcef.mixins;

import com.cinemamod.mcef.example.ExampleScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin class to add a debug button to the TitleScreen that opens the ExampleScreen.
 * The class is not added to the mixin config. If you want to test things, just add it to the common mixin config.
 */
@Mixin(TitleScreen.class)
public class MixinTitleScreen extends Screen {

    private MixinTitleScreen() {
        super(Component.empty());
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void after_init_MCEF(CallbackInfo info) {

        this.addRenderableWidget(Button.builder(Component.literal("OPEN MCEF EXAMPLE SCREEN"), button -> {
            Minecraft.getInstance().setScreen(new ExampleScreen(Component.literal("Example Screen")));
        }).bounds(20, 20, 150, 20).build());

    }

}

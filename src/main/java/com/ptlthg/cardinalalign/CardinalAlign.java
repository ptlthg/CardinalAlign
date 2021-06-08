package com.ptlthg.cardinalalign;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = "cardinalalign", clientSideOnly = true, name = "Cardinal Align", version = "1.0.0", acceptedMinecraftVersions = "[1.8.9]")
public class CardinalAlign {

    public static KeyBinding cardinalAlignKey = new KeyBinding("Align", Keyboard.KEY_F4, "Cardinal Align");

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new CardinalAlign());
        ClientRegistry.registerKeyBinding(cardinalAlignKey);
    }

    public static void align() {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;

        double yaw = player.getRotationYawHead();
        int value = (int) Math.round(yaw);

        if (value % 90 != 0) { return; }

        if (value + 0.21 > yaw && yaw > value - 0.21) {
            player.setPositionAndRotation(player.posX, player.posY, player.posZ, value, player.rotationPitch);
        }
    }

    @SubscribeEvent
    public void trackKeyInputs(InputEvent.KeyInputEvent event) {
        if (cardinalAlignKey.isPressed()) {
            CardinalAlign.align();
        }
    }
}

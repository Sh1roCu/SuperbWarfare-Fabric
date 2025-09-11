package com.atsuishio.superbwarfare.init;

import com.mojang.blaze3d.platform.InputConstants;
import committee.nova.mkb.api.IKeyBinding;
import committee.nova.mkb.keybinding.KeyConflictContext;
import committee.nova.mkb.keybinding.KeyModifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ModKeyMappings {

    public static final KeyMapping RELOAD = new KeyMapping("key.superbwarfare.reload", GLFW.GLFW_KEY_R, "key.categories.superbwarfare");
    public static final KeyMapping FIRE_MODE = new KeyMapping("key.superbwarfare.fire_mode", GLFW.GLFW_KEY_N, "key.categories.superbwarfare");
    public static final KeyMapping SENSITIVITY_INCREASE = new KeyMapping("key.superbwarfare.sensitivity_increase", GLFW.GLFW_KEY_PAGE_UP, "key.categories.superbwarfare");
    public static final KeyMapping SENSITIVITY_REDUCE = new KeyMapping("key.superbwarfare.sensitivity_reduce", GLFW.GLFW_KEY_PAGE_DOWN, "key.categories.superbwarfare");
    public static final KeyMapping INTERACT = new KeyMapping("key.superbwarfare.interact", GLFW.GLFW_KEY_X, "key.categories.superbwarfare");
    public static final KeyMapping DISMOUNT = new KeyMapping("key.superbwarfare.dismount", GLFW.GLFW_KEY_LEFT_ALT, "key.categories.superbwarfare");
    public static final KeyMapping BREATH = new KeyMapping("key.superbwarfare.breath", GLFW.GLFW_KEY_LEFT_CONTROL, "key.categories.superbwarfare");

    public static final KeyMapping CONFIG = new KeyMapping("key.superbwarfare.config", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, "key.categories.superbwarfare");

    public static final KeyMapping EDIT_MODE = new KeyMapping("key.superbwarfare.edit_mode", GLFW.GLFW_KEY_H, "key.categories.superbwarfare");
    public static final KeyMapping CHANGE_AMMO_FORWARD = new KeyMapping("key.superbwarfare.change_ammo_forward", GLFW.GLFW_KEY_LEFT, "key.categories.superbwarfare");
    public static final KeyMapping CHANGE_AMMO_BACKWARD = new KeyMapping("key.superbwarfare.change_ammo_backward", GLFW.GLFW_KEY_RIGHT, "key.categories.superbwarfare");
    public static final KeyMapping UNLOAD = new KeyMapping("key.superbwarfare.unload", InputConstants.UNKNOWN.getValue(), "key.categories.superbwarfare");

    public static final KeyMapping FIRE = new KeyMapping("key.superbwarfare.fire", InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_LEFT, "key.categories.superbwarfare");
    public static final KeyMapping HOLD_ZOOM = new KeyMapping("key.superbwarfare.hold_zoom", InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_RIGHT, "key.categories.superbwarfare");
    public static final KeyMapping SWITCH_ZOOM = new KeyMapping("key.superbwarfare.switch_zoom", GLFW.GLFW_KEY_UNKNOWN, "key.categories.superbwarfare");
    public static final KeyMapping RELEASE_DECOY = new KeyMapping("key.superbwarfare.release_decoy", GLFW.GLFW_KEY_V, "key.categories.superbwarfare");
    public static final KeyMapping FREE_CAMERA = new KeyMapping("key.superbwarfare.free_camera", GLFW.GLFW_KEY_C, "key.categories.superbwarfare");
    public static final KeyMapping MELEE = new KeyMapping("key.superbwarfare.melee", GLFW.GLFW_KEY_V, "key.categories.superbwarfare");
    public static final KeyMapping MARK = new KeyMapping("key.superbwarfare.mark", InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_MIDDLE, "key.categories.superbwarfare");

    public static void registerKeyMappings() {
        register(RELOAD);
        register(FIRE_MODE);
        register(SENSITIVITY_INCREASE);
        register(SENSITIVITY_REDUCE);
        register(INTERACT);
        register(DISMOUNT);
        register(BREATH);
        register(CONFIG, KeyConflictContext.IN_GAME, KeyModifier.ALT);
        register(EDIT_MODE);
        register(FIRE);
        register(HOLD_ZOOM);
        register(SWITCH_ZOOM);
        register(RELEASE_DECOY);
        register(MELEE);
        register(FREE_CAMERA);
        register(MARK);
        register(CHANGE_AMMO_FORWARD);
        register(CHANGE_AMMO_BACKWARD);
        register(UNLOAD);
    }

    private static void register(KeyMapping keyMapping) {
        KeyBindingHelper.registerKeyBinding(keyMapping);
    }

    private static void register(KeyMapping keyMapping, KeyConflictContext keyConflictContext, KeyModifier keyModifier) {
        KeyBindingHelper.registerKeyBinding(keyMapping);
        var iKey = (IKeyBinding) keyMapping;
        iKey.setKeyConflictContext(keyConflictContext);
        iKey.setKeyModifierAndCode(keyModifier, iKey.getKey());
    }
}

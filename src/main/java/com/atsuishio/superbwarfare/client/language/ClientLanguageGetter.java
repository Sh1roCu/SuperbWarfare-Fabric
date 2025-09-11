package com.atsuishio.superbwarfare.client.language;

import com.atsuishio.superbwarfare.Mod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.resources.language.ClientLanguage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@Environment(EnvType.CLIENT)
public class ClientLanguageGetter {

    public static ClientLanguage EN_US;
    public static ResourceLocation ID = Mod.loc("lang_reload");

    public static void onResourcePackReload() {
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new LangListener());
    }

    static class LangListener extends SimplePreparableReloadListener<ClientLanguage> implements IdentifiableResourceReloadListener {

        @Override
        public ResourceLocation getFabricId() {
            return null;
        }

        @Override
        @ParametersAreNonnullByDefault
        protected @NotNull ClientLanguage prepare(ResourceManager pResourceManager, ProfilerFiller pProfiler) {
            return ClientLanguage.loadFrom(pResourceManager, List.of("en_us"), false);
        }

        @Override
        @ParametersAreNonnullByDefault
        protected void apply(ClientLanguage pObject, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
            EN_US = pObject;
        }
    }
}

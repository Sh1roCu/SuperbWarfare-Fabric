package com.atsuishio.superbwarfare.capability;

import com.atsuishio.superbwarfare.Mod;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class LaserCapability {

    public static final ResourceLocation ID = Mod.loc("laser_capability");

    public interface ILaserCapability extends AutoSyncedComponent, PlayerComponent<ILaserCapability> {

        void init(LaserHandler handler);

        void start();

        void tick();

        void stop();

        void end();

    }

    public static class LaserCapabilityImpl implements ILaserCapability {

        public LaserHandler laserHandler;

        public LaserCapabilityImpl(Player player) {
        }

        @Override
        public void init(LaserHandler handler) {
            this.laserHandler = handler;
        }

        @Override
        public void start() {
            this.laserHandler.start();
        }

        @Override
        public void tick() {
        }

        @Override
        public void stop() {
            if (this.laserHandler != null) {
                this.laserHandler.stop();
            }
        }

        @Override
        public void end() {
            if (this.laserHandler != null) {
                this.laserHandler.end();
            }
        }

        public void serializeNBT(CompoundTag tag) {
            if (this.laserHandler != null) {
                tag.put("Laser", this.laserHandler.writeNBT());
            }
        }

        public void deserializeNBT(CompoundTag nbt) {
            if (nbt.contains("Laser") && this.laserHandler != null) {
                this.laserHandler.readNBT(nbt.getCompound("Laser"));
            }
        }

        @Override
        public void readFromNbt(@NotNull CompoundTag tag) {
            deserializeNBT(tag);
        }

        @Override
        public void writeToNbt(@NotNull CompoundTag tag) {
            serializeNBT(tag);
        }
    }
}

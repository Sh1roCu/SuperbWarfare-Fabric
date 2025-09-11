package com.atsuishio.superbwarfare.init;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;

import java.util.ArrayList;
import java.util.List;

public class ModSerializers {

    public static void init() {
        EntityDataSerializers.registerSerializer(INT_LIST_SERIALIZER);
        EntityDataSerializers.registerSerializer(FLOAT_LIST_SERIALIZER);
    }

    public static final EntityDataSerializer<IntList> INT_LIST_SERIALIZER = EntityDataSerializer.simple(FriendlyByteBuf::writeIntIdList, FriendlyByteBuf::readIntIdList);
    public static final EntityDataSerializer<List<Float>> FLOAT_LIST_SERIALIZER = EntityDataSerializer.simple((buf, list) -> {
        buf.writeVarInt(list.size());
        for (Float v : list) {
            buf.writeFloat(v);
        }
    }, buf -> {
        var length = buf.readVarInt();
        var list = new ArrayList<Float>();
        for (int i = 0; i < length; i++) {
            list.add(buf.readFloat());
        }
        return list;
    });
}

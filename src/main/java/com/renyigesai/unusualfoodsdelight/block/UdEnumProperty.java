package com.renyigesai.unusualfoodsdelight.block;

import net.minecraft.util.StringRepresentable;

public enum UdEnumProperty implements StringRepresentable {

    NONE("none"),
    FIRE("fire"),
    POISON("poison"),
    MAGIC("magic");
    private final String name;

    UdEnumProperty(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public String toString() {
        return this.name;
    }
}

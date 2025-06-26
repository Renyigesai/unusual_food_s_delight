package com.renyigesai.unusualfoodsdelight.block.hang_lantern;

import com.renyigesai.unusualfoodsdelight.init.UdBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class HangLanternBlockEntity extends BlockEntity {
    protected final ItemStackHandler inventory = new ItemStackHandler(1);
    public HangLanternBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(UdBlocks.HANG_LANTERN_ENTITY.get(), p_155229_, p_155230_);
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public int getContainerSize() {return inventory.getSlots();}

    public void drops(HangLanternBlockEntity blockEntity) {
        SimpleContainer inventory = new SimpleContainer(blockEntity.inventory.getSlots());
        inventory.setItem(0, blockEntity.inventory.getStackInSlot(0));
        if (this.level != null) {
            Containers.dropContents(this.level, this.worldPosition, inventory);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Inventory")) {
            inventory.deserializeNBT(tag.getCompound("Inventory"));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", inventory.serializeNBT());
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        load(pkt.getTag());
    }
}

package com.renyigesai.unusualfoodsdelight.block.smoked_meat_hook;

import com.renyigesai.unusualfoodsdelight.init.UdBlocks;
import com.renyigesai.unusualfoodsdelight.recipes.SmokedMeatRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Optional;

public class SmokedMeatHookBlockEntity extends BlockEntity {

    private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    private int cookingTime;
    private final RecipeManager.CachedCheck<Container, CampfireCookingRecipe> quickCheck = RecipeManager.createCheck(RecipeType.CAMPFIRE_COOKING);

    public SmokedMeatHookBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(UdBlocks.SMOKED_MEAT_HOOK_ENTITY.get(), p_155229_, p_155230_);
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    private static Optional<SmokedMeatRecipes> getCurrentRecipe(Level level, ItemStack stack) {
        SimpleContainer inventory = new SimpleContainer(1);
        inventory.setItem(0, stack);
        if (level == null) {
            return Optional.empty();
        }
        return level.getRecipeManager()
                .getRecipeFor(SmokedMeatRecipes.Type.INSTANCE, inventory, level);
    }

//    public Optional<CampfireCookingRecipe> getCookableRecipe(ItemStack pStack) {
//        return this.items.stream().noneMatch(ItemStack::isEmpty) ? Optional.empty() : this.quickCheck.getRecipeFor(new SimpleContainer(pStack), this.level);
//    }

    private static Optional<CampfireCookingRecipe> getCookableRecipe(Level level, ItemStack stack) {
        SimpleContainer inventory = new SimpleContainer(1);
        inventory.setItem(0, stack);
        if (level == null) {
            return Optional.empty();
        }
        return level.getRecipeManager()
                .getRecipeFor(RecipeType.CAMPFIRE_COOKING, inventory, level);
    }

    public void placeOrTakeFood(Player player, ItemStack pStack) {
        ItemStack itemstack = this.items.get(0);
        if (itemstack.isEmpty()){
            this.items.set(0, pStack.split(1));
        }else {
            player.getInventory().placeItemBackInInventory(this.items.get(0));
        }
        this.cookingTime = 0;
        this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(player, this.getBlockState()));
        this.markUpdated();
    }

    public BlockState getBelowCampfire(Level pLevel, BlockPos pPos){
        return pLevel.getBlockState(pPos.below(2));
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, SmokedMeatHookBlockEntity pBlockEntity) {
        boolean flag = false;
        ItemStack stack = pBlockEntity.items.get(0);
        ItemStack resultItem;
        Optional<CampfireCookingRecipe> cookableRecipe = Optional.empty();
        Optional<SmokedMeatRecipes> smokedRecipe = Optional.empty();

        if (stack.isEmpty()) {
            return;
        }

        BlockState belowCampfire = pBlockEntity.getBelowCampfire(pLevel, pPos);

        if (belowCampfire.is(Blocks.CAMPFIRE) && belowCampfire.getValue(CampfireBlock.LIT)){
                cookableRecipe = getCookableRecipe(pLevel,stack);
                if (cookableRecipe.isPresent()){
                    flag = true;
                    if (pBlockEntity.cookingTime >= 600) {
                        Container container = new SimpleContainer(stack);
                        resultItem = pBlockEntity.quickCheck.getRecipeFor(container, pLevel).map((p_270054_) -> p_270054_.assemble(container, pLevel.registryAccess())).orElse(stack);
                        pBlockEntity.items.set(0, resultItem.copy());
                        pLevel.sendBlockUpdated(pPos, pState, pState, 3);
                    }
                }
            }

            if (belowCampfire.is(UdBlocks.SMOKED_MEAT_CAMPFIRE.get())){
                smokedRecipe = getCurrentRecipe(pLevel, stack);
                if (smokedRecipe.isPresent()) {
                    flag = true;
                    if (pBlockEntity.cookingTime >= 600) {
                        SmokedMeatRecipes recipes = smokedRecipe.get();
                        pBlockEntity.items.set(0, recipes.getResultItem(pLevel.registryAccess()).copy());
                        pLevel.sendBlockUpdated(pPos, pState, pState, 3);
                    }
                }
            }

            if (smokedRecipe.isPresent() || cookableRecipe.isPresent()){
                pBlockEntity.cookingTime++;
            }

        if (flag) {
            setChanged(pLevel, pPos, pState);
        }
    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.items.clear();
        ContainerHelper.loadAllItems(pTag, this.items);
        if (pTag.contains("CookingTime")) {
            this.cookingTime = pTag.getInt("CookingTime");
        }

    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        ContainerHelper.saveAllItems(pTag, this.items, true);
        pTag.putInt("CookingTime",this.cookingTime);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public CompoundTag getUpdateTag() {
        CompoundTag compoundtag = new CompoundTag();
        ContainerHelper.saveAllItems(compoundtag, this.items, true);
        return compoundtag;
    }
}

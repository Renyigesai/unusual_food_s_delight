package com.renyigesai.unusualfoodsdelight.client;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FullbrightBakedModel implements BakedModel {
    private final BakedModel original;

    public FullbrightBakedModel(BakedModel original) {
        this.original = original;
    }

    /**
     * 修改单个 BakedQuad 的光照值为全亮
     */
    private BakedQuad modifyQuadLight(BakedQuad quad) {
        TextureAtlasSprite sprite = quad.getSprite();
        int[] vertexData = quad.getVertices().clone();
        if (sprite.contents().name().getPath().contains("lit")) {
            int vertices = vertexData.length / 8;
            for (int i = 0; i < vertices; i++) {
                int lightOffset = i * 8 + 6;
                vertexData[lightOffset] = LightTexture.pack(15, 15);
            }
        }
        return new BakedQuad(vertexData, quad.getTintIndex(), quad.getDirection(), quad.getSprite(), quad.isShade());

    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource randomSource) {
        List<BakedQuad> originalQuads = original.getQuads(state, side,randomSource);
        if (originalQuads.isEmpty()) return originalQuads;

        List<BakedQuad> newQuads = new ArrayList<>(originalQuads.size());
        for (BakedQuad quad : originalQuads) {
            newQuads.add(modifyQuadLight(quad));
        }
        return newQuads;
    }

    @Override public boolean useAmbientOcclusion() { return original.useAmbientOcclusion(); }
    @Override public boolean isGui3d() { return original.isGui3d(); }
    @Override public boolean usesBlockLight() { return original.usesBlockLight(); }
    @Override public boolean isCustomRenderer() { return original.isCustomRenderer(); }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return original.getParticleIcon();
    }

    @Override public ItemOverrides getOverrides() { return original.getOverrides(); }
}

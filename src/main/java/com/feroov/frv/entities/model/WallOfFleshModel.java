package com.feroov.frv.entities.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.WallOfFlesh;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WallOfFleshModel extends AnimatedGeoModel<WallOfFlesh>
{
    public WallOfFleshModel() {}

    @Override
    public ResourceLocation getModelLocation(WallOfFlesh object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/wall_of_flesh.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WallOfFlesh entity)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/entity/wall_of_flesh.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WallOfFlesh object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/wall_of_flesh.animation.json");
    }
}

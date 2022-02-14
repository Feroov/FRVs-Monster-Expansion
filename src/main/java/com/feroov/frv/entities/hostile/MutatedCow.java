package com.feroov.frv.entities.hostile;


import com.feroov.frv.entities.variants.MutatedCowVariant;
import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumSet;


public class MutatedCow extends Monster implements IAnimatable
{

    public static final EntityDataAccessor<Boolean> STUNNED = SynchedEntityData.defineId(MutatedCow.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(MutatedCow.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> ATTACK = SynchedEntityData.defineId(MutatedCow.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(MutatedCow.class, EntityDataSerializers.INT);

    /******************************** Animation methods *****************************/
    private final AnimationFactory factory = new AnimationFactory(this);
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (!(animationSpeed > -0.10F && animationSpeed < 0.10F) && !this.isAggressive())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        if ((this.dead || this.getHealth() < 0.01 || this.isDeadOrDying()))
        {
            if (level.isClientSide)
            {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("death", false));
                return PlayState.CONTINUE;
            }
        }
        if (this.isAggressive() && !(this.dead || this.getHealth() < 0.01 || this.isDeadOrDying()))
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<MutatedCow>
                (this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    public void setAttackingState(int time)
    {
        this.entityData.set(ATTACK, time);
    }
    /*********************************************************************************/


    /******************************** Constructor *************************************/
    public MutatedCow(EntityType<? extends Monster> p_i48549_1_, Level p_i48549_2_)
    {
        super(p_i48549_1_, p_i48549_2_);
    }
    /*********************************************************************************/


    /******************************** Variant methods *****************************/
    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag)
    {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag p_21815_)
    {
        super.readAdditionalSaveData(p_21815_);
        this.entityData.set(DATA_ID_TYPE_VARIANT, p_21815_.getInt("Variant"));
    }

    public MutatedCowVariant getVariant()
    {
        return MutatedCowVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(MutatedCowVariant variant)
    {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    private int getTypeVariant()
    {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor p_146746_, @NotNull DifficultyInstance p_146747_,
                                        @NotNull MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_, @Nullable CompoundTag p_146750_)
    {
        MutatedCowVariant variant = Util.getRandom(MutatedCowVariant.values(), this.random);
        setVariant(variant);
        return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
    }
    /*********************************************************************************/

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
        this.entityData.define(STUNNED, false);
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
        this.entityData.define(ATTACK, 1);
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 75.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.60D)
                .add(Attributes.FOLLOW_RANGE, 170.0D)
                .add(Attributes.ATTACK_DAMAGE, 12.0D);
    }

    @Override
    protected void updateControlFlags()
    {
        super.updateControlFlags();
        this.goalSelector.setControlFlag(Goal.Flag.MOVE, true);
        this.goalSelector.setControlFlag(Goal.Flag.JUMP, true);
         this.goalSelector.setControlFlag(Goal.Flag.LOOK, true);
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.44F));
        this.targetSelector.addGoal(2, new MeleeAttackGoal(this, 0.52D, true));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Monster.class, true));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Animal.class, true));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    /****************************** Sounds **********************************/

    @Override
    protected SoundEvent getAmbientSound()
    {
        this.playSound(SoundEvents.COW_AMBIENT, 1.0F, 0.3F);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn)
    {
        this.playSound(SoundEvents.COW_HURT, 1.0F, 0.3F);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        this.playSound(SoundEvents.COW_DEATH, 1.0F, 0.3F);
        return null;
    }
    /*************************************************************************/


    @Override
    protected float getStandingEyeHeight(@NotNull Pose poseIn, @NotNull EntityDimensions sizeIn)
    {
        return 1.65F;
    }

    @Override
    protected void tickDeath()
    {
        ++this.deathTime;
        if (this.deathTime == 50 && !this.level.isClientSide())
        {
            this.level.broadcastEntityEvent(this, (byte)50);
            this.remove(RemovalReason.KILLED);
        }

    }

    @Override
    public boolean isBaby()
    {
        return false;
    }
}

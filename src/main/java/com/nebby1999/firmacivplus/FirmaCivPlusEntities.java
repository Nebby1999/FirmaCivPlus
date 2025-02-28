package com.nebby1999.firmacivplus;

import com.alekiponi.firmaciv.common.entity.vehicle.CanoeEntity;
import com.alekiponi.firmaciv.common.entity.vehicle.FirmacivRowboatEntity;
import com.alekiponi.firmaciv.common.entity.vehicle.FirmacivSloopEntity;
import com.alekiponi.firmaciv.common.entity.vehicle.FirmacivSloopUnderConstructionEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.function.Consumer;

public class FirmaCivPlusEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FirmaCivPlus.MOD_ID);

    private static final Map<WatercraftMaterial, RegistryObject<EntityType<CanoeEntity>>> _CANOES = new HashMap<>();
    public static Map<WatercraftMaterial, RegistryObject<EntityType<CanoeEntity>>> getCanoes()
    {
        return Collections.unmodifiableMap(_CANOES);
    }

    private static final Map<WatercraftMaterial, RegistryObject<EntityType<FirmacivRowboatEntity>>> _ROWBOATS = new HashMap<>();
    public static Map<WatercraftMaterial, RegistryObject<EntityType<FirmacivRowboatEntity>>> getRowboats()
    {
        return Collections.unmodifiableMap(_ROWBOATS);
    }

    private static final Map<WatercraftMaterial, RegistryObject<EntityType<FirmacivSloopUnderConstructionEntity>>> _SLOOPS_UNDER_CONSTRUCTION = new HashMap<>();
    public static Map<WatercraftMaterial, RegistryObject<EntityType<FirmacivSloopUnderConstructionEntity>>> getSloopsUnderConstruction()
    {
        return Collections.unmodifiableMap(_SLOOPS_UNDER_CONSTRUCTION);
    }

    private static final Map<WatercraftMaterial, RegistryObject<EntityType<FirmacivSloopEntity>>> _SLOOPS = new HashMap<>();
    public static Map<WatercraftMaterial, RegistryObject<EntityType<FirmacivSloopEntity>>> getSloops()
    {
        return Collections.unmodifiableMap(_SLOOPS);
    }

    private static final ArrayList<Consumer<WriteOnlyRegistration>> _consumers = new ArrayList<>();
    public static void addConsumerForRegistration(Consumer<WriteOnlyRegistration> consumer)
    {
        _consumers.add(consumer);
    }

    static void init(IEventBus eventBus)
    {
        var registrationClass = new WriteOnlyRegistration();
        for(var consumer : _consumers)
        {
            consumer.accept(registrationClass);
        }

        if(!ENTITY_TYPES.getEntries().isEmpty())
            ENTITY_TYPES.register(eventBus);
    }

    public static final class WriteOnlyRegistration
    {
        public void registerFromWatercraftMaterials(WatercraftMaterial[] materials)
        {
            for(var material : materials)
            {
                if(material.isSoftwood())
                {
                    putCanoeEntity(material);
                }
                else
                {
                    putRowboatEntity(material);
                    putSloopUnderConstructionEntity(material);
                    putSloopEntity(material);
                }
            }
        }

        private void putCanoeEntity(WatercraftMaterial watercraftMaterial)
        {
            String name = "dugout_canoe/" + watercraftMaterial.getSerializedName();
            var entry = registerEntity(name, EntityType.Builder.of((EntityType<CanoeEntity> entityType, Level level) ->
            {
                return new CanoeEntity(entityType, level, watercraftMaterial);
            }, MobCategory.MISC));
            _CANOES.put(watercraftMaterial, entry);
        }

        private void putRowboatEntity(WatercraftMaterial watercraftMaterial)
        {
            String name = "rowboat/" + watercraftMaterial.getSerializedName();
            var entry = registerEntity(name, EntityType.Builder.of((EntityType<FirmacivRowboatEntity> entityType, Level level) ->
            {
                return new FirmacivRowboatEntity(entityType, level, watercraftMaterial);
            }, MobCategory.MISC));
            _ROWBOATS.put(watercraftMaterial, entry);
        }

        private void putSloopUnderConstructionEntity(WatercraftMaterial watercraftMaterial)
        {
            String name = "sloop_construction/" + watercraftMaterial.getSerializedName();
            var entry = registerEntity(name, EntityType.Builder.of((EntityType<FirmacivSloopUnderConstructionEntity> entityType, Level level) ->
            {
                return new FirmacivSloopUnderConstructionEntity(entityType, level, watercraftMaterial);
            }, MobCategory.MISC));
            _SLOOPS_UNDER_CONSTRUCTION.put(watercraftMaterial, entry);
        }

        private void putSloopEntity(WatercraftMaterial watercraftMaterial)
        {
            String name = "sloop/" + watercraftMaterial.getSerializedName();
            var entry = registerEntity(name, EntityType.Builder.of((EntityType<FirmacivSloopEntity> entityType, Level level) ->
            {
                return new FirmacivSloopEntity(entityType, level, watercraftMaterial);
            }, MobCategory.MISC));
            _SLOOPS.put(watercraftMaterial, entry);
        }

        private <E extends Entity> RegistryObject<EntityType<E>> registerEntity(final String name,
                                                                                     final EntityType.Builder<E> builder)
        {
            final String id = name.toLowerCase(Locale.ROOT);
            return ENTITY_TYPES.register(id, () ->
            {
                return builder.build(FirmaCivPlus.MOD_ID + ":" + id);
            });
        }
    }
}

package com.teammetallurgy.atum.world.gen.feature;

import com.teammetallurgy.atum.init.AtumBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.Nonnull;
import java.util.Random;

public class WorldGenPapyrus extends WorldGenerator {

    @Override
    public boolean generate(@Nonnull World world, @Nonnull Random rand, @Nonnull BlockPos pos) {
        for (int i = 0; i < 20; ++i) {
            BlockPos randPos = pos.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));

            if (world.isAirBlock(randPos)) {
                BlockPos posDown = randPos.down();

                if (world.getBlockState(posDown.west()).getMaterial() == Material.WATER || world.getBlockState(posDown.east()).getMaterial() == Material.WATER || world.getBlockState(posDown.north()).getMaterial() == Material.WATER || world.getBlockState(posDown.south()).getMaterial() == Material.WATER) {
                    int j = 2 + rand.nextInt(rand.nextInt(3) + 1);

                    for (int k = 0; k < j; ++k) {
                        if (AtumBlocks.PAPYRUS.canBlockStay(world, randPos)) {
                            world.setBlockState(randPos.up(k), AtumBlocks.PAPYRUS.getDefaultState(), 2);
                        }
                    }
                }
            }
        }
        return true;
    }
}
package GraveStone;

import GraveStone.structures.GraveStoneWorldGenerator;
import GraveStone.structures.village.VillageHandlerGSCemetery;
import GraveStone.structures.village.VillageHandlerGSMemorial;
import GraveStone.structures.village.VillageHandlerGSUndertaker;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.block.Block;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneStructures {

    private static GraveStoneStructures instance;
    
    public static final int[] VALUEBLE_BLOCKS = {
        Block.blockGold.blockID, Block.blockLapis.blockID, Block.blockRedstone.blockID,
        Block.blockGold.blockID, Block.blockLapis.blockID, Block.blockRedstone.blockID,
        Block.blockDiamond.blockID, Block.blockEmerald.blockID
    };


    private GraveStoneStructures() {
        generateStructures();
    }

    public static GraveStoneStructures getInstance() {
        if (instance == null) {
            return new GraveStoneStructures();
        } else {
            return instance;
        }
    }

    private void generateStructures() {
        // register cemeteries
        if (GraveStoneConfig.generateCemeteries) {
            VillageHandlerGSCemetery villageCemeteryHandler = new VillageHandlerGSCemetery();
            VillagerRegistry.instance().registerVillageCreationHandler(villageCemeteryHandler);
        }
        
        // register memorials
        if (GraveStoneConfig.generateVillageMemorials) {
            VillageHandlerGSMemorial villageMemorialHandler = new VillageHandlerGSMemorial();
            VillagerRegistry.instance().registerVillageCreationHandler(villageMemorialHandler);
        }

        // register Undertaker
        if (GraveStoneConfig.generateUndertaker) {
            VillageHandlerGSUndertaker villageUndertakerHandler = new VillageHandlerGSUndertaker();
            VillagerRegistry.instance().registerVillageCreationHandler(villageUndertakerHandler);
            VillagerRegistry.instance().registerVillagerType(385, "/mods/GraveStone/textures/entity/undertaker.png");
            VillagerRegistry.instance().registerVillageTradeHandler(385, villageUndertakerHandler);
        }

        // structure generator
        GameRegistry.registerWorldGenerator(new GraveStoneWorldGenerator());
    }
}

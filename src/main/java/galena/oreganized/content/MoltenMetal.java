package galena.oreganized.content;

import java.util.function.Supplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class MoltenMetal {

    protected Block solid;
    protected Fluid molten;


    public MoltenMetal(Supplier<? extends Block> solid, Supplier<? extends Fluid> molten) {
        this.solid = solid.get();
        this.molten = molten.get();
    }

    public Block getSolid() {
        return solid;
    }

    public Fluid getMolten() {
        return molten;
    }
}

package galena.oreganized.plumbum.client.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;

public record ThermometerTooltip(int heat) implements TooltipComponent {

}

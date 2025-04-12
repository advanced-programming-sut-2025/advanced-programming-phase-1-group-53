package Models;

import Enums.ToolLevel;
import Enums.ToolType;

public class Tool {
    private ToolType name;
    private ToolLevel level;
    private int energyConsumed;

    public Tool(ToolType name) {
        this.name = name;
        this.level = ToolLevel.normal;
        this.energyConsumed = this.name.getInitialEnergyConsumed();
    }

    public ToolType getName() {
        return name;
    }

    public void setName(ToolType name) {
        this.name = name;
    }

    public ToolLevel getLevel() {
        return level;
    }

    public void setLevel(ToolLevel level) {
        this.level = level;
    }

    public int getEnergyConsumed() {
        // TODO شرط هایش
        return energyConsumed;
    }

    public void setEnergyConsumed(int energyConsumed) {
        this.energyConsumed = energyConsumed;
    }
}

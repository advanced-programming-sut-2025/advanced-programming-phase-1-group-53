package Models;

import Enums.ToolLevel;
import Enums.ToolName;

public class Tool {
    private ToolName name;
    private ToolLevel level;
    private int energyConsumed;

    public Tool(ToolName name) {
        this.name = name;
        this.level = ToolLevel.normal;
        this.energyConsumed = this.initialEnergyConsumed();
    }

    private int initialEnergyConsumed() {
        int energyConsumed = 0;
        return energyConsumed;
        // TODO
    }

    public ToolName getName() {
        return name;
    }

    public void setName(ToolName name) {
        this.name = name;
    }

    public ToolLevel getLevel() {
        return level;
    }

    public void setLevel(ToolLevel level) {
        this.level = level;
    }

    public int getEnergyConsumed() {
        return energyConsumed;
    }

    public void setEnergyConsumed(int energyConsumed) {
        this.energyConsumed = energyConsumed;
    }
}

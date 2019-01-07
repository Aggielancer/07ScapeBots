package scripts.mythirdscript;

import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.methods.container.impl.Inventory;

@ScriptManifest(category = Category.MISC, name = "my first script", author = "plsnojagex", version = 1.0)
public class MyThirdScript extends AbstractScript {
    public static final String EVENT_RPG = "Event rpg";
    public static final String MONK = "Monk";
    public static final Filter<NPC> MONK_FILTER = npc -> npc != null && npc.getName().equals(MONK) && !npc.isHealthBarVisible();

    private final Area killArea = new Area(3043, 3499, 3053, 3482);

    @Override
    public int onLoop() {
        if (getCombat().getHealthPercent() <= 50) { // if hp is below 75%
            getInventory().interact("Trout", "Eat");
        }
        if(getLocalPlayer().isInCombat()){
            //do nothing
        }else if(killArea.contains(getLocalPlayer())){
            if(getEquipment().isSlotEmpty(EquipmentSlot.WEAPON.getSlot())){
                if(getInventory().contains(EVENT_RPG)){
                    getInventory().interact(EVENT_RPG, "Wield");
                }else {
                    stop();
                    return -1;
                }
            }else {
                    NPC monk = getNpcs().closest(MONK_FILTER);
                    if(monk != null){
                        monk.interact("Attack");
                    }
            }
        }else {
            getWalking().walk(killArea.getRandomTile());
        }

        return 1000;
    }
}

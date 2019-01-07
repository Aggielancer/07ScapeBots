package scripts.myfirstscript;

import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;

@ScriptManifest(category = Category.MISC, name = "my first script", author = "plsnojagex", version = 1.0)
public class MyFirstScript extends AbstractScript {
    public static final String BRONZE_SWORD = "Bronze sword";
    public static final String GOBLIN = "Goblin";
    public static final Filter <NPC> GOBLIN_FILTER = npc -> npc != null && npc.getName().equals(GOBLIN) && !npc.isHealthBarVisible();

    private final Area killArea = new Area(3243, 3241, 3259, 3231);

    @Override
    public int onLoop() {
        if(getLocalPlayer().isInCombat()) {
            //do nothing
        }else if(killArea.contains(getLocalPlayer())){
            if(getEquipment().isSlotEmpty(EquipmentSlot.WEAPON.getSlot())){
                if(getInventory().contains(BRONZE_SWORD)) {
                    getInventory().interact(BRONZE_SWORD, "Wield");
                }else {
                    stop();
                    return -1;
                }
            }else {
                NPC goblin = getNpcs().closest(GOBLIN_FILTER);
                if(goblin != null){
                    goblin.interact("Attack");
                }
            }
        }else {
            getWalking().walk(killArea.getRandomTile());
        }

        return 1000;
    }

}

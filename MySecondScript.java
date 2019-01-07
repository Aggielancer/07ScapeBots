package scripts.mysecondscript;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;


@ScriptManifest(category = Category.MISC, name = "my second script", author = "plsnojagex", version = 1.0)
public class MySecondScript extends AbstractScript {
    @Override
    public int onLoop() {
        if (!getLocalPlayer().isAnimating()) {
            if (getInventory().isFull()){
                getInventory().dropAllExcept("Bronze Pickaxe", "Black Pickaxe", "Mithril Pickaxe", "Adamant Pickaxe");
            }
            GameObject rocks = getGameObjects().closest("Rocks");
            if (rocks != null) {
                rocks.interact("Mine");
            }
        }
        return 1000;
    }
}



package woodcutter;

import org.tbot.internal.AbstractScript;
import org.tbot.internal.Manifest;
import org.tbot.internal.ScriptCategory;
import org.tbot.internal.handlers.LogHandler;

@Manifest(authors = "choice96", name = "iChopper", description = "Intelligent Chopper", version = 1, category = ScriptCategory.WOODCUTTING)
public class Main extends AbstractScript {
    @Override
    public boolean onStart() {
        LogHandler.log("Script started.");
        return true;
    }

    @Override
    public int loop() {
        return 100;
    }

    @Override
    public void onFinish() {
        LogHandler.log("Script finished.");
    }
}

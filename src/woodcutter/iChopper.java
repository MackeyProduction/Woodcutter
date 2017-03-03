package woodcutter;

import org.tbot.internal.AbstractScript;
import org.tbot.internal.Manifest;
import org.tbot.internal.ScriptCategory;
import org.tbot.internal.handlers.LogHandler;

@Manifest(authors = "choice96", name = "iChopper", description = "Intelligent Chopper", version = 1, category = ScriptCategory.WOODCUTTING)
public class Main extends AbstractScript {
    // Declarate variables
    private boolean powerchoppingEnabled = false;
    private boolean axeInHand = false;
    private boolean intelligentChopping = false;
    private boolean hopWorlds = false;
    private boolean antiMod = false;

    private String usedAxe = "";
    private String bestAxe = "";
    private String treeToChop = "";
    private String locationForChop = "";
    private String result = "";

    private Area currentLocation;
    private RS2Object guiTree;

    private int woodcuttingLevel = 0;
    private int currentAttackLevel = 0;
    private int requiredAttackLevel = 0;
    private int distance = 0;
    private int maxPlayers;

    private Player player;

    private LocationMain locationMain = new LocationMain();
    private LocationClass locations;
    private LevelRequirement lvlRequirement = new LevelRequirement();

    private List<RS2Object> treeList = new ArrayList<>();
    private List<Item> itemList = new ArrayList<>();

    private final Font treeFont = new Font("Arial", 1, 12);
    private final Color fontColor = new Color(255, 255, 0);

    @Override
    public boolean onStart() {
        LogHandler.log("Script started.");
        return true;
    }

    private enum State {

        CHOP, DROP, BANK, WALK, SLEEP, ANTIBAN
    };

    private State getState() {
        // Is intelligent chopping enabled?
        if (intelligentChopping) {
            treeToChop = lvlRequirement.getLevelRequirementForTree(woodcuttingLevel).getTreeName();
            locations = locationMain.getLocation(treeToChop);
            bestAxe = lvlRequirement.getLevelRequirementForAxe(woodcuttingLevel).getAxeName();
            woodcuttingLevel = skills.getDynamic(Skill.WOODCUTTING);
        }
        player = getPlayers().myPlayer();
        currentLocation = locations.getTreeArea();
        requiredAttackLevel = lvlRequirement.getLevelRequirementForAxe(currentAttackLevel).getAttackLevel();

        // Inventory is full?
        if (getInventory().isFull()) {
            currentLocation = locations.getBankArea();
            // Dropping
            if (powerchoppingEnabled) {
                return State.DROP;
            }

            // Banking if player is in area
            if (playerInArea(currentLocation)) {
                return State.BANK;
            }
        }

        if (checkInventoryItems(constants.AXES)) {
            usedAxe = getCurrentAxe();
            if (!equipment.isWearingItem(EquipmentSlot.WEAPON, usedAxe) && requiredAttackLevel <= currentAttackLevel) {
                equipment.equip(EquipmentSlot.HANDS, usedAxe);
                axeInHand = true;
            }
        }

        // Walk to area if player isn't there
        if (!playerInArea(currentLocation)) {
            return State.WALK;
        }

        // Axe isn't in inventory?
        if (!checkInventoryItems(constants.AXES) && !equipment.isWearingItem(EquipmentSlot.WEAPON)) {
            return State.BANK;
        }

        // Player animating?
        if (player.isAnimating()) {
            return State.ANTIBAN;
        }

        // Player isn't animating?
        if (!player.isAnimating() && !player.isMoving()) {
            return State.CHOP;
        }
        return State.SLEEP;
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

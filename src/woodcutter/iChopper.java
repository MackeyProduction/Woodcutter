package woodcutter;

import org.tbot.client.GameObject;
import org.tbot.client.Player;
import org.tbot.internal.AbstractScript;
import org.tbot.internal.Manifest;
import org.tbot.internal.ScriptCategory;
import org.tbot.internal.handlers.LogHandler;
import org.tbot.methods.tabs.Equipment;
import org.tbot.util.requirements.EquipmentRequirement;
import org.tbot.wrappers.Area;
import org.tbot.wrappers.Item;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Manifest(authors = "choice96", name = "iChopper", description = "Intelligent Chopper", version = 1, category = ScriptCategory.WOODCUTTING)
public class iChopper extends AbstractScript {
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
    private GameObject guiTree;

    private int woodcuttingLevel = 0;
    private int currentAttackLevel = 0;
    private int requiredAttackLevel = 0;
    private int distance = 0;
    private int maxPlayers;

    private Player player;

    private LocationMain locationMain = new LocationMain();
    private LocationClass locations;
    private LevelRequirement lvlRequirement = new LevelRequirement();

    private List<GameObject> treeList = new ArrayList<>();
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
            if (!Equipment.contains(usedAxe) && requiredAttackLevel <= currentAttackLevel) {
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

    private boolean canChop(Area area, RS2Object obj) {
        return area.contains(obj);
    }

    private boolean playerInArea(Area area) {
        return area.contains(getPlayers().myPlayer().getPosition());
    }

    private RS2Object getCurrentTree(List<RS2Object> trees) {
        RS2Object pos = trees.get(0);
        for (int i = 0; i < trees.size(); i++) {
            if (trees.get(i).getPosition().equals(getAllTrees(treeToChop).get(i).getPosition())) {
                pos = trees.get(i);
            }
        }
        return pos;
    }

    private List<RS2Object> getAllTrees(String treeName) {
        // http://osbot.org/forum/topic/72612-looking-for-a-ground-item-within-a-certain-distance-from-player/?p=803493
        // http://osbot.org/forum/topic/92723-how-to-ensure-player-only-chops-in-specified-area/?hl=distance
        // http://osbot.org/forum/topic/96505-osbot-scripting-basics-and-snippets/
        // Filtert die Objekte nach einer ID und der Distanz von der Position des Spielers
        // stream(): Datenstrom mit allen Elementen des Objektes.
        // collect(): Anhand der Klasse Collectors wird durch die Methode toList(), der Datenstrom in eine Liste umgewandelt.
        // distinct(): Sortiert doppelte Einträge aus.
        treeList = getObjects().getAll().stream().
                // Lambda Expression: obj -> ...
                        filter(obj -> obj.getName().equalsIgnoreCase(treeName)).
                        distinct().
                        collect(Collectors.toList());
        return treeList;
    }

    public List<RS2Object> getAllTrees(String treeName, Position position) {
        return getAllTrees(treeName).stream().filter(obj -> obj.getName().equalsIgnoreCase(treeName) && obj.getPosition().equals(position)).collect(Collectors.toList());
    }

    public List<RS2Object> getAllTrees(String treeName, Area location) {
        return getAllTrees(treeName).stream().filter(obj -> obj.getName().equalsIgnoreCase(treeName) && canChop(location, obj)).collect(Collectors.toList());
    }

    public List<RS2Object> getAllTrees(String treeName, int distance) {
        return getAllTrees(treeName).stream().filter(obj -> obj.getName().equalsIgnoreCase(treeName) && player.getPosition().distance(obj.getPosition()) < distance).collect(Collectors.toList());
    }

    private boolean checkInventoryItems(String[] items) {
        itemList.addAll(Arrays.asList(getInventory().getItems()));
        return compare(items, itemList);
    }

    private boolean checkBankItems(String[] items) {
        itemList.addAll(Arrays.asList(getBank().getItems()));
        return compare(items, itemList);
    }

    private boolean compare(String[] items, List<Item> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < items.length; j++) {
                if (list.get(i) != null) {
                    if (list.get(i).getName().equals(items[j])) {
                        result = items[j];
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private String getCurrentAxe() {
        return (result != null) ? result : "Error: Item dousn't exist.";
    }

    private Area getNearestArea(List<LocationClass> list) {
        int min = Integer.MAX_VALUE;
        Area nearestArea = list.get(0).getBankArea();
        for (int i = 0; i < list.size(); i++) {
            distance = player.getPosition().distance(list.get(i).getBankArea().getPositions().get(i));
            if (distance < min) {
                min = distance;
                nearestArea = list.get(i).getBankArea();
            }
        }
        return nearestArea;
    }

    private void loadGUI() throws InterruptedException {
        // Open GUI
        g = new GUI(this);
        g.setVisible(true);

        // Sleeping while GUI is visible
        while (g.isVisible()) {
            sleep(100);
        }

        // Information from the GUI
        treeToChop = g.getTreeName();
        locationForChop = g.getLocationName();
        powerchoppingEnabled = g.getPowerchopping();
        intelligentChopping = g.getIntelligentChopping();
        maxPlayers = g.getMaxPlayers();
        hopWorlds = g.getHopWorlds();
        antiMod = g.getAntiMod();
        guiTree = g.getTree();

        if (g.getAddedTrees() != null) {
            if (!g.getAddedTrees().isEmpty()) {
                treeList = g.getAddedTrees();
            }
        }
    }
}

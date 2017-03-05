package woodcutter;

import org.tbot.methods.*;
import org.tbot.methods.walking.Walking;
import org.tbot.wrappers.*;
import org.tbot.internal.AbstractScript;
import org.tbot.internal.Manifest;
import org.tbot.internal.ScriptCategory;
import org.tbot.internal.handlers.LogHandler;
import org.tbot.methods.tabs.Equipment;
import org.tbot.methods.tabs.Inventory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Manifest(authors = "choice96", name = "iChopper", description = "Intelligent Chopper", version = 1, category = ScriptCategory.WOODCUTTING)
public class iChopper extends AbstractScript {
    //<editor-fold defaultstate="collapsed" desc="Declaration">
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

    GUI g;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Main">
    @Override
    public boolean onStart() {
        // Get my player
        player = Players.getLocal();
        currentAttackLevel = Skills.getCurrentLevel(Skills.Skill.ATTACK);
        woodcuttingLevel = Skills.getCurrentLevel(Skills.Skill.WOODCUTTING);
        LogHandler.log(lvlRequirement.getLevelRequirementForAxe(currentAttackLevel).getAttackLevel());

        // Loading the GUI
        loadGUI();

        if (!intelligentChopping) {
            locations = locationMain.getLocation(treeToChop, locationForChop);
        }

        LogHandler.log(String.format("Hello %s, I hope you enjoy using my script.", player.getName()));
        return true;
    }

    private enum State {

        CHOP, DROP, BANK, WALK, SLEEP, ANTIBAN
    }

    private State getState() {
        // Is intelligent chopping enabled?
        if (intelligentChopping) {
            treeToChop = lvlRequirement.getLevelRequirementForTree(woodcuttingLevel).getTreeName();
            locations = locationMain.getLocation(treeToChop);
            bestAxe = lvlRequirement.getLevelRequirementForAxe(woodcuttingLevel).getAxeName();
            woodcuttingLevel = Skills.getCurrentLevel(Skills.Skill.WOODCUTTING);
        }
        player = Players.getLocal();
        currentLocation = locations.getTreeArea();
        requiredAttackLevel = lvlRequirement.getLevelRequirementForAxe(currentAttackLevel).getAttackLevel();

        // Inventory is full?
        if (Inventory.isFull()) {
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
                Inventory.getFirst(usedAxe).click();
                axeInHand = true;
            }
        }

        // Walk to area if player isn't there
        if (!playerInArea(currentLocation)) {
            return State.WALK;
        }

        // Axe isn't in inventory?
        if (!checkInventoryItems(constants.AXES) && Equipment.getItemInSlot(Equipment.SLOTS_WEAPON) != null) {
            return State.BANK;
        }

        // Player animating?
        if (player.getAnimation() != -1) {
            return State.ANTIBAN;
        }

        // Player isn't animating?
        if (player.getAnimation() == -1 && !player.isMoving()) {
            return State.CHOP;
        }
        return State.SLEEP;
    }

    @Override
    public int loop() {
        switch (getState()) {
            case DROP:
                int id = 0;
                for (int i = 0; i < constants.AXES.length; i++) {
                    String axe = constants.AXES[i];
                    Item currentAxe = Inventory.getItems(item -> item.getName().equalsIgnoreCase(axe))[i];
                    id = currentAxe.getID();
                }
                Inventory.dropAllExcept(id);
                break;
            case BANK:
                log("bla...");
                // Check if bank is open
                if (Bank.isOpen()) {
                    // Axe isn't in inventory?
                    if (!checkInventoryItems(constants.AXES)) {
                        // Axe in bank?
                        if (checkBankItems(constants.AXES)) {
                            Bank.withdraw(getCurrentAxe(), 1);
                        }
                    } else {
                        // Desosit all except the current axe
                        Bank.depositAllExcept(getCurrentAxe());
                    }
                } else {
                    Bank.open();
                }
                break;
            case CHOP:
                // Get all trees
                if (!g.getAddedTrees().isEmpty()) {
                    treeList = g.getAddedTrees();
                    log("Chopping...");
                } else {
                    treeList = getAllTrees(treeToChop, currentLocation);
                    log("Test...");
                }

                // Interacting with tree
                //RS2Object tree = objects.closest(treeList);
                if (treeList.size() > 0) {
                    GameObject tree = treeList.get(Random.nextInt(treeList.size()));

                    if (tree != null) {
                        tree.interact("Chop down");
                        while (player.getAnimation() != -1) {
                            sleep(800, 1000);
                        }
                    }
                }
                break;
            case WALK:
                Walking.walkTileMM(currentLocation.getCentralTile());
                //getWalking().webWalk(currentLocation);
                break;
            case SLEEP:
                sleep(Random.nextInt(600, 800));
                break;
            case ANTIBAN:
                switch (Random.nextInt(100)) {
                    case 0:
                        if (antiMod) {
                            Player moderator = Players.getLoaded(p -> p.getName().startsWith("Mod"))[0];

                            if (moderator != null) {
                                if (moderator.isOnScreen()) {
                                    Game.hopRandomF2P();
                                }
                            }
                        }
                        break;
                    case 10:
                        if (hopWorlds) {
                            if (playerInArea(locations.getTreeArea()) && !playerInArea(locations.getBankArea()) && Players.getLoaded().length > maxPlayers) {
                                Game.hopRandomF2P();
                            }
                        }
                        break;
                    case 40:
                        Camera.setAngle(Random.nextInt(180));
                        break;
                    case 60:
                        Mouse.move(0, 0);
                        break;
                    case 80:
                        Widgets.openTab(Widgets.TAB_STATS);
                        WidgetChild widget = Widgets.getWidgetByText("Woodcutting");
                        boolean woodcuttingHover = Mouse.move(widget.getX(), widget.getY());
                        sleep(800, 1200);

                        if (woodcuttingHover) {
                            Widgets.openTab(Widgets.TAB_INVENTORY);
                        }
                        break;
                }
                break;
            default:
                break;
        }
        return 100;
    }

    @Override
    public void onFinish() {
        LogHandler.log("Thank you for using my script.");
    }

    public void onPaint(Graphics2D g) {
        g.setFont(treeFont);
        g.setColor(fontColor);

        Rectangle bounds;

        if (guiTree != null) {
            bounds = new Rectangle(guiTree.getWorldX(), guiTree.getWorldY(), guiTree.getModel().getLocalX(), guiTree.getModel().getLocalY());
            g.drawRect(bounds.x, bounds.y, bounds.getBounds().width, bounds.getBounds().height);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Private methods">
    private boolean canChop(Area area, GameObject obj) {
        return area.contains(obj);
    }

    private boolean playerInArea(Area area) {
        return area.contains(Players.getLocal().getRegionX(), Players.getLocal().getRegionY());
    }

    private GameObject getCurrentTree(List<GameObject> trees) {
        GameObject pos = trees.get(0);
        for (int i = 0; i < trees.size(); i++) {
            if (trees.get(i).getLocation().equals(getAllTrees(treeToChop).get(i).getLocation())) {
                pos = trees.get(i);
            }
        }
        return pos;
    }

    private List<GameObject> getAllTrees(String treeName) {
        treeList = Arrays.asList(GameObjects.getLoaded(obj -> obj.getName().equalsIgnoreCase(treeName)));

        return treeList;
    }

    public List<GameObject> getAllTrees(String treeName, Tile position) {
        return getAllTrees(treeName).stream().filter(obj -> obj.getName().equalsIgnoreCase(treeName) && obj.getLocation().equals(position)).collect(Collectors.toList());
    }

    public List<GameObject> getAllTrees(String treeName, Area location) {
        return getAllTrees(treeName).stream().filter(obj -> obj.getName().equalsIgnoreCase(treeName) && canChop(location, obj)).collect(Collectors.toList());
    }

    public List<GameObject> getAllTrees(String treeName, int distance) {
        return getAllTrees(treeName).stream().filter(obj -> obj.getName().equalsIgnoreCase(treeName) && player.getLocation().distance(obj.getLocation()) < distance).collect(Collectors.toList());
    }

    private boolean checkInventoryItems(String[] items) {
        itemList.addAll(Arrays.asList(Inventory.getItems()));
        return compare(items, itemList);
    }

    private boolean checkBankItems(String[] items) {
        itemList.addAll(Arrays.asList(Bank.getItems()));
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
            distance = player.getLocation().distance(list.get(i).getBankArea().getTileArray()[i]);
            if (distance < min) {
                min = distance;
                nearestArea = list.get(i).getBankArea();
            }
        }
        return nearestArea;
    }

    private void loadGUI() {
        // Open GUI
        g = new GUI();
        g.frame.setVisible(true);

        // Sleeping while GUI is visible
        while (g.frame.isVisible()) {
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
    //</editor-fold>
}

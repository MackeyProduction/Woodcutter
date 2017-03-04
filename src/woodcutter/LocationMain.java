package woodcutter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LocationMain extends LocationClass {
    private final LocationClass[] locationArray = new LocationClass[] {
        new LocationClass(0, 0, constants.GRAND_EXCHANGE, constants.TREE_GRAND_EXCHANGE),
        new LocationClass(0, 6, constants.LUMBRIDGE_CASTLE_BANK, constants.TREE_LUMBRIDGE),
        new LocationClass(1, 0, constants.GRAND_EXCHANGE, constants.OAK_GRAND_EXCHANGE),
        new LocationClass(1, 3, constants.VARROCK_WEST_BANK, constants.OAK_VARROCK_WEST),
        new LocationClass(2, 2, constants.FALADOR_BANK, constants.WILLOW_RIMMINGTON),
        new LocationClass(2, 5, constants.DRAYNOR_VILLAGE_BANK, constants.WILLOW_PORT_SARIM),
        new LocationClass(2, 4, constants.DRAYNOR_VILLAGE_BANK, constants.WILLOW_DRAYNOR_VILLAGE),
        new LocationClass(3, 0, constants.GRAND_EXCHANGE, constants.YEW_GRAND_EXCHANGE),
        new LocationClass(3, 1, constants.EDGEVILLE_BANK, constants.YEW_EDGEVILLE),
        new LocationClass(3, 2, constants.FALADOR_BANK, constants.YEW_RIMMINGTON)
    };
    private final List<LocationClass> locationList = new ArrayList<>();
    
    /**
     * Get all locations.
     * @return Returns a list of all locations.
     */
    public List<LocationClass> getAllLocations() {
        locationList.addAll(Arrays.asList(locationArray));
        return locationList;
    }
    
    private List<LocationClass> locationStream(String treeName) {
        return getAllLocations().stream().filter(obj -> obj.getTreeName().equalsIgnoreCase(treeName)).collect(Collectors.toList());
    }
    
    public LocationClass getLocation(String treeName) {
        return locationStream(treeName).get(0);
    }
    
    public LocationClass getLocation(String treeName, String locationName) {
        return locationStream(treeName).stream().
                filter(obj -> obj.getLocationName().equalsIgnoreCase(locationName)).
                findFirst().
                get();
    }
}

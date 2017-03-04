package woodcutter;

import java.util.List;
import org.tbot.wrappers.Area;

public class LocationClass {
    private int _treeID;
    private int _locationID;
    private String _result;
    private Area _treeArea;
    private Area _bankArea;
    
    // Leerer Konstruktor
    public LocationClass() {
        
    }
    
    public LocationClass(int treeID, int locationID, Area bankArea, Area treeArea) {
        _treeID = treeID;
        _locationID = locationID;
        _bankArea = bankArea;
        _treeArea = treeArea;
    }
    
    public LocationClass(LocationMain[] locations) {
        
    }
    
    public LocationClass(int treeID, int locationID) {
        _treeID = treeID;
        _locationID = locationID;
    }
    
    public String getTreeName() {
        return compare(constants.TREE_NAMES, _treeID);
    }

    public String getLocationName() {
        return compare(constants.LOCATIONS, _locationID);
    }
    
    public Area getTreeArea() {
        return _treeArea;
    }
    
    public Area getBankArea() {
        return _bankArea;
    }
    
    private int getTreeId() {
        return _treeID;
    }
    
    private int getLocationId() {
        return _locationID;
    }
    
    protected String compare(String[] array, int id) {
        if (id < array.length) {
            for (String obj : array) {
                if (obj.equals(array[id])) {
                    _result = obj;
                }
            }
        } else {
            _result = "Error: ID dousn't exist.";
        }
        return _result;
    }
}

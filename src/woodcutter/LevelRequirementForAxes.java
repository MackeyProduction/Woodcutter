package iChopper;

public class LevelRequirementForAxes extends LocationClass {
    private int _axeID;
    private int _wcLevel;
    private int _attackLevel;
    
    // Leerer Konstruktor
    public LevelRequirementForAxes() {
        
    }
    
    // Parametisierter Konstruktor
    public LevelRequirementForAxes(int axeID, int wcLevel, int attackLevel) {
        _axeID = axeID;
        _wcLevel = wcLevel;
        _attackLevel = attackLevel;
    }
    
    /**
     * Gets the name of the axe.
     * @return Returns the axe name as string.
     */
    public String getAxeName() {
        return compare(constants.AXES, _axeID);
    }
    
    /**
     * Gets the required level to hold the axe.
     * @return Returns the required level as string.
     */
    public int getAxeLevel() {
        return _wcLevel;
    }
    
    public int getAttackLevel() {
        return _attackLevel;
    }
}

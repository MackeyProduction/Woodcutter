package iChopper;

public class LevelRequirementForTrees extends LocationClass {
    private int _treeID;
    private int _level;
    
    public LevelRequirementForTrees() {
        
    }
    
    public LevelRequirementForTrees(int treeID, int level) {
        _treeID = treeID;
        _level = level;
    }
    
    /**
     * Gets the name of the tree.
     * @return Returns the name of the tree as string.
     */
    @Override
    public String getTreeName() {
        return compare(constants.TREE_NAMES, _treeID);
    }
    
    /**
     * Gets the required level of the tree.
     * @return Returns the required level as string.
     */
    public int getTreeLevel() {
        return _level;
    }
}

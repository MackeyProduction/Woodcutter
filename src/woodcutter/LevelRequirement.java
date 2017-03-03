package iChopper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LevelRequirement {
    private final List<LevelRequirementForAxes> axeList = new ArrayList<>();
    private final List<LevelRequirementForTrees> treeList = new ArrayList<>();
    
    private final LevelRequirementForAxes[] axeArray = new LevelRequirementForAxes[] {
        new LevelRequirementForAxes(0, 1, 1),
        new LevelRequirementForAxes(1, 1, 1),
        new LevelRequirementForAxes(2, 6, 5),
        new LevelRequirementForAxes(3, 6, 10),
        new LevelRequirementForAxes(4, 21, 20),
        new LevelRequirementForAxes(5, 31, 30),
        new LevelRequirementForAxes(6, 41, 40)
    };
    
    private final LevelRequirementForTrees[] treeArray = new LevelRequirementForTrees[] {
        new LevelRequirementForTrees(0, 1),
        new LevelRequirementForTrees(1, 15),
        new LevelRequirementForTrees(2, 30),
        new LevelRequirementForTrees(3, 60)
    };
    
    /**
     * Gets a list of axes and the requirements.
     * @return Returns a list of axes.
     */
    public List<LevelRequirementForAxes> getAllLevelRequirementForAxes() {
        axeList.addAll(Arrays.asList(axeArray));
        return axeList;
    }
    
    /**
     * Gets a list of trees and the requirements.
     * @return Returns a list of trees.
     */
    public List<LevelRequirementForTrees> getAllLevelRequirementForTrees() {
        treeList.addAll(Arrays.asList(treeArray));
        return treeList;
    }
    
    public LevelRequirementForTrees getLevelRequirementForTree(int level) {
        LevelRequirementForTrees lvlRequirement = new LevelRequirementForTrees();
        for (int i = 0; i < treeArray.length; i++) {
            if (level >= treeArray[i].getTreeLevel()) {
                lvlRequirement = treeArray[i];
            }
        }
        return lvlRequirement;
    }
    
    public LevelRequirementForAxes getLevelRequirementForAxe(int level) {
        LevelRequirementForAxes lvlRequirement = new LevelRequirementForAxes();
        for (int i = 0; i < axeArray.length; i++) {
            if (level >= axeArray[i].getAxeLevel()) {
                lvlRequirement = axeArray[i];
            }
        }
        return lvlRequirement;
    }
}

package woodcutter;

import org.tbot.wrappers.GameObject;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Til Anheier on 04.03.2017.
 */
public class GUI {
    private JPanel panelMain;
    private JLabel treeLabel;
    private JComboBox cbTree;
    private JCheckBox chbPowerchopping;
    private JCheckBox chbIntelligentChopping;
    private JTabbedPane jTPMain;
    private JPanel jPMain;
    private JPanel jPManual;
    private JPanel jPAntiban;
    private JLabel locationLabel;
    private JLabel distanceLabel;
    private JLabel currentDistanceLabel;
    private JSlider slDistance;
    private JLabel availableTreesLabel;
    private JList listAvailableTrees;
    private JList listAddedTrees;
    private JButton btnRemoveFromList;
    private JButton btnAddToList;
    private JCheckBox chbHopWorlds;
    private JSpinner spMaxPlayers;
    private JLabel lblMaxPlayers;
    private JCheckBox chbAntiMod;
    private JButton btnStart;
    private JButton btnIntelligentChoppingInfo;
    private JComboBox cbLocation;

    JFrame frame = new JFrame("GUI");
    private iChopper _start;
    private DefaultListModel modelAvailableTrees = new DefaultListModel();
    private DefaultListModel modelAddedTrees = new DefaultListModel();
    private List<GameObject> treeList = new ArrayList<>();
    private List<GameObject> addedTrees = new ArrayList<>();

    public GUI() {
        init();
    }

    public GUI(iChopper start) {
        init();
        _start = start;
    }

    private void init() {
        cbTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getAllLocations();
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((int)spMaxPlayers.getValue() < 1) {
                    JOptionPane.showMessageDialog(null, "Value of max players have to > 0.");
                } else {
                    frame.dispose();
                }
            }
        });

        slDistance.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Remove all elements from model
                modelAvailableTrees.removeAllElements();

                // Catch information from slider and treeList
                currentDistanceLabel.setText(String.format("Current: %s", slDistance.getValue()));
                treeList = _start.getAllTrees(getTreeName(), Integer.parseInt(currentDistanceLabel.getText().substring(9)));
                availableTreesLabel.setText(String.format("Available Trees: %s", treeList.size()));

                // Adding elements to model
                for (int i = 0; i < treeList.size(); i++) {
                    modelAvailableTrees.addElement(String.format("%s %s", treeList.get(i).getName(), treeList.get(i).getLocation()));
                }

                // Set model
                listAvailableTrees.setModel(modelAvailableTrees);
            }
        });

        btnAddToList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean treeAdded = false;
                if (listAvailableTrees.getSelectedIndex() > -1) {
                    // Check all elements if an entry already exist
                    for (int i = 0; i < listAddedTrees.getModel().getSize(); i++) {
                        if (listAddedTrees.getModel().getElementAt(i).equals(listAvailableTrees.getModel().getElementAt(listAvailableTrees.getSelectedIndex()))) {
                            treeAdded = true;
                        }
                    }

                    try {
                        // Element dousn't exist?
                        if (!treeAdded) {
                            // Adding elements to list
                            for (int i = 0; i < listAvailableTrees.getModel().getSize(); i++) {
                                modelAddedTrees.add(i, listAvailableTrees.getModel().getElementAt(listAvailableTrees.getSelectedIndices()[i]));
                                listAddedTrees.setModel(modelAddedTrees);
                                addedTrees.add(treeList.get(listAvailableTrees.getSelectedIndices()[i]));
                            }
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        });

        btnRemoveFromList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (listAddedTrees.getSelectedIndex() > -1) {
                        // Removing items from list
                        for (int i = 0; i < listAddedTrees.getModel().getSize(); i++) {
                            modelAddedTrees.remove(listAddedTrees.getSelectedIndices()[i]);
                        }
                    }
                } catch (Exception ex) {
                }
            }
        });

        chbHopWorlds.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (chbHopWorlds.isSelected()) {
                    lblMaxPlayers.setEnabled(true);
                    spMaxPlayers.setEnabled(true);
                } else {
                    lblMaxPlayers.setEnabled(false);
                    spMaxPlayers.setEnabled(false);
                }
            }
        });

        btnIntelligentChoppingInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "What is intelligent chopping?\n"
                        + "Intelligent Chopping is the best alternative when you want to train Woodcutting from level 1 to 99.\n"
                        + "The script knows your current level and automatically goes to the next best location to chop the next best tree."
                        + "\n\n"
                        + "Does intelligent chopping support banking and powerchopping?\n"
                        + "Of course! Intelligent Chopping compares the distance of all bank locations with your current position.\n"
                        + "The nearest location will be chosen."
                        + "\n\n"
                        + "But what happens when I have the requirement for the next best axe?\n"
                        + "This is no problem. The script will handle that for you.\n"
                        + "The script will buy the best next axe and will sell the old axe at the Grand Exchange."
                        + "\n\n"
                        + "A dream of every woodcutter. You don't need to do anything!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("");
        frame.setContentPane(new GUI().panelMain);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public String getLocationName() {
        return cbLocation.getSelectedItem().toString();
    }

    public String getTreeName() {
        return cbTree.getSelectedItem().toString();
    }

    public boolean getPowerchopping() {
        return chbPowerchopping.isSelected();
    }

    public boolean getIntelligentChopping() {
        return chbIntelligentChopping.isSelected();
    }

    public boolean getHopWorlds() {
        return chbHopWorlds.isSelected();
    }

    public boolean getAntiMod() {
        return chbAntiMod.isSelected();
    }

    public int getMaxPlayers() {
        return (int) spMaxPlayers.getValue();
    }

    public List<GameObject> getAddedTrees() {
        return addedTrees;
    }

    public GameObject getTree() {
        return treeList.get(listAvailableTrees.getSelectedIndex());
    }

    private javax.swing.JComboBox getAllLocations() {
        // Removing all items
        cbLocation.removeAllItems();

        // Initializing
        LocationMain location = new LocationMain();

        // Catch all locations by the tree name
        List<LocationClass> locationList = location.getAllLocations().stream().filter(obj -> obj.getTreeName().equals(cbTree.getSelectedItem())).collect(Collectors.toList());

        // Adding items
        for (LocationClass obj : locationList) {
            cbLocation.addItem(obj.getLocationName());
        }
        return cbLocation;
    }
}

package woodcutter;

import org.tbot.wrappers.GameObject;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Til Anheier on 04.03.2017.
 */
public class GUI {
    private JPanel panel1;
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

    private DefaultListModel modelAvailableTrees = new DefaultListModel();
    private DefaultListModel modelAddedTrees = new DefaultListModel();
    private List<GameObject> treeList = new ArrayList<>();
    private List<GameObject> addedTrees = new ArrayList<>();

    public GUI() {
        cbTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        slDistance.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });

        btnAddToList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnRemoveFromList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        chbHopWorlds.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });

        btnIntelligentChoppingInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {

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
        return (int)spMaxPlayers.getValue();
    }

    public List<GameObject> getAddedTrees() {
        return addedTrees;
    }

    public GameObject getTree() {
        return treeList.get(listAvailableTrees.getSelectedIndex());
    }
}

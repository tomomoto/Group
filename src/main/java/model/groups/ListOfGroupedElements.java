package model.groups;

import model.TripleLong;
import model.TripleLongHashSet;

import java.util.ArrayList;

/**
 * Created by Tom on 24.03.2017.
 */
public class ListOfGroupedElements {

    private ArrayList<Group> groups;

    private TripleLongHashSet tripleLongHashSet;

    public ListOfGroupedElements() {
        groups = new ArrayList<>();
        tripleLongHashSet = new TripleLongHashSet();
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public boolean IsUnique (TripleLong tripleLong){
        if (tripleLongHashSet.ContainsByA(tripleLong.getA()))
            return false;
        if (tripleLongHashSet.ContainsByB(tripleLong.getB()))
            return false;
        if (tripleLongHashSet.ContainsByC(tripleLong.getC()))
            return false;
        return true;
    }

    public boolean IsUnique (Group group){
        return !tripleLongHashSet.IsRetain(group.getTripleLongHashSet());
    }

    public boolean AddGroup(Group group) {
        tripleLongHashSet.Merge(group.getTripleLongHashSet());
        return groups.add(group);
    }

    public void Merge(TripleLong tripleLong){
        ArrayList<Integer> mergingIndexList = new ArrayList<>();
        boolean listsAlreadyMerged=false;
        int removedGroups = 0;
        for (int i = 0; i < groups.size(); i++) {
            if (!groups.get(i - removedGroups).IsUnique(tripleLong)) {
                mergingIndexList.add(i - removedGroups);
                if (!listsAlreadyMerged){
                    groups.get(mergingIndexList.get(0)).Add(tripleLong);
                    listsAlreadyMerged=true;
                }
                if (mergingIndexList.size() == 2) {
                    //System.out.println("Merging triplelong");
                    groups.get(mergingIndexList.get(0)).Add(groups.get(mergingIndexList.get(1)));
                    mergingIndexList.remove(1);
                    groups.remove(i - removedGroups);
                    removedGroups++;
                }
            }
        }
    }

    public void Merge(Group group){
        ArrayList<Integer> mergingIndexList = new ArrayList<>();
        boolean listsAlreadyMerged=false;
        int removedGroups = 0;
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i - removedGroups).IsIntersect(group)) {
                mergingIndexList.add(i - removedGroups);
                if (!listsAlreadyMerged){
                    groups.get(mergingIndexList.get(0)).Add(group);
                    listsAlreadyMerged=true;
                }
                if (mergingIndexList.size() == 2) {
                    //System.out.println("Merging groups");
                    groups.get(mergingIndexList.get(0)).Add(groups.get(mergingIndexList.get(1)));
                    mergingIndexList.remove(1);
                    groups.remove(i - removedGroups);
                    removedGroups++;
                }
            }
        }
    }

}

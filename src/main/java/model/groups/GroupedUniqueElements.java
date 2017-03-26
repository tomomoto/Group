package model.groups;

import model.TripleLong;
import model.TripleLongHashSet;

import java.util.ArrayList;

/**
 * Created by Tom on 24.03.2017.
 */
public class GroupedUniqueElements {

    private ArrayList<Group> groups;

    private TripleLongHashSet tripleLongHashSet;

    public GroupedUniqueElements() {
        groups = new ArrayList<>();
        tripleLongHashSet = new TripleLongHashSet();
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public boolean isUnique(TripleLong tripleLong){
        if (tripleLongHashSet.containsByA(tripleLong.getA()))
            return false;
        if (tripleLongHashSet.containsByB(tripleLong.getB()))
            return false;
        if (tripleLongHashSet.containsByC(tripleLong.getC()))
            return false;
        return true;
    }

    public boolean checkAndAddGroup(Group group) {
        //Проверить наличие группы в списках групп на пересечение множеств
        if (groups.size() == 0 || !tripleLongHashSet.isRetain(group.getTripleLongHashSet()))
        {
            //Группа уникальна для списков. Добавить группу во списки
            //listOfGroupedElements.AddGroup(removed);
            tripleLongHashSet.merge(group.getTripleLongHashSet());
            return groups.add(group);
        }
        else {
            //Группа неуникальна для списков. Мержить группы по мере поиска
            merge(group);
            return true;
        }
    }

    public void merge(TripleLong tripleLong){
        ArrayList<Integer> mergingIndexList = new ArrayList<>();
        boolean listsAlreadyMerged=false;
        int removedGroups = 0;
        for (int i = 0; i < groups.size(); i++) {
            if (!groups.get(i - removedGroups).isUnique(tripleLong)) {
                mergingIndexList.add(i - removedGroups);
                if (!listsAlreadyMerged){
                    groups.get(mergingIndexList.get(0)).add(tripleLong);
                    listsAlreadyMerged=true;
                }
                if (mergingIndexList.size() == 2) {
                    //System.out.println("Merging triplelong");
                    groups.get(mergingIndexList.get(0)).add(groups.get(mergingIndexList.get(1)));
                    mergingIndexList.remove(1);
                    groups.remove(i - removedGroups);
                    removedGroups++;
                }
            }
        }
    }

    private void merge(Group group){
        ArrayList<Integer> mergingIndexList = new ArrayList<>();
        boolean listsAlreadyMerged=false;
        int removedGroups = 0;
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i - removedGroups).isIntersect(group)) {
                mergingIndexList.add(i - removedGroups);
                if (!listsAlreadyMerged){
                    groups.get(mergingIndexList.get(0)).add(group);
                    listsAlreadyMerged=true;
                }
                if (mergingIndexList.size() == 2) {
                    //System.out.println("Merging groups");
                    groups.get(mergingIndexList.get(0)).add(groups.get(mergingIndexList.get(1)));
                    mergingIndexList.remove(1);
                    groups.remove(i - removedGroups);
                    removedGroups++;
                }
            }
        }
    }

}

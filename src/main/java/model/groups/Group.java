package model.groups;

import model.TripleLong;
import model.TripleLongArrayList;
import model.TripleLongHashSet;

public class Group extends AbstractGroup {

    private TripleLongArrayList tripleLongArrayList;

    public Group() {
        super();
        tripleLongArrayList = new TripleLongArrayList();
    }

    public Group(TripleLong tripleLong) {
        super(tripleLong);
        tripleLongArrayList = new TripleLongArrayList();
        tripleLongArrayList.add(tripleLong);
    }

    public Group(TripleLongHashSet tlhs, TripleLongArrayList tripleLongArrayList) {
        super(tlhs);
        this.tripleLongArrayList = tripleLongArrayList;
    }

    public TripleLongArrayList getTripleLongArrayList() {
        return tripleLongArrayList;
    }

    public void add(TripleLong tripleLong) {
        super.addToTripledSet(tripleLong);
        tripleLongArrayList.add(tripleLong);
    }

    public void add(Group group) {
        super.addToTripledSet(group.getTripleLongHashSet());
        tripleLongArrayList.add(group.getTripleLongArrayList());
    }

    public Group getGroupAndRemove(TripleLong tripleLong) {
        TripleLongHashSet tripleLongHashSet = new TripleLongHashSet(tripleLong);
        TripleLongArrayList tripleLongArrayList = new TripleLongArrayList(tripleLong);
        Long a = tripleLong.getA();
        Long b = tripleLong.getB();
        Long c = tripleLong.getC();
        if (containsByA(a)){
            TripleLong tl = this.tripleLongArrayList.removeRowByAValue(a);
            getTripleLongHashSet().remove(tl);
            tripleLongHashSet.add(tl);
            tripleLongArrayList.add(tl);
        }
        if (containsByB(b)){
            TripleLong tl = this.tripleLongArrayList.removeRowByBValue(b);
            getTripleLongHashSet().remove(tl);
            tripleLongHashSet.add(tl);
            tripleLongArrayList.add(tl);
        }
        if (containsByC(c)){
            TripleLong tl = this.tripleLongArrayList.removeRowByCValue(c);
            getTripleLongHashSet().remove(tl);
            tripleLongHashSet.add(tl);
            tripleLongArrayList.add(tl);
        }
        removeFromTripledSet(tripleLong);
        return new Group(tripleLongHashSet,tripleLongArrayList);
    }
}


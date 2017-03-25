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
        tripleLongArrayList.Add(tripleLong);
    }

    public Group(TripleLongHashSet tlhs, TripleLongArrayList tripleLongArrayList) {
        super(tlhs);
        this.tripleLongArrayList = tripleLongArrayList;
    }

    public TripleLongArrayList getTripleLongArrayList() {
        return tripleLongArrayList;
    }

    public void Add(TripleLong tripleLong) {
        super.AddToTripledSet(tripleLong);
        tripleLongArrayList.Add(tripleLong);
    }

    public void Add(Group group) {
        super.AddToTripledSet(group.getTripleLongHashSet());
        tripleLongArrayList.Add(group.getTripleLongArrayList());
    }

    public Group GetGroupAndRemove(TripleLong tripleLong) {
        TripleLongHashSet tripleLongHashSet = new TripleLongHashSet(tripleLong);
        TripleLongArrayList tripleLongArrayList = new TripleLongArrayList(tripleLong);
        Long a = tripleLong.getA();
        Long b = tripleLong.getB();
        Long c = tripleLong.getC();
        if (ContainsByA(a)){
            TripleLong tl = this.tripleLongArrayList.RemoveRowByAValue(a);
            getTripleLongHashSet().Remove(tl);
            tripleLongHashSet.Add(tl);
            tripleLongArrayList.Add(tl);
        }
        if (ContainsByB(b)){
            TripleLong tl = this.tripleLongArrayList.RemoveRowByBValue(b);
            getTripleLongHashSet().Remove(tl);
            tripleLongHashSet.Add(tl);
            tripleLongArrayList.Add(tl);
        }
        if (ContainsByC(c)){
            TripleLong tl = this.tripleLongArrayList.RemoveRowByCValue(c);
            getTripleLongHashSet().Remove(tl);
            tripleLongHashSet.Add(tl);
            tripleLongArrayList.Add(tl);
        }
        RemoveFromTripeledSet(tripleLong);
        return new Group(tripleLongHashSet,tripleLongArrayList);
    }
}


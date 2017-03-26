package model.groups;

import model.TripleLong;
import model.TripleLongArrayList;
import model.TripleLongHashSet;

public class Group {

    private TripleLongHashSet tripleLongHashSet;
    private TripleLongArrayList tripleLongArrayList;

    public Group() {
        tripleLongHashSet = new TripleLongHashSet();
        tripleLongArrayList = new TripleLongArrayList();
    }

    public Group(TripleLong tripleLong) {
        tripleLongHashSet = new TripleLongHashSet(tripleLong);
        tripleLongArrayList = new TripleLongArrayList(tripleLong);
    }

    public Group(TripleLongHashSet tripleLongHashSet, TripleLongArrayList tripleLongArrayList) {
        this.tripleLongHashSet = new TripleLongHashSet();
        this.tripleLongHashSet.add(tripleLongHashSet);
        this.tripleLongArrayList = tripleLongArrayList;
    }

    public TripleLongArrayList getTripleLongArrayList() {
        return tripleLongArrayList;
    }

    public TripleLongHashSet getTripleLongHashSet() {
        return tripleLongHashSet;
    }

    public void add(TripleLong tripleLong) {
        if (!isAlreadyContains(tripleLong)) {
            tripleLongHashSet.add(tripleLong);
            tripleLongArrayList.add(tripleLong);
        }
    }

    public void add(Group group) {
        tripleLongHashSet.add(group.getTripleLongHashSet());
        tripleLongArrayList.add(group.getTripleLongArrayList());
    }

    public Group getGroupAndRemove(TripleLong tripleLong) {
        TripleLongHashSet tripleLongHashSet = new TripleLongHashSet(tripleLong);
        TripleLongArrayList tripleLongArrayList = new TripleLongArrayList(tripleLong);
        Long a = tripleLong.getA();
        if (this.tripleLongHashSet.containsByA(a)){
            TripleLong tl = this.tripleLongArrayList.removeRowByAValue(a);
            getTripleLongHashSet().remove(tl);
            tripleLongHashSet.add(tl);
            tripleLongArrayList.add(tl);
        }
        Long b = tripleLong.getB();
        if (this.tripleLongHashSet.containsByB(b)){
            TripleLong tl = this.tripleLongArrayList.removeRowByBValue(b);
            getTripleLongHashSet().remove(tl);
            tripleLongHashSet.add(tl);
            tripleLongArrayList.add(tl);
        }
        Long c = tripleLong.getC();
        if (this.tripleLongHashSet.containsByC(c)){
            TripleLong tl = this.tripleLongArrayList.removeRowByCValue(c);
            getTripleLongHashSet().remove(tl);
            tripleLongHashSet.add(tl);
            tripleLongArrayList.add(tl);
        }
        this.tripleLongHashSet.remove(tripleLong);
        return new Group(tripleLongHashSet,tripleLongArrayList);
    }

    public boolean isUnique(TripleLong tripleLong) {
        Long a = tripleLong.getA();
        Long b = tripleLong.getB();
        Long c = tripleLong.getC();
        if (a != null && tripleLongHashSet.containsByA(a))
            return false;
        if (b != null && tripleLongHashSet.containsByB(b))
            return false;
        if (c != null && tripleLongHashSet.containsByC(c))
            return false;
        return true;
    }

    public boolean isIntersect(Group group) {
        if (tripleLongHashSet.isRetain(group.getTripleLongHashSet()))
            return true;
        return false;
    }

    public boolean isAlreadyContains(TripleLong tripleLong) {
        boolean b1 = tripleLongHashSet.containsByA(tripleLong.getA());
        boolean b2 = tripleLongHashSet.containsByB(tripleLong.getB());
        boolean b3 = tripleLongHashSet.containsByC(tripleLong.getC());
        if (b1 && b2 && b3) {
            for (int i = 0; i < tripleLongArrayList.size(); i++)
                if (tripleLong.equals(tripleLongArrayList.get(i)))
                    return true;
        }
        if ((b1 && b2 && tripleLong.getC()== null) ||
                (b2 && b3 && tripleLong.getA()== null) ||
                (b1 && b3 && tripleLong.getB()== null) ||
                (b1 && tripleLong.getB()==null && tripleLong.getC()==null) ||
                (b2 && tripleLong.getA()==null && tripleLong.getC()==null) ||
                (b3 && tripleLong.getB()==null && tripleLong.getA()==null))
            return true;
        return false;
    }
}


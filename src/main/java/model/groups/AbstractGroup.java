package model.groups;

import model.TripleLong;
import model.TripleLongHashSet;

/**
 * Created by Tom on 23.03.2017.
 */
public abstract class AbstractGroup {

    private TripleLongHashSet tripleLongHashSet;

    public AbstractGroup() {
        tripleLongHashSet = new TripleLongHashSet();
    }

    public AbstractGroup(TripleLong tripleLong) {
        tripleLongHashSet = new TripleLongHashSet();
        tripleLongHashSet.Add(tripleLong);
    }

    public AbstractGroup(TripleLongHashSet tlhs) {
        tripleLongHashSet = new TripleLongHashSet();
        tripleLongHashSet.Merge(tlhs);
    }

    public TripleLongHashSet getTripleLongHashSet() {
        return tripleLongHashSet;
    }

    public boolean IsUnique(TripleLong tripleLong) {
        Long a = tripleLong.getA();
        Long b = tripleLong.getB();
        Long c = tripleLong.getC();
        if (a != 0l && tripleLongHashSet.ContainsByA(a))
            return false;
        if (b != 0l && tripleLongHashSet.ContainsByB(b))
            return false;
        if (c != 0l && tripleLongHashSet.ContainsByC(c))
            return false;
        return true;
    }

    public boolean IsIntersect(AbstractGroup group) {
        TripleLongHashSet tripleLongHashSet = group.tripleLongHashSet;
        if (this.tripleLongHashSet.IsRetain(tripleLongHashSet))
            return true;
        return false;
    }

    public void AddToTripledSet(TripleLong tripleLong){
        tripleLongHashSet.Add(tripleLong);
    }

    public void AddToTripledSet(TripleLongHashSet tl){
        tripleLongHashSet.Merge(tl);
    }

    public boolean RemoveFromTripeledSet(TripleLong tripleLong){
        return tripleLongHashSet.Remove(tripleLong);
    }

    public boolean ContainsByA(Long a){
        return tripleLongHashSet.ContainsByA(a);
    }

    public boolean ContainsByB(Long b){
        return tripleLongHashSet.ContainsByB(b);
    }

    public boolean ContainsByC(Long c){
        return tripleLongHashSet.ContainsByC(c);
    }


}

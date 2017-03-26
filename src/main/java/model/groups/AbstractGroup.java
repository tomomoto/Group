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
        tripleLongHashSet.add(tripleLong);
    }

    public AbstractGroup(TripleLongHashSet tlhs) {
        tripleLongHashSet = new TripleLongHashSet();
        tripleLongHashSet.merge(tlhs);
    }

    public TripleLongHashSet getTripleLongHashSet() {
        return tripleLongHashSet;
    }

    public boolean isUnique(TripleLong tripleLong) {
        Long a = tripleLong.getA();
        Long b = tripleLong.getB();
        Long c = tripleLong.getC();
        if (a != 0l && tripleLongHashSet.containsByA(a))
            return false;
        if (b != 0l && tripleLongHashSet.containsByB(b))
            return false;
        if (c != 0l && tripleLongHashSet.containsByC(c))
            return false;
        return true;
    }

    public boolean isIntersect(AbstractGroup group) {
        TripleLongHashSet tripleLongHashSet = group.tripleLongHashSet;
        if (this.tripleLongHashSet.isRetain(tripleLongHashSet))
            return true;
        return false;
    }

    public void addToTripledSet(TripleLong tripleLong){
        tripleLongHashSet.add(tripleLong);
    }

    public void addToTripledSet(TripleLongHashSet tl){
        tripleLongHashSet.merge(tl);
    }

    public boolean removeFromTripledSet(TripleLong tripleLong){
        return tripleLongHashSet.remove(tripleLong);
    }

    public boolean containsByA(Long a){
        return tripleLongHashSet.containsByA(a);
    }

    public boolean containsByB(Long b){
        return tripleLongHashSet.containsByB(b);
    }

    public boolean containsByC(Long c){
        return tripleLongHashSet.containsByC(c);
    }


}

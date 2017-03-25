package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tom on 23.03.2017.
 */
public class TripleLongHashSet {

    private Set<Long> aLongSet;
    private Set<Long> bLongSet;
    private Set<Long> cLongSet;

    public TripleLongHashSet() {
        aLongSet = new HashSet<>();
        bLongSet = new HashSet<>();
        cLongSet = new HashSet<>();
    }

    public TripleLongHashSet(TripleLong tl){
        aLongSet = new HashSet<>();
        bLongSet = new HashSet<>();
        cLongSet = new HashSet<>();
        Add(tl.getA(), tl.getB(), tl.getC());
    }

    public void Merge(TripleLongHashSet tlhs){
        aLongSet.addAll(tlhs.aLongSet);
        bLongSet.addAll(tlhs.bLongSet);
        cLongSet.addAll(tlhs.cLongSet);
    }

    public void Add(Long a, Long b, Long c){
        if (a != 0L)
            aLongSet.add(a);
        if (b != 0L)
            bLongSet.add(b);
        if (c != 0L)
            cLongSet.add(c);
    }

    public void Add(TripleLong tripleLong){
        Long a = tripleLong.getA();
        Long b = tripleLong.getB();
        Long c = tripleLong.getC();
        if (a != 0L)
            aLongSet.add(a);
        if (b != 0L)
            bLongSet.add(b);
        if (c != 0L)
            cLongSet.add(c);
    }

    public boolean Remove(Long a, Long b, Long c){
        boolean remA=false,remB=false,remC=false;
        if (aLongSet.contains(a)){
            aLongSet.remove(a);
            remA = true;
        }
        if (bLongSet.contains(b)){
            bLongSet.remove(b);
            remB = true;
        }
        if (cLongSet.contains(c)){
            cLongSet.remove(c);
            remC = true;
        }
        return (remA || remB || remC);
    }

    public boolean ContainsByA (Long a){
        return aLongSet.contains(a);
    }

    public boolean ContainsByB (Long b){
        return bLongSet.contains(b);
    }

    public boolean ContainsByC (Long c){
        return cLongSet.contains(c);
    }

    public boolean Remove(TripleLong tripleLong){
        return Remove(tripleLong.getA(), tripleLong.getB(), tripleLong.getC());
    }

    public boolean IsRetain(TripleLongHashSet tripleLongHashSet){
        HashSet<Long> aclone = new HashSet<>(aLongSet);
        aclone.retainAll(tripleLongHashSet.aLongSet);
        if (aclone.size()> 0)
            return true;
        HashSet<Long> bclone = new HashSet<>(bLongSet);
        bclone.retainAll(tripleLongHashSet.bLongSet);
        if (bclone.size()> 0)
            return true;
        HashSet<Long> cclone = new HashSet<>(cLongSet);
        cclone.retainAll(tripleLongHashSet.cLongSet);
        if (cclone.size()> 0)
            return true;
        return false;
    }

}

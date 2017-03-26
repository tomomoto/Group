package model;

import java.util.ArrayList;

public class TripleLongArrayList {

    private ArrayList<Long> aLongArray;
    private ArrayList<Long> bLongArray;
    private ArrayList<Long> cLongArray;

    public TripleLongArrayList() {
        aLongArray = new ArrayList<>();
        bLongArray = new ArrayList<>();
        cLongArray = new ArrayList<>();
    }

    public TripleLongArrayList(TripleLong tripleLong) {
        aLongArray = new ArrayList<>();
        bLongArray = new ArrayList<>();
        cLongArray = new ArrayList<>();
        add(tripleLong);
    }

    public TripleLong get(int index){
        return new TripleLong(aLongArray.get(index), bLongArray.get(index), cLongArray.get(index));
    }

    public boolean add(TripleLong tripleLong){
        boolean addA = aLongArray.add(tripleLong.getA());
        boolean addB = bLongArray.add(tripleLong.getB());
        boolean addC = cLongArray.add(tripleLong.getC());
        return (addA || addB || addC);
    }

    public boolean add(TripleLongArrayList tripleLongArrayList){
        boolean addA = aLongArray.addAll(tripleLongArrayList.aLongArray);
        boolean addB = bLongArray.addAll(tripleLongArrayList.bLongArray);
        boolean addC = cLongArray.addAll(tripleLongArrayList.cLongArray);
        return (addA || addB || addC);
    }

    public void remove(int index){
        aLongArray.remove(index);
        bLongArray.remove(index);
        cLongArray.remove(index);
    }

    public int size() {
        int asize = aLongArray.size();
        int bsize = bLongArray.size();
        int csize = cLongArray.size();
        if (asize == bsize && asize == csize)
            return  asize;
        else
            return 0;
    }

    public TripleLong removeRowByAValue(Long value) {
        int i = aLongArray.indexOf(value);
        TripleLong tripleLong = get(i);
        remove(i);
        return tripleLong;
    }

    public TripleLong removeRowByBValue(Long value) {
        int i = bLongArray.indexOf(value);
        TripleLong tripleLong = get(i);
        remove(i);
        return tripleLong;
    }

    public TripleLong removeRowByCValue(Long value) {
        int i = cLongArray.indexOf(value);
        TripleLong tripleLong = get(i);
        remove(i);
        return tripleLong;
    }
}

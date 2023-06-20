package main.algorithms;

import main.SortArray;

public interface SortingAlgorithmInterface {

    public String getName();

    public long getDelay();

    public void setDelay(long algorithmDelay);

    public void runSort(SortArray array);

}

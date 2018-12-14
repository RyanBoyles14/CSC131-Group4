package CSC131Fall2018.Group4;

import java.io.File;

public class HalsteadBuilder {

    public class Metrics implements IMetrics{
        public File f;
        public int totalOperators;
        public int totalOperands;
        public int vocab;
        public int length;
        public int time;
        public int bugs;
        public int effort;
        public int difficulty;
        public double calcLength;
        public double volume;
    }

    IMetrics metrics;

    public File f;
    public int totalOperators;
    public int totalOperands;
    public int vocab;
    public int length;
    public int time;
    public int bugs;
    public int effort;
    public int difficulty;
    public double calcLength;
    public double volume;

    public IMetrics getMetrics() {
        return this.metrics;
    }

    public String getName(){
        return f.toString();
    }

    public int getOprt(){
        return totalOperators;
    }

    public int getOpnd(){ return totalOperands; }

    public int getVocab(){ return vocab;}

    public int getLength(){ return length;}

    public int getTime(){ return time;}

    public int getBugs(){ return bugs;}

    public int getEffort(){return effort;}

    public int getDiff(){ return difficulty;}

    public double getCalcLength(){ return calcLength;}

    public double getVolume(){ return volume;}
}

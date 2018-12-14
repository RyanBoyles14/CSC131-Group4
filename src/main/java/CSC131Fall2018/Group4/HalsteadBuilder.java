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

    Metrics metrics;

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

    public HalsteadBuilder(){
        metrics = new HalsteadBuilder.Metrics();
        this.metrics.totalOperators = totalOperators;
        this.metrics.totalOperands = totalOperands;
        this.metrics.vocab = vocab;
        this.metrics.length = length;
        this.metrics.time = time;
        this.metrics.bugs = bugs;
        this.metrics.effort = effort;
        this.metrics.difficulty = difficulty;
        this.metrics.calcLength = calcLength;
        this.metrics.volume = volume;
    }

    public IMetrics getMetrics() {
        return this.metrics;
    }

    public String getName(){
        return f.toString();
    }

    public String getOpr(){
        StringBuilder s = new StringBuilder();
        s.append("Total Operators: " + totalOperators);
        s.append("Total Operators: " + totalOperands);
        s.append("Total Operators: " + vocab);
        s.append("Total Operators: " + length);
        s.append("tTotal Operators: " + time);
        s.append("Total Operators: " + bugs);
        s.append("Total Operators: " + effort);
        s.append("Total Operators: " + difficulty);
        s.append("Total Operators: " + calcLength);
        s.append("Total Operators: " + volume);

        return s.toString();
    }
}

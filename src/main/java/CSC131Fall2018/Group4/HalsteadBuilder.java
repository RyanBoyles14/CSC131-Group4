package CSC131Fall2018.Group4;

import java.io.File;

public class HalsteadBuilder {

    public class Metrics implements IMetrics{
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
}

package CSC131Fall2018.Group4;

import java.io.File;

public class HalsteadBuilder {
    public File f;
    int totalOperators;
    int totalOperands;
    int vocab;
    public int length;
    public int time;
    int bugs;
    int effort;
    int difficulty;
    double calcLength;
    double volume;

    public String getName(){
        return f.toString();
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("\n\tTotal Operators: " + totalOperators);
        s.append("\n\tTotal Operators: " + totalOperands);
        s.append("\n\tTotal Operators: " + vocab);
        s.append("\n\tTotal Operators: " + length);
        s.append("\n\tTotal Operators: " + time);
        s.append("\n\tTotal Operators: " + bugs);
        s.append("\n\tTotal Operators: " + effort);
        s.append("\n\tTotal Operators: " + difficulty);
        s.append("\n\tTotal Operators: " + calcLength);
        s.append("\n\tTotal Operators: " + volume);

        return s.toString();
    }
}

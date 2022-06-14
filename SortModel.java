public class SortModel {
    public int count;
    public int index;
    public long time;
    public int[] sortedArray;
    public int[] countLog;
    public long[] timeLog;

    public SortModel(int numRuns) {
        this.count = 0;
        this.index = 0;
        this.time = 0;
        this.countLog = new int[numRuns];
        this.timeLog = new long[numRuns];
    }
}

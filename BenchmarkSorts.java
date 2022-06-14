import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkSorts {

    private final static int NUMBER_OF_RUNS = 50;
    private static SortModel iterative;
    private static SortModel recursive;

    public static void main(String[] args) {
        int[] sizes = { 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000 };
        for (int size : sizes) {
            DoSorts(size);
        }
    }

    private static void DoSorts(int size) {
        int[] array;
        ReportDataModel iterativeData = new ReportDataModel(size, NUMBER_OF_RUNS);
        ReportDataModel recursiveData = new ReportDataModel(size, NUMBER_OF_RUNS);

        for (int i = 0; i < NUMBER_OF_RUNS; i++) {
            array = new int[size];
            for (int j = 0; j < size; j++) {
                Random random = new Random();
                array[j] = random.nextInt(1000);
            }

            // Run the sort and produces output if an unsorted exception is found
            try {
                ReportModel[] datas = runSorts(array, size);
                iterativeData.dataModels[i] = datas[0];
                recursiveData.dataModels[i] = datas[1];
            } catch (UnsortedException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            generateReport(iterativeData, "Reports/iterativeInput.txt");
            generateReport(recursiveData, "Reports/recursiveInput.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static ReportModel[] runSorts(int[] array, int numRuns) throws UnsortedException {
        int[] itArray = array.clone();
        int[] recArray = array.clone();

        // Runs iterative sort
        iterative = new SortModel(numRuns);
        iterative = runSort(itArray, iterative, numRuns, true);
        ReportModel itData = new ReportModel(iterative.count, iterative.time);

        // Runs recursive sort
        recursive = new SortModel(numRuns);
        recursive = runSort(recArray, recursive, numRuns, false);
        ReportModel recData = new ReportModel(recursive.count, recursive.time);

        return new ReportModel[] { itData, recData };
    }

    private static SortModel runSort(int[] array, SortModel model, int numRuns, boolean isIterative)
            throws UnsortedException {
        MergeSort mergeSort = new MergeSort();
        model.sortedArray = (isIterative) ? mergeSort.iterativeSort(array) : mergeSort.recursiveSort(array);
        int returnCount = mergeSort.getCount();
        long returnTime = mergeSort.getTime();
        model.count = model.count + returnCount;
        model.time = model.time + returnTime;
        model.countLog[model.index] = returnCount;
        model.timeLog[model.index] = returnTime;
        model.index++;
        return model;
    }

    private static void generateReport(ReportDataModel reportData, String fileName) throws IOException {
        String newLine = "";
        newLine += reportData.size + " ";
        for (ReportModel model : reportData.dataModels) {
            newLine += model.toString() + " ";
        }

        FileWriter fileWriter = new FileWriter(fileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(newLine);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
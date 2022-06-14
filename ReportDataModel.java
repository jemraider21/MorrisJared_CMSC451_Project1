public class ReportDataModel {
    public int size;
    public ReportModel[] dataModels;
    public String string;

    public ReportDataModel(int sizeOfSet, int numSets) {
        this.size = sizeOfSet;
        this.dataModels = new ReportModel[numSets];
    }
}

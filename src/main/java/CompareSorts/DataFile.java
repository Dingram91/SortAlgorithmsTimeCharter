package CompareSorts;


import java.nio.file.Path;

public class DataFile {
  private Path filePath;
  private int fileLength;
  private int maxNumber;
  private DATAORDER sortOrder;

  public DataFile(Path filePath, int fileLength, int maxNumber, DATAORDER sortOrder) {
    this.filePath = filePath;
    this.fileLength = fileLength;
    this.maxNumber = maxNumber;
    this.sortOrder = sortOrder;
  }

  public Path getFilePath() {
    return filePath;
  }

  public void setFilePath(Path filePath) {
    this.filePath = filePath;
  }

  public int getFileLength() {
    return fileLength;
  }

  public void setFileLength(int fileLength) {
    this.fileLength = fileLength;
  }

  public int getMaxNumber() {
    return maxNumber;
  }

  public void setMaxNumber(int maxNumber) {
    this.maxNumber = maxNumber;
  }

  public DATAORDER getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(DATAORDER sortOrder) {
    this.sortOrder = sortOrder;
  }
}

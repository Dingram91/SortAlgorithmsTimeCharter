package CompareSorts;

public class MethodLog {
  private DATAORDER dataOrder;
  private int dataLength;
  private ALGORITHM algorithm;
  private Long sortTime;
  private int maxNumber;

  public MethodLog(DATAORDER dataOrder, int dataLength, ALGORITHM algorithm, Long sortTime,
      int maxNumber) {
    this.dataOrder = dataOrder;
    this.dataLength = dataLength;
    this.algorithm = algorithm;
    this.sortTime = sortTime;
    this.maxNumber = maxNumber;
  }

  public DATAORDER getDataOrder() {
    return dataOrder;
  }

  public void setDataOrder(DATAORDER dataOrder) {
    this.dataOrder = dataOrder;
  }

  public ALGORITHM getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(ALGORITHM algorithm) {
    this.algorithm = algorithm;
  }

  public Long getSortTime() {
    return sortTime;
  }

  public void setSortTime(Long sortTime) {
    this.sortTime = sortTime;
  }

  public int getDataLength() {
    return dataLength;
  }

  public void setDataLength(int dataLength) {
    this.dataLength = dataLength;
  }

  public int getMaxNumber() {
    return maxNumber;
  }

  public void setMaxNumber(int maxNumber) {
    this.maxNumber = maxNumber;
  }

  @Override
  public String toString() {
    return "Data Length: " + dataLength + " Data Order: " + dataOrder.name()
        + " Max Number: " + maxNumber + " Algorithm: " + algorithm.name() + " Execution Time(ns):" + sortTime;
  }
}

package CompareSorts;

import java.io.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartUtilities;

public class AnalysisChart {

  private static XYSeriesCollection createDataSet (ArrayList<MethodLog> mlogs) {
    HashMap<String, XYSeries> series = new HashMap<>();
    for(ALGORITHM alg : ALGORITHM.values()) {
      for(DATAORDER order : DATAORDER.values())
        series.put(alg.name() + "-" + order.name(), new XYSeries(alg.name() + "-" + order.name()));
    }

    for(MethodLog mlog : mlogs) {
      series.get(mlog.getAlgorithm().name() + "-" + mlog.getDataOrder().name()).add(mlog.getDataLength(), Math.log10(mlog.getSortTime()));
    }

    final XYSeriesCollection dataset = new XYSeriesCollection( );
    for(String s : series.keySet()) {
      dataset.addSeries(series.get(s));
    }

    return dataset;
  }

  public static void makeGraph(ArrayList<MethodLog> mlogs) {
    XYSeriesCollection dataSet = createDataSet(mlogs);

    JFreeChart xylineChart = ChartFactory.createXYLineChart(
        "Algorithm sort time statastics",
        "Numbers to Sort",
        "Log Sort Time (ns)",
        dataSet,
        PlotOrientation.VERTICAL,
        true, true, false);

    int width = 640;   /* Width of the image */
    int height = 480;  /* Height of the image */
    File XYChart = new File( "SortTimesChart.jpeg" );
    try {
      ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
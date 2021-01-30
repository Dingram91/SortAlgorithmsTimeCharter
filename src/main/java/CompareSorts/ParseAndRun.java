package CompareSorts;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class ParseAndRun {

  private static final int DEFAULTNUMBERLENGTH = 10;
  private static final int DEFAULTNUMBERCOUNT = 1000;


  /**
   * This method parses the command line arguments and returns a Hashmap of argument keys (denoted
   * with a "-") connected to stack containing the value(s) for that key
   *
   * @param args Command line arguments to parse
   * @return HashMap<String, Stack < String>> commands and value lists
   */
  private static HashMap<String, Stack<String>> parseCommands(String[] args) {
    HashMap<String, Stack<String>> commands = new HashMap<String, Stack<String>>();
    String openArg = null;
    for (String arg : args) {
      if (arg.contains("-")) {
        openArg = arg;
        if (!commands.containsKey(openArg))
          commands.put(openArg, new Stack<String>());
      } else {
        Stack<String> commandList = commands.get(openArg);
        commandList.push(arg);
        commands.put(openArg, commandList);
      }
    }
    return commands;
  }

  /**
   * Converts a stack of strings to a stack of integers
   *
   * @param stringStack
   * @return Stack<Integer>
   */
  private static Stack<Integer> stringStackToIntegerStack(Stack<String> stringStack) {
    Stack<Integer> integerStack = new Stack<>();
    while (!stringStack.empty()) {
      integerStack.push(Integer.parseInt(stringStack.pop()));
    }
    return integerStack;
  }

  /**
   * Parses the command line arguments, then generates the appropriate data and runs the specified
   * sort algorithms
   *
   * @param args Command line arguments
   * @return
   */
  private static ArrayList<DataFile> makeDataFiles(String[] args) {
    HashMap<String, Stack<String>> parsedArgs = parseCommands(args);

    ArrayList<DataFile> dataFiles = new ArrayList<>();

    HashMap<String, Long> executionTimeLog = new HashMap<>();

    // generate data
    Stack<Integer> numberLengths = new Stack<>();
    if (parsedArgs.containsKey("-numberLength"))
      numberLengths = stringStackToIntegerStack(parsedArgs.get("-numberLength"));
    if (numberLengths.empty()) // if no numberLength specified use the default
      numberLengths.push(DEFAULTNUMBERLENGTH);

    Stack<Integer> numberCount = new Stack<>();
    if (parsedArgs.containsKey("-numberCount"))
      numberCount = stringStackToIntegerStack(parsedArgs.get("-numberCount"));
    if (numberLengths.empty()) // if no numberLength specified use the default
      numberCount.push(DEFAULTNUMBERCOUNT);

    Stack<String> preSorts = new Stack<>();
    if (parsedArgs.containsKey("-preSort"))
      preSorts = parsedArgs.get("-preSort");
    if (preSorts.empty()) // if no presorts specified use the default
      preSorts.push("random");


    for (Integer numberC : numberCount) {
      for (Integer numberL : numberLengths) {
        for (String preSort : preSorts) {
          DATAORDER order;
          if (preSort.toLowerCase().equals("forward"))
            order = DATAORDER.DESCENDING;
          else if (preSort.toLowerCase().equals("reverse"))
            order = DATAORDER.ASCENDING;
          else
            order = DATAORDER.RANDOM;

          dataFiles.add(new DataFile(DataGenerator.createRandomDataFile(  numberC +
              "." + preSort + ".txt", numberC, numberL, order), numberC, numberL, order));
        }
      }
    }


    return dataFiles;
  }

  private static void generateLogFile(ArrayList<DataFile> dataFiles) {
    ArrayList<MethodLog> methodLogs = new ArrayList<>();

    for (DataFile file : dataFiles) {
      for (ALGORITHM alg : ALGORITHM.values()) {
        int[] data = DataGenerator.getIntArrayFromFile(file.getFilePath());
        methodLogs.add(new MethodLog(file.getSortOrder(), file.getFileLength(), alg,
            logExecutionTime(data, alg), file.getMaxNumber()));
      }

    }

    // create the log file
    File logFile = new File("log.txt");
    try {
      logFile.createNewFile();

      BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));

      for(MethodLog l : methodLogs) {
        writer.write(l.toString() + System.lineSeparator());
      }

      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Could not make log file!");
    }

    //
    //todo: only print this with some mode selection like verbose mode
    // print logs
    for(MethodLog l : methodLogs) {
      System.out.println(l);
    }

    AnalysisChart.makeGraph(methodLogs);
  }



  private static Long logExecutionTime(int[] data, ALGORITHM algorithm) {
    Long start = System.nanoTime();
    Long end = System.nanoTime();
    switch (algorithm) {
      case MERGE:
        start = System.nanoTime();
        SortAlgorithms.mergeSort(data);
        end = System.nanoTime();
        break;
      case INSERTION:
        start = System.nanoTime();
        SortAlgorithms.insertionSort(data);
        end = System.nanoTime();
        break;
    }

    return end-start;

  }

  private static void runCommands(String[] args) {
    generateLogFile(makeDataFiles(args));
  }


  public static void main(String[] args) {
    if (args.length > 1) {
      runCommands(args);
    } else {
      System.out.println("This program tests the sort time of lists of 4 byte Integers");
      System.out.println("Example input:");
      System.out.println("java -jar SortAlgorithmsTimeChart -numberCount 50 500 5000 \n"
          + "-numberLength 1000 5000 -preSort random reverse forward");
      System.out.println("This input would test on 3 lengths of data (50, 500, 5000) 2 lengths of \n"
          + "max number lengths (1000, 5000) and with 3 different presorts (random, reverse, \n"
          + "forward) \n Therefore this will test on 18 different sets of data (3*2*3) \n"
          + "Once complete the program will save a JPG in the same directory showing a graph of the"
          + "\n runtimes for the various data and algorithms");
    }



  }

}

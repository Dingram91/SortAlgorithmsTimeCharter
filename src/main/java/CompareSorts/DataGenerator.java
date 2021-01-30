package CompareSorts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class DataGenerator {


  /**
   * Generates an array of int values and writes it to a binary file
   * @param fileName name of file to generate
   * @param numberCount number of ints in the generated array
   * @param maxNumberSize maximum size of int generated
   */
  public static Path createRandomDataFile(String fileName, int numberCount, int maxNumberSize,
      DATAORDER dataOrder) {
    File dataDir = new File("data/");
    File newFile = new File(dataDir.getPath() + "/" + fileName);
    try {
      // make data dir if needed
      dataDir.mkdirs();
      if(newFile.createNewFile())
        System.out.println("Created new File: " + newFile.getName());
      FileOutputStream fos = new FileOutputStream(newFile);

      // generate the data
      Random rand = new Random();
      Integer[] data = new Integer[numberCount];
      for(int i = 0; i < numberCount; i++) {
        data[i] = rand.nextInt(maxNumberSize);
      }

      // presort data if necessary
      switch (dataOrder) {
        case DESCENDING:
          Arrays.sort(data);
          break;
        case ASCENDING:
          Arrays.sort(data, Collections.reverseOrder());
          break;
        default:
      }

      // write the data to file
      for(int i = 0; i < numberCount; i++) {
        byte[] bytes = ByteBuffer.allocate(4).putInt(data[i]).array();
        fos.write(bytes);
      }
      fos.close();

    } catch (IOException ex) {
      ex.printStackTrace();
    }

    return newFile.toPath();

  }

  /**
   * Reads and returns an array of unt values from a file
   * @param filePath path of the file to read from
   * @return array of integers
   */
  public static int[] getIntArrayFromFile(Path filePath) {

    try {
      byte[] bytes = Files.readAllBytes(filePath);

      IntBuffer intBuf =
          ByteBuffer.wrap(bytes)
              .order(ByteOrder.BIG_ENDIAN)
              .asIntBuffer();
      int[] array = new int[intBuf.remaining()];
      intBuf.get(array);
      return array;

    } catch (IOException e) {
      e.printStackTrace();
    }

    return new int[0];
  }


}

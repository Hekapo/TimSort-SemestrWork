package ClassWork.SemestrWork;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Generator {
    int row = 100;

    private int[] randomElements(int size) {
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = 100 + (new Random().nextInt(9900));
        }
        return a;
    }

    public int[] reader(String filename, int testNumber, int length) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(filename)).withSkipLines(1).build();

        ArrayList<String> arr = new ArrayList<>();
        String[] myArray;
        while ((myArray = reader.readNext()) != null) {
            arr.add(myArray[testNumber]);

        }

        int[] intArr = new int[arr.size() + length - arr.size()];
        for (int i = 0; i < arr.size() + length - arr.size(); i++) {
            intArr[i] = Integer.parseInt(arr.get(i));

        }
        reader.close();
        return intArr;
    }


    public void writer(String file) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(file), ',', '"', '"', ",\n");

        int[] headInt = IntStream.range(1, row + 1).toArray();
        String[] headStr = Arrays.stream(headInt).mapToObj(String::valueOf).toArray(String[]::new);
        csvWriter.writeNext(headStr, false);

        int[] arrInt = null;
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < row + 1; j++) {
                arrInt = randomElements(j);

            }
            String[] arrStr = Arrays.stream(arrInt).mapToObj(String::valueOf).toArray(String[]::new);
            csvWriter.writeNext(arrStr, false);
        }

        csvWriter.close();

    }

    public void answerWriter(String file) throws IOException, CsvValidationException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(file, false), ',', '"', '"', "\n");
        String[] head = {"TestCounter", "Size", "Time", "Iterations"};
        Generator g = new Generator();
        String data = "C:\\ClassWork\\ihsanov_11013\\ClassWork\\SemestrWork\\data.csv";

        csvWriter.writeNext(head, false);

        int countLength = 0;
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int[] arrData = g.reader(data, i, 100 + countLength);
            int n = arrData.length + 100 + countLength - arrData.length;

            list.add(String.valueOf(i));
            list.add(String.valueOf(100 + countLength));

            TimSort.iterations = 0;
            long start = System.nanoTime();

            TimSort.timSort(arrData, n);

            long end = System.nanoTime() - start;


            int iter = TimSort.iterations;


            list.add(String.valueOf(end));
            list.add(String.valueOf(iter));

            String[] arr = list.toArray(String[]::new);

            csvWriter.writeNext(arr, false);

            list.clear();

            countLength += 100;


        }

        csvWriter.close();

    }


}


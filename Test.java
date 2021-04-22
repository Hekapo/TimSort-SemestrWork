package ClassWork.SemestrWork;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, CsvValidationException {
        String data = "C:\\ClassWork\\ihsanov_11013\\ClassWork\\SemestrWork\\data.csv";
        String dataTable = "C:\\ClassWork\\ihsanov_11013\\ClassWork\\SemestrWork\\dataTable.csv";
        Generator g = new Generator();

        //g.writer(data);
        g.answerWriter(dataTable);



    }
}

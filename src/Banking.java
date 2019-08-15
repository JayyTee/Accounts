/*
*Cant deposit negative numbers
*validate withrawals and deposits (positive inputs only)
*allow 2-point decimal format for all values
 */
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;

public class Banking {
    File logs = new File("log.html");
    Document doc;
    int total = 0;

     public Banking(File log) throws IOException{
         int i = 1;//iterator
         this.doc =  Jsoup.parse(logs, "UTF-8");
         // include log.html

         ArrayList<Integer> list = new ArrayList<Integer>();


        //insert after first td
         doc.select("td:nth-of-type(1)").after("<td>800</td>");

         //select data table from html
         Element table = doc.select("table").get(0); //select the first table.
         Elements rows = table.select("td");


         System.out.println(doc);
        //doc.select("table").select("td").get(0).after("<td>800<td>");


        while(i < rows.size()){
            list.add(Integer.parseInt(rows.get(i).html()));//insert element i into arraylist;
            total += Integer.parseInt(rows.get(i).html());
            i++;
        }

         System.out.println(total);

         doc.select(".balance").html(Integer.toString(total));
         System.out.println(list);

         BufferedWriter bw = new BufferedWriter(new FileWriter(logs));
         bw.write(doc.toString()); //toString will give all the elements as a big string
         bw.close();  //Close to apply the changes
//        int number = Integer.parseInt(rows.get(1).text());
//        String content = rows.html();
//        System.out.print(rows.length());
    }

    public int getbalance(){
        return total;
    }
}

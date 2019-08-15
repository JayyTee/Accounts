/*
*round value to 2 decimal places before inserting into table
 */
import java.io.*;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.text.DecimalFormat;
import java.math.*;

public class Banking {
   private File log;
   private Document doc; //parsed log.html file
   private double total;
   private DecimalFormat df = new DecimalFormat("####0.00");

    public Banking(File log) throws IOException{
        this.log = log;
        this.doc =  Jsoup.parse(log, "UTF-8");
        this.total = Double.parseDouble(doc.select("td:nth-of-type(1)").html());
    }

    //insert changes into log.html
    boolean updateLog()throws IOException{
        doc.select(".balance").html(Double.toString(total)); //insert new balance into table
        BufferedWriter bw = new BufferedWriter(new FileWriter(log));
        bw.write(doc.toString()); //update table
        bw.close();
        return true;
    }

    //make deposit
    public boolean deposit(String amount)throws IOException{
        double parsedAmount = validate(amount);

        if (parsedAmount == 0)
            return false;
        else{
            total += parsedAmount;

            doc.select("td:nth-of-type(1)").after("<td>" + parsedAmount + "</td>"); //insert after first td

            updateLog();
            return true;
        }
    }

    public boolean withdraw(String amount) throws IOException{
        double parsedAmount = validate(amount);
        if (parsedAmount == 0)
            return false;
        else{
            total -= parsedAmount;

            doc.select("td:nth-of-type(1)").after("<td>" + parsedAmount + "</td>"); //insert after first td

            updateLog();
            return true;
        }
    }

    //validate input
    public double validate(String amount){
        double n;

        try{
            n = Double.parseDouble(amount);

        }
        catch(Exception e)
        {
            return 0;
        }

        if(BigDecimal.valueOf(n).scale() > 2)
            return 0;

        if(n > -1)
            return n;
        else{
            return 0;
        }
    }

    public double getbalance(){
        return total;
    }
}

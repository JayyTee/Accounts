/*
*Add file-open validation
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

    //return value of validated inputs
    public boolean deposit(String amount)throws IOException{
        double parsedAmount = validate(amount);

        if (parsedAmount == 0)
            return false;
        else{
            updateLog(parsedAmount);
            return true;
        }
    }

    //parse and validate string input using deposit() and return negated value
    public boolean withdraw(String amount) throws IOException{
        double parsedAmount = validate(amount);

        if (parsedAmount == 0)
            return false;
        else{
            updateLog(parsedAmount * -1);
            return true;
        }
    }

    //insert changes into parsed document and update log.html
    boolean updateLog(double parsedAmount)throws IOException{
        total += parsedAmount;
        doc.select("td:last-of-type").after("<td>" + parsedAmount + "</td>"); //insert after first td

        doc.select(".balance").html(df.format(total)); //insert new balance into table
        BufferedWriter bw = new BufferedWriter(new FileWriter(log));
        bw.write(doc.toString()); //update table
        bw.close();
        return true;
    }

    //validate input (return 0 if number has more than 2 decimals or is negative else return parsed input)
    public double validate(String amount){
        double n;

        try{
            n = Double.parseDouble(amount);

        }
        catch(Exception e){
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

    public String getbalance(){
        return df.format(total);
    }
}

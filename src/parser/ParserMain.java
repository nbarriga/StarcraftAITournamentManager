package parser;

import server.ServerSettings;
import utility.ResultsParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by nbarriga on 26/05/2015.
 */
public class ParserMain {
    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            ServerSettings.Instance().parseSettingsFile(args[0], false);
        } else {
            System.err.println("\n\nPlease provide server settings file as command line argument.\n");
            System.exit(-1);
        }
        writeHTMLFiles();
    }

    public static void writeHTMLFiles() throws Exception
    {
        try
        {
            ResultsParser rp = new ResultsParser(ServerSettings.Instance().ResultsFile, false);

            String entrantsHTML = rp.getEntrantsHTML();
            String headerHTML = rp.getHeaderHTML();
            String footerHTML = rp.getFooterHTML();
            String resultsHTML = rp.getResultsHTML();

            writeHTMLFile(rp.getAllResultsHTML(), "html/results.html");

            writeHTMLFile(headerHTML + entrantsHTML + resultsHTML + footerHTML, "html/index.html");
        }
        catch (Exception e)
        {

        }
    }

    public static void writeHTMLFile(String html, String filename) throws Exception
    {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(html);
        bw.close();
        fw.close();
    }
}

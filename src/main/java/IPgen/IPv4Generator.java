package IPgen;

import au.com.bytecode.opencsv.CSVReader;
import java.io.*;
import java.util.List;

public class IPv4Generator {
    protected File IPList;
    protected boolean append; // append or rewrite file
    protected int IPN;

    // parse IPs amd mask from string to int[]-----------------------------------------------------------------------------
    protected int[] parseIP(String ip) {
        int[] IP = new int[4]; // IPv4 IP[0].IP[1].IP[2].IP[3]
        String[] stringIP = ip.split("\\.");
        for (int i = 0; i < 4; i++) {
            IP[i] = Integer.parseInt(stringIP[i]);
        }
        return IP;
    }
//---------------------------------------------------------------------------------------------------------------------

    //Constructor initiates file-------------------------------------------------------------------------------------------
    public IPv4Generator(String fileName, boolean append, int IPN) {
        this.IPList = new File(fileName);
        this.append = append;
        this.IPN = IPN;
    }
//---------------------------------------------------------------------------------------------------------------------

    //Default constructor--------------------------------------------------------------------------------------------------
    public IPv4Generator() {
        this.IPList = null;
        this.append = false;
        this.IPN = 1;
    }
//---------------------------------------------------------------------------------------------------------------------

    // gen IPs by int[] and count------------------------------------------------------------------------------------------
    protected void genInt(int[] IP, int count) {
        try {
            FileWriter fileWriter = new FileWriter(this.IPList, append);
            BufferedWriter bf = new BufferedWriter(fileWriter);
            int i = 0;
            while (i < count) {
                for (int a = IP[0]; a <= 255; a++) {
                    for (int b = IP[1]; b <= 255; b++) {
                        for (int c = IP[2]; c <= 255; c++) {
                            for (int d = IP[3]; d <= 255; d++) {
                                if (i >= count) break;
                                bf.write("IP" + (IPN + i) + ";" + a + "." + b + "." + c + "." + d + "\n");
                                if (i % 1000000 == 0) {
                                    bf.flush();
                                }
                                i++;
                            }
                            if (i >= count) break;
                            IP[3] = 0;
                        }
                        if (i >= count) break;
                        IP[2] = 0;
                    }
                    if (i >= count) break;
                    IP[1] = 0;
                }
            }
            bf.flush();
            bf.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }
    }
//---------------------------------------------------------------------------------------------------------------------

    // generate IPList by first IP and a number of IPs---------------------------------------------------------------------
    public void genNIPs(String firstIP, int count) {
        int[] IP = parseIP(firstIP);
        genInt(IP, count);
    }
//---------------------------------------------------------------------------------------------------------------------

    // generate by range of IPs--------------------------------------------------------------------------------------------
    public void genRange(String firstIP, String lastIP) {
        int[] fIP = parseIP(firstIP);
        int[] lIP = parseIP(lastIP);
        int count = 1 + (lIP[3] - fIP[3]) + 256 * (lIP[2] - fIP[2]) + 256 * 256 * (lIP[1] - fIP[1]) + 256 * 256 * 256 * (lIP[0] - fIP[0]);
        genInt(fIP, count);
    }
//---------------------------------------------------------------------------------------------------------------------

    //generate IPs by subnet (full mask)-----------------------------------------------------------------------------------
    public void genFullMask(String IP, String netmask) {
        int[] IParr = parseIP(IP);
        int[] netmaskArr = parseIP(netmask);
        int[] fIP = new int[4];
        int[] lIP = new int[4];
        for (int i = 0; i < 4; i++) {
            fIP[i] = IParr[i] & netmaskArr[i];
            lIP[i] = fIP[i] + (255 - netmaskArr[i]);
        }
        if (netmaskArr[3] == 0) {
            fIP[3]++;
            lIP[3]--;
        }
        int count = 1 + (lIP[3] - fIP[3]) + 256 * (lIP[2] - fIP[2]) + 256 * 256 * (lIP[1] - fIP[1]) + 256 * 256 * 256 * (lIP[0] - fIP[0]);
        genInt(fIP, count);
    }
//---------------------------------------------------------------------------------------------------------------------


    // generate by prefix--------------------------------------------------------------------------------------------------
    public void genPrefix(String IPpref) throws Exception {
        String[] stringIP = IPpref.split("/");
        List<String[]> allRows;
        CSVReader reader = new CSVReader(new FileReader("resources/ipv4prefix.csv"), ',', '"', 1);
        allRows = reader.readAll();
        for (String[] row: allRows) {
            if (stringIP[1].equals(row[0])) {
                genFullMask(stringIP[0], row[1]);
            }
        }
    }
}

//---------------------------------------------------------------------------------------------------------------------


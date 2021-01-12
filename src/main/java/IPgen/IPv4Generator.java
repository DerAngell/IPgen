/**
* This class generates IPList of IPv4
* */

package IPgen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IPv4Generator {
    protected File IPList;
    protected boolean append; // append or rewrite file
    protected int IPN;


    protected int[] parceIP(String ip) {
        int IP[] = new int[4]; // IPv4 IP[0].IP[1].IP[2].IP[3]
        String[] stringIP = ip.split("\\.");
        for (int i=0; i<4; i++) {
            IP[i] = Integer.parseInt(stringIP[i]);
        }
        return IP;

    }

    // TODO generate range of IPs
    // TODO generate subnet

    //Constructor initiates file
    public IPv4Generator(String fileName, boolean append, int IPN) {
        IPList = new File(fileName);
        this.append = append;
        this.IPN = IPN;
    }

    public IPv4Generator(String fileName){
        this(fileName, false, 1);
    }

// generate IPList by first IP and a number of IPs --------------------------------------------------------------------
    public void genNIPs(String firstIP, int count) {
       int[] IP = parceIP(firstIP);
       try {
            FileWriter fileWriter = new FileWriter(this.IPList, append);
            int i = 0;
            while (i < count) {
                for (int a = IP[0]; a <= 255; a++) {
                    for (int b = IP[1]; b <= 255; b++) {
                        for (int c = IP[2]; c <= 255; c++) {
                            for (int d = IP[3]; d <= 255; d ++) {
                                if (i >= count) break;
                                fileWriter.write("IP" + (IPN+i) + "; ");
                                fileWriter.append(a + "." + b + "." + c + "." + d);
                                fileWriter.append("\n");
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
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }
    }
//---------------------------------------------------------------------------------------------------------------------
//tocomplete
// generate by range of IPs--------------------------------------------------------------------------------------------
    public void genRange (String filename, String firstIP, String lastIP) {
        int[] fIP = parceIP(firstIP);
        int[] lIP = parceIP(lastIP);
        try {
            FileWriter fileWriter = new FileWriter(this.IPList, append);
            int i = 0;
            for (int a = fIP[0]; a <= 255; a++) {
                    for (int b = fIP[1]; b <= 255; b++) {
                        for (int c = fIP[2]; c <= 255; c++) {
                            for (int d = fIP[3]; d <= 255; d ++) {
                                fileWriter.write("IP" + (IPN+i) + "; ");
                                fileWriter.append(a + "." + b + "." + c + "." + d);
                                fileWriter.append("\n");
                                i++;
                            }
                            fIP[3] = 0;
                        }
                        fIP[2] = 0;
                    }
                    fIP[1] = 0;
                }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }



    }
//---------------------------------------------------------------------------------------------------------------------
}

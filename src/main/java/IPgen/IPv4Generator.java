package IPgen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IPv4Generator {
    protected File IPList;
    protected boolean append; // append or rewrite file
    protected int IPN;

    // parse IPs amd mask from string to int[]
    protected int[] parseIP(String ip) {
        int[] IP = new int[4]; // IPv4 IP[0].IP[1].IP[2].IP[3]
        String[] stringIP = ip.split("\\.");
        for (int i=0; i<4; i++) {
            IP[i] = Integer.parseInt(stringIP[i]);
        }
        return IP;
    }


   // gen by range via int[] (method for genRange and genFullSubnet)
    protected void genrangeInt (int[] fIP, int[] lIP) {

        try {
            FileWriter fileWriter = new FileWriter(this.IPList, append);
            BufferedWriter bf = new BufferedWriter(fileWriter);
            int i = 0;
            int a,b,c,d;
            for (a = fIP[0]; a < lIP[0]; a++) {
                for (b = fIP[1]; b <= 255; b++) {
                    for (c = fIP[2]; c <= 255; c++) {
                        for (d = fIP[3]; d <= 255; d ++) {
                            bf.write("IP" + (IPN+i) + "; " + a + "." + b + "." + c + "." + d + "\n");
                            i++;
                        }
                        fIP[3] = 0;
                    }
                    fIP[2] = 0;
                }
                fIP[1] = 0;
                if (i % 1000000 == 0) {
                    bf.flush();
                }
            }
            for (b = fIP[1]; b < lIP[1]; b++) {
                for (c = fIP[2]; c <= 255; c++) {
                    for (d = fIP[3]; d <= 255; d++) {
                        bf.write("IP" + (IPN+i) + "; " + a + "." + b + "." + c + "." + d + "\n");
                        i++;
                    }
                    fIP[3] = 0;
                }
                fIP[2] = 0;
                if (i % 1000000 == 0) {
                    bf.flush();
                }
            }
            for (c = fIP[2]; c < lIP[2]; c++) {
                for (d = fIP[3]; d <= 255; d++) {
                    bf.write("IP" + (IPN+i) + "; " + a + "." + b + "." + c + "." + d + "\n");
                    i++;
                }
                fIP[3] = 0;
                if (i % 1000000 == 0) {
                    bf.flush();
                }
            }
            for (d = fIP[3]; d <= lIP[3]; d++) {
                bf.write("IP" + (IPN+i) + "; " + a + "." + b + "." + c + "." + d + "\n");
                if (i % 1000000 == 0) {
                    bf.flush();
                }
                i++;
            }
            bf.flush();
            bf.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }
    }

    //Constructor initiates file
    public IPv4Generator(String fileName, boolean append, int IPN) {
        IPList = new File(fileName);
        this.append = append;
        this.IPN = IPN;
    }


// generate IPList by first IP and a number of IPs --------------------------------------------------------------------
    public void genNIPs(String firstIP, int count) {
       int[] IP = parseIP(firstIP);
       try {
            FileWriter fileWriter = new FileWriter(this.IPList, append);
            BufferedWriter bf = new BufferedWriter(fileWriter);
            int i = 0;
            while (i < count) {
                for (int a = IP[0]; a <= 255; a++) {
                    for (int b = IP[1]; b <= 255; b++) {
                        for (int c = IP[2]; c <= 255; c++) {
                            for (int d = IP[3]; d <= 255; d ++) {
                                if (i >= count) break;
                                bf.write("IP" + (IPN+i) + "; " + a + "." + b + "." + c + "." + d + "\n");
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
                if (i % 1000000 == 0) {
                    bf.flush();
                }
            }
            bf.flush();
            bf.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }
    }

//---------------------------------------------------------------------------------------------------------------------
// generate by range of IPs--------------------------------------------------------------------------------------------
    public void genRange (String firstIP, String lastIP) {
        int[] fIP = parseIP(firstIP);
        int[] lIP = parseIP(lastIP);
        genrangeInt(fIP, lIP);
    }
//---------------------------------------------------------------------------------------------------------------------

//generate IPs by subnet (full mask)-----------------------------------------------------------------------------------
    public void genFullSubnet (String IP, String netmask) {
        int[] IParr = parseIP(IP);
        int[] netmaskArr = parseIP(netmask);
        int[] fIP = new int[4];
        int[] lIP = new int[4];
        for (int i = 0; i<4; i++) {
            fIP[i] = IParr[i] & netmaskArr[i];
            lIP[i] = fIP[i] + (255 - netmaskArr[i]);
        } fIP[3]++; lIP[3]--;
        genrangeInt(fIP, lIP);
    }
//---------------------------------------------------------------------------------------------------------------------
}

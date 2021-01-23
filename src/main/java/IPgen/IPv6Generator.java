package IPgen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IPv6Generator extends IPv4Generator {

    /*
    * Inherited fields
    * File IPList;
    * boolean append;
    * int IPN;
    * */

    //constructor
    public IPv6Generator (String fileName, boolean append, int IPN) {
        this.IPList = new File(fileName);
        this.append = append;
        this.IPN = IPN;
    }


    // parse IPs amd mask from string to int[]
    @Override
    protected int[] parseIP(String ip) {
        int[] IP = new int[8]; // IPv4 IP[0]:IP[1]:IP[2]:IP[3]:IP[4]:IP[5]:IP[6]:IP[7]
        String[] stringIP = ip.split(":");
        for (int i=0; i<8; i++) {
            IP[i] = Integer.parseInt(stringIP[i], 16);
        }
        return IP;
    }

    // gen IPs by int[] and count
    @Override
    protected void genInt(int[] IP, int count) {
        try {
            FileWriter fileWriter = new FileWriter(this.IPList, append);
            BufferedWriter bf = new BufferedWriter(fileWriter);
            int i = 0;
            while (i < count) {
                for (int i0=IP[0]; i0<=65536; i0++) {
                    for (int i1=IP[1]; i1<=65536; i1++) {
                        for (int i2=IP[2]; i2<=65535; i2++) {
                            for (int i3=IP[3]; i3<=65535; i3++) {
                                for (int i4=IP[4]; i4<=65535; i4++) {
                                    for (int i5=IP[5]; i5<=65535; i5++) {
                                        for (int i6=IP[6]; i6<=65535; i6++) {
                                            for (int i7=IP[7]; i7<=65535; i7++) {
                                                if (i >= count) break;
                                                bf.write("IP" + (IPN+i) + ";" +
                                                        String.format("%X", i0) + ":" +
                                                        String.format("%X", i1) + ":" +
                                                        String.format("%X", i2) + ":" +
                                                        String.format("%X", i3) + ":" +
                                                        String.format("%X", i4) + ":" +
                                                        String.format("%X", i5) + ":" +
                                                        String.format("%X", i6) + ":" +
                                                        String.format("%X", i7) + "\n");
                                                if (i % 1000000 == 0) {
                                                    bf.flush();
                                                }
                                                i++;

                                            }
                                            if (i >= count) break;
                                            IP[7] = 0;
                                        }
                                        if (i >= count) break;
                                        IP[6] = 0;
                                    }
                                    if (i >= count) break;
                                    IP[5] = 0;
                                }
                                if (i >= count) break;
                                IP[4] = 0;
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


}

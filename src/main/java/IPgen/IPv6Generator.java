package IPgen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class IPv6Generator extends IPv4Generator {

    /*
    * Inherited fields
    * File IPList;
    * boolean append;
    * int IPN;
    * */

//constructor----------------------------------------------------------------------------------------------------------
    public IPv6Generator (String fileName, boolean append, int IPN) {
        this.IPList = new File(fileName);
        this.append = append;
        this.IPN = IPN;
    }
//---------------------------------------------------------------------------------------------------------------------


// parse IPs amd mask from string to int[]-----------------------------------------------------------------------------
    @Override
    protected int[] parseIP(String ip) {
        int[] IP = new int[8]; // IPv4 IP[0]:IP[1]:IP[2]:IP[3]:IP[4]:IP[5]:IP[6]:IP[7]
        String[] stringIP = ip.split(":");
        for (int i=0; i<8; i++) {
            IP[i] = Integer.parseInt(stringIP[i], 16);
        }
        return IP;
    }
//---------------------------------------------------------------------------------------------------------------------

// gen IPs by int[] and count------------------------------------------------------------------------------------------
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
//---------------------------------------------------------------------------------------------------------------------

    @Override
    public void genRange (String firstIP, String lastIP) {
        int[] fIP = parseIP(firstIP);
        int[] lIP = parseIP(lastIP);
        try {
            FileWriter fileWriter = new FileWriter(this.IPList, append);
            BufferedWriter bf = new BufferedWriter(fileWriter);
            //--------------------------------------------------------------------------------------------
            int i = 0;
            int i0=fIP[0], i1=fIP[1], i2=fIP[2], i3=fIP[3], i4=fIP[4], i5=fIP[5], i6=fIP[6], i7=fIP[7];
            while (i0 < lIP[0]) {
                while (i1 <= 65535) {
                    while (i2 <= 65535) {
                        while (i3 <= 65535) {
                            while (i4 <= 65535) {
                                while (i5 <= 65535) {
                                    while (i6 <= 65535) {
                                        while (i7 <= 65535) {
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
                                            i7++;
                                        }
                                        i7 = 0;
                                        i6++;
                                    }
                                    i6 = 0;
                                    i5++;
                                }
                                i5 = 0;
                                i4++;
                            }
                            i4 = 0;
                            i3++;
                        }
                        i3 = 0;
                        i2++;
                    }
                    i2 = 0;
                    i1++;
                }
                i1 = 0;
                i0++;
            }
            //--------------------------------------------------------------------------------------------
            while (i1 < lIP[1]) {
                while (i2 <= 65535) {
                    while (i3 <= 65535) {
                        while (i4 <= 65535) {
                            while (i5 <= 65535) {
                                while (i6 <= 65535) {
                                    while (i7 <= 65535) {
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
                                        i7++;
                                    }
                                    i7 = 0;
                                    i6++;
                                }
                                i6 = 0;
                                i5++;
                            }
                            i5 = 0;
                            i4++;
                        }
                        i4 = 0;
                        i3++;
                    }
                    i3 = 0;
                    i2++;
                }
                i2 = 0;
                i1++;
            }
            //--------------------------------------------------------------------------------------------
            while (i2 < lIP[2]) {
                while (i3 <= 65535) {
                    while (i4 <= 65535) {
                        while (i5 <= 65535) {
                            while (i6 <= 65535) {
                                while (i7 <= 65535) {
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
                                    i7++;
                                }
                                i7 = 0;
                                i6++;
                            }
                            i6 = 0;
                            i5++;
                        }
                        i5 = 0;
                        i4++;
                    }
                    i4 = 0;
                    i3++;
                }
                i3 = 0;
                i2++;
            }
            //--------------------------------------------------------------------------------------------
            while (i3 < lIP[3]) {
                while (i4 <= 65535) {
                    while (i5 <= 65535) {
                        while (i6 <= 65535) {
                            while (i7 <= 65535) {
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
                                i7++;
                            }
                            i7 = 0;
                            i6++;
                        }
                        i6 = 0;
                        i5++;
                    }
                    i5 = 0;
                    i4++;
                }
                i4 = 0;
                i3++;
            }
            //--------------------------------------------------------------------------------------------
            while (i4 < lIP[4]) {
                while (i5 <= 65535) {
                    while (i6 <= 65535) {
                        while (i7 <= 65535) {
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
                            i7++;
                        }
                        i7 = 0;
                        i6++;
                    }
                    i6 = 0;
                    i5++;
                }
                i5 = 0;
                i4++;
            }
            //--------------------------------------------------------------------------------------------
            while (i5 < lIP[5]) {
                while (i6 <= 65535) {
                    while (i7 <= 65535) {
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
                        i7++;
                    }
                    i7 = 0;
                    i6++;
                }
                i6 = 0;
                i5++;
            }
            //--------------------------------------------------------------------------------------------
            while (i6 < lIP[6]) {
                while (i7 <= 65535) {
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
                    i7++;
                }
                i7 = 0;
                i6++;
            }
            //--------------------------------------------------------------------------------------------
            while (i7 <= lIP[7]) {
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
                i7++;
            }

            bf.flush();
            bf.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }
    }
//---------------------------------------------------------------------------------------------------------------------

    @Override
    public void genFullMask(String IP, String netmask) {
        System.out.println("netmask is not supported for IPv6");
    }
//---------------------------------------------------------------------------------------------------------------------


}

package IPgen;

/*
TODO IPv6 generator (new class)
*/


import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String filename = ""; // filename for IP list
        boolean append = false; // append or rewrite
        int IPN = 1; // the first IP №
        int count=0;
        char choice;
        String firstIP = "";

        // interactive dialog if there is no args --------------------------------------------------------------------
        {
            if (args.length == 0) {
                Scanner scan = new Scanner(System.in);
                System.out.println("1 - Generate list by the first IP and count of IPs\n" +
                        "q - quit\n");
                boolean loop = true;
                while (loop) {
                    choice = scan.next().charAt(0);
                    switch (choice) {
                        case '1':
                            System.out.println("Enter the IPList filename: ");
                            filename = scan.next();
                            System.out.println("Append (y/n)? ");
                            String appnd = scan.next();
                            append = appnd.contains("y") || appnd.contains("Y");
                            System.out.println("Enter the first IP ID: ");
                            IPN = scan.nextInt();
                            System.out.println("Enter the first IP: ");
                            firstIP = scan.next();
                            System.out.println("Enter IP count: ");
                            count = scan.nextInt();
                            IPv4Generator gen = new IPv4Generator(filename, append, IPN);
                            gen.genNIPs(firstIP, count);
                            loop = false;
                            break;
                        case 'q':
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid input");
                    }
                }
            }
        }
        // -----------------------------------------------------------------------------------------------------------

        // run with args
        if (args.length > 0) {

            {
                for (int i=1; i<args.length; i++) {
                    // --append [-a] <IPN> - append mode
                    if ((args[i].equals("--append") || args[i].equals("-a") && args.length > i+1)) {
                        append = true;
                        IPN = Integer.parseInt(args[i+1]);
                    }
                    // --file [-f] <filename> - mandatory - set filename
                    if ((args[i].equals("--file") || args[i].equals("-f")) && args.length > i+1) {
                        filename = args[i+1];
                    }
                    // --count [-c] <N> number of IPs
                    if ((args[i].equals("--count") || args[i].equals("-c")) && args.length > i+1) {
                        count = Integer.parseInt(args[i+1]);
                    }
                    // --first [-s] - first IP (only for bynumber)
                    if ((args[i].equals("--first") || args[i].equals("-s")) && args.length > i+1) {
                        firstIP = args[i+1];
                    }
                }
            }

            // bynumber
            if (args[0].equals("bynumber")) {
                IPv4Generator gen = new IPv4Generator(filename, append, IPN);
                gen.genNIPs(firstIP, count);
            }

            //TOCOMPLETE
            // help
            if (args[0].equals("--help") || args[0].equals("-h")) {
                System.out.println("--append [-a] <IPN> - append mode (add entries to an existing file)\n" +
                        "--file [-f] <filename> - set filename\n" +
                        "\nModes:\n" +
                        "bynumber - generates IP list by first IP and number of IPs\n" +
                        "--first [-s] - first IP\n" +
                        "--count [-c] <N> number of IPs\n"
                );
            }

        }
        // TODO input verification

    }



}

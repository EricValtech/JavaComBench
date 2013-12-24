package com.vidal.sandbox.statelessvxp;


import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;


import com.vidal.sandbox.statelessvxp.comunicator.BenchCommunicator;
import com.vidal.sandbox.statelessvxp.serialiser.BenchSerialiser;
import com.vidal.sandbox.statelessvxp.util.ClassFinder;
import org.apache.commons.cli.*;

import com.vidal.sandbox.statelessvxp.util.ChronoPerf;


public class PerfTestStateless
{

    private static CommandLine COMMAND_LINE=null;
    private static final Options OPTIONS = new Options();
    private static final Option OPT_HELP=new Option( "h","help",false, "print this message" );
    private static final Option OPT_PROVIDERSERIAL=new Option( "ps","providerSerial",true, "provider for serialisation can be multiple (ie mp,pb) [default all]" );
    private static final Option OPT_PROVIDERCOMMUNICATION=new Option( "pc","providerComm",true, "provider for communication can be multiple (ie so,mf,pi) [default all]" );
    private static final Option OPT_SCENARIO=new Option( "s","scenario",true, "scenario can be multiple (ie se,co) [default all]" );
    private static final Option OPT_NBPOJO=new Option( "n", "nbObjects",true,"The number of objects to serialise can be multiple (ie 10,10000) [default 1,10,100,1000,10000,100000]" );
    private static final Option OPT_FILENAME=new Option( "f", "file",true,"name to csv file to generate [default console (no file)]" );

    static {
        OPTIONS.addOption(OPT_HELP);
        OPTIONS.addOption(OPT_PROVIDERSERIAL);
        OPTIONS.addOption(OPT_PROVIDERCOMMUNICATION);
        OPTIONS.addOption(OPT_SCENARIO);
        OPTIONS.addOption(OPT_NBPOJO);
        OPTIONS.addOption(OPT_FILENAME);
    }


    //Constants
    private static Map<String, Class<?>> PROVIDERSERIAL= ClassFinder.makeBencnhMap("com.vidal.sandbox.statelessvxp.serialiser", BenchSerialiser.class);
    private static Map<String, Class<?>> PROVIDERCOM= ClassFinder.makeBencnhMap("com.vidal.sandbox.statelessvxp.comunicator", BenchCommunicator.class);

    private static String SC_SERIALISE="se";
    private static String SC_COMM="cp";
    private static List<String> SCENARIOS= Arrays.asList(SC_SERIALISE, SC_COMM);
    private static List<Integer> NBPOJO= Arrays.asList(1,10,100,1000,10000,100000);

    private static String FILENAME= null;


    private static final NumberFormat FORMATTER = new DecimalFormat("0000000");

    private static void printUsage(String appName) throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( appName, OPTIONS );
        System.out.println("\nPossible values for scenario (default all): ");
        System.out.println("\tse: Serialisation scenario, objrct are only serialised/deserialised.");
        System.out.println("\tco: Communication, time is measured only  for communication (not se/de)");
        System.out.println("Possible values for providerSerial  (default all): ");
        for(String key : PROVIDERSERIAL.keySet()) {
            BenchSerialiser serialiser = (BenchSerialiser)PROVIDERSERIAL.get(key).newInstance();
            System.out.println("\t"+serialiser.getShortName()+": "+serialiser.getDescription());
        }

        System.out.println("Possible values for providerCom  (default all): ");
        for(String key : PROVIDERCOM.keySet()) {
            BenchCommunicator communicator = (BenchCommunicator)PROVIDERCOM.get(key).newInstance();
            System.out.println("\t"+communicator.getShortName()+": "+communicator.getDescription());
        }

    }

    private static boolean  parseArgs(String[] args) throws Exception {
        //Parse commandline
        CommandLineParser parser = new BasicParser();
        try {
            // parse the command line arguments
            COMMAND_LINE= parser.parse( OPTIONS, args );
        }
        catch( ParseException exp ) {
            printUsage(args[0]);
            return false;
        }

        if (COMMAND_LINE.hasOption(OPT_HELP.getOpt())) {
            return false; // when -h just printusage and leave..
        }

        //once parsed init constants..
        PROVIDERSERIAL= commandLineExtractMapValue(OPT_PROVIDERSERIAL, PROVIDERSERIAL);
        if (PROVIDERSERIAL==null) return false;
        PROVIDERCOM= commandLineExtractMapValue(OPT_PROVIDERSERIAL, PROVIDERCOM);
        if (PROVIDERCOM==null) return false;
        SCENARIOS= commandLineExtractListValue(OPT_SCENARIO, SCENARIOS);
        if (SCENARIOS==null) return false;

        if (COMMAND_LINE.hasOption(OPT_NBPOJO.getOpt())) {
            String scenario= COMMAND_LINE.getOptionValue(OPT_NBPOJO.getOpt());
            String [] pojoValues = scenario.split(",");
            NBPOJO= new ArrayList<Integer>();
                for (int i=0; i < pojoValues.length;i++) {
                try { NBPOJO.add(Integer.parseInt(pojoValues[i])); }
                catch(NumberFormatException e) {
                        System.out.println("ERROR IN OPTION : "+OPT_NBPOJO.getArgName()+" not valid number ["+pojoValues[i]+"]");
                        return false;
                }
            }
        }



        if (COMMAND_LINE.hasOption(OPT_FILENAME.getOpt()))  {
            FILENAME = COMMAND_LINE.getOptionValue(OPT_FILENAME.getOpt());
            if (! new File(FILENAME).canWrite()) {
                System.out.println("ERROR : "+OPT_FILENAME.getArgName()+" can't write to file.");
            }
        }

        return true;

    }

    private static Map<String, Class<?>> commandLineExtractMapValue(Option option, Map<String, Class<?>> accepted) {
        if (COMMAND_LINE.hasOption(option.getOpt())) {
                Map<String, Class<?>> res = new HashMap<String, Class<?>>();
                String [] optionsValues = COMMAND_LINE.getOptionValue(option.getOpt()).split(",");
                for (int i=0; i < optionsValues.length;i++) {
                    Class<?> className = accepted.get(optionsValues[i]);
                    if (className==null) {
                        System.out.println("ERROR IN OPTION : "+option.getArgName()+" provider ["+optionsValues[i]+"] not valid should be in "+accepted.keySet());
                        return null;
                    }
                    res.put(optionsValues[i],className);
                }
            return res;
        }
        return accepted;
    }

    private static List<String> commandLineExtractListValue(Option option, List<String> accepted) {
        if (COMMAND_LINE.hasOption(option.getOpt())) {
            String [] optionsValues = COMMAND_LINE.getOptionValue(option.getOpt()).split(",");
            for (int i=0; i < optionsValues.length;i++) {
                if (! accepted.contains(optionsValues[i])) {
                    System.out.println("ERROR IN OPTION : "+option.getArgName()+" provider ["+optionsValues[i]+"] not valid should be in "+accepted);
                    return null;
                }
            }
            return Arrays.asList(optionsValues);
        }
        return accepted;
    }


    public static void main( String[] args ) throws Exception
    {
        //parsing command line
        if (! parseArgs(args) ) {
            printUsage(PerfTestStateless.class.getCanonicalName());
            System.exit(2);
        }

        //wirting app params.
        System.out.println("starting bench with params :");
        if (FILENAME != null) System.out.println("wrinting csv file: "+FILENAME);
        else System.out.print("Log console.");
        System.out.println("scenario: "+SCENARIOS);
        System.out.println("volume tests:"+NBPOJO);
        System.out.println("serialisation providers:"+PROVIDERSERIAL.keySet());
        System.out.println("communication providers:"+PROVIDERCOM.keySet());

        System.out.println("STARTING TEST...");
        ChronoPerf chrono = new ChronoPerf();
        chrono.start();
        for(String scenario : SCENARIOS) {
            chrono.start("SC"+scenario);
            System.out.println("********************************************* "+scenario);
            doBench(scenario,chrono);
            chrono.pause("SC" + scenario);
            System.out.println("OVERALL :"+chrono.getFormattedTime("SC"+scenario));
        }
        chrono.pause();
        System.out.println("OVERALL TIME :"+chrono.getFormattedTime());
		
    }

    private static void doBench(String scenario,ChronoPerf chrono) throws Exception {
        if (SC_SERIALISE.equals(scenario)) {doBenchSerialise(chrono);}

        if (SC_COMM.equals(scenario)) {
            for(String communicatorName : PROVIDERCOM.keySet()) {
                Class <?> communicationClass= PROVIDERCOM.get(communicatorName);
                BenchCommunicator communicator =  (BenchCommunicator)communicationClass.newInstance();
                communicator.startServer();
                for(Integer nbPojo : NBPOJO ) {
                    Object res = communicator.doBench(nbPojo);
                }
                communicator.stopServer();
            }
        }
        System.out.println();
    }

    private static void doBenchSerialise(ChronoPerf chrono) throws Exception {
        for(Integer nbPojo : NBPOJO ) {
            System.out.print("\nTime for " + FORMATTER.format(nbPojo) + " : ");
            for(String serialiserName : PROVIDERSERIAL.keySet()) {
                Class <?> serialiserClass= PROVIDERSERIAL.get(serialiserName);
                BenchSerialiser serialiser = (BenchSerialiser)serialiserClass.newInstance();
                serialiser.warmup();
                String chronoName = (serialiser.getName());
                chrono.start(chronoName);
                Object res = serialiser.doBench(nbPojo);
                chrono.pause(chronoName);
                System.out.print("\t"+chronoName+": "+chrono.getFormattedTime(chronoName)+" ["+res+"]");
                chrono.reset(chronoName);
            }
        }
    }

/*
    private static void testSocket(int nbPack, MessagePack packer) throws Exception {
		String serverHostname = new String ("127.0.0.1");
        System.out.println ("Attemping to connect to host " +
		serverHostname + " on port 10007.");
        
        Socket echoSocket = null;
        PrintWriter out = null;
 
        echoSocket = new Socket(serverHostname, 10007);
        out = new PrintWriter(echoSocket.getOutputStream(), true);
        
        PackContainer pc= PackFactory.createPackList(nbPack, 100);
        
        ChronoPerf<Integer> chrono = new ChronoPerf<Integer>();
        chrono.start("WRITE STREAM");
        packer.write(echoSocket.getOutputStream(),pc);
        chrono.pause("WRITE STREAM");
		System.out.print("NB OBJECT  : "+ FORMATTER.format(pc.list.size()));
		System.out.print(" TIME SOCKET : "+ chrono.getFormattedTime("WRITE STREAM"));

        out.close();	
        echoSocket.close();
		
	}
  */

    

 
}


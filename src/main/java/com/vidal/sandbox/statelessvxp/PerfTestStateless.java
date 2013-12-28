package com.vidal.sandbox.statelessvxp;


import com.vidal.sandbox.statelessvxp.bench.comunicator.BenchCommunicator;
import com.vidal.sandbox.statelessvxp.bench.rcp.BenchRCP;
import com.vidal.sandbox.statelessvxp.bench.serialiser.BenchSerialiser;
import com.vidal.sandbox.statelessvxp.pojo.PojoFactory;
import com.vidal.sandbox.statelessvxp.pojo.factory.PackFactory;
import com.vidal.sandbox.statelessvxp.util.ChronoPerf;
import com.vidal.sandbox.statelessvxp.util.ClassFinder;
import com.vidal.sandbox.statelessvxp.util.NicePrinterHelper;
import org.apache.commons.cli.*;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;


public class PerfTestStateless
{

    private static final Class<?> DEFAULT_FACTORY = PackFactory.class;
    private static CommandLine COMMAND_LINE=null;
    private static final Options OPTIONS = new Options();
    private static final Option OPT_HELP=new Option( "h","help",false, "print this message" );
    private static final Option OPT_PROVIDERSERIAL=new Option( "ps","providerSerial",true, "provider for serialisation can be multiple  [default all]" );
    private static final Option OPT_PROVIDERCOMMUNICATION=new Option( "pc","providerComm",true, "provider for communication can be multiple  [default all]" );
    private static final Option OPT_PROVIDERRCP=new Option( "rc","providerRCP",true, "provider for rcp can be multiple [default all]" );
    private static final Option OPT_SCENARIO=new Option( "s","scenario",true, "scenario can be multiple  [default all]" );
    private static final Option OPT_NBPOJO=new Option( "n", "nbObjects",true,"The number of objects to serialise can be multiple (ie 10,10000) [default 1,10,100,1000,10000,100000]" );
    private static final Option OPT_FILENAME=new Option( "f", "file",true,"name to csv file to generate [default console (no file)]" );

    static {
        OPTIONS.addOption(OPT_HELP);
        OPTIONS.addOption(OPT_PROVIDERSERIAL);
        OPTIONS.addOption(OPT_PROVIDERCOMMUNICATION);
        OPTIONS.addOption(OPT_PROVIDERRCP);
        OPTIONS.addOption(OPT_SCENARIO);
        OPTIONS.addOption(OPT_NBPOJO);
        OPTIONS.addOption(OPT_FILENAME);
    }


    //Constants
    private static Map<String, Class<?>> PROVIDERSERIAL= ClassFinder.makeBencnhMap("com.vidal.sandbox.statelessvxp.bench.serialiser", BenchSerialiser.class);
    private static Map<String, Class<?>> PROVIDERCOM= ClassFinder.makeBencnhMap("com.vidal.sandbox.statelessvxp.bench.comunicator", BenchCommunicator.class);
    private static Map<String, Class<?>> PROVIDERRCP= ClassFinder.makeBencnhMap("com.vidal.sandbox.statelessvxp.bench.rcp", BenchRCP.class);

    private static String SC_SERIALISE="se";
    private static String SC_COMM="co";
    private static String SC_RCP="rc";
    private static List<String> SCENARIOS= Arrays.asList(SC_COMM,SC_SERIALISE, SC_RCP);
    private static List<Integer> NBPOJO= Arrays.asList(1,1,10,100,1000,10000,100000);

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

        System.out.println("Possible values for providerRcp  (default all): ");
        for(String key : PROVIDERRCP.keySet()) {
            BenchCommunicator communicator = (BenchCommunicator)PROVIDERRCP.get(key).newInstance();
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
        PROVIDERRCP= commandLineExtractMapValue(OPT_PROVIDERRCP, PROVIDERRCP);
        if (PROVIDERRCP==null) return false;

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
        else System.out.println("Log console.");
        System.out.println("scenario: "+SCENARIOS);
        System.out.println("volume tests:"+NBPOJO);
        System.out.println("serialisation providers:"+PROVIDERSERIAL.keySet());
        System.out.println("communication providers:"+PROVIDERCOM.keySet());
        System.out.println("rcp providers:"+PROVIDERRCP.keySet());

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
        if (SC_COMM.equals(scenario)) { doBenchCommunicator(chrono); }
        if (SC_RCP.equals(scenario)) { doBenchRCP(chrono); }
        System.out.println();
    }

    private static void doBenchCommunicator(ChronoPerf chrono) throws Exception {
        if (PROVIDERCOM.keySet() ==null || PROVIDERCOM.keySet().size()==0) {
            System.out.println("No communication provider found...");
            return;
        }
        for(String communicatorName : PROVIDERCOM.keySet()) {
            Class <?> communicationClass= PROVIDERCOM.get(communicatorName);
            BenchCommunicator communicator =  (BenchCommunicator)communicationClass.newInstance();
            communicator.setFactory((Class<? extends PojoFactory>) DEFAULT_FACTORY);
            if (! communicator.startServer() ) { System.out.println(communicator.getName()+"Test skipped server not launched"); continue;}
            System.out.println("STARTING tests for "+communicator.getName()+"("+ communicator.getShortName()+") :"+communicator.getDescription());
            communicator.startCommunication();
            for(Integer nbPojo : NBPOJO ) {
                String chronoName = (communicator.getName());
                communicator.initObjects(nbPojo);
                chrono.start(chronoName);
                Object res = communicator.doBench(PackFactory.class,nbPojo);
                System.out.println("\t"+chronoName+" "+nbPojo+" : "+chrono.getFormattedTime(chronoName)+" ["+ NicePrinterHelper.printMemory((Integer) res)+"]");
                chrono.pause(chronoName);
                chrono.reset(chronoName);
            }
            communicator.stopCommunication();
            communicator.stopServer();
            System.out.println();
        }
    }

    private static void doBenchSerialise(ChronoPerf chrono) throws Exception {
        if (PROVIDERSERIAL.keySet() ==null || PROVIDERSERIAL.keySet().size()==0) {
            System.out.println("No Serialization provider found...");
            return;
        }
        for(Integer nbPojo : NBPOJO ) {
            System.out.print("\nTime for " + FORMATTER.format(nbPojo) + " : ");
            for(String serialiserName : PROVIDERSERIAL.keySet()) {
                Class <?> serialiserClass= PROVIDERSERIAL.get(serialiserName);
                BenchSerialiser serialiser = (BenchSerialiser)serialiserClass.newInstance();
                serialiser.setFactory((Class<? extends PojoFactory>) DEFAULT_FACTORY);
                serialiser.warmup();
                String chronoName = (serialiser.getName());
                serialiser.initObjects(nbPojo);
                chrono.start(chronoName);
                Object res = serialiser.doBench(nbPojo);
                chrono.pause(chronoName);
                System.out.print("\t"+chronoName+": "+chrono.getFormattedTime(chronoName)+" ["+ NicePrinterHelper.printMemory((Integer)res)+"]");
                chrono.reset(chronoName);
            }
        }
    }

    private static void doBenchRCP(ChronoPerf chrono) throws Exception {
        if (PROVIDERRCP.keySet() ==null || PROVIDERRCP.keySet().size()==0) {
            System.out.println("No RCP provider found...");
            return;
        }
        for(Integer nbPojo : NBPOJO ) {
            System.out.print("\nTime for " + FORMATTER.format(nbPojo) + " : ");
            for(String serialiserName : PROVIDERRCP.keySet()) {
                Class <?> serialiserClass= PROVIDERRCP.get(serialiserName);
                BenchRCP rcp= (BenchRCP)serialiserClass.newInstance();
                rcp.setFactory((Class<? extends PojoFactory>) DEFAULT_FACTORY);
                //rcp.warmup();
                String chronoName = (rcp.getName());
                rcp.initObjects(nbPojo);
                chrono.start(chronoName);
                Object res = null;
                //Object res = rcp.doBench(nbPojo);
                chrono.pause(chronoName);
                System.out.print("\t"+chronoName+": "+chrono.getFormattedTime(chronoName)+" ["+ NicePrinterHelper.printMemory((Integer)res)+"]");
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


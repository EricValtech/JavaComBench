package com.vidal.sandbox.statelessvxp.util;


public class NicePrinterHelper {
    public static int MEM_KO = 1024;
    public static int MEM_MO = MEM_KO*1024;

    public static String printMemory(int bytes) {
        if (bytes < MEM_KO) return ""+bytes+"o";
        if (bytes < 100*MEM_KO) return ""+bytes/MEM_KO+"Ko "+bytes%MEM_KO+"o";
        if (bytes < MEM_MO) return ""+bytes%MEM_KO+"Ko";
        if (bytes < 100*MEM_MO) return ""+bytes/MEM_MO+"Mo "+ bytes%MEM_KO+"Ko";
        else  return ""+bytes/MEM_MO+"Mo";
    }
}

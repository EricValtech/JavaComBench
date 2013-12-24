package com.vidal.sandbox.statelessvxp.util;

import com.vidal.sandbox.statelessvxp.pojo.vidal.util.PackContainer;
import org.junit.Test;

public class ClassToProtBuffTest {

    @Test
    public void testProtify() throws Exception {
        String prot = ClassToProtBuff.classToProtoBuffFile(PackContainer.class, true);
        System.out.println(prot);
    }
}

package com.vidal.sandbox.statelessvxp.util;

import com.vidal.sandbox.statelessvxp.generators.ClassToProtBuff;
import com.vidal.sandbox.statelessvxp.pojo.factory.PackContainer;
import org.junit.Test;

public class ClassToProtBuffTest {

    @Test
    public void testProtify() throws Exception {
        String prot = ClassToProtBuff.classToProtoBuffFile(PackContainer.class, true);
        System.out.println(prot);
    }
}

package com.vidal.sandbox.statelessvxp.serialiser;

import com.vidal.sandbox.statelessvxp.pojo.protbufgen.PackcontainerProt;
import com.vidal.sandbox.statelessvxp.pojo.vidal.*;
import com.vidal.sandbox.statelessvxp.pojo.vidal.util.PackContainer;
import com.vidal.sandbox.statelessvxp.pojo.vidal.util.PackFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProtBufferSerialiser implements BenchSerialiser {

    private final PackcontainerProt.PackContainer.Builder pcBuilder= PackcontainerProt.PackContainer.newBuilder();
    private final PackcontainerProt.Pack.Builder pBuilder= PackcontainerProt.Pack.newBuilder();
    @Override
    public void warmup() throws IOException {
        PackcontainerProt.PackContainer.Builder builder= PackcontainerProt.PackContainer.newBuilder();

        Iterable<? extends PackcontainerProt.Pack> toto = createPacks(10);
        builder.addAllList(toto);

        PackcontainerProt.PackContainer bean = builder.build();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bean.writeTo(out);
        out.close();

        PackcontainerProt.PackContainer readBean = PackcontainerProt.PackContainer.parseFrom(out.toByteArray());
    }

    public Iterable<? extends PackcontainerProt.Pack> createPacks(int nbPacks) {
        PackContainer pc = PackFactory.createPackList(nbPacks,100);
        List<PackcontainerProt.Pack> res = new ArrayList<PackcontainerProt.Pack>();
        for (Pack currPack : pc.getList()) {
            pBuilder.clear();
            pBuilder.setId(currPack.getId());
            pBuilder.setName(currPack.getName());
            pBuilder.setActCode(PackcontainerProt.Pack.ActCode.valueOf(currPack.getActCode().ordinal()));
            pBuilder.setCip(currPack.getCip());
            pBuilder.setCip13(currPack.getCip13());
            pBuilder.setCis(currPack.getCis());
            pBuilder.setCommunityAgrement(currPack.getCommunityAgrement());
            pBuilder.setDispensationPlace(PackcontainerProt.Pack.DispensationPlace.valueOf(currPack.getDispensationPlace().ordinal()));
            pBuilder.setDose(currPack.getDose());
            pBuilder.setDoseUnit(currPack.getDoseUnit());
            pBuilder.setDrugInSport(currPack.getDrugInSport());
            pBuilder.setEan(currPack.getEan());
            pBuilder.setGenericType(PackcontainerProt.Pack.GenericType.valueOf(currPack.getGenericType().ordinal()));
            pBuilder.setList(PackcontainerProt.Pack.ListType.valueOf(currPack.getList().ordinal()));
            pBuilder.setManufacturerPrice(currPack.getManufacturerPrice());
            pBuilder.setMarketStatus(PackcontainerProt.Pack.MarketStatus.valueOf(currPack.getMarketStatus().ordinal()));
            pBuilder.setMaxPrescriptionDuration(PackcontainerProt.Pack.PrescriptionDuration.valueOf(currPack.getMaxPrescriptionDuration().ordinal()));
            pBuilder.setNarcoticPrescription( currPack.isNarcoticPrescription()) ;
            pBuilder.setOffMarketDate( currPack.getOffMarketDate().getTime());
            pBuilder.setOnMarketDate(currPack.getOnMarketDate().getTime());
            pBuilder.setOnFreeAccess(currPack.isOnFreeAccess());
            pBuilder.setOtc(currPack.isOtc());
            pBuilder.setPharmacistPrice( currPack.getPharmacistPrice());
            pBuilder.setPricePerDose( currPack.getPricePerDose());
            pBuilder.setProductId(currPack.getProductId());
            pBuilder.setPublicPrice(currPack.getPublicPrice());
            pBuilder.setRefundingBase(currPack.getRefundingBase());
            pBuilder.setRefundingRate((PackcontainerProt.Pack.RefundRate.valueOf(currPack.getRefundingRate().ordinal())));
            pBuilder.setSafetyAlert(currPack.getSafetyAlert());
            pBuilder.setShortName(currPack.getShortName());
            pBuilder.setTfr(currPack.getTfr());
            pBuilder.setType(PackcontainerProt.Pack.ProductType.valueOf(currPack.getType().ordinal()));
            pBuilder.setUcdPrice(currPack.getUcdPrice());
            pBuilder.setVatRate(PackcontainerProt.Pack.VatRate.valueOf(currPack.getVatRate().ordinal()));
            pBuilder.setVatRateValue(currPack.getVatRateValue());
            pBuilder.setWithoutPrescr( currPack.isWithoutPrescr());
            res.add(pBuilder.build());
        }

        return res;
    }



    @Override
    public Object doBench(Integer nbPojo) throws IOException {
        PackcontainerProt.PackContainer.Builder builder= PackcontainerProt.PackContainer.newBuilder();

        Iterable<? extends PackcontainerProt.Pack> toto = createPacks(nbPojo);
        builder.addAllList(toto);

        PackcontainerProt.PackContainer bean = builder.build();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bean.writeTo(out);
        out.close();

        PackcontainerProt.PackContainer readBean = PackcontainerProt.PackContainer.parseFrom(out.toByteArray());
        return out.toByteArray().length;
    }

    @Override
    public String getName() {
        return "ProtocolBufferSerialiser";
    }

    @Override
    public String getShortName() {
        return "pb";
    }

    @Override
    public String getDescription() {
        return "uses Protocol Buffer 2.5 with a '.proto' file (@see ClassToProtBuff)";
    }
}

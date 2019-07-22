package com.globant.codeyourfuture.databaseexample.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class VendorTests {

    private Vendor testVendorA;
    private Vendor testVendorB;


    @Test
    public void twoVendors_SameIdAndName_EqualsReturnsTrue() {
        testVendorA = new Vendor();
        testVendorB = new Vendor();
        testVendorA.setVendorId(1L);
        testVendorA.setName("TEST");
        testVendorB.setVendorId(1L);
        testVendorB.setName("TEST");
        Assertions.assertThat(testVendorA)
                .isEqualTo(testVendorB)
                .hasSameHashCodeAs(testVendorB);
    }

    @Test
    public void twoVendors_DifferentIdSameName_EqualsReturnsFalse() {
        testVendorA = new Vendor();
        testVendorB = new Vendor();

        testVendorA.setVendorId(1L);
        testVendorA.setName("TEST");
        testVendorB.setVendorId(2L);
        testVendorB.setName("TEST");

        Assertions.assertThat(testVendorA)
                .isNotEqualTo(testVendorB);
        Assertions.assertThat(testVendorA.hashCode())
                .isNotEqualTo(testVendorB.hashCode());
    }

    @Test
    public void twoVendors_SameIdDifferentName_EqualsReturnsFalse() {
        testVendorA = new Vendor();
        testVendorB = new Vendor();

        testVendorA.setVendorId(1L);
        testVendorA.setName("TEST 1");
        testVendorB.setVendorId(1L);
        testVendorB.setName("TEST 2");

        Assertions.assertThat(testVendorA)
                .isNotEqualTo(testVendorB);
        Assertions.assertThat(testVendorA.hashCode())
                .isNotEqualTo(testVendorB.hashCode());
    }

    @Test
    public void nullVendorComparison_EqualsReturnsFalse() {
        testVendorA = new Vendor();
        testVendorB = null;
        testVendorA.setVendorId(1L);

        Assertions.assertThat(testVendorA)
                .isNotEqualTo(testVendorB);
    }

}

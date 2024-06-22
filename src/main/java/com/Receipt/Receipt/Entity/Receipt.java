package com.Receipt.Receipt.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Receipt {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Receiptno;
        private String Customername;
        private Long Contactno;
        private String Address;
        private String Companyname;
        private String Modelno;
        private Long IMEI1;
        private Long IMEI2;
        private String Physicalcondition;
        private Long Estimatedprice;

        // Getters and setters


    public Receipt() {
    }

    public Receipt(Long receiptno, String customername, Long contactno, String address, String companyname, String modelno, Long IMEI1, Long IMEI2, String physicalcondition, Long estimatedprice) {
        this.Receiptno = receiptno;
        this.Customername = customername;
        this.Contactno = contactno;
        this.Address = address;
        this.Companyname = companyname;
        this.Modelno = modelno;
        this.IMEI1 = IMEI1;
        this.IMEI2 = IMEI2;
        this.Physicalcondition = physicalcondition;
        this.Estimatedprice = estimatedprice;
    }

    public Long getReceiptno() {
        return Receiptno;
    }

    public void setReceiptno(Long receiptno) {
        Receiptno = receiptno;
    }

    public String getCustomername() {
        return Customername;
    }

    public void setCustomername(String customername) {
        Customername = customername;
    }

    public Long getContactno() {
        return Contactno;
    }

    public void setContactno(Long contactno) {
        Contactno = contactno;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCompanyname() {
        return Companyname;
    }

    public void setCompanyname(String companyname) {
        Companyname = companyname;
    }

    public String getModelno() {
        return Modelno;
    }

    public void setModelno(String modelno) {
        Modelno = modelno;
    }

    public Long getIMEI1() {
        return IMEI1;
    }

    public void setIMEI1(Long IMEI1) {
        this.IMEI1 = IMEI1;
    }

    public Long getIMEI2() {
        return IMEI2;
    }

    public void setIMEI2(Long IMEI2) {
        this.IMEI2 = IMEI2;
    }

    public String getPhysicalcondition() {
        return Physicalcondition;
    }

    public void setPhysicalcondition(String physicalcondition) {
        Physicalcondition = physicalcondition;
    }

    public Long getEstimatedprice() {
        return Estimatedprice;
    }

    public void setEstimatedprice(Long estimatedprice) {
        Estimatedprice = estimatedprice;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "Receiptno=" + Receiptno +
                ", Customername='" + Customername + '\'' +
                ", Contactno=" + Contactno +
                ", Address='" + Address + '\'' +
                ", Companyname='" + Companyname + '\'' +
                ", Modelno='" + Modelno + '\'' +
                ", IMEI1=" + IMEI1 +
                ", IMEI2=" + IMEI2 +
                ", Physicalcondition='" + Physicalcondition + '\'' +
                ", Estimatedprice=" + Estimatedprice +
                '}';
    }
}






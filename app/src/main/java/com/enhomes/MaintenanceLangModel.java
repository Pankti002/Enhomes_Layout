package com.enhomes;

public class MaintenanceLangModel {
    String houseId,maintenanceAmount, creationDate,paymentDate,lastDate,penalty;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getMaintenanceAmount() {
        return maintenanceAmount;
    }

    public void setMaintenanceAmount(String maintenanceAmount) {
        this.maintenanceAmount = maintenanceAmount;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }
}

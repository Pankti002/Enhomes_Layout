package com.enhomes;

public class StaffLangModel {
    String _id,staffMemberName, type, entryTime, exitTime, contactNo, address,
           agencyName , agencyContactNumber;

    public String get_id() {return _id;}

    public void set_id(String _id) {this._id = _id;}

    public String getStaffMemberName() {
        return staffMemberName;
    }

    public void setStaffMemberName(String staffMemberName) {
        this.staffMemberName = staffMemberName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyContactNumber() {
        return agencyContactNumber;
    }

    public void setAgencyContactNumber(String agencyContactNumber) {
        this.agencyContactNumber = agencyContactNumber;
    }
}

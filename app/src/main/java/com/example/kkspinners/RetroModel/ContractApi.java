package com.example.kkspinners.RetroModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContractApi {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("contract_count")
    @Expose
    private Integer contractCount;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getContractCount() {
        return contractCount;
    }

    public void setContractCount(Integer contractCount) {
        this.contractCount = contractCount;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

    @SerializedName("contract_id")
    @Expose
    private Integer contractId;
    @SerializedName("contract_no")
    @Expose
    private String contractNo;
    @SerializedName("contract_updated")
    @Expose
    private String contractUpdated;
    @SerializedName("user")
    @Expose
    private String user;

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractUpdated() {
        return contractUpdated;
    }

    public void setContractUpdated(String contractUpdated) {
        this.contractUpdated = contractUpdated;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
}

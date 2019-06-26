package com.example.kkspinners.RetroModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderApi {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("dispathed_order_count")
    @Expose
    private Integer dispathedOrderCount;
    @SerializedName("contract_details")
    @Expose
    private List<ContractDetail> contractDetails = null;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDispathedOrderCount() {
        return dispathedOrderCount;
    }

    public void setDispathedOrderCount(Integer dispathedOrderCount) {
        this.dispathedOrderCount = dispathedOrderCount;
    }

    public List<ContractDetail> getContractDetails() {
        return contractDetails;
    }

    public void setContractDetails(List<ContractDetail> contractDetails) {
        this.contractDetails = contractDetails;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }


    public class Datum {

        @SerializedName("order_id")
        @Expose
        private Integer orderId;
        @SerializedName("product")
        @Expose
        private String product;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("quantity")
        @Expose
        private String quantity;
        @SerializedName("inv_no")
        @Expose
        private String invNo;
        @SerializedName("inv_copy")
        @Expose
        private String invCopy;
        @SerializedName("no_of_container")
        @Expose
        private String noOfContainer;
        @SerializedName("container_no")
        @Expose
        private String containerNo;
        @SerializedName("current_status")
        @Expose
        private String currentStatus;
        @SerializedName("etd")
        @Expose
        private String etd;
        @SerializedName("awb")
        @Expose
        private String awb;
        @SerializedName("special_remarks")
        @Expose
        private String specialRemarks;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getInvNo() {
            return invNo;
        }

        public void setInvNo(String invNo) {
            this.invNo = invNo;
        }

        public String getInvCopy() {
            return invCopy;
        }

        public void setInvCopy(String invCopy) {
            this.invCopy = invCopy;
        }

        public String getNoOfContainer() {
            return noOfContainer;
        }

        public void setNoOfContainer(String noOfContainer) {
            this.noOfContainer = noOfContainer;
        }

        public String getContainerNo() {
            return containerNo;
        }

        public void setContainerNo(String containerNo) {
            this.containerNo = containerNo;
        }

        public String getCurrentStatus() {
            return currentStatus;
        }

        public void setCurrentStatus(String currentStatus) {
            this.currentStatus = currentStatus;
        }

        public String getEtd() {
            return etd;
        }

        public void setEtd(String etd) {
            this.etd = etd;
        }

        public String getAwb() {
            return awb;
        }

        public void setAwb(String awb) {
            this.awb = awb;
        }

        public String getSpecialRemarks() {
            return specialRemarks;
        }

        public void setSpecialRemarks(String specialRemarks) {
            this.specialRemarks = specialRemarks;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

    public class ContractDetail {
        @SerializedName("contract_id")
        @Expose
        private Integer contractId;
        @SerializedName("contract_no")
        @Expose
        private String contractNo;
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

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

    }
}

package com.mandywebdesign.kkspinners.RetroModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PendingOrderApi {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("pen_order")
    @Expose
    private Integer penOrder;
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

    public Integer getPenOrder() {
        return penOrder;
    }

    public void setPenOrder(Integer penOrder) {
        this.penOrder = penOrder;
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

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("p_sale_cont_no")
        @Expose
        private String pSaleContNo;
        @SerializedName("pen_product")
        @Expose
        private String penProduct;
        @SerializedName("pen_price")
        @Expose
        private String penPrice;
        @SerializedName("pen_quantity")
        @Expose
        private String penQuantity;
        @SerializedName("pen_lc_status")
        @Expose
        private String penLcStatus;
        @SerializedName("pen_current_status")
        @Expose
        private String penCurrentStatus;
        @SerializedName("pen_special_remarks")
        @Expose
        private String penSpecialRemarks;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPSaleContNo() {
            return pSaleContNo;
        }

        public void setPSaleContNo(String pSaleContNo) {
            this.pSaleContNo = pSaleContNo;
        }

        public String getPenProduct() {
            return penProduct;
        }

        public void setPenProduct(String penProduct) {
            this.penProduct = penProduct;
        }

        public String getPenPrice() {
            return penPrice;
        }

        public void setPenPrice(String penPrice) {
            this.penPrice = penPrice;
        }

        public String getPenQuantity() {
            return penQuantity;
        }

        public void setPenQuantity(String penQuantity) {
            this.penQuantity = penQuantity;
        }

        public String getPenLcStatus() {
            return penLcStatus;
        }

        public void setPenLcStatus(String penLcStatus) {
            this.penLcStatus = penLcStatus;
        }

        public String getPenCurrentStatus() {
            return penCurrentStatus;
        }

        public void setPenCurrentStatus(String penCurrentStatus) {
            this.penCurrentStatus = penCurrentStatus;
        }

        public String getPenSpecialRemarks() {
            return penSpecialRemarks;
        }

        public void setPenSpecialRemarks(String penSpecialRemarks) {
            this.penSpecialRemarks = penSpecialRemarks;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
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

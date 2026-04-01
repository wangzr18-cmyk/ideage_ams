package com.ideage.ams.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TrxExpenseRequest{

    private Integer id;
    private String departmentCode;
    private String empId;
    private String empName;
    private LocalDate requestDate;
    private Integer regularTransportAmount;
    private Integer nonregularTransportAmount;
    private Integer otherExpenseAmount;
    private Integer totalAmount;
    private String approvalStatus;
    private String publicFlg;
    private String editFlg;
    private String remarks;
    private String createUserId;
    private String updateUserId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String delFlg;
    private String pictureUrl;

    // Getter & Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDepartmentCode() { return departmentCode; }
    public void setDepartmentCode(String departmentCode) { this.departmentCode = departmentCode; }

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }

    public LocalDate getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDate requestDate) { this.requestDate = requestDate; }

    public Integer getRegularTransportAmount() { return regularTransportAmount; }
    public void setRegularTransportAmount(Integer regularTransportAmount) { this.regularTransportAmount = regularTransportAmount; }

    public Integer getNonregularTransportAmount() { return nonregularTransportAmount; }
    public void setNonregularTransportAmount(Integer nonregularTransportAmount) { this.nonregularTransportAmount = nonregularTransportAmount; }

    public Integer getOtherExpenseAmount() { return otherExpenseAmount; }
    public void setOtherExpenseAmount(Integer otherExpenseAmount) { this.otherExpenseAmount = otherExpenseAmount; }

    public Integer getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Integer totalAmount) { this.totalAmount = totalAmount; }

    public String getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(String approvalStatus) { this.approvalStatus = approvalStatus; }

    public String getPublicFlg() { return publicFlg; }
    public void setPublicFlg(String publicFlg) { this.publicFlg = publicFlg; }

    public String getEditFlg() { return editFlg; }
    public void setEditFlg(String editFlg) { this.editFlg = editFlg; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getCreateUserId() { return createUserId; }
    public void setCreateUserId(String createUserId) { this.createUserId = createUserId; }

    public String getUpdateUserId() { return updateUserId; }
    public void setUpdateUserId(String updateUserId) { this.updateUserId = updateUserId; }

    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }

    public LocalDateTime getUpdateTime() { return updateTime; }
    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }

    public String getDelFlg() { return delFlg; }
    public void setDelFlg(String delFlg) { this.delFlg = delFlg; }

    public String getPictureUrl() {return pictureUrl;}
    public void setPictureUrl(String pictureUrl) {this.pictureUrl = pictureUrl;}
}
package com.ideage.ams.entity;

import java.time.LocalDateTime;


public class TrxExpenseDetail {
    private Integer id; // 経費明細ID
    private Integer requestId; // 経費申請ID
    private LocalDateTime requestDate; // 経費申請年月
    private String siteName; // 現場名

    private LocalDateTime expenseDate; // 日付
    private String transportDestination; // 交通費行先
    private String transportSection; // 交通費区間
    private String transportMethod; // 交通手段
    private String transportExpenseType; // 経費種類
    private String transportItem; // 交通費事項
    private Integer transportAmount = 0; // 交通費金額

   // private Integer transportTotal = 0; // 交通費合計

    private String otherPayee; // 立替経費支払い先
    private String otherAccountTitle; // 立替経費勘定科目
    private String otherSummary; // 立替経費摘要

    private Integer otherAmount = 0; // 立替経費金額

   // private Integer otherTotal = 0; // 立替経費合計
    //private Integer subtotalAmount = 0; // 総額

    private String receiptUrl; // 領収書URL
    private String remarks; // 備考
    private String createUserId; // 登録者ID
    private String updateUserId; // 更新者ID
    private LocalDateTime createTime; // 登録時間
    private LocalDateTime updateTime; // 更新時間
    private String delFlg = "0"; // 削除フラグ

    // ========== Getters & Setters ==========
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getRequestId() { return requestId; }
    public void setRequestId(Integer requestId) { this.requestId = requestId; }

    public LocalDateTime getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDateTime requestDate) { this.requestDate = requestDate; }

    public String getSiteName() { return siteName; }
    public void setSiteName(String siteName) { this.siteName = siteName; }

    public LocalDateTime getExpenseDate() { return expenseDate; }
    public void setExpenseDate(LocalDateTime expenseDate) { this.expenseDate = expenseDate; }

    public String getTransportDestination() { return transportDestination; }
    public void setTransportDestination(String transportDestination) { this.transportDestination = transportDestination; }

    public String getTransportSection() { return transportSection; }
    public void setTransportSection(String transportSection) { this.transportSection = transportSection; }

    public String getTransportMethod() { return transportMethod; }
    public void setTransportMethod(String transportMethod) { this.transportMethod = transportMethod; }

    public String getTransportExpenseType() { return transportExpenseType; }
    public void setTransportExpenseType(String transportExpenseType) { this.transportExpenseType = transportExpenseType; }

    public String getTransportItem() { return transportItem; }
    public void setTransportItem(String transportItem) { this.transportItem = transportItem; }

    public Integer getTransportAmount() { return transportAmount; }
    public void setTransportAmount(Integer transportAmount) { this.transportAmount = transportAmount; }

    public String getOtherPayee() { return otherPayee; }
    public void setOtherPayee(String otherPayee) { this.otherPayee = otherPayee; }

    public String getOtherAccountTitle() { return otherAccountTitle; }
    public void setOtherAccountTitle(String otherAccountTitle) { this.otherAccountTitle = otherAccountTitle; }

    public String getOtherSummary() { return otherSummary; }
    public void setOtherSummary(String otherSummary) { this.otherSummary = otherSummary; }

    public Integer getOtherAmount() { return otherAmount; }
    public void setOtherAmount(Integer otherAmount) { this.otherAmount = otherAmount; }

    public String getReceiptUrl() { return receiptUrl; }
    public void setReceiptUrl(String receiptUrl) { this.receiptUrl = receiptUrl; }

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
}

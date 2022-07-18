package com.project.FurnLand.DTO.Requests;

public class UpdateOrderRequest {
    private String status;

    public UpdateOrderRequest(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

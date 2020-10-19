package com.intuit.practice.courtbookingbackend.model;

public class BookingResponse {

    public BookingResponse() {}

    public BookingResponse(String status, String message, Integer bookingId, SlotModal slotModal, User user, Integer court) {
        this.status = status;
        this.message = message;
        this.bookingId = bookingId;
        this.slotModal = slotModal;
        this.user = user;
        this.court = court;
    }

    private User user;

    private String status;

    private String message;

    private Integer bookingId;

    private SlotModal slotModal;

    private Integer court;

    public Integer getCourt() {
        return court;
    }

    public void setCourt(Integer court) {
        this.court = court;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public SlotModal getSlotModal() {
        return slotModal;
    }

    public void setSlotModal(SlotModal slotModal) {
        this.slotModal = slotModal;
    }
}

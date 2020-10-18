package com.intuit.practice.courtbookingbackend.model;

public class BookingResponse {

    public BookingResponse() {}

    public BookingResponse(String status, String message, Integer bookingId, SlotModal slotModal, User user, Integer courtId) {
        this.status = status;
        this.message = message;
        this.bookingId = bookingId;
        this.slotModal = slotModal;
        this.courtId = courtId;
        this.user = user;
    }

    private User user;

    private String status;

    private String message;

    private Integer bookingId;

    private SlotModal slotModal;

    private Integer courtId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCourtId() {
        return courtId;
    }

    public void setCourtId(Integer courtId) {
        this.courtId = courtId;
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

    public Integer getCourt() {
        return courtId;
    }

    public void setCourt(Court court) {
        this.courtId = courtId;
    }
}

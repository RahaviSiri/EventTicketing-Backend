package eventticketing.eventease_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateEventDTO {
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private BigDecimal ticketPrice;
    private Integer totalSeats;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }
    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    public Integer getTotalSeats() {
        return totalSeats;
    }
    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    
}

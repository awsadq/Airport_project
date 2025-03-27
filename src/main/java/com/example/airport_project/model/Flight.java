package com.example.airport_project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    @JsonProperty("flightNumber")
    private String flightNumber;

    @Column(nullable = false)
    @JsonProperty("departureTime")
    private String departureTime;

    @Column(nullable = false)
    @JsonProperty("arrivalTime")
    private String arrivalTime;

    @Column(nullable = false)
    @JsonProperty("departureAirport")
    private String departureAirport;

    @Column(nullable = false)
    @JsonProperty("arrivalAirport")
    private String arrivalAirport;

    @Column(nullable = false)
    @JsonProperty("airline")
    private String airline;

    @Column(nullable = false)
    @JsonProperty("status")
    private String status;

    @JsonProperty("remark")
    private String remark;

    @Column(name = "departure_date")
    @JsonProperty("departureDate")
    private String departureDate;

    @Column(name = "airline_image")
    @JsonProperty("airlineImage")
    private String airlineImage;

    @Column(name = "total_price")
    @JsonProperty("totalPrice")
    private Integer totalPrice;

    @Column(name = "total_time")
    @JsonProperty("totalTime")
    private String totalTime;

    @Column(name = "stop_count")
    @JsonProperty("stopCount")
    private Integer stopCount;

    @Column(name = "transfer_cities")
    @JsonProperty("transferCities")
    private String[] transferCities;

    @Column(name = "baggage_hand")
    @JsonProperty("baggageHand")
    private String baggageHand;

    @Column(name = "baggage_luggage")
    @JsonProperty("baggageLuggage")
    private String baggageLuggage;



    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", airline='" + airline + '\'' +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", airlineImage='" + airlineImage + '\'' +
                ", totalPrice=" + totalPrice +
                ", totalTime='" + totalTime + '\'' +
                ", stopCount=" + stopCount +
                ", transferCities=" + transferCities +
                ", baggageHand='" + baggageHand + '\'' +
                ", baggageLuggage='" + baggageLuggage + '\'' +

                '}';
    }
}

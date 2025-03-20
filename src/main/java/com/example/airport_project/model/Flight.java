package com.example.airport_project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("flightNumber")
    private String flightNumber;

    @JsonProperty("departureTime")
    private String departureTime;

    @JsonProperty("arrivalTime")
    private String arrivalTime;

    @JsonProperty("departureAirport")
    private String departureAirport;

    @JsonProperty("arrivalAirport")
    private String arrivalAirport;

    @JsonProperty("airline")
    private String airline;

    @JsonProperty("status")
    private String status;

    @JsonProperty("remark")
    private String remark;


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
                '}';
    }
}

package com.example.home.types;

import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Value
@Entity
@Table(name = "temperatures")
public class Temperature {

    double temperature;
    Date date;
}

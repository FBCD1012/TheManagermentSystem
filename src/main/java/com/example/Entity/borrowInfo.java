package com.example.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class borrowInfo {
    Integer bookId;
    String title;
    Date  time;
    String sName;
    Integer sId;
}

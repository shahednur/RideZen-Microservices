package com.rideshare.passenger.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "success")
public class ApiResponse<T> {
    private T data;
}

package com.course.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommissionRequestDTO {

	   @NotNull(message = "Phần trăm chiết khấu không được để trống")
	private Double percentage;
	   
	   private Date applicableDate;

}

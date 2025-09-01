package com.course.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="TeacherBanks")
public class TeacherBank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String bankCode;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String bankName;
	
	@Column(columnDefinition = "varchar(20)")
	private String accountNumber;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String accountHolder;
	
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@Temporal(TemporalType.DATE)
	private Date uploadedDate;
	
	private boolean isDefault;
	
	@ManyToOne
	@JoinColumn(name="teacherId", nullable = false)
	private Teacher teacher;
	
}

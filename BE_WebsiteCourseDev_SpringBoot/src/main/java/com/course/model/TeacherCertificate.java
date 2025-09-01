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
@Table(name="TeacherCertificates")
public class TeacherCertificate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "nvarchar(250)")
	private String certificateName;
	
	@Column(columnDefinition = "nvarchar(250)")
	private String issuingOrganization;
	
	private Integer issuedYear;
	
	@Column(columnDefinition = "nvarchar(250)")
	private String certificateImage;
	
	@Temporal(TemporalType.DATE)
	private Date uploadedDate;
	
	@ManyToOne
	@JoinColumn(name="teacherId", nullable = false)
	private Teacher teacher;
}

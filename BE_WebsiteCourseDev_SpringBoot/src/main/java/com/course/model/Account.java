package com.course.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name="Accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "varchar(100)", unique = true)
	private String email;
	
	@Column(columnDefinition = "varchar(100)")
	private String password;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String fullname;
	
	private Boolean gender;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@Column(columnDefinition = "nvarchar(250)")
	private String avatar;
	
	@Temporal(TemporalType.DATE)
	private Date registrationDate;
	
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name="roleId", nullable = false)
	private Role role;
	
	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
	private Teacher teacher;

	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
	private Student student;
	
	@OneToMany(mappedBy = "account")
	private List<Comment> comments;
}

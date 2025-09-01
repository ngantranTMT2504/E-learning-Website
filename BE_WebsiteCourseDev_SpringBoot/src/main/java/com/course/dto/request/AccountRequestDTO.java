package com.course.dto.request;

import java.util.Date;

import com.course.model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {
	 	@NotBlank(message = "Email không được để trống")
	    @Email(message = "Email không đúng định dạng")
	    private String email;

	 	@NotBlank(message = "Mật khẩu không được để trống")
	    @Size(min = 6, max = 50, message = "Mật khẩu phải có từ 6 đến 50 ký tự")
	    private String password;

	    @NotBlank(message = "Họ tên không được để trống")
	    @Size(max = 100, message = "Họ tên không được vượt quá 100 ký tự")
	    private String fullname;

	    private Boolean gender;

	    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
	    private Date birthday;

	    @Size(max = 250, message = "Đường dẫn ảnh đại diện không được vượt quá 250 ký tự")
	    private String avatar;

	    @NotNull(message = "Vai trò không được để trống")
	    private Role role;
	    
	    private Date registrationDate;
	    
	    @NotNull(message = "Trạng thái tài khoản không được để trống")
		private boolean status;
}

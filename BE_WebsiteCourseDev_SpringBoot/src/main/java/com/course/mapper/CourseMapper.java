package com.course.mapper;

import java.util.Date;

import com.course.dto.request.CourseRequestDTO;
import com.course.dto.response.CourseResponseDTO;
import com.course.model.Commission;
import com.course.model.Course;
import com.course.model.CourseType;
import com.course.model.Teacher;

public class CourseMapper {
	
	public static Course toEntity(CourseRequestDTO dto) {
	    Course course = new Course();

	    course.setTitle(dto.getTitle());
	    course.setSlug(dto.getSlug());
	    course.setImage(dto.getImage());
	    course.setDiscription(dto.getDiscription());
	    course.setShortDiscription(dto.getShortDiscription());
	    course.setPrice(dto.getPrice());
	    course.setDiscount(dto.getDiscount());
	    course.setCreatedDate(dto.getCreatedDate() != null ? dto.getCreatedDate() : new Date());
	    course.setUpdatedDate(dto.getUpdatedDate() != null ? dto.getUpdatedDate() : new Date());
	    course.setAverageRating(dto.getAverageRating());
	    course.setStatus(dto.isStatus());
	    
	    Teacher teacher = new Teacher();
	    teacher.setId(dto.getTeacherId());
	    course.setTeacher(teacher);

	    CourseType courseType = new CourseType();
	    courseType.setId(dto.getCourseTypeId());
	    course.setCourseType(courseType);

	    Commission commission = new Commission();
	    commission.setId(dto.getCommissionId());
	    course.setCommission(commission);

	    return course;
	}

    public static CourseResponseDTO toResponse(Course course) {
        CourseResponseDTO dto = new CourseResponseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setSlug(course.getSlug());
        dto.setImage(course.getImage());
        dto.setDiscription(course.getDiscription());
        dto.setShortDiscription(course.getShortDiscription());
        dto.setPrice(course.getPrice());
        dto.setDiscount(course.getDiscount());
        dto.setCreatedDate(course.getCreatedDate());
        dto.setUpdatedDate(course.getUpdatedDate());
        dto.setAverageRating(course.getAverageRating());
        dto.setStatus(course.isStatus());

        if (course.getTeacher() != null) {
            dto.setTeacherId(course.getTeacher().getId());

            if (course.getTeacher().getAccount() != null) {
            	dto.setAccountId(course.getTeacher().getAccount().getId());
                dto.setTeacherName(course.getTeacher().getAccount().getFullname());
            }
        }

        if (course.getCourseType() != null) {
            dto.setCourseTypeId(course.getCourseType().getId());
            dto.setCourseTypeName(course.getCourseType().getName());
        }

        if (course.getCommission() != null) {
            dto.setCommissionId(course.getCommission().getId());
            dto.setCommissionPercentage(course.getCommission().getPercentage());
        }
        
        dto.setLessonTotal(course.getLessons() != null ? course.getLessons().size() : 0);
        
//        String originalFilename = course.getImage();
//        String filenameWithoutExt = originalFilename.substring(0, originalFilename.lastIndexOf("."));
//        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//
//        String publicId = "course/" + course.getSlug() + "-" + filenameWithoutExt;
//        String imageUrl = "https://res.cloudinary.com/dlde02n/image/upload/c_fill,w_400,h_300/" + publicId + "." + extension;
//
//        dto.setImage(imageUrl);
              
        return dto;
    }
}

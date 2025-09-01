package com.course.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.course.exception.BadRequestException;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryUtil {

	@Autowired
	private Cloudinary cloudinary;

	public String uploadFile(MultipartFile file) throws IOException {
		if (file == null || file.isEmpty()) {
			throw new BadRequestException("File is empty");
		}
		if (!ImageUtil.isImageExtensionValid(file.getOriginalFilename())) {
			throw new BadRequestException("Invalid image file type");
		}
		Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("overwrite", true));
		return uploadResult.get("secure_url").toString();
	}

	public String deleteFile(String publicId) throws IOException {
		Map<?, ?> result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
		return result.get("result").toString();
	}

	public String extractPublicId(String urlImage) {
		String parts[] = urlImage.split("/upload/");
		if (parts.length < 2)
			return null;
		String part = parts[1];
		int dotIndex = part.indexOf(".");
		if (dotIndex == -1)
			return null;
		part = part.substring(0, dotIndex);
		String publicIdWithVersion[] = part.split("/");
		return publicIdWithVersion.length > 1 ? publicIdWithVersion[1] : publicIdWithVersion[0];
	}
}
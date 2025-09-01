package com.course.util;

public class ImageUtil {
	public static boolean isImageExtensionValid(String filename) {
	    String lowerName = filename.toLowerCase();
	    return lowerName.endsWith(".jpg") ||
	           lowerName.endsWith(".jpeg")||
	           lowerName.endsWith(".png") ||
	           lowerName.endsWith(".gif") ||
	           lowerName.endsWith(".pdf") ||
	           lowerName.endsWith(".webp");
	}
}
package com.instagram.api.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class ImageUtil {
	
	public static String SaveImage(String uploadFolder, MultipartFile profileImageFile) {

		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + profileImageFile.getOriginalFilename();
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);

		try {
			Files.write(imageFilePath, profileImageFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return imageFileName;
	}
}

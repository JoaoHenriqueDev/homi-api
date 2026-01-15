package app.Homi.HomiApp.service;

import org.springframework.web.multipart.MultipartFile;

public interface imageStoreService {
    String upload(MultipartFile file, String folder, String publicId);
}

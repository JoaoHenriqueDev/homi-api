package app.Homi.HomiApp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryImageStorageService implements imageStoreService{
    private final Cloudinary cloudinary;

    @Override
    public String upload(MultipartFile file, String folder, String publicId) {
        try {
            Map result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", folder,
                            "public_id", publicId,
                            "overwrite", true,
                            "resource_type", "image"
                    )
            );

            return result.get("secure_url").toString();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao fazer upload da imagem", e);
        }
    }
}

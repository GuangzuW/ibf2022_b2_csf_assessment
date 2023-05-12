package ibf2022.batch2.csf.backend.repositories;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Repository
public class ImageRepository {

    @Autowired
    private AmazonS3 s3Client;

    @Value("${do.storage.bucketname}")
    private String bucketName;

	List<String> imageUrls = new ArrayList<String>();
	

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public void upload(MultipartFile file) throws IOException {

        ZipInputStream zipIn = new ZipInputStream(file.getInputStream());// create zip input stream

        ZipEntry entry = zipIn.getNextEntry();//first entry

        while (entry != null) {// stop when there is no more entry
            // verify the current entry, make sure the enty is not a directory,non image file
            if (!entry.isDirectory() && isImageFile(entry.getName())) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int len;
                while ((len = zipIn.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, len);
                }
                outputStream.close();

                byte[] contentBytes = outputStream.toByteArray();
				String key = UUID.randomUUID().toString().substring(0, 8);
				String fileName=entry.getName();


                // Set content type based on file extension
                String contentType = "";
                if (entry.getName().endsWith(".png")) {
                    contentType = "image/png";
                } else if (entry.getName().endsWith(".gif")) {
                    contentType = "image/gif";
                } else if (entry.getName().endsWith(".jpg") || entry.getName().endsWith(".jpeg")) {
                    contentType = "image/jpeg";
                }

                // Set object metadata
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType(contentType);
                metadata.setContentLength(contentBytes.length);

                
                PutObjectRequest putRequest = new PutObjectRequest(bucketName, key + fileName,
                        new ByteArrayInputStream(contentBytes), metadata).withCannedAcl(CannedAccessControlList.PublicRead);

                // Upload the file to S3
                s3Client.putObject(putRequest);

				String url = s3Client.getUrl(bucketName, key + fileName).toString();
				imageUrls.add(url);
            }
            entry = zipIn.getNextEntry();// after upload current file or current file is not a image, get the next entry
        }
        zipIn.closeEntry();// close the last entry
        zipIn.close();
    }

    // Method to check if a given file name has an image file extension
    private boolean isImageFile(String fileName) {
        String[] validExtensions = { "png", "gif", "jpg", "jpeg" };
        for (String ext : validExtensions) {
            if (fileName.endsWith("." + ext)) {
                return true;
            }
        }
        return false;
    }
}


package cn.kyrioscraft.web.ueditor.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dell on 2017/4/12.
 */
@Controller
public class UeditorUploadController {

    @Value("${ueditor.read-url}")
    private String baseVisitUrl;

    private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif))$)";
    private Pattern pattern;
    @ResponseBody
    @RequestMapping(value = "/ueditorUpload")
    public Map<String, Object> images(MultipartFile upfile, String upfolder) {
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            pattern = Pattern.compile(IMAGE_PATTERN);
            String originalFilename = upfile.getOriginalFilename();
            boolean isImg = isValidImageFile(originalFilename);
            String newFilenameBase = UUID.randomUUID().toString();
            String ext = FilenameUtils.getExtension(originalFilename).toLowerCase();
            if (isImg) {
                originalFilename = String.format("%s.%s", newFilenameBase, ext);
            }
            String fileName=originalFilename;
            if(upfolder!=null)
                fileName= upfolder.concat("/").concat(originalFilename);

//            OSSUtil.getInstance().uploadFile(fileName, upfile.getInputStream());
            String visitUrl = baseVisitUrl.concat("/").concat(fileName);
            params.put("state", "SUCCESS");
            params.put("url", visitUrl);
            params.put("size", upfile.getSize());
            params.put("original", originalFilename);
            params.put("type", upfile.getContentType());
        } catch (Exception e) {
            params.put("state", "ERROR");
        }
        return params;
    }

    private boolean isValidImageFile(final String image) {
        Matcher matcher = pattern.matcher(image);
        return matcher.matches();
    }
}

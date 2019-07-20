package uni.mirkoz.homebankingdemo.controller.ws.bytestream;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uni.mirkoz.homebankingdemo.model.banks.Image;
import uni.mirkoz.homebankingdemo.service.contract.ImageStorageService;

import java.io.FileNotFoundException;
import java.io.IOException;


@Controller
@ResponseBody
public class ImagesController {

    private ImageStorageService imageStorageService;

    public ImagesController(ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }

    @PostMapping(value = "/admin/bank/{id}/image")
    public Image uploadBankImage(@PathVariable Integer id, @RequestParam("file") MultipartFile file) throws IOException{

        return imageStorageService.storeBankImageFile(id, file);
    }

    @PostMapping(value = "/manager/bank-branch/{id}/image")
    public Image uploadBankBranchImage(@PathVariable Integer id, @RequestParam("file") MultipartFile file) throws IOException {

        return imageStorageService.storeBankBranchImageFile(id, file);
    }

    @GetMapping(value = "/image/{id}",
            produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    public ResponseEntity<Resource> downloadImage(@PathVariable Integer id) throws FileNotFoundException {
        return ResponseEntity.ok()
                .body(imageStorageService.loadImageAsResource(id));
    }
}

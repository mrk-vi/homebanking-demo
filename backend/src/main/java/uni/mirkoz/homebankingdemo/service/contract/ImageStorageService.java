package uni.mirkoz.homebankingdemo.service.contract;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uni.mirkoz.homebankingdemo.model.banks.Image;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@Transactional
public interface ImageStorageService {

    Image storeBankImageFile(Integer bankId, MultipartFile file) throws IOException;
    Image storeBankBranchImageFile(Integer bankBranchId, MultipartFile file) throws IOException;
    Resource loadImageAsResource(Integer imageId) throws FileNotFoundException;
}

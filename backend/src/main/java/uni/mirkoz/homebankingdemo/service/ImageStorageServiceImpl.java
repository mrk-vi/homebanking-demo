package uni.mirkoz.homebankingdemo.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uni.mirkoz.homebankingdemo.configuration.FileStorageProperties;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.banks.Image;
import uni.mirkoz.homebankingdemo.repository.banks.BankBranchRepository;
import uni.mirkoz.homebankingdemo.repository.banks.BankRepository;
import uni.mirkoz.homebankingdemo.repository.banks.ImageRepository;
import uni.mirkoz.homebankingdemo.service.contract.ImageStorageService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Component
public class ImageStorageServiceImpl implements ImageStorageService {

    private final Path fileStorageLocation;
    private BankRepository bankRepository;
    private BankBranchRepository bankBranchRepository;
    private ImageRepository imageRepository;

    public ImageStorageServiceImpl(FileStorageProperties fileStorageProperties, BankRepository bankRepository, BankBranchRepository bankBranchRepository, ImageRepository imageRepository) {
        this.bankRepository = bankRepository;
        this.bankBranchRepository = bankBranchRepository;
        this.imageRepository = imageRepository;

        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ioe) {
        }
    }

    @Override
    public Image storeBankImageFile(Integer bankId, MultipartFile file) throws IOException {
        Bank bank = bankRepository.getOne(bankId);
        String fileName = _storeToFileSystem(file);
        return imageRepository.save(Image.builder()
                .bank(bank)
                .fileName(fileName)
                .build());
    }

    @Override
    public Image storeBankBranchImageFile(Integer bankBranchId, MultipartFile file) throws IOException {
        BankBranch bankBranch = bankBranchRepository.getOne(bankBranchId);
        String fileName = _storeToFileSystem(file);
        return imageRepository.save(Image.builder()
                .bankBranch(bankBranch)
                .fileName(fileName)
                .build());
    }

    @Override
    public Resource loadImageAsResource(Integer imageId) throws FileNotFoundException {
        Image image = imageRepository.getOne(imageId);
        String fileName = image.getFileName();
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException();
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException();
        }
    }

    private String _storeToFileSystem(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        int count = 1;
        while (Files.exists(targetLocation)) {
            count++;
            String name = StringUtils.stripFilenameExtension(fileName);
            String ext = StringUtils.getFilenameExtension(fileName);
            fileName = name.concat(String.valueOf(count));
            if (ext != null){
                fileName = fileName.concat(".").concat(ext);
            }
            targetLocation = this.fileStorageLocation.resolve(fileName);
        }
        Files.copy(file.getInputStream(), targetLocation);
        return fileName;
    }
}

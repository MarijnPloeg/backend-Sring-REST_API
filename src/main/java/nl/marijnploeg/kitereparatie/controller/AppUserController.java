package nl.marijnploeg.kitereparatie.controller;

import javassist.NotFoundException;
import nl.marijnploeg.kitereparatie.exception.ApiExceptions.ApiRequestException;
import nl.marijnploeg.kitereparatie.helpers.FileUploadUtil;
import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.model.AppUser;
import nl.marijnploeg.kitereparatie.repository.AppUserRepository;
import nl.marijnploeg.kitereparatie.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserRepository appUserRepository;


    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<Object> GetAllUsers() {
        return ResponseEntity.ok().body(appUserService.getAppUsers());
    }


    @CrossOrigin
    @GetMapping("/{email}")
    public ResponseEntity<Object> getAppUserByEmail(@PathVariable String email)  {
        if (appUserRepository.findByEmail(email).isPresent()) {
        return ResponseEntity.ok().body(appUserService.getUserByEmail(email));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gebruiker met email " + email + " niet gevonden!");
        }
    }

    @CrossOrigin
    @PostMapping("/{appUserId}/address")
    public AppUser getAppUserAddress(@PathVariable Long appUserId,
                                    @Valid @RequestBody Address address) throws ApiRequestException {
        return appUserRepository.findById(appUserId)
                .map(appUser -> {
                    appUser.setAddress(address);
                    return appUserRepository.save(appUser);
                }).orElseThrow(() -> new ApiRequestException("User not found!"));
    }

    @CrossOrigin
    @PostMapping("/save")
    public RedirectView saveUser(AppUser appUser, @RequestParam("image")MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        appUser.setProfileImg(fileName);

        AppUser savedAppUser = appUserService.save(appUser);

        String uploadDir = "profile_img/" + savedAppUser.getAppUserId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/users", true);
    }
}

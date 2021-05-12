package nl.marijnploeg.kitereparatie.controller;

import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.model.UserRoles.AppUser;
import nl.marijnploeg.kitereparatie.security.rolesAndPermissions.AppUserRole;
import nl.marijnploeg.kitereparatie.service.AppUserService;
import nl.marijnploeg.kitereparatie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<Object> GetAllUsers() {
        return ResponseEntity.ok().body(appUserService.getAppUsers());
    }


    @CrossOrigin
    @GetMapping("/{email}")
    public ResponseEntity<Object> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(appUserService.getUserByEmail(email));
    }

    @CrossOrigin
    @PostMapping("/save")
    public RedirectView saveUser(AppUser appUser, @RequestParam("image")MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        appUser.setProfileImg(fileName);

        AppUser savedAppUser = appUserService.save(appUser);

        String uploadDir = "profile_img/" + savedAppUser.getAppUserId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    }
}

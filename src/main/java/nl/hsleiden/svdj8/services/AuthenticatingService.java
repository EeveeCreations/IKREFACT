package nl.hsleiden.svdj8.services;

import nl.hsleiden.svdj8.models.tables.Admin;
import nl.hsleiden.svdj8.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatingService {

    private final DecryptService decryptService;
    private final AdminRepository adminRepository;

    @Autowired
    public AuthenticatingService(
            DecryptService decryptService,
            AdminRepository adminRepository) {
        this.decryptService = decryptService;
        this.adminRepository = adminRepository;
    }

    public boolean isAdmin(String token) {
        Admin admin = this.decryptService.decrypt(token);
        return this.adminRepository.existsById(admin.getAdminID());
    }


}

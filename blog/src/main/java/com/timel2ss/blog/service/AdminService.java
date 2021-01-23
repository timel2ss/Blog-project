package com.timel2ss.blog.service;

import com.timel2ss.blog.domain.Admin;
import com.timel2ss.blog.dto.AdminDto;
import com.timel2ss.blog.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    @Transactional
    public long create(AdminDto.Create create) {
        Admin admin = Admin.createMember(create.getName(), create.getPassword());
        adminRepository.save(admin);
        return admin.getId();
    }

    public AdminDto.Response getInfo(long id) {
        Admin admin = adminRepository.findOne(id);
        return new AdminDto.Response(admin.getId(), admin.getName());
    }
}

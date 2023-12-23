package com.vishnuparasu.EnforcementDirectorate.service.impl;

import com.vishnuparasu.EnforcementDirectorate.entity.EdAdminEntity;
import com.vishnuparasu.EnforcementDirectorate.entity.EdRolesEntity;
import com.vishnuparasu.EnforcementDirectorate.entity.EdUserCredentials;
import com.vishnuparasu.EnforcementDirectorate.repository.EdAdminRepo;
import com.vishnuparasu.EnforcementDirectorate.repository.EdRolesRepo;
import com.vishnuparasu.EnforcementDirectorate.repository.EdUserCredentialRepo;
import com.vishnuparasu.EnforcementDirectorate.service.EdAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EdAdminServiceImpl implements EdAdminService {

    @Autowired
    private EdAdminRepo edAdminRepo;

    @Autowired
    private EdUserCredentialRepo edUserCredentialRepo;

    @Autowired
    private EdRolesRepo edRolesRepo;

    @Override
    public EdAdminEntity getAdmin(String edaid) {
        Optional<EdAdminEntity> oneAdmin = edAdminRepo.findByEduid(edaid);
        return oneAdmin.get();
    }

    @Override
    public List<EdAdminEntity> getAllAdmin() {
        List<EdAdminEntity> listOfAllAdmin = new ArrayList<>();
        edAdminRepo.findAll().forEach(listOfAllAdmin::add);
        return listOfAllAdmin;
    }

    @Transactional
    @Override
    public String removeAdmin(String edaid) {
        edAdminRepo.deleteByEduid(edaid);
        edUserCredentialRepo.deleteAdminByEduid(edaid);
        return edaid;
    }

    @Override
    public EdAdminEntity modifyAdmin(EdAdminEntity edAdminEntity, String edaid) {
        Optional<EdAdminEntity> adminEntity = edAdminRepo.findByEduid(edaid);
        if (adminEntity.isPresent()) {
            EdAdminEntity edAdminEntity1 = adminEntity.get();
            edAdminEntity1.setDob(edAdminEntity.getDob());
            edAdminEntity1.setName(edAdminEntity.getName());
            edAdminEntity1.setGmail(edAdminEntity.getGmail());
            edAdminEntity1.setEdUserCredentials(edAdminEntity.getEdUserCredentials());
            return edAdminRepo.save(edAdminEntity1);
        }
        return null;
    }

    @Override
    public EdAdminEntity createAdmin(EdAdminEntity edAdminEntity) {
        return edAdminRepo.save(edAdminEntity);
    }

    @Override
    public EdUserCredentials getUserCredential(String userName) {
        Optional<EdUserCredentials> oneUserCredential = edUserCredentialRepo.findById(userName);
        return oneUserCredential.get();
    }

    @Override
    public List<EdUserCredentials> getAllUserCredentials() {
        List<EdUserCredentials> listOfAllUserCredentials = new ArrayList<>();
        edUserCredentialRepo.findAll().forEach(listOfAllUserCredentials::add);
        return listOfAllUserCredentials;
    }

    @Override
    public EdUserCredentials modifyUserCredentials(EdUserCredentials edUserCredentials, String userid) {
        Optional<EdUserCredentials> userCredentials = edUserCredentialRepo.findById(userid);
        if (userCredentials.isPresent()) {
            EdUserCredentials edUserCredentials1 = userCredentials.get();
            edUserCredentials1.setPassword(edUserCredentials.getPassword());
            edUserCredentials1.setEdRolesModels(edUserCredentials.getEdRolesModels());
            return edUserCredentialRepo.save(edUserCredentials1);
        }
        return null;
    }

    @Override
    public EdRolesEntity getRole(String roleName) {
        Optional<EdRolesEntity> oneRole = edRolesRepo.findById(roleName);
        return oneRole.get();
    }

    @Override
    public List<EdRolesEntity> getAllRoles() {
        List<EdRolesEntity> listOfRoll = new ArrayList<>();
        edRolesRepo.findAll().forEach(listOfRoll::add);
        return listOfRoll;
    }

    @Override
    public String removeRole(String roleName) {
        edRolesRepo.deleteById(roleName);
        return roleName;
    }

    @Override
    public EdRolesEntity createRole(EdRolesEntity edRolesEntity) {
        return edRolesRepo.save(edRolesEntity);
    }

    @Override
    public EdRolesEntity modifyRole(String roleName, EdRolesEntity edRolesModel) {
        Optional<EdRolesEntity> rolesEntity = edRolesRepo.findById(roleName);
        if (rolesEntity.isPresent()) {
            EdRolesEntity modifyRole = rolesEntity.get();
            modifyRole.setRole(edRolesModel.getRole());
            modifyRole.setRoleDesc(edRolesModel.getRoleDesc());
            return edRolesRepo.save(modifyRole);
        }
        return null;
    }
}
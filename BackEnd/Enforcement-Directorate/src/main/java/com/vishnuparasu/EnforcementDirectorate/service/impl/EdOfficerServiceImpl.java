package com.vishnuparasu.EnforcementDirectorate.service.impl;

import com.vishnuparasu.EnforcementDirectorate.entity.EdOfficerEntity;
import com.vishnuparasu.EnforcementDirectorate.entity.EdUserCredentials;
import com.vishnuparasu.EnforcementDirectorate.repository.EdOfficerRepo;
import com.vishnuparasu.EnforcementDirectorate.repository.EdUserCredentialRepo;
import com.vishnuparasu.EnforcementDirectorate.service.EdOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EdOfficerServiceImpl implements EdOfficerService {

    @Autowired
    EdOfficerRepo edOfficerRepo;

    @Autowired
    EdUserCredentialRepo edUserCredentialRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public EdOfficerEntity getOfficer(String edoid) {
        Optional<EdOfficerEntity> oneOfficer = edOfficerRepo.findOfficerByEdoid(edoid);
        return oneOfficer.get();
    }

    @Override
    public List<EdOfficerEntity> getAllOfficers() {
        List<EdOfficerEntity> listOfAllOfficers = new ArrayList<>();
        edOfficerRepo.findAll().forEach(listOfAllOfficers::add);
        return listOfAllOfficers;
    }

    @Override
    public EdOfficerEntity modifyOfficer(EdOfficerEntity edOfficerEntity, String edoid) {
        Optional<EdOfficerEntity> modifyOfficer = edOfficerRepo.findOfficerByEdoid(edoid);
        if (modifyOfficer.isPresent()) {
            EdOfficerEntity officerEntity = modifyOfficer.get();
            officerEntity.setAddress(edOfficerEntity.getAddress());
            officerEntity.setCommunity(edOfficerEntity.getCommunity());
            officerEntity.setDob(edOfficerEntity.getDob());
            officerEntity.setName(edOfficerEntity.getName());
            officerEntity.setGender(edOfficerEntity.getGender());
            officerEntity.setFname(edOfficerEntity.getFname());
            officerEntity.setAdharNumber(edOfficerEntity.getAdharNumber());
            officerEntity.setJob(edOfficerEntity.getJob());
            officerEntity.setJobPosition(officerEntity.getJobPosition());
            officerEntity.setSalary(officerEntity.getSalary());
            officerEntity.setReligion(edOfficerEntity.getReligion());
            officerEntity.setPho(officerEntity.getPho());
            officerEntity.setQualification(officerEntity.getQualification());
            officerEntity.setJsd(officerEntity.getJsd());
            officerEntity.setGmail(officerEntity.getGmail());
            return edOfficerRepo.save(officerEntity);
        }
        return null;
    }

    @Transactional
    @Override
    public String deleteOfficer(String edoid) {
        edOfficerRepo.deleteOfficerByEdoid(edoid);
        edUserCredentialRepo.deleteOfficerByEduid(edoid);
        return edoid;
    }

    @Override
    public EdOfficerEntity createOfficer(EdOfficerEntity edOfficerEntity) {
        EdUserCredentials userCredentials =  edOfficerEntity.getEdUserCredentials().iterator().next();
        userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        return edOfficerRepo.save(edOfficerEntity);
    }

    @Override
    public long getNoRow() {
        return edOfficerRepo.count();
    }


}

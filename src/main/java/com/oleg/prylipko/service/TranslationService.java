package com.oleg.prylipko.service;

import com.oleg.prylipko.repository.*;
import com.oleg.prylipko.repository.repositoryhelper.RepositoryHelper;
import org.bitbucket.brunneng.ot.ObjectTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private RepositoryHelper repositoryHelper;

    private ObjectTranslator objectTranslator;

}
package com.jsp.onetooneMapping1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorProfileService {

    @Autowired
    private static AuthorProfileRepository authorProfileRepository;

    public AuthorProfile saveAuthorProfile(AuthorProfile authorProfile) {
        return authorProfileRepository.save(authorProfile);
    }

    public AuthorProfile getAuthorProfileById(Long id) {
        return authorProfileRepository.findById(id).orElse(null);
    }

    public void deleteAuthorProfile(Long id) {
        authorProfileRepository.deleteById(id);
    }

    public static Page<AuthorProfile> getAllAuthorProfiles(Pageable pageable) {
        return authorProfileRepository.findAll(pageable);
    }

}

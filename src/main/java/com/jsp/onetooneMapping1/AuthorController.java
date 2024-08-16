package com.jsp.onetooneMapping1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorProfileService authorProfileService;
    
    
    @GetMapping("/")
    public String page() {
        return "Welcome";
    }
    @GetMapping
    public ResponseEntity<Page<Author>> getAllAuthors(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String sortDir) {
        Pageable pageable = PageRequest.of(page, size, sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        Page<Author> authors = authorService.getAllAuthors(pageable);
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author savedAuthor = authorService.saveAuthor(author);
        return ResponseEntity.ok(savedAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        author.setName(authorDetails.getName());
        if (author.getProfile() != null && authorDetails.getProfile() != null) {
            author.getProfile().setBiography(authorDetails.getProfile().getBiography());
            author.getProfile().setWebsite(authorDetails.getProfile().getWebsite());
        } else if (authorDetails.getProfile() != null) {
            author.setProfile(authorDetails.getProfile());
        }
        Author updatedAuthor = authorService.saveAuthor(author);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<AuthorProfile> getAuthorProfile(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        if (author == null || author.getProfile() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author.getProfile());
    }
    @PostMapping("/{id}/profile")
    public ResponseEntity<AuthorProfile> createAuthorProfile(@PathVariable Long id, @RequestBody AuthorProfile profileDetails) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }

        if (author.getProfile() != null) {
            return ResponseEntity.badRequest().body(null); // or you can return a custom error message
        }

        AuthorProfile savedProfile = authorProfileService.saveAuthorProfile(profileDetails);
        author.setProfile(savedProfile);
        authorService.saveAuthor(author);

        return ResponseEntity.ok(savedProfile);
    }


    @PutMapping("/{id}/profile")
    public ResponseEntity<AuthorProfile> updateAuthorProfile(@PathVariable Long id, @RequestBody AuthorProfile profileDetails) {
        Author author = authorService.getAuthorById(id);
        if (author == null || author.getProfile() == null) {
            return ResponseEntity.notFound().build();
        }
        AuthorProfile profile = author.getProfile();
        profile.setBiography(profileDetails.getBiography());
        profile.setWebsite(profileDetails.getWebsite());
        AuthorProfile updatedProfile = authorProfileService.saveAuthorProfile(profile);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/{id}/profile")
    public ResponseEntity<Void> deleteAuthorProfile(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        if (author == null || author.getProfile() == null) {
            return ResponseEntity.notFound().build();
        }
        authorProfileService.deleteAuthorProfile(author.getProfile().getId());
        author.setProfile(null);
        authorService.saveAuthor(author);
        return ResponseEntity.noContent().build();
    }

}

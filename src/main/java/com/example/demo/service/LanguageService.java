package com.example.demo.service;

import com.example.demo.entity.Language;
import com.example.demo.repository.LanguageRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    // ✅ Get languages with pagination & sorting
    public List<Language> getAllLanguages(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Language> languagePage = languageRepository.findAll(pageable);
        return languagePage.getContent();
    }

    public Optional<Language> getLanguageById(Long id) {
        return languageRepository.findById(id);
    }

    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }

    public Language updateLanguage(Long id, Language updatedLanguage) {
        return languageRepository.findById(id).map(language -> {
            language.setName(updatedLanguage.getName());
            language.setCode(updatedLanguage.getCode());
            language.setDescription(updatedLanguage.getDescription());
            return languageRepository.save(language);
        }).orElseThrow(() -> new RuntimeException("Language not found with id: " + id));
    }

    public void deleteLanguage(Long id) {
        languageRepository.deleteById(id);
    }
}

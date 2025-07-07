package com.sandyflat.Url_Shortner.repository;

import com.sandyflat.Url_Shortner.model.UrlMapping;
import com.sandyflat.Url_Shortner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    Optional<UrlMapping> findByShortUrl(String shortUrl);
    List<UrlMapping> findByUser(User user);
}

package com.sandyflat.Url_Shortner.repository;

import com.sandyflat.Url_Shortner.model.UrlMapping;
import com.sandyflat.Url_Shortner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    UrlMapping findByShortUrl(String shortUrl);
    List<UrlMapping> findByUser(User user);
}

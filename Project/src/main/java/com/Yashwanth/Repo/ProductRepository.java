package com.Yashwanth.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Yashwanth.Entity.Productentity;

@Repository
public interface ProductRepository extends JpaRepository<Productentity, Long> {
}

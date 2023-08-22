package com.company.gamestore.repository;

import com.company.gamestore.model.TShirt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TShirtRepository  extends JpaRepository<TShirt, Integer> {
}

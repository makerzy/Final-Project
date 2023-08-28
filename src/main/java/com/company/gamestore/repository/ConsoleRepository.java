package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.TShirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {

    List<Console> findByManufacturer(String manufacturer);


<<<<<<< HEAD
}
=======
}
>>>>>>> 0f87fa88037903488f8864fac99764d566d5d4b6

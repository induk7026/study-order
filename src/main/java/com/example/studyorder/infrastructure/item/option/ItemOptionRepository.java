package com.example.studyorder.infrastructure.item.option;

import com.example.studyorder.domain.item.option.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long> {
}
